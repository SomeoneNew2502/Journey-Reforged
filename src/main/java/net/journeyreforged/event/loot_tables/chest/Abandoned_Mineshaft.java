package net.journeyreforged.event.loot_tables.chest;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

public class Abandoned_Mineshaft {
    private static final Identifier ABANDONED_MINESHAFT = new Identifier("minecraft", "chests/abandoned_mineshaft");

    public static void modifyLootTables() {

        //Replaces the Abandoned Mineshaft loottable with a new one
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (ABANDONED_MINESHAFT.equals(id)) {
                LootPool pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(3.0f, 6.0f))
                    .with(ItemEntry.builder(Items.STRING).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f,11.0f))).weight(7).build())
                    .with(ItemEntry.builder(Items.TORCH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.REDSTONE_TORCH).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.GLOW_BERRIES).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 6.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.BREAD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 6.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.REDSTONE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.LAPIS_LAZULI).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f))).weight(6).build())
                    .with(ItemEntry.builder(Items.COAL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 6.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.RAW_IRON).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.RAW_GOLD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.TNT).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.MELON_SEEDS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.PUMPKIN_SEEDS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.BEETROOT_SEEDS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.GLOW_INK_SAC).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.GLOW_LICHEN).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.NAME_TAG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))).weight(2).build())
                    .with(ItemEntry.builder(Items.GOLDEN_APPLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))).weight(2).build())
                    .build();
                
                LootPool pool2 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 3.0f))
                    .with(ItemEntry.builder(Items.RAIL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 8.0f))).weight(18).build())
                    .with(ItemEntry.builder(Items.POWERED_RAIL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.DETECTOR_RAIL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.ACTIVATOR_RAIL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 8.0f))).weight(3).build())
                    .with(ItemEntry.builder(Items.MINECART).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.CHEST_MINECART).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.FURNACE_MINECART).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.TNT_MINECART).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.HOPPER_MINECART).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .build();

                LootPool pool3 = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0f, 2.0f))
                    .with(ItemEntry.builder(Items.DIAMOND).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(20).build())
                    .with(ItemEntry.builder(Items.EMERALD).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))).weight(20).build())
                    .with(ItemEntry.builder(Items.BOOK).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 13))).weight(15).build()) //Randomly enchants the item with 1 - 13 levels
                    .with(ItemEntry.builder(Items.IRON_PICKAXE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.IRON_SHOVEL).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(5).build())
                    .with(ItemEntry.builder(Items.IRON_PICKAXE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 21))).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.IRON_SHOVEL).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(1, 21))).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.25f, 1.0f))).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.NIGHT_VISION)).weight(1).build())
                    .with(ItemEntry.builder(Items.POTION).apply(SetPotionLootFunction.builder(Potions.HEALING)).weight(1).build())
                    .with(ItemEntry.builder(Items.BOOK).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).apply(new EnchantWithLevelsLootFunction.Builder(UniformLootNumberProvider.create(13, 30))).weight(1).build())
                    .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f))).weight(1).build())
                    .build();

                return LootTable.builder().pool(pool).pool(pool2).pool(pool3).build();
            }
            return null;
        });
    }
}
