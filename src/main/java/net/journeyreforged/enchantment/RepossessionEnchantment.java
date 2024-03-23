package net.journeyreforged.enchantment;

import net.journeyreforged.item.gear.dagger.DaggerItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.PiercingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class RepossessionEnchantment extends Enchantment {
    public RepossessionEnchantment() {
        // Rarity (UNCOMMON, RARE, or VERY_RARE), applicable target, and slots where the enchantment can be applied.
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof DaggerItem;
    }

    @Override
    public int getMinPower(int level) {
        // Defines the minimum enchantability requirement for the enchantment.
        return 15; // You might still want to override this for treasure enchantments.
    }

    @Override
    public int getMaxLevel() {
        // Defines the maximum level of the enchantment.
        return 3; // If it's particularly powerful, you might limit this to 1.
    }

    @Override
    public boolean isTreasure() {
        // Marks the enchantment as a treasure enchantment.
        return true;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        // Ensures this enchantment cannot be obtained from the enchantment table.
        return false;
    }
}