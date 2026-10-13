package org.tastytrash.spatialGUI.mixin.compat;

import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase", remap = false)
public abstract class SophisticatedCoreMixin {
    @Shadow
    abstract void updateDimensionsAndSlotPositions(int height);

    //? if 1.20.1 && forge {
    /*@Inject(method = {"init", "m_7856_"}, at = @At("HEAD"), remap = false)
    *///?} else {
    @Inject(method = "init", at = @At("HEAD"), remap = false, require = 0)
    //?}
    private void spatialGUI$relayoutForScreenHeight(CallbackInfo ci) {
        updateDimensionsAndSlotPositions(((Screen) (Object) this).height);
    }
}
