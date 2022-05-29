package cc.woverflow.overflowanimations;

import cc.woverflow.overflowanimations.config.AnimationsConfig;
import net.minecraft.block.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.*;

public class MixcesHandler {
    public static boolean applyMixcesTransformations(ItemCameraTransforms.TransformType type) {
        if (AnimationsConfig.mixcesAnimations) {
            ItemStack stack = OverflowAnimations.renderingStack;
            if (stack != null) {
                if (type == ItemCameraTransforms.TransformType.THIRD_PERSON) {
                    if (stack.getItem() instanceof ItemBlock) {
                        Block block = ((ItemBlock) stack.getItem()).getBlock();
                        if (block instanceof BlockCarpet || block instanceof BlockSnow) {
                            applyThatWeirdTransformation();
                        } else if (block instanceof BlockFence) {
                            GlStateManager.translate(0, -0.01, 0.04);
                            GlStateManager.rotate(135, 0, 1, 0);
                            GlStateManager.rotate(15, 1, 0, 0);
                            GlStateManager.rotate(-15, 0, 0, 1);
                            GlStateManager.scale(1, 1, 1);
                        } else if (block instanceof BlockFenceGate) {
                            GlStateManager.translate(-0.04, -0.01, 0);
                            GlStateManager.rotate(135, 0, 1, 0);
                            GlStateManager.rotate(10, 1, 0, 0);
                            GlStateManager.rotate(7.5f, 0, 0, 1);
                            GlStateManager.scale(1, 1, 1);
                        } else if (block instanceof BlockChest || block instanceof BlockEnderChest) {
                            GlStateManager.translate(0, 0, 0);
                            GlStateManager.rotate(-90, 0, 1, 0);
                            GlStateManager.rotate(0, 1, 0, 0);
                            GlStateManager.rotate(0, 0, 0, 1);
                            GlStateManager.scale(1, 1, 1);
                        } else if (block instanceof BlockLadder || block instanceof BlockPane || block instanceof BlockRail || block instanceof BlockRailPowered || block instanceof BlockRailDetector || block instanceof BlockVine || block instanceof BlockWeb || block instanceof BlockLever || block instanceof BlockBush || block instanceof BlockTorch || block instanceof BlockTripWireHook) {
                            GlStateManager.translate(0.0985, -0.02, -0.034);
                            GlStateManager.rotate(-13, 0, 1, 0);
                            GlStateManager.rotate(163, 1, 0, 0);
                            GlStateManager.rotate(193, 0, 0, 1);
                            GlStateManager.scale(1.0, 1.0, 1.0);
                        } else if (block instanceof BlockFurnace || block instanceof BlockDispenser || block instanceof BlockPumpkin) {
                            GlStateManager.translate(0.025, -0.01, -0.025);
                            GlStateManager.rotate(180, 0, 1, 0);
                            GlStateManager.rotate(5, 1, 0, 0);
                            GlStateManager.rotate(5, 0, 0, 1);
                            GlStateManager.scale(1, 1, 1);
                        } else if (block instanceof BlockBanner) {
                            GlStateManager.translate(0.01, -0.066, -0.007);
                            GlStateManager.rotate(-4, 0, 1, 0);
                            GlStateManager.rotate(-4, 1, 0, 0);
                            GlStateManager.rotate(-20, 0, 0, 1);
                            GlStateManager.scale(1.1, 1.1, 1.1);
                        } else if (block instanceof BlockButton) {
                            GlStateManager.translate(0.095, -0.04, -0.095);
                            GlStateManager.rotate(0, 0, 1, 0);
                            GlStateManager.rotate(-5, 1, 0, 0);
                            GlStateManager.rotate(-5, 0, 0, 1);
                            GlStateManager.scale(1, 1, 1);
                        } else if (block != null) {
                            GlStateManager.translate(-0.025, -0.01, 0.025);
                            GlStateManager.rotate(0, 0, 1, 0);
                            GlStateManager.rotate(-5, 1, 0, 0);
                            GlStateManager.rotate(-5, 0, 0, 1);
                            GlStateManager.scale(1, 1, 1);
                        } else {
                            OverflowAnimations.renderingStack = null;
                            return false;
                        }
                    } else {
                        if (stack.getItem() instanceof ItemBow) {
                            GlStateManager.translate(0.037, 0.020, 0.007);
                            GlStateManager.rotate(-15, 0, 1, 0);
                            GlStateManager.rotate(0, 1, 0, 0);
                            GlStateManager.rotate(-10, 0, 0, 1);
                            GlStateManager.scale(1, 1, 1);
                        } else if (stack.getItem() instanceof ItemFishingRod || stack.getItem() instanceof ItemCarrotOnAStick) {
                            GlStateManager.translate(0.0, -0.052, -0.007);
                            GlStateManager.rotate(-4, 0, 1, 0);
                            GlStateManager.rotate(-4, 1, 0, 0);
                            GlStateManager.rotate(-20, 0, 0, 1);
                            GlStateManager.scale(1.1, 1.1, 1.1);
                        } else if (stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemHoe || stack.getItem().getUnlocalizedName().equals("item.stick") || stack.getItem().getUnlocalizedName().equals("item.blazeRod")) {
                            GlStateManager.translate(0.01, -0.066, -0.007);
                            GlStateManager.rotate(-4, 0, 1, 0);
                            GlStateManager.rotate(-4, 1, 0, 0);
                            GlStateManager.rotate(-20, 0, 0, 1);
                            GlStateManager.scale(1.1, 1.1, 1.1);
                        } else if (stack.getItem() instanceof ItemSkull) {
                            GlStateManager.translate(-0.025, -0.01, 0.025);
                            GlStateManager.rotate(0, 0, 1, 0);
                            GlStateManager.rotate(15, 1, 0, 0);
                            GlStateManager.rotate(15, 0, 0, 1);
                            GlStateManager.scale(1, 1, 1);
                        } else if (stack.getItem() != null) {
                            GlStateManager.translate(0.0985, -0.02, -0.034);
                            GlStateManager.rotate(-13, 0, 1, 0);
                            GlStateManager.rotate(163, 1, 0, 0);
                            GlStateManager.rotate(193, 0, 0, 1);
                            GlStateManager.scale(1.0, 1.0, 1.0);
                        } else {
                            //applyThatWeirdTransformation();
                            OverflowAnimations.renderingStack = null;
                            return false;
                        }
                    }
                } else {
                    OverflowAnimations.renderingStack = null;
                    return false;
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

    // Carpet & Snow Layer transformations )))):
    private static void applyThatWeirdTransformation() {
        GlStateManager.translate(0.01, -0.2, -0.01);
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(5, 1, 0, 0);
        GlStateManager.rotate(5, 0, 0, 1);
        GlStateManager.scale(1, 1, 1);
    }
}

