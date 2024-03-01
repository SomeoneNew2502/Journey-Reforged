package net.journeyreforged.event.loot_tables.chest.village;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class Village_Cartographer {
    
    private static final Identifier VILLAGE_CARTOGRAPHER = new Identifier("minecraft", "chests/village/village_cartographer");

    public static void modifyLootTables() {

        //Replaces the Village Cartographer loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (VILLAGE_CARTOGRAPHER.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(3.0f, 7.0f))
                    .with(ItemEntry.builder(Items.PAPER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.WHITE_WOOL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.BREAD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.MAP).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.ITEM_FRAME).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.COMPASS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.SHEARS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(EmptyEntry.builder().weight(25)) //An empty entry that rolls nothing
                    .with(ItemEntry.builder(Items.FLOWER_BANNER_PATTERN).weight(1).build())
                    .with(ItemEntry.builder(Items.CREEPER_BANNER_PATTERN).weight(1).build())
                    .with(ItemEntry.builder(Items.SKULL_BANNER_PATTERN).weight(1).build())
                    .with(ItemEntry.builder(Items.MOJANG_BANNER_PATTERN).weight(1).build())
                    .with(ItemEntry.builder(Items.GLOBE_BANNER_PATTERN).weight(1).build())
                    .with(ItemEntry.builder(Items.PIGLIN_BANNER_PATTERN).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).build();
            }
            return null;
        });
    }
}
