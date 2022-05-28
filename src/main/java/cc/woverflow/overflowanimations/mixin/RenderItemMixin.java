package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.MixcesHandler;
import cc.woverflow.overflowanimations.OverflowAnimations;
import cc.woverflow.overflowanimations.config.AnimationsConfig;
import net.minecraft.block.*;
import net.minecraft.client.renderer.GlStateManager;
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
        if (cameraTransformType == ItemCameraTransforms.TransformType.THIRD_PERSON) {
            OverflowAnimations.renderingStack = stack;
        } else if (AnimationsConfig.mixcesAnimations && stack.getItem() != null) {
            GlStateManager.translate(-0.01, 0.002, 0.0005);
            GlStateManager.rotate(0, 0, 1, 0);
            GlStateManager.rotate(0, 1, 0, 0);
            GlStateManager.rotate(0, 0, 0, 1);
            GlStateManager.scale(1, 1, 1);
            if (stack.getItem() instanceof ItemBlock) {
                Block block = ((ItemBlock) stack.getItem()).getBlock();
                if (AnimationsConfig.firstPersonCarpetPosition && (block instanceof BlockCarpet || block instanceof BlockSnow)) {
                    GlStateManager.translate(0.015, -0.331, -0.0005);
                    GlStateManager.rotate(0, 0, 1, 0);
                    GlStateManager.rotate(0, 1, 0, 0);
                    GlStateManager.rotate(0, 0, 0, 1);
                    GlStateManager.scale(1, 1, 1);
                } else if (block instanceof BlockLadder || block instanceof BlockPane || block instanceof BlockRail || block instanceof BlockRailPowered || block instanceof BlockRailDetector || block instanceof BlockVine || block instanceof BlockWeb || block instanceof BlockLever || block instanceof BlockBush || block instanceof BlockTorch || block instanceof BlockTripWireHook) {
                    GlStateManager.translate(0, 0, 0.0005);
                    GlStateManager.rotate(0, 0, 1, 0);
                    GlStateManager.rotate(0, 1, 0, 0);
                    GlStateManager.rotate(0, 0, 0, 1);
                    GlStateManager.scale(1, 1, 1);
                } else if (block instanceof BlockFurnace || block instanceof BlockDispenser || block instanceof BlockPumpkin || block instanceof BlockChest || block instanceof BlockEnderChest || block instanceof BlockFence || block instanceof BlockFenceGate) {
                    GlStateManager.translate(0.015, -0.004, -0.0005);
                    GlStateManager.rotate(0, 0, 1, 0);
                    GlStateManager.rotate(0, 1, 0, 0);
                    GlStateManager.rotate(0, 0, 0, 1);
                    GlStateManager.scale(1, 1, 1);
                } else if (block != null) {
                    GlStateManager.translate(0.015, -0.004, -0.0005);
                    GlStateManager.rotate(90, 0, 1, 0);
                    GlStateManager.rotate(0, 1, 0, 0);
                    GlStateManager.rotate(0, 0, 0, 1);
                    GlStateManager.scale(1, 1, 1);
                }
            }
        }
    }


    @Dynamic("OptiFine")
    @Redirect(method = "renderItemModelTransform", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms;applyTransform(Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V"))
    private void onApplyTransformations(ItemCameraTransforms instance, ItemCameraTransforms.TransformType type) {
        if (!MixcesHandler.applyMixcesTransformations(type)) {
            instance.applyTransform(type);
        }
    }
}
