package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.util.function.EnchantWithLevelRangeBuilder;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

public class Nether_Bridge {
    private static final Identifier NETHER_BRIDGE = new Identifier("minecraft", "chests/nether_bridge");

    public static void modifyLootTables() {

        //Replaces the Nether Bridge loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (NETHER_BRIDGE.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(3.0f, 5.0f))
                    .with(ItemEntry.builder(Items.NETHER_WART).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f,9.0f))).weight(9).build())
                    .with(ItemEntry.builder(Items.QUARTZ).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,9.0f))).weight(9).build())
                    .with(ItemEntry.builder(Items.OBSIDIAN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.NETHER_BRICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.ARROW).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f,7.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.ROTTEN_FLESH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.TWISTING_VINES).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.WEEPING_VINES).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.WARPED_FUNGUS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.CRIMSON_FUNGUS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.CRYING_OBSIDIAN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(ItemEntry.builder(Items.GOLD_NUGGET).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f,7.0f))).weight(70).build())
                    .with(ItemEntry.builder(Items.GOLD_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f,4.0f))).weight(30).build())
                    .with(ItemEntry.builder(Items.GOLDEN_HELMET).apply(new EnchantWithLevelRangeBuilder(Enchantments.PROTECTION).setLevelRange(0, 3)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 3)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.GOLDEN_CHESTPLATE).apply(new EnchantWithLevelRangeBuilder(Enchantments.PROTECTION).setLevelRange(0, 3)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 3)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.GOLDEN_LEGGINGS).apply(new EnchantWithLevelRangeBuilder(Enchantments.PROTECTION).setLevelRange(0, 3)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 3)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.GOLDEN_BOOTS).apply(new EnchantWithLevelRangeBuilder(Enchantments.PROTECTION).setLevelRange(0, 3)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 3)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.5f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_HELMET).apply(new EnchantWithLevelRangeBuilder(Enchantments.PROTECTION).setLevelRange(0, 1)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 2)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_CHESTPLATE).apply(new EnchantWithLevelRangeBuilder(Enchantments.PROTECTION).setLevelRange(0, 1)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 2)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_LEGGINGS).apply(new EnchantWithLevelRangeBuilder(Enchantments.PROTECTION).setLevelRange(0, 1)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 2)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_BOOTS).apply(new EnchantWithLevelRangeBuilder(Enchantments.PROTECTION).setLevelRange(0, 1)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 2)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(3).build())
                    .build();

                LootPool pool3 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(EmptyEntry.builder().weight(60))
                    .with(ItemEntry.builder(Items.STONE_SWORD).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.FLINT_AND_STEEL).weight(5).build())
                    .with(ItemEntry.builder(Items.BOW).apply(new EnchantWithLevelRangeBuilder(Enchantments.POWER).setLevelRange(0, 4)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.SHIELD).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 3)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_SWORD).apply(new EnchantWithLevelRangeBuilder(Enchantments.SHARPNESS).setLevelRange(0, 3)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.DIAMOND_SWORD).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.SADDLE).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.FIRE_RESISTANCE)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.REGENERATION)).weight(1).build())
                    .build();

                LootPool pool4 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(ItemEntry.builder(Items.GLOWSTONE_DUST).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f,8.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.BLAZE_ROD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.MAGMA_CREAM).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.ENDER_PEARL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).pool(pool3).pool(pool4).build();
            }
            return null;
        });
    }
}
