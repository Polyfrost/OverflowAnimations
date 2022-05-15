package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.MixcesHandler;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.vecmath.Matrix4f;

@Mixin(value = ForgeHooksClient.class, remap = false)
public class ForgeHooksClientMixin {

    @Redirect(method = "handleCameraTransforms", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/ForgeHooksClient;multiplyCurrentGlMatrix(Ljavax/vecmath/Matrix4f;)V"))
    private static void onHandlePerspective(Matrix4f i, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType) {
        if (!MixcesHandler.applyMixcesTransformations(cameraTransformType)) {
            ForgeHooksClient.multiplyCurrentGlMatrix(i);
        }
    }

    @Redirect(method = "handleCameraTransforms", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms;applyTransform(Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V", remap = true))
    private static void onApplyTransformations(ItemCameraTransforms instance, ItemCameraTransforms.TransformType type) {
        if (!MixcesHandler.applyMixcesTransformations(type)) {
            instance.applyTransform(type);
        }
    }
}
