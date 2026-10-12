package org.tastytrash.spatialGUI.mixin.render;

import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.tastytrash.spatialGUI.client.SpatialGUIClient;

@Mixin(PlayerModel.class)
public class PlayerModelMixin {
    @Unique private static final double HIDE_DISTANCE_SQ = 0.6 * 0.6;
    @Unique private boolean spatialGUI$headHidden = false;

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)V", at = @At("TAIL"))
    private void spatialGUI$hideHeadWhenCameraIsClose(AvatarRenderState state, CallbackInfo ci) {
        PlayerModel model = (PlayerModel) (Object) this;

        if (spatialGUI$headHidden) {
            model.head.visible = true;
            spatialGUI$headHidden = false;
        }

        var renderer = SpatialGUIClient.renderer();
        Vec3 cameraPos = SpatialGUIClient.getCameraPosition();
        if (renderer == null || cameraPos == null || !SpatialGUIClient.isEnabled()) {
            return;
        }
        if (!renderer.shouldCapture() && !renderer.isClosing() && !SpatialGUIClient.getWasClosing()) {
            return;
        }

        double dx = cameraPos.x - state.x;
        double dy = cameraPos.y - (state.y + state.eyeHeight);
        double dz = cameraPos.z - state.z;
        if (dx * dx + dy * dy + dz * dz < HIDE_DISTANCE_SQ) {
            model.head.visible = false;
            model.hat.visible = false;
            spatialGUI$headHidden = true;
        }
    }
}