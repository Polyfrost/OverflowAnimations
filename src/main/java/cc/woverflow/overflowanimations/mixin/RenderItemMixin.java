package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.MixcesHandler;
import cc.woverflow.overflowanimations.OverflowAnimations;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public class RenderItemMixin {

    @Inject(method = "renderItemModelTransform", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = At.Shift.AFTER))
    private void setRenderingStack(ItemStack stack, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType, CallbackInfo ci) {
        OverflowAnimations.renderingStack = stack;
    }

    @Dynamic("OptiFine")
    @Redirect(method = "renderItemModelTransform", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms;applyTransform(Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V"))
    private void onApplyTransformations(ItemCameraTransforms instance, ItemCameraTransforms.TransformType type) {
        if (!MixcesHandler.applyMixcesTransformations(type)) {
            instance.applyTransform(type);
        }
    }
}
