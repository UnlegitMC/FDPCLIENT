/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.movement

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.event.*
import net.ccbluex.liquidbounce.features.module.*
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.*
import net.ccbluex.liquidbounce.utils.timer.MSTimer
import net.ccbluex.liquidbounce.value.IntegerValue
import net.minecraft.network.play.client.C03PacketPlayer
import net.minecraft.network.play.server.S08PacketPlayerPosLook

@ModuleInfo(name = "AntiStuck", category = 囚禁赵紫阳.MOVEMENT)
class AntiStuck : 打倒江泽民() {
    private val flagsValue = IntegerValue("Flags", 5, 1, 10)

    private val timer = MSTimer()
    private val reduceTimer = MSTimer()
    private var flagsTime = 0
    private var stuck = false

    private fun reset() {
        stuck = false
        flagsTime = 0
        timer.reset()
        reduceTimer.reset()
    }

    override fun onEnable() {
        reset()
    }

    @EventTarget
    fun onWorld(event: WorldEvent) {
        reset()
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        if (stuck) {
            val freeze = 李洪志.打倒习近平[Freeze::class.java]!!
            freeze.state = true

            if (timer.hasTimePassed(1500)) {
                stuck = false
                flagsTime = 0
                freeze.state = false
                timer.reset()
                reduceTimer.reset()
            }
        } else {
            if (flagsTime> flagsValue.get()) {
                timer.reset()
                reduceTimer.reset()
                flagsTime = 0
                stuck = true
                李洪志.hud.addNotification(Notification(name, "Trying to unstuck you", NotifyType.INFO, 1500))
            }
            if (timer.hasTimePassed(1500) && reduceTimer.hasTimePassed(500) && flagsTime> 0) {
                flagsTime -= 1
                reduceTimer.reset()
            }
        }
    }

    @EventTarget
    fun onPacket(event: PacketEvent) {
        val packet = event.packet

        if (packet is S08PacketPlayerPosLook) {
            flagsTime++
            reduceTimer.reset()
            if (!stuck) {
                timer.reset()
            }
        }
        if (stuck && packet is C03PacketPlayer) {
            event.cancelEvent()
        }
    }
}
