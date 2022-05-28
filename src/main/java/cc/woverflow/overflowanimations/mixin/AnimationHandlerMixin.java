package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.OverflowAnimations;
import cc.woverflow.overflowanimations.config.AnimationsConfig;
import club.sk1er.oldanimations.AnimationHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Inject(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lclub/sk1er/oldanimations/AnimationHandler;doFirstPersonTransform(Lnet/minecraft/item/ItemStack;)Z"))
    private void setRenderingStack(ItemRenderer renderer, ItemStack stack, float equipProgress, float partialTicks, CallbackInfoReturnable<Boolean> cir) {
        OverflowAnimations.renderingStack = stack;
    }

    @Inject(method = { "doSwordBlock3rdPersonTransform" }, at = { @At("RETURN") })
    private void cancel(final CallbackInfo ci) {
        if (AnimationsConfig.mixcesAnimations) {
            GlStateManager.rotate(20, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(10, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate(0, 0.0f, 0.0f, 1.0f);
            GlStateManager.translate(0.067, 0.035, 0.0);
        }
    }
}
