package net.journeyreforged.mixin.entity.villager;

import net.journeyreforged.registry.EnchantmentRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

@Mixin(Enchantment.class)
public abstract class DisableEnchantedBookTradeMixin {

    @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("HEAD"), cancellable = true)
    public void CheckEnchantment(CallbackInfoReturnable<Boolean> info) {
        if ((Object)this == Enchantments.MENDING || (Object)this == EnchantmentRegistry.REPOSSESSION) {
            info.setReturnValue(false);
        }
    }
}