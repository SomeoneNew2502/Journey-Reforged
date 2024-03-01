package net.journeyreforged.event.loot_tables.chest.village;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class Village_Armorer {
    
    private static final Identifier VILLAGE_ARMORER = new Identifier("minecraft", "chests/village/village_armorer");

    public static void modifyLootTables() {

        //Replaces the Village Armorer loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (VILLAGE_ARMORER.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 5.0f))
                    .with(ItemEntry.builder(Items.BREAD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.IRON_NUGGET).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 6.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.CHAIN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.GOLD_NUGGET).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(EmptyEntry.builder().weight(50))
                    .with(ItemEntry.builder(Items.CHAINMAIL_HELMET).weight(4).build())
                    .with(ItemEntry.builder(Items.CHAINMAIL_BOOTS).weight(4).build())
                    .with(ItemEntry.builder(Items.IRON_HELMET).weight(4).build())
                    .with(ItemEntry.builder(Items.SHIELD).weight(4).build())
                    .with(ItemEntry.builder(Items.CHAINMAIL_HELMET).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 7))).weight(1).build()) //Randomly enchants the item with 1 - 7 levels
                    .with(ItemEntry.builder(Items.CHAINMAIL_BOOTS).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 7))).weight(1).build())
                    .with(ItemEntry.builder(Items.IRON_HELMET).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 7))).weight(1).build())
                    .with(ItemEntry.builder(Items.SHIELD).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 7))).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).build();
            }
            return null;
        });
    }
}
