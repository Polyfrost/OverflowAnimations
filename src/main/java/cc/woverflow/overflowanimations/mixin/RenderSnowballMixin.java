package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.config.AnimationsConfig;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSnowball.class)
public abstract class RenderSnowballMixin<T extends Entity> extends Render<T> {
    public RenderSnowballMixin(RenderManager renderManager) {
        super(renderManager);
    }

    @ModifyArg(method = "doRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V", ordinal = 1), index = 0)
    private float fixRotation(float angle) {
        return (renderManager.options.thirdPersonView == 2 && AnimationsConfig.oldProjectiles ? -1F : 1F) * angle;
    }

    @Inject(method = "doRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V", ordinal = 1, shift = At.Shift.AFTER))
    private void flipProjectile(T entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        if (AnimationsConfig.oldProjectiles) {
            GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
        }
    }
}
