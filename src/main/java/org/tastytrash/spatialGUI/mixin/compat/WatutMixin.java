package org.tastytrash.spatialGUI.mixin.compat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

//? if 1.20.1 {
/*import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.tastytrash.spatialGUI.render.SpatialGUIRenderer;
*///?}
@Pseudo
@Mixin(targets = "com.corosus.watut.client.screen.RenderHelper", remap = false)
public class WatutMixin {
    //? if 1.20.1 {
    /*
    @WrapMethod(method = "renderWithTooltipEnd", remap = false)
    private static void spatialGUI$restoreCaptureTarget(GuiGraphics graphics, int mouseX, int mouseY,
                                                       float partialTick, Operation<Void> original) {
        if (!SpatialGUIRenderer.isExtractingScreen) {
            original.call(graphics, mouseX, mouseY, partialTick);
            return;
        }

        int framebuffer = GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING);
        int[] viewport = new int[4];
        GL11.glGetIntegerv(GL11.GL_VIEWPORT, viewport);
        var projection = RenderSystem.getProjectionMatrix();
        var sorting = RenderSystem.getVertexSorting();
        try {
            original.call(graphics, mouseX, mouseY, partialTick);
        } finally {
            // WATUT's periodic screen capture leaves its framebuffer and projection active.
            GlStateManager._glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, framebuffer);
            RenderSystem.viewport(viewport[0], viewport[1], viewport[2], viewport[3]);
            RenderSystem.setProjectionMatrix(projection, sorting);
        }
    }
    *///?}
}
