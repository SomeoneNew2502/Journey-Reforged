package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class Underwater_Ruins {
    
    private static final Identifier UNDERWATER_RUIN_BIG = new Identifier("minecraft", "chests/underwater_ruin_big");
    private static final Identifier UNDERWATER_RUIN_SMALL = new Identifier("minecraft", "chests/underwater_ruin_small");

    public static void modifyLootTables() {

        //Adding a new pool to the Underwater Ruin Big loottable
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            if (UNDERWATER_RUIN_BIG.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.35f)) //The percentage chance for a roll to succeed
                    .with(ItemEntry.builder(ItemRegistry.getItem("pearl")).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).build())
                    .with(ItemEntry.builder(Items.PRISMARINE_SHARD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).build())
                    .build();
                    supplier.pool(pool).build();

            }
        });

        //Adding a new pool to the Underwater Ruin Small loottable
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            if (UNDERWATER_RUIN_SMALL.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.10f))
                    .with(ItemEntry.builder(ItemRegistry.getItem("pearl")).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).build())
                    .build();
                    supplier.pool(pool).build();
            }
        });
    }
}
