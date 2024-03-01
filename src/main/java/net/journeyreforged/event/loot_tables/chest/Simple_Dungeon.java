package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.journeyreforged.registry.EnchantmentRegistry;
import net.journeyreforged.util.function.EnchantWithLevelRangeBuilder;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

public class Simple_Dungeon {
    private static final Identifier SIMPLE_DUNGEON = new Identifier("minecraft", "chests/simple_dungeon");

    public static void modifyLootTables() {

        //Replaces the Woodland Mansion loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (SIMPLE_DUNGEON.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(6.0f, 9.0f))
                    .with(ItemEntry.builder(Items.BONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.GUNPOWDER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.ROTTEN_FLESH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.STRING).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.WHEAT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,4.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.BREAD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.COAL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f,5.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.REDSTONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f,5.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.GLOW_LICHEN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f,5.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.GOLD_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.BUCKET).weight(2).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(ItemEntry.builder(Items.MELON_SEEDS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(12).build())
                    .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(12).build())
                    .with(ItemEntry.builder(Items.BEETROOT_SEEDS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(12).build())
                    .with(ItemEntry.builder(Items.COCOA_BEANS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.SLIME_BALL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(5).build())
                    .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(4).build())
                    .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(4).build())
                    .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(3).build())
                    .with(ItemEntry.builder(Items.BOOK).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 13))).weight(3).build())
                    .with(ItemEntry.builder(Items.MUSIC_DISC_13).weight(3).build())
                    .with(ItemEntry.builder(Items.MUSIC_DISC_CAT).weight(3).build())
                    .with(ItemEntry.builder(Items.NAME_TAG).weight(3).build())
                    .with(ItemEntry.builder(Items.SADDLE).weight(3).build())
                    .with(ItemEntry.builder(Items.MUSIC_DISC_OTHERSIDE).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_SHOVEL).apply(new EnchantWithLevelRangeBuilder(Enchantments.EFFICIENCY).setLevelRange(0, 3)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 1)).apply(new EnchantWithLevelRangeBuilder(Enchantments.SILK_TOUCH).setLevelRange(0, 1)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_PICKAXE).apply(new EnchantWithLevelRangeBuilder(Enchantments.EFFICIENCY).setLevelRange(0, 3)).apply(new EnchantWithLevelRangeBuilder(Enchantments.UNBREAKING).setLevelRange(0, 1)).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.GOLDEN_APPLE).weight(3).build())
                    .with(ItemEntry.builder(Items.SKELETON_SKULL).weight(1).build())
                    .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1).build())
                    .build();


                return LootTable.builder().pool(pool).pool(pool2).build();
            }
            return null;
        });
    }
}
