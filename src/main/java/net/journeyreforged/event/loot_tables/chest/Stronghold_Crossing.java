package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class Stronghold_Crossing {
    private static final Identifier STRONGHOLD_CROSSING = new Identifier("minecraft", "chests/stronghold_crossing");

    public static void modifyLootTables() {

        //Replacing Pillager Outpost's loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (STRONGHOLD_CROSSING.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(ItemEntry.builder(Items.OAK_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))).weight(10).build())
                    .with(ItemEntry.builder(Items.SPRUCE_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))).weight(10).build())
                    .with(ItemEntry.builder(Items.BIRCH_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))).weight(10).build())
                    .with(ItemEntry.builder(Items.JUNGLE_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))).weight(5).build())
                    .with(ItemEntry.builder(Items.ACACIA_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))).weight(5).build())
                    .with(ItemEntry.builder(Items.DARK_OAK_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))).weight(5).build())
                    .with(ItemEntry.builder(Items.MANGROVE_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))).weight(5).build())
                    .with(ItemEntry.builder(Items.OBSIDIAN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))).weight(2).build())
                    .with(ItemEntry.builder(Items.CRYING_OBSIDIAN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(2.0f, 4.0f))
                    .with(ItemEntry.builder(Items.APPLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(10).build())
                    .with(ItemEntry.builder(Items.BREAD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(8).build())
                    .with(ItemEntry.builder(Items.COOKED_BEEF).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(8).build())
                    .with(ItemEntry.builder(Items.GLOW_BERRIES).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.CAKE).weight(1).build())
                    .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(1).build())
                    .build();

                LootPool pool3 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 4.0f))
                    .with(ItemEntry.builder(Items.TORCH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(20).build())
                    .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(20).build())
                    .with(ItemEntry.builder(Items.COMPASS).weight(1).build())
                    .with(ItemEntry.builder(Items.CLOCK).weight(1).build())
                    .with(ItemEntry.builder(Items.SPYGLASS).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).pool(pool3).build();
            }
            return null;
        });
    }
}
