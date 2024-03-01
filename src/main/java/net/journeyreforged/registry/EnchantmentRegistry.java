package net.journeyreforged.registry;

import net.journeyreforged.enchantment.RepossessionEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EnchantmentRegistry {
    public static final Enchantment REPOSSESSION = new RepossessionEnchantment();

    public static void init() {
        Registry.register(Registries.ENCHANTMENT, new Identifier("journeyreforged", "repossession"), REPOSSESSION);
    }
}
