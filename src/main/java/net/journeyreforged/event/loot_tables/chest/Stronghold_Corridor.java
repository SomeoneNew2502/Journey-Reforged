package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

public class Stronghold_Corridor {
    private static final Identifier STRONGHOLD_CORRIDOR = new Identifier("minecraft", "chests/stronghold_corridor");

    public static void modifyLootTables() {

        //Replaces the Stronghold Corridor loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (STRONGHOLD_CORRIDOR.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(3.0f, 7.0f))
                    .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f,9.0f))).weight(20).build())
                    .with(ItemEntry.builder(Items.ARROW).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(10).build())
                    .with(ItemEntry.builder(Items.BREAD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.APPLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.ENDER_PEARL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,4.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.BLAZE_POWDER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(2).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.BOW).weight(5).build())
                    .with(ItemEntry.builder(Items.CROSSBOW).weight(5).build())
                    .with(ItemEntry.builder(Items.SHIELD).weight(5).build())
                    .with(ItemEntry.builder(Items.DIAMOND_SWORD).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND_PICKAXE).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(1).build())
                    .build();

                LootPool pool3 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.STRONG_HEALING)).weight(5).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.STRONG_REGENERATION)).weight(5).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.STRONG_STRENGTH)).weight(5).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.LONG_SLOW_FALLING)).weight(1).build())
                    .build();

                LootPool pool4 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.IRON_HELMET).apply(EnchantRandomlyLootFunction.builder()).weight(10).build())
                    .with(ItemEntry.builder(Items.IRON_CHESTPLATE).apply(EnchantRandomlyLootFunction.builder()).weight(10).build())
                    .with(ItemEntry.builder(Items.IRON_LEGGINGS).apply(EnchantRandomlyLootFunction.builder()).weight(10).build())
                    .with(ItemEntry.builder(Items.IRON_BOOTS).apply(EnchantRandomlyLootFunction.builder()).weight(10).build())
                    .with(ItemEntry.builder(Items.DIAMOND_HELMET).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND_CHESTPLATE).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND_LEGGINGS).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND_BOOTS).weight(1).build())
                    .build();

                LootPool pool5 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.BOOK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(75).build())
                    .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).weight(10).build())
                    .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder()).weight(1).build())
                    .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).pool(pool3).pool(pool4).pool(pool5).build();
            }
            return null;
        });
    }
}
