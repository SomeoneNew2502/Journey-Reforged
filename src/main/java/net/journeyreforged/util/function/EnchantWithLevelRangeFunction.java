package net.journeyreforged.util.function;

import net.journeyreforged.JourneyReforged;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.util.math.random.Random;

public class EnchantWithLevelRangeFunction implements LootFunction {
    private final Enchantment enchantment;
    private final int minLevel;
    private final int maxLevel;

    @Override
    public LootFunctionType getType() {
        // Return the registered LootFunctionType for this function
        return JourneyReforged.ENCHANT_WITH_LEVEL_RANGE_LOOT_FUNCTION;
    }
    public EnchantWithLevelRangeFunction(Enchantment enchantment, int minLevel, int maxLevel) {
        this.enchantment = enchantment;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    @Override
    public ItemStack apply(ItemStack stack, LootContext lootContext) {
        Random random = lootContext.getRandom();
        int level = random.nextInt((maxLevel - minLevel) + 1) + minLevel;

        // Check if the rolled level is greater than 0
        if (level > 0) {
            if (stack.isOf(Items.BOOK)) {
                stack = new ItemStack(Items.ENCHANTED_BOOK);
                EnchantedBookItem.addEnchantment(stack, new EnchantmentLevelEntry(enchantment, level));
            } else {
                stack.addEnchantment(enchantment, level);
            }
        }
        return stack;
    }


    public Enchantment getEnchantment() {
        return enchantment;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
