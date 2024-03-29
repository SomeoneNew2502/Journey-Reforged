package net.journeyreforged.mixin.item.gear;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InfinityEnchantment.class)
public class BowInfinityMendingMixin extends Enchantment{

    protected BowInfinityMendingMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
    private void canAccept(Enchantment other, CallbackInfoReturnable<Boolean> info) {
        info.cancel();
        info.setReturnValue(super.canAccept(other));
    }
}
