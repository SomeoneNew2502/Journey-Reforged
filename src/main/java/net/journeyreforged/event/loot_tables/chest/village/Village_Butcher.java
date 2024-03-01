package net.journeyreforged.event.loot_tables.chest.village;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class Village_Butcher {
    
    private static final Identifier VILLAGE_BUTCHER = new Identifier("minecraft", "chests/village/village_butcher");

    public static void modifyLootTables() {

        //Replaces the Village Butcher loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (VILLAGE_BUTCHER.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(3.0f, 7.0f))
                    .with(ItemEntry.builder(Items.BEEF).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.MUTTON).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.PORKCHOP).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.CHICKEN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.RABBIT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.WHEAT_SEEDS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.WHEAT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.LEATHER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.RABBIT_HIDE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.COAL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.RABBIT_STEW).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(EmptyEntry.builder().weight(20)) //EmptyEntry that rolls nothing
                    .with(ItemEntry.builder(Items.RABBIT_FOOT).weight(1).build())
                    .with(ItemEntry.builder(Items.LEAD).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).build();
            }
            return null;
        });
    }
}
