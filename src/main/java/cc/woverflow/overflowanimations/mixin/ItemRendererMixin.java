package cc.woverflow.overflowanimations.mixin;

import cc.woverflow.overflowanimations.config.AnimationsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Shadow private float prevEquippedProgress;

    @Shadow private float equippedProgress;

    @Shadow private ItemStack itemToRender;

    @Shadow private int equippedItemSlot;

    @Shadow @Final private Minecraft mc;

    @Inject(method = "updateEquippedItem", at = @At("HEAD"), cancellable = true)
    private void update17Animation(CallbackInfo ci) {
        if (AnimationsConfig.itemSwitch) {
            ci.cancel();
            prevEquippedProgress = equippedProgress;
            EntityPlayerSP player = mc.thePlayer;
            ItemStack itemstack = player.inventory.getCurrentItem();
            boolean flag = equippedItemSlot == player.inventory.currentItem && itemstack == itemToRender;

            if (itemToRender == null && itemstack == null)
            {
                flag = true;
            }

            if (itemstack != null && itemToRender != null && itemstack != itemToRender && itemstack.getItem() == itemToRender.getItem() && itemstack.getItemDamage() == itemToRender.getItemDamage())
            {
                itemToRender = itemstack;
                flag = true;
            }

            float f = 0.4F;
            float f1 = flag ? 1.0F : 0.0F;
            float f2 = f1 - equippedProgress;

            if (f2 < -f)
            {
                f2 = -f;
            }

            if (f2 > f)
            {
                f2 = f;
            }

            equippedProgress += f2;

            if (equippedProgress < 0.1F)
            {
                itemToRender = itemstack;
                equippedItemSlot = player.inventory.currentItem;
            }
        }
    }
}
