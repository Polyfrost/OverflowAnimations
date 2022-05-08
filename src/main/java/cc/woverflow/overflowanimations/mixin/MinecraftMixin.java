package cc.woverflow.overflowanimations.mixin;

import net.minecraft.client.Minecraft;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @see cc.woverflow.overflowanimations.plugin.AnimationsMixinPlugin#postApply(String, ClassNode, String, IMixinInfo)
 */
@Mixin(value = Minecraft.class, priority = Integer.MAX_VALUE)
public class MinecraftMixin {
    @Inject(method = "startGame", at = @At("HEAD"))
    private void onStartGame(CallbackInfo ci) {
        //no-op
    }
}
