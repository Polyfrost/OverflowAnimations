package cc.woverflow.overflowanimations.plugin;

import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.*;

public class AnimationsMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        if (Objects.equals(targetClassName, "net.minecraft.client.Minecraft")) {
            for (MethodNode method : targetClass.methods) {
                if (method.name.toLowerCase(Locale.ENGLISH).contains("rightclickmouse")) {
                    ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();
                    while (iterator.hasNext()) {
                        AbstractInsnNode next = iterator.next();
                        if (next instanceof FieldInsnNode) {
                            FieldInsnNode field = (FieldInsnNode) next;
                            if (field.owner.equals("club/sk1er/oldanimations/config/OldAnimationsSettings") && field.name.equals("punching")) {
                                method.instructions.insert(next, new MethodInsnNode(Opcodes.INVOKESTATIC, "cc/woverflow/overflowanimations/OverflowAnimations", "shouldPunch", "()Z", false));
                                iterator.remove();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
