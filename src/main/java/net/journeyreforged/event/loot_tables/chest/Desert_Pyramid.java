package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

public class Desert_Pyramid {

    public static void modifyLootTables() {
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (new Identifier("minecraft", "chests/desert_pyramid").equals(id)) {
                LootPool pool1 = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(3.0f, 4.0f))
                        .with(ItemEntry.builder(Items.SAND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f))).weight(20).build())
                        .with(ItemEntry.builder(Items.GUNPOWDER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f))).weight(10).build())
                        .with(ItemEntry.builder(Items.BONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(7).build())
                        .with(ItemEntry.builder(Items.ROTTEN_FLESH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(5).build())
                        .with(ItemEntry.builder(Items.CACTUS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(5).build())
                        .with(ItemEntry.builder(Items.STRING).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(4).build())
                        .with(ItemEntry.builder(Items.SPIDER_EYE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(3).build())
                        .with(ItemEntry.builder(Items.RABBIT_FOOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(3).build())
                        .build();

                LootPool pool2 = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(3.0f, 4.0f))
                        .with(ItemEntry.builder(Items.GOLD_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 6.0f))).weight(20).build())
                        .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))).weight(13).build())
                        .with(ItemEntry.builder(Items.REDSTONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 6.0f))).weight(8).build())
                        .with(ItemEntry.builder(Items.GLOWSTONE_DUST).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 6.0f))).weight(8).build())
                        .with(ItemEntry.builder(ItemRegistry.getItem("pearl")).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f))).weight(8).build())
                        .with(ItemEntry.builder(Items.GOLDEN_CARROT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(7).build())
                        .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))).weight(5).build())
                        .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))).weight(5).build())
                        .with(ItemEntry.builder(Items.AMETHYST_SHARD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))).weight(3).build())
                        .with(ItemEntry.builder(Items.SADDLE).weight(2).build())
                        .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(2).build())
                        .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(2).build())
                        .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(2).build())
                        .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder()).weight(2).build())
                        .with(ItemEntry.builder(Items.BRUSH).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(2).build())
                        .with(ItemEntry.builder(Items.GOLDEN_APPLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(1).build())
                        .build();

                LootPool pool3 = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1.0f, 1.0f))
                        .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 7.0f))).weight(18).build())
                        .with(ItemEntry.builder(Items.ENDER_PEARL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(7).build())
                        .with(ItemEntry.builder(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(5).build())
                        .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.HEALING)).weight(3).build())
                        .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.REGENERATION)).weight(3).build())
                        .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1).build())
                        .build();

                return LootTable.builder().pool(pool1).pool(pool2).pool(pool3).build();
            }
            return null;
        });
    }
}
