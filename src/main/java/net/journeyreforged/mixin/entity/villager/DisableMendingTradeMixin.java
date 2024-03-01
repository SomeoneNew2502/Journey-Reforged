package net.journeyreforged.mixin.entity.villager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

@Mixin(Enchantment.class)
public abstract class DisableMendingTradeMixin {

    @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("HEAD"), cancellable = true)
    public void CheckMending(CallbackInfoReturnable<Boolean> info) {
        if ((Object)this == Enchantments.MENDING) {
            info.setReturnValue(false);
        }
    }
}