package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.config.AnimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public abstract class RenderItemMixin_Glint {

    @Shadow
    @Final
    private static ResourceLocation RES_ITEM_GLINT;
    @Shadow
    @Final
    private TextureManager textureManager;

    @Shadow
    protected abstract void renderModel(IBakedModel model, int color);

    @Inject(method = "renderEffect", at = @At("HEAD"), cancellable = true)
    private void renderOldEffect(IBakedModel model, CallbackInfo ci) {
        if (AnimationsConfig.enchantmentGlint) {
            ci.cancel();
            GlStateManager.depthMask(false);
            GlStateManager.depthFunc(514);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(768, 1);
            textureManager.bindTexture(RES_ITEM_GLINT);
            GlStateManager.matrixMode(5890);

            // first
            /*?
            GlStateManager.pushMatrix();
            GlStateManager.scale(8.0, 8.0, 8.0);
            GlStateManager.translate((Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
            renderModel(model, -8372020);
            GlStateManager.popMatrix();

             */


            // second
            GlStateManager.pushMatrix();
            GlStateManager.scale(8.0, 8.0, 8.0);
            GlStateManager.translate((Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F, 0.0F, 0.0F);
            GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
            renderModel(model, -8372020);
            GlStateManager.popMatrix();

            GlStateManager.matrixMode(5888);
            GlStateManager.blendFunc(770, 771);
            GlStateManager.enableLighting();
            GlStateManager.depthFunc(515);
            GlStateManager.depthMask(true);
            this.textureManager.bindTexture(TextureMap.locationBlocksTexture);
        }
    }
}
