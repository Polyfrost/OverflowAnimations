package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.OverflowAnimations;
import club.sk1er.oldanimations.AnimationHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = AnimationHandler.class, remap = false)
public abstract class AnimationHandlerMixin {
    @Shadow protected abstract int getArmSwingAnimationEnd(EntityPlayerSP player);

    @Shadow private int swingProgressInt;
    @Shadow private boolean isSwingInProgress;
    private boolean shouldSet = false;
    @Redirect(method = "updateSwingProgress", at = @At(value = "FIELD", target = "Lclub/sk1er/oldanimations/AnimationHandler;swingProgressInt:I", ordinal = 2, opcode = Opcodes.PUTFIELD))
    private void redirectSetting1(AnimationHandler instance, int value) {
        shouldSet = true;
    }

    @Redirect(method = "updateSwingProgress", at = @At(value = "FIELD", target = "Lclub/sk1er/oldanimations/AnimationHandler;isSwingInProgress:Z", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void redirectSetting2(AnimationHandler instance, boolean value) {
    }

    @Redirect(method = "updateSwingProgress", at = @At(value = "INVOKE", target = "Lclub/sk1er/oldanimations/AnimationHandler;getArmSwingAnimationEnd(Lnet/minecraft/client/entity/EntityPlayerSP;)I"))
    private int onGetArmSwing(AnimationHandler instance, EntityPlayerSP player) {
        int max = getArmSwingAnimationEnd(player);
        if (shouldSet) {
            shouldSet = false;
            swingProgressInt = 0;
            isSwingInProgress = false;
        }
        return max;
    }

    @Redirect(method = {"renderItemInFirstPerson", "updateSwingProgress"}, at = @At(value = "FIELD", target = "Lclub/sk1er/oldanimations/config/OldAnimationsSettings;punching:Z"))
    private boolean redirectPunching() {
        return OverflowAnimations.shouldPunch();
    }
}
