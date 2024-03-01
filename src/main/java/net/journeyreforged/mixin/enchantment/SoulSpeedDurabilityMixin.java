package net.journeyreforged.mixin.enchantment;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class SoulSpeedDurabilityMixin {

    @Redirect(method = "addSoulSpeedBoostIfNeeded()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V"))
    private void preventDurabilityLoss(ItemStack itemStack, int amount, LivingEntity entity, java.util.function.Consumer<LivingEntity> breakCallback) {
        // Prevents durability loss by not calling the ItemStack's damage method.
        // This leaves the movement speed bonus unaffected.
    }
}
