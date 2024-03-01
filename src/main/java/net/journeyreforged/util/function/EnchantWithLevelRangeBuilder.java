package net.journeyreforged.util.function;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunction.Builder;

public class EnchantWithLevelRangeBuilder implements Builder {
    private final Enchantment enchantment;
    private int minLevel = 1;
    private int maxLevel = 1;

    public EnchantWithLevelRangeBuilder(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public Builder setLevelRange(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        return this;
    }

    @Override
    public LootFunction build() {
        return new EnchantWithLevelRangeFunction(enchantment, minLevel, maxLevel);
    }
}
