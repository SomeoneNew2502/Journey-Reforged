package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registry.ItemRegistry;
import net.journeyreforged.util.function.EnchantWithLevelRangeBuilder;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
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
                    .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.AMETHYST_SHARD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(4).build())
                    .with(ItemEntry.builder(ItemRegistry.getItem("pearl")).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.GOLD_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.RABBIT_FOOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 7.0f))).weight(18).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.INVISIBILITY)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.LEAPING)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.SWIFTNESS)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.HEALING)).weight(1).build())
                    .build();

                LootPool pool3 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 1.0f))
                    .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(7).build())
                    .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(7).build())
                    .with(ItemEntry.builder(Items.SADDLE).weight(7).build())
                    .with(ItemEntry.builder(ItemRegistry.getItem("iron_dagger")).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.50f, 1.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE).weight(5).build())
                    .with(ItemEntry.builder(ItemRegistry.getItem("golden_dagger")).apply(new EnchantRandomlyLootFunction.Builder()).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.80f, 1.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.IRON_PICKAXE).apply(new EnchantWithLevelRangeBuilder(Enchantments.EFFICIENCY).setLevelRange(2, 4)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 3)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.IRON_PICKAXE).apply(new EnchantWithLevelRangeBuilder(Enchantments.FORTUNE).setLevelRange(0, 3)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.BOOK).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 13))).weight(1).build())
                    .with(ItemEntry.builder(ItemRegistry.getItem("diamond_dagger")).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.05f, 0.20f))).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND_AXE).apply(new EnchantWithLevelRangeBuilder(Enchantments.EFFICIENCY).setLevelRange(3, 5)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(2, 3)).apply(new EnchantWithLevelRangeBuilder(Enchantments.SILK_TOUCH).setLevelRange(0, 1)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(1).build())
                    .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1).build())
                    .build();

                LootPool pool4 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.03f)) //The percentage chance for a roll to succeed
                    .with(ItemEntry.builder(Items.BOOK)).apply(new EnchantRandomlyLootFunction.Builder().add(Enchantments.MENDING).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).pool(pool3).pool(pool4).build();
            }
            return null;
        });
    }
}
