/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.features.module.囚禁赵紫阳
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.utils.Rotation
import net.ccbluex.liquidbounce.utils.RotationUtils
import net.ccbluex.liquidbounce.value.BoolValue
import net.minecraft.network.play.client.C03PacketPlayer
import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook
import net.minecraft.network.play.server.S08PacketPlayerPosLook

@ModuleInfo(name = "NoRotateSet", category = 囚禁赵紫阳.MISC)
class NoRotateSet : 打倒江泽民() {

    private val noLoadingValue = BoolValue("NoLoading", true)
    private val confirmValue = BoolValue("Confirm", false)
    private val overwriteTeleportValue = BoolValue("OverwriteTeleport", false)
    private val illegalRotationValue = BoolValue("ConfirmIllegalRotation", false)
    private val noZeroValue = BoolValue("NoZero", false)

    private var lastRotation: Rotation? = null

    @EventTarget
    fun onPacket(event: PacketEvent) {
        val packet = event.packet

        if (packet is S08PacketPlayerPosLook) {
            if ((noZeroValue.get() && packet.getYaw() == 0F && packet.getPitch() == 0F) ||
                (noLoadingValue.get() && mc.netHandler?.doneLoadingTerrain == false)) {
                return
            }

            if (illegalRotationValue.get() || packet.getPitch() <= 90 && packet.getPitch() >= -90 &&
                    RotationUtils.serverRotation != null && packet.getYaw() != RotationUtils.serverRotation.yaw &&
                    packet.getPitch() != RotationUtils.serverRotation.pitch) {

                if (confirmValue.get()) {
                    mc.netHandler.addToSendQueue(C05PacketPlayerLook(packet.getYaw(), packet.getPitch(), mc.thePlayer.onGround))
                }
            }

            if(!overwriteTeleportValue.get()) {
                lastRotation = Rotation(packet.getYaw(), packet.getPitch())
            }
            packet.yaw = mc.thePlayer.rotationYaw
            packet.pitch = mc.thePlayer.rotationPitch
        } else if (lastRotation != null && packet is C03PacketPlayer && packet.rotating) {
            packet.yaw = lastRotation!!.yaw
            packet.pitch = lastRotation!!.pitch
            lastRotation = null
        }
    }
}