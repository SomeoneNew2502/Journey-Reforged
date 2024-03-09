package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class Shipwreck_Treasure {
    
    private static final Identifier SHIPWRECK_TREASURE = new Identifier("minecraft", "chests/shipwreck_treasure");

    public static void modifyLootTables() {

        //Adds a new pool to the Shipwreck Treasure loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (SHIPWRECK_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .conditionally(RandomChanceLootCondition.builder(0.30f)) //The percentage chance for a roll to succeed
                    .with(ItemEntry.builder(ItemRegistry.getItem("pearl")).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f,7.0f))).build());
                supplier.pool(poolBuilder).build();
            }
        }));
    }
}
