package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetInstrumentLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.InstrumentTags;
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
                    .with(ItemEntry.builder(Items.ROTTEN_FLESH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.TWISTING_VINES).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.WEEPING_VINES).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.WARPED_FUNGUS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.CRIMSON_FUNGUS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.CRYING_OBSIDIAN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(EmptyEntry.builder().weight(75))
                    .with(ItemEntry.builder(Items.IRON_HELMET).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_CHESTPLATE).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_LEGGINGS).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_BOOTS).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_HELMET).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 7))).weight(1).build()) //Randomly enchants the item with 1 - 7 levels
                    .with(ItemEntry.builder(Items.IRON_CHESTPLATE).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 7))).weight(1).build())
                    .with(ItemEntry.builder(Items.IRON_LEGGINGS).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 7))).weight(1).build())
                    .with(ItemEntry.builder(Items.IRON_BOOTS).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 7))).weight(1).build())
                    .build();

                LootPool pool3 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(EmptyEntry.builder().weight(50))
                    .with(ItemEntry.builder(Items.GOAT_HORN).apply(SetInstrumentLootFunction.builder(InstrumentTags.GOAT_HORNS)))
                    .with(ItemEntry.builder(Items.STONE_SWORD).weight(5).build())
                    .with(ItemEntry.builder(Items.FLINT_AND_STEEL).weight(5).build())
                    .with(ItemEntry.builder(Items.BOW).weight(4).build())
                    .with(ItemEntry.builder(Items.SHIELD).weight(3).build())
                    .with(ItemEntry.builder(Items.IRON_SWORD).weight(2).build())
                    .with(ItemEntry.builder(Items.DIAMOND_SWORD).weight(1).build())
                    .with(ItemEntry.builder(Items.SADDLE).weight(1).build())
                    .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.FIRE_RESISTANCE)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.REGENERATION)).weight(1).build())
                    .build();

                LootPool pool4 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(ItemEntry.builder(Items.GLOWSTONE_DUST).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f,8.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.BLAZE_ROD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.ROTTEN_FLESH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.MAGMA_CREAM).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.ENDER_PEARL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(7).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).pool(pool3).pool(pool4).build();
            }
            return null;
        });
    }
}
