package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.enchantment.RepossessionEnchantment;
import net.journeyreforged.registry.EnchantmentRegistry;
import net.journeyreforged.util.function.EnchantWithLevelRangeBuilder;
import net.journeyreforged.util.function.EnchantWithLevelRangeFunction;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

public class Woodland_Mansion {
    private static final Identifier WOODLAND_MANSION = new Identifier("minecraft", "chests/woodland_mansion");

    public static void modifyLootTables() {

        //Replaces the Woodland Mansion loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (WOODLAND_MANSION.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(3.0f, 5.0f))
                    .with(ItemEntry.builder(Items.BONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(9).build())
                    .with(ItemEntry.builder(Items.GUNPOWDER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,6.0f))).weight(9).build())
                    .with(ItemEntry.builder(Items.ROTTEN_FLESH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(9).build())
                    .with(ItemEntry.builder(Items.IRON_NUGGET).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.POISONOUS_POTATO).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.POTATO).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,4.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.MUTTON).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.BLUE_CANDLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.CHAIN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,4.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,4.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.LEAD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.TIPPED_ARROW).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f,7.0f))).apply(SetPotionLootFunction.builder(Potions.HARMING)).weight(2).build())
                    .with(ItemEntry.builder(Items.TNT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(2).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(2.0f, 5.0f))
                    .with(ItemEntry.builder(Items.GLASS_BOTTLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(15).build())
                    .with(ItemEntry.builder(Items.LAPIS_LAZULI).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(15).build())
                    .with(ItemEntry.builder(Items.LAPIS_BLOCK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.BOOK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,4.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.SPLASH_POTION).apply(SetPotionLootFunction.builder(Potions.POISON)).weight(5).build())
                    .with(ItemEntry.builder(Items.SPLASH_POTION).apply(SetPotionLootFunction.builder(Potions.WEAKNESS)).weight(5).build())
                    .with(ItemEntry.builder(Items.SPLASH_POTION).apply(SetPotionLootFunction.builder(Potions.SLOWNESS)).weight(5).build())
                    .with(ItemEntry.builder(Items.SPLASH_POTION).apply(SetPotionLootFunction.builder(Potions.HARMING)).weight(5).build())
                    .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.OBSIDIAN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,4.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.ENCHANTING_TABLE).weight(1).build())
                    .build();

                LootPool pool3 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 3.0f))
                    .with(ItemEntry.builder(Items.IRON_AXE).apply(EnchantRandomlyLootFunction.builder()).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(15).build())
                    .with(ItemEntry.builder(Items.CROSSBOW).apply(EnchantRandomlyLootFunction.builder()).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(15).build())
                    .with(ItemEntry.builder(Items.FLINT_AND_STEEL).weight(15).build())
                    .with(ItemEntry.builder(Items.SADDLE).weight(15).build())
                    .with(ItemEntry.builder(Items.IRON_SWORD).apply(EnchantRandomlyLootFunction.builder()).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(15).build())
                    .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder()).weight(10).build())
                    .with(ItemEntry.builder(Items.BOOK).apply(new EnchantWithLevelRangeBuilder(EnchantmentRegistry.REPOSSESSION).setLevelRange(1, 3)).weight(10).build())
                    .with(ItemEntry.builder(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE).weight(5).build())
                    .with(ItemEntry.builder(Items.TOTEM_OF_UNDYING).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND_PICKAXE).apply(EnchantRandomlyLootFunction.builder()).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).pool(pool3).build();
            }
            return null;
        });
    }
}
