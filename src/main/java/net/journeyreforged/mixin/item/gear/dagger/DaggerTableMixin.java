package net.journeyreforged.mixin.item.gear.dagger;

import net.journeyreforged.item.gear.dagger.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(EnchantmentHelper.class)
public abstract class DaggerTableMixin {

    @Inject(method = "getPossibleEntries", at = @At("RETURN"), cancellable = true)
    private static void modifyDaggerEnchantments(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        if (!(stack.getItem() instanceof DaggerItem)) {
            return; // Exit if the item is not a DaggerItem.
        }

        List<EnchantmentLevelEntry> originalEntries = cir.getReturnValue();
        List<EnchantmentLevelEntry> modifiedEntries = originalEntries.stream()
                .filter(entry -> entry.enchantment != Enchantments.SWEEPING) // Remove Sweeping Edge
                .collect(Collectors.toList());
        cir.setReturnValue(modifiedEntries); // Update the list of possible enchantments
    }

}

