package org.tastytrash.spatialGUI.util;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.util.Mth;
import org.tastytrash.spatialGUI.SpatialGUI;
//? if 1.20.1 && forge {
/*import net.minecraft.client.Minecraft;
import org.tastytrash.spatialGUI.compat.SophisticatedCoreCompat;
*///?}

public final class GuiScaleUtil {
    public static int effectiveScale(Window w) {
        int scale = SpatialGUI.config.getEffectiveGuiScale(w.getWidth(), w.getHeight());
        //? if 1.20.1 && forge {
        /*Minecraft mc = Minecraft.getInstance();
        if (mc.screen instanceof SophisticatedCoreCompat) {
            // Do not hide rows that fit in the ordinary storage screen.
            scale = Math.min(scale, w.calculateScale(mc.options.guiScale().get(), mc.isEnforceUnicode()));
        }
        *///?}
        return scale;
    }
    public static int scaledWidth(Window w)  {
        return Mth.ceil(w.getWidth()  / (double) effectiveScale(w));
    }
    public static int scaledHeight(Window w) {
        return Mth.ceil(w.getHeight() / (double) effectiveScale(w));
    }
}
