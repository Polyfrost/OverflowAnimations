package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.config.AnimationsConfig;
import club.sk1er.oldanimations.AnimationHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RendererLivingEntity.class)
public abstract class RendererLivingEntityMixin<T extends EntityLivingBase> {
    @Shadow protected abstract float getSwingProgress(T livingBase, float partialTickTime);

    @Redirect(method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RendererLivingEntity;getSwingProgress(Lnet/minecraft/entity/EntityLivingBase;F)F"))
    private float redirectSwing(RendererLivingEntity<T> instance, T livingBase, float partialTickTime) {
        if (AnimationsConfig.thirdPersonItemRendering && livingBase instanceof EntityPlayerSP) {
            return AnimationHandler.getInstance().getSwingProgress(partialTickTime);
        } else {
            return getSwingProgress(livingBase, partialTickTime);
        }
    }
}
