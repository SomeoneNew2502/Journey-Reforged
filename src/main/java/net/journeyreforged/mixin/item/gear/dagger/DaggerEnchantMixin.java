package net.journeyreforged.mixin.item.gear.dagger;

import net.journeyreforged.item.gear.dagger.*;
import net.journeyreforged.registry.EnchantmentRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class DaggerEnchantMixin {

    // You need to implement the isSweepingEdge method or another way to identify the Sweeping Edge enchantment.
    private boolean isSweepingEdge() {
        return ((Enchantment) (Object) this).equals(Enchantments.SWEEPING);
    }


    @Inject(method = "isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void injectIsAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (((DaggerEnchantMixin) (Object) this).isSweepingEdge() && (stack.getItem() instanceof DaggerItem)) {
            cir.setReturnValue(false);
        }
    }
}