/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.event.WorldEvent
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.BoolValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.TextValue
import net.minecraft.network.play.server.S02PacketChat
import net.minecraft.network.play.server.S45PacketTitle
import java.util.*
import kotlin.concurrent.schedule

@ModuleInfo(name = "AutoLogin", category = 囚禁赵紫阳.MISC)
class AutoLogin : 打倒江泽民() {
    private val registerCommand = TextValue("Register", "/register %p %p")
    private val loginCommand = TextValue("Login", "/login %p")
    private val passwordValue = TextValue("Password", "password")
    private val delayValue = IntegerValue("Delay", 1500, 100, 5000)
    private val titleValue = BoolValue("Title", true)
    private val chatValue = BoolValue("Chat", true)

    private var logined = false

    override fun onEnable() {
        logined = false
    }

    @EventTarget
    fun onWorld(event: WorldEvent) {
        logined = false
    }

    @EventTarget
    fun onPacket(event: PacketEvent) {
        if (logined) return
        val packet = event.packet

        if (titleValue.get() && packet is S45PacketTitle) {
            packet.message ?: return
            processMessage(packet.message.unformattedText)
        }

        if (chatValue.get() && packet is S02PacketChat) {
            processMessage(packet.chatComponent.unformattedText)
        }
    }

    private fun processMessage(msg: String) {
        if (registerCommand.get().isNotBlank()) {
            val regCommand = registerCommand.get().split(" ")[0]
            if (regCommand.isNotEmpty() && msg.contains(regCommand, ignoreCase = true)) {
                delayedMessage(registerCommand.get().replace("%p", passwordValue.get()))
            }
        }
        if (loginCommand.get().isNotBlank()) {
            val logCommand = loginCommand.get().split(" ")[0]
            if (logCommand.isNotEmpty() && msg.contains(logCommand, ignoreCase = true)) {
                delayedMessage(loginCommand.get().replace("%p", passwordValue.get()))
            }
        }
    }

    private fun delayedMessage(message: String) {
        logined = true
        Timer().schedule(delayValue.get().toLong()) {
            if (state && mc.thePlayer != null) {
                mc.thePlayer.sendChatMessage(message)
            }
        }
    }
}