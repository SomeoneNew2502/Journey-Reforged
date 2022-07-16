package net.journeyreforged.events.loot_tables.chests;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registries.ItemRegistry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class Buried_Treasure {
    
    private static final Identifier BURIED_TREASURE = new Identifier("minecraft", "chests/buried_treasure");

    public static void modifyLootTables() {

        //Adds 2 new pools to the Buried Treasure loottable
        LootTableEvents.MODIFY.register((resourceManager, manager, id, supplier, setter) -> {
            if (BURIED_TREASURE.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.35f)) //The percentage chance for a roll to succeed
                    .with(ItemEntry.builder(ItemRegistry.PEARL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).build());
                supplier.pool(pool.build());

                LootPool.Builder pool2 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.07f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build()); //Enchants a book with Mending
                supplier.pool(pool).pool(pool2).build();
            }
        });
    }
}
