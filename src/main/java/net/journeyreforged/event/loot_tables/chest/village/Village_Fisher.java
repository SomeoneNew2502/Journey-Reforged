package net.journeyreforged.event.loot_tables.chest.village;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class Village_Fisher {
    
    private static final Identifier VILLAGE_FISHER = new Identifier("minecraft", "chests/village/village_fisher");

    public static void modifyLootTables() {

        //Replaces the Village Fisher loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (VILLAGE_FISHER.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(3.0f, 7.0f))
                    .with(ItemEntry.builder(Items.SALMON).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.COD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.TROPICAL_FISH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.PUFFERFISH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.INK_SAC).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.KELP).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 7.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.SEA_PICKLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.COAL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.BARREL).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.FISHING_ROD).weight(30).build())
                    .with(ItemEntry.builder(Items.FISHING_ROD).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 7))).weight(15).build()) //Randomly enchants the item with 1 - 7 levels
                    .with(ItemEntry.builder(Items.WATER_BUCKET).weight(1).build())
                    .with(ItemEntry.builder(Items.SPYGLASS).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).build();
            }
            return null;
        });
    }
}
