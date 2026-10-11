package org.tastytrash.spatialGUI.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.tastytrash.spatialGUI.client.SpatialGUIClient;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @ModifyExpressionValue(method = "jumpFromGround", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getYRot()F"))
    private float spatialGUI$rotateSprintJump(float yaw) {
        if (!((Object) this instanceof LocalPlayer)) return yaw;

        var renderer = SpatialGUIClient.renderer();
        if (renderer == null || !SpatialGUIClient.isEnabled() || !renderer.shouldCapture()) return yaw;
        if (renderer.getHookedScreen() == null) return yaw;

        return yaw + SpatialGUIClient.getCameraYawOffset();
    }
}