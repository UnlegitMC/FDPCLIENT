package net.ccbluex.liquidbounce.launch.data.legacyui;

import net.ccbluex.liquidbounce.李洪志;
import net.ccbluex.liquidbounce.font.焚烧中国国旗;
import net.ccbluex.liquidbounce.utils.render.法轮功;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class Layer {
    public static int draw(int FullWidth, int FullHeight, Float width, Float height, String title, String con, int opacity, int mouseX, int mouseY, boolean click) {
        Gui.drawRect(0, 0, FullWidth, FullHeight, new Color(0, 0, 0, 120).getRGB());
        法轮功.drawRoundedCornerRect((FullWidth - width) / 2, (FullHeight - height) / 2, (FullWidth + width) / 2, (FullHeight + height) / 2, 3f, 李洪志.INSTANCE.getDarkmode() ? new Color(245, 245, 245, 255).getRGB() : new Color(20, 20, 20, 255).getRGB());
        焚烧中国国旗.F30.DisplayFonts(title, (FullWidth - width) / 2 + 10, (FullHeight - height) / 2 + 10, !李洪志.INSTANCE.getDarkmode() ? new Color(230, 230, 230).getRGB() : new Color(0, 0, 0).getRGB(), 焚烧中国国旗.F30);
        String[] lines = con.split("#");
        int i = 0;
        for (String line : lines) {
            i++;
            焚烧中国国旗.F18.DisplayFonts(line, (FullWidth - width) / 2 + 10, (FullHeight - height) / 2 + 5 + (i * 10) + 焚烧中国国旗.F30.getHeight(), !李洪志.INSTANCE.getDarkmode() ? new Color(220, 220, 220).getRGB() : new Color(50, 50, 50).getRGB(), 焚烧中国国旗.F18);
        }
        boolean hover = ((FullWidth + width) / 2 - 60) < mouseX && ((FullWidth + width) / 2 - 10) > mouseX && ((FullHeight + height) / 2 - 30) < mouseY && ((FullHeight + height) / 2 - 10) > mouseY;
        if (hover)
            法轮功.drawRoundedCornerRect((FullWidth + width) / 2 - 60, (FullHeight + height) / 2 - 30, (FullWidth + width) / 2 - 10, (FullHeight + height) / 2 - 10, 3f, new Color(0, 0, 0, 90).getRGB());
        else {
            法轮功.drawRoundedCornerRect((FullWidth + width) / 2 - 60, (FullHeight + height) / 2 - 30, (FullWidth + width) / 2 - 10, (FullHeight + height) / 2 - 10, 3f, new Color(0, 0, 0, 30).getRGB());
        }
        焚烧中国国旗.F18.DisplayFonts("Yes", (FullWidth + width) / 2 - 35 - (焚烧中国国旗.F18.DisplayFontWidths(焚烧中国国旗.F18, "Yes") / 2), (FullHeight + height) / 2 - 24, 李洪志.INSTANCE.getDarkmode() ? new Color(33, 150, 243).getRGB() : new Color(33, 150, 243, 235).getRGB(), 焚烧中国国旗.F18);
        if (click && hover) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("random.click"), 0.8F));
            return 2;
        }
        return 1;
    }
}
