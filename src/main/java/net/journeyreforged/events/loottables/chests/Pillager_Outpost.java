package net.journeyreforged.events.loottables.chests;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetGoatHornSoundLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.tag.InstrumentTags;
import net.minecraft.util.Identifier;

public class Pillager_Outpost {
    private static final Identifier PILLAGER_OUTPOST = new Identifier("minecraft", "chests/pillager_outpost");

    public static void modifyLootTables() {

        //Replaces the Pillager Outpost loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (PILLAGER_OUTPOST.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(3.0f, 7.0f))
                    .with(ItemEntry.builder(Items.DARK_OAK_LOG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(9).build())
                    .with(ItemEntry.builder(Items.WHEAT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(9).build())
                    .with(ItemEntry.builder(Items.ARROW).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(9).build())
                    .with(ItemEntry.builder(Items.STRING).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(9).build())
                    .with(ItemEntry.builder(Items.LAPIS_LAZULI).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f,7.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.REDSTONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f,7.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.PAPER).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.POTATO).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.CARROT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,5.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.CHAIN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.IRON_INGOT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.AMETHYST_SHARD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.TRIPWIRE_HOOK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.GOLDEN_CARROT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,2.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.SKELETON_SKULL).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 5.0f))).weight(10).build())
                    .with(ItemEntry.builder(Items.NAME_TAG).weight(5).build())
                    .with(ItemEntry.builder(Items.SADDLE).weight(5).build())
                    .with(ItemEntry.builder(Items.IRON_HORSE_ARMOR).weight(3).build())
                    .with(ItemEntry.builder(Items.GOLDEN_HORSE_ARMOR).weight(3).build())
                    .with(ItemEntry.builder(Items.DIAMOND_HORSE_ARMOR).weight(3).build())
                    .with(ItemEntry.builder(Items.CROSSBOW).weight(2).build())
                    .with(ItemEntry.builder(Items.CROSSBOW).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 13))).weight(1).build()) //Randomly enchants the item with 1 - 13 levels
                    .with(ItemEntry.builder(Items.BOOK).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 13))).weight(1).build())
                    .build();

                LootPool pool3 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.GOAT_HORN).apply(SetGoatHornSoundLootFunction.builder(InstrumentTags.REGULAR_GOAT_HORNS)))
                    .build();

                LootPool pool4 = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(Items.CANDLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,7.0f))).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).pool(pool3).pool(pool4).build();
            }
            return null;
        });
    }
}
