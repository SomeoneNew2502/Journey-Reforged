package net.journeyreforged.event.loot_tables.chest.village;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class Village_Desert_House {
    
    private static final Identifier VILLAGE_DESERT_HOUSE = new Identifier("minecraft", "chests/village/village_desert_house");

    public static void modifyLootTables() {

        //Adds a new pool to the Village Desert House loottable
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            if (VILLAGE_DESERT_HOUSE.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.20f)) //The percentage chance for a roll to succeed
                    .with(ItemEntry.builder(Items.NAME_TAG).build());
                supplier.pool(pool.build());
            }
        });
    }
}
