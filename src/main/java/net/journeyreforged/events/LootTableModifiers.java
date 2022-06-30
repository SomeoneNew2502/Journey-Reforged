package net.journeyreforged.events;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableModifiers {

    private static final Identifier BASTION_TREASURE = new Identifier("minecraft", "chests/bastion_treasure");
    private static final Identifier BURIED_TREASURE = new Identifier("minecraft", "chests/buried_treasure");
    private static final Identifier DESERT_PYRAMID = new Identifier("minecraft", "chests/desert_pyramid");
    private static final Identifier END_CITY_TREASURE = new Identifier("minecraft", "chests/end_city_treasure");
    private static final Identifier JUNGLE_TEMPLE = new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier STRONGHOLD_LIBRARY = new Identifier("minecraft", "chests/stronghold_library");
    private static final Identifier WOODLAND_MANSION = new Identifier("minecraft", "chests/woodland_mansion");

    public static void modifyLootTables() {

        //Adding a pool to Bastion Treasure loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (BASTION_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.20f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build());
                supplier.pool(poolBuilder.build());
            }
        }));

        //Adding a pool to Buried Treasure loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (BURIED_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.07f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build());
                supplier.pool(poolBuilder.build());
            }
        }));

        //Adding a pool to Desert Pyramid loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (DESERT_PYRAMID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.04f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build());
                supplier.pool(poolBuilder.build());
            }
        }));

        //Adding a pool to End City Treasure loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (END_CITY_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.15f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build());
                supplier.pool(poolBuilder.build());
            }
        }));

        //Adding a pool to Jungle Temple loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (JUNGLE_TEMPLE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.05f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build());
                supplier.pool(poolBuilder.build());
            }
        }));

        //Adding a pool to Stronghold Library loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (STRONGHOLD_LIBRARY.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.20f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build());
                supplier.pool(poolBuilder.build());
            }
        }));

        //Adding a pool to Woodland Mansion loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (WOODLAND_MANSION.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.15f))
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build());
                supplier.pool(poolBuilder.build());
            }
        }));
    }
}