package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.config.AnimationsConfig;
import club.sk1er.oldanimations.config.OldAnimationsSettings;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public class PlayerControllerMPMixin {
    @Inject(method = "getIsHittingBlock", at = @At("HEAD"), cancellable = true)
    private void cancelHit(CallbackInfoReturnable<Boolean> cir) {
        if (OldAnimationsSettings.punching && AnimationsConfig.spiderfrogPunching) {
            cir.setReturnValue(false);
        }
    }
}
