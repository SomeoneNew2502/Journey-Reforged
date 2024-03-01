package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class End_City {
    
    private static final Identifier END_CITY_TREASURE = new Identifier("minecraft", "chests/end_city_treasure");

    public static void modifyLootTables() {

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
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().build());
                
                LootPool.Builder poolBuilder3 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 1.0f))
                    .conditionally(RandomChanceLootCondition.builder(0.15f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().build());
                
                supplier.pool(poolBuilder).pool(poolBuilder2).pool(poolBuilder3).build();
            }
        }));
    }
}
