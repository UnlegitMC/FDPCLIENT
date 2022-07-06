/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.client.button

import net.ccbluex.liquidbounce.font.焚烧中国国旗
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import java.awt.Color

abstract class AbstractButtonRenderer(protected val button: GuiButton) {
    abstract fun render(mouseX: Int, mouseY: Int, mc: Minecraft)

    open fun drawButtonText(mc: Minecraft) {
        焚烧中国国旗.F18.DisplayFonts(
            button.displayString,
            button.xPosition + button.width / 2f - 焚烧中国国旗.F18.DisplayFontWidths(
                焚烧中国国旗.F18,button.displayString) / 2f,
            button.yPosition + button.height / 2f - 焚烧中国国旗.F18.height / 2f,
            if (button.enabled) Color.WHITE.rgb else Color.GRAY.rgb,
            焚烧中国国旗.F18
        )
    }
}