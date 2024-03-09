package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registry.EnchantmentRegistry;
import net.journeyreforged.registry.ItemRegistry;
import net.journeyreforged.util.function.EnchantWithLevelRangeBuilder;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class End_City {
    
    private static final Identifier END_CITY_TREASURE = new Identifier("minecraft", "chests/end_city_treasure");

    public static void modifyLootTables() {


        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (END_CITY_TREASURE.equals(id)) {
                LootPool pool = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(3.0f, 7.0f))
                        .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f,5.0f))).weight(20).build())
                        .with(ItemEntry.builder(Items.GOLD_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f,5.0f))).weight(20).build())
                        .with(ItemEntry.builder(Items.CHORUS_FRUIT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f,5.0f))).weight(10).build())
                        .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,4.0f))).weight(10).build())
                        .with(ItemEntry.builder(Items.SHULKER_SHELL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f,4.0f))).weight(5).build())
                        .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(5).build())
                        .with(ItemEntry.builder(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(3).build())
                        .with(ItemEntry.builder(Items.SADDLE).weight(2).build())
                        .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(2).build())
                        .build();

                LootPool pool2 = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1.0f, 3.0f))
                        .with(ItemEntry.builder(Items.IRON_PICKAXE).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.IRON_SHOVEL).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.IRON_SWORD).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(ItemRegistry.getItem("iron_dagger")).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.IRON_HELMET).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.IRON_CHESTPLATE).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.IRON_LEGGINGS).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.IRON_BOOTS).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.DIAMOND_PICKAXE).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.DIAMOND_SHOVEL).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.DIAMOND_SWORD).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(ItemRegistry.getItem("diamond_dagger")).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.DIAMOND_HELMET).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.DIAMOND_CHESTPLATE).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.DIAMOND_LEGGINGS).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .with(ItemEntry.builder(Items.DIAMOND_BOOTS).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(20, 39))).build())
                        .build();

                return LootTable.builder().pool(pool).pool(pool2).build();
            }
            return null;
        });

        //Adds 3 new pools to the End City Treasure loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (END_CITY_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 1.0f))
                    .conditionally(RandomChanceLootCondition.builder(0.50f)) //The percentage chance for a roll to succeed
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().build()); //Randomly enchants the item

                LootPool.Builder poolBuilder2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 1.0f))
                    .conditionally(RandomChanceLootCondition.builder(0.20f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantWithLevelRangeBuilder(EnchantmentRegistry.REPOSSESSION).setLevelRange(1, 3).build());
                
                LootPool.Builder poolBuilder3 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 1.0f))
                    .conditionally(RandomChanceLootCondition.builder(0.15f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().build());
                
                supplier.pool(poolBuilder).pool(poolBuilder2).pool(poolBuilder3).build();
            }
        }));
    }
}
