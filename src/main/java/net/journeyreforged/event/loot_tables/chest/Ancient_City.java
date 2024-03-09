package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registry.EnchantmentRegistry;
import net.journeyreforged.util.function.EnchantWithLevelRangeBuilder;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class Ancient_City {

    private static final Identifier ANCIENT_CITY = new Identifier("minecraft", "chests/ancient_city");

    public static void modifyLootTables() {

        //Adding a new pool to the Bastion Treasure loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (ANCIENT_CITY.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.20f)) //The percentage chance for a roll to succeed
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantWithLevelRangeBuilder(EnchantmentRegistry.REPOSSESSION).setLevelRange(2, 3).build());
                supplier.pool(poolBuilder.build());
            }
        }));
    }
}