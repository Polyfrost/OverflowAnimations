package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.OverflowAnimations;
import cc.woverflow.overflowanimations.config.AnimationsConfig;
import net.minecraft.block.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.*;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.vecmath.Matrix4f;

@Mixin(value = ForgeHooksClient.class, remap = false)
public class ForgeHooksClientMixin {

    @Redirect(method = "handleCameraTransforms", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/ForgeHooksClient;multiplyCurrentGlMatrix(Ljavax/vecmath/Matrix4f;)V"))
    private static void onHandlePerspective(Matrix4f i, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType) {
        if (!applyMixcesTransformations(cameraTransformType)) {
            ForgeHooksClient.multiplyCurrentGlMatrix(i);
        }
    }

    @Redirect(method = "handleCameraTransforms", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms;applyTransform(Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V"))
    private static void onApplyTransformations(ItemCameraTransforms instance, ItemCameraTransforms.TransformType type) {
        if (!applyMixcesTransformations(type)) {
            instance.applyTransform(type);
        }
    }

    private static boolean applyMixcesTransformations(ItemCameraTransforms.TransformType type) {
        if (AnimationsConfig.mixcesAnimations) {
            ItemStack stack = OverflowAnimations.renderingStack;
            if (stack != null) {
                if (type == ItemCameraTransforms.TransformType.THIRD_PERSON) {
                    if (stack.getItem() instanceof ItemBlock) {
                        Block block = ((ItemBlock) stack.getItem()).getBlock();
                        if (block instanceof BlockCarpet || block instanceof BlockSnow) {
                            applyThatWeirdTransformation();
                        } else if (block instanceof BlockFence) {
                            GlStateManager.translate(0.0125, 0.1, -0.171875);
                            GlStateManager.rotate(45, 0, 1, 0);
                            GlStateManager.rotate(14, 1, 0, 0);
                            GlStateManager.rotate(193, 0, 0, 1);
                            GlStateManager.scale(0.375, 0.375, 0.375);
                        } else if (block instanceof BlockFenceGate) {
                            GlStateManager.translate(0.0125, 0.1, -0.171875);
                            GlStateManager.rotate(-45, 0, 1, 0);
                            GlStateManager.rotate(14, 1, 0, 0);
                            GlStateManager.rotate(167, 0, 0, 1);
                            GlStateManager.scale(0.375, 0.375, 0.375);
                        } else {
                            GlStateManager.translate(0.05, 0.046875, -0.18);
                            GlStateManager.rotate(-133, 0, 1, 0);
                            GlStateManager.rotate(110, 1, 0, 0);
                            GlStateManager.rotate(60, 0, 0, 1);
                            GlStateManager.scale(0.375, 0.375, 0.375);
                        }
                    } else {
                        if (stack.getItem() instanceof ItemBow) {
                            GlStateManager.translate(0.0625, 0.0, -0.03125);
                            GlStateManager.rotate(67, 0, 1, 0);
                            GlStateManager.rotate(-6, 1, 0, 0);
                            GlStateManager.rotate(-45, 0, 0, 1);
                            GlStateManager.scale(1, 1, 1);
                        } else if (stack.getItem() instanceof ItemFishingRod || stack.getItem() instanceof ItemCarrotOnAStick) {
                            GlStateManager.translate(0.005, 0.035, -0.200625);
                            GlStateManager.rotate(91, 0, 1, 0);
                            GlStateManager.rotate(175, 1, 0, 0);
                            GlStateManager.rotate(-56, 0, 0, 1);
                            GlStateManager.scale(0.95, 0.95, 0.95);
                        } else if (stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemHoe || stack.getItem().getUnlocalizedName().equals("item.stick")) {
                            GlStateManager.translate(-0.003125, 0.031875, -0.1875);
                            GlStateManager.rotate(88, 0, 1, 0);
                            GlStateManager.rotate(-4, 1, 0, 0);
                            GlStateManager.rotate(-54, 0, 0, 1);
                            GlStateManager.scale(0.9, 0.9, 0.9);
                        } else {
                            //applyThatWeirdTransformation();
                            OverflowAnimations.renderingStack = null;
                            return false;
                        }
                    }
                } else if (stack.getItemUseAction() == EnumAction.BLOCK) {
                    GlStateManager.translate(-0.00625, 0.001875, 0.004375);
                    GlStateManager.rotate(0, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(0, 1.0f, 0.0f, 0.0f);
                    GlStateManager.rotate(0, 0.0f, 0.0f, 1.0f);
                    GlStateManager.scale(1.0, 1.0, 1.0);
                } else if (!(stack.getItem() instanceof ItemBlock)) {
                    GlStateManager.translate(0, 0, 0);
                    GlStateManager.rotate(0, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(0, 1.0f, 0.0f, 0.0f);
                    GlStateManager.rotate(0, 0.0f, 0.0f, 1.0f);
                    GlStateManager.scale(1.0, 1.0, 1.0);
                } else {
                    Block block = ((ItemBlock) stack.getItem()).getBlock();
                    if (block instanceof BlockCarpet || block instanceof BlockSnow) {
                        /*/
                        GlStateManager.translate(0, 0, 0);
                        GlStateManager.rotate(0, 0.0f, 1.0f, 0.0f);
                        GlStateManager.rotate(0, 1.0f, 0.0f, 0.0f);
                        GlStateManager.rotate(0, 0.0f, 0.0f, 1.0f);
                        GlStateManager.scale(1.0, 1.0, 1.0);

                         */
                        OverflowAnimations.renderingStack = null;
                        return false;
                    } else {
                        OverflowAnimations.renderingStack = null;
                        return false;
                    }
                }
                OverflowAnimations.renderingStack = null;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static void applyThatWeirdTransformation() {
        GlStateManager.translate(0.0, 0.096875, -0.15);
        GlStateManager.rotate(-47.5F, 0, 1, 0);
        GlStateManager.rotate(15, 1, 0, 0);
        GlStateManager.rotate(165, 0, 0, 1);
        GlStateManager.scale(0.375, 0.375, 0.375);
    }
}
