package net.journeyreforged.event.loot_tables.entity;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class Elder_Guardian {
    
    private static final Identifier ELDER_GUARDIAN = new Identifier("minecraft", "entities/elder_guardian");

    public static void modifyLootTables() {

        //Adds a new pool to the Elder Guardian loottable
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            if (ELDER_GUARDIAN.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(ItemRegistry.getItem("elder_guardian_scale")).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f))).build());
                supplier.pool(pool.build());
            }
        });
    }
}
