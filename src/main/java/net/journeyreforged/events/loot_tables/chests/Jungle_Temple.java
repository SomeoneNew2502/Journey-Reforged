package net.journeyreforged.events.loot_tables.chests;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registries.ItemRegistry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

public class Jungle_Temple {
    private static final Identifier JUNGLE_TEMPLE = new Identifier("minecraft", "chests/jungle_temple");

    public static void modifyLootTables() {

        //Replaces the Jungle Temple loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (JUNGLE_TEMPLE.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(3.0f, 7.0f))
                    .with(ItemEntry.builder(Items.ROTTEN_FLESH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0f,7.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.BONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 7.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.BAMBOO).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 5.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.VINE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(5).build())
                    .with(ItemEntry.builder(ItemRegistry.PEARL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.GOLD_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.RABBIT_FOOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(EmptyEntry.Serializer().weight(30))
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.INVISIBILITY)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.LEAPING)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.SWIFTNESS)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.HEALING)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.LUCK)).weight(1).build())
                    .build();

                LootPool pool3 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 1.0f))
                    .with(EmptyEntry.Serializer().weight(15))
                    .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.STONE_PICKAXE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.STONE_AXE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.IRON_AXE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.STONE_PICKAXE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 13))).weight(1).build()) //Randomly enchants the item with 1 - 13 levels
                    .with(ItemEntry.builder(Items.STONE_AXE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 13))).weight(1).build())
                    .with(ItemEntry.builder(Items.BOOK).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 13))).weight(1).build())
                    .with(ItemEntry.builder(Items.GOLDEN_APPLE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .build();

                LootPool pool4 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.03f)) //The percentage chance for a roll to succeed
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build()) //Enchants a book with Mending
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).pool(pool3).pool(pool4).build();
            }
            return null;
        });
    }
}
