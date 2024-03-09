package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registry.ItemRegistry;
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
                    .conditionally(RandomChanceLootCondition.builder(0.75f)) //The percentage chance for a roll to succeed
                    .with(ItemEntry.builder(ItemRegistry.getItem("pearl")).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(9).build())
                    .with(ItemEntry.builder(ItemRegistry.getItem("prismarine_nugget")).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(1).build());


                LootPool.Builder pool2 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.07f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build()); //Enchants a book with Mending
                supplier.pool(pool).pool(pool2).build();
            }
        });
    }
}
