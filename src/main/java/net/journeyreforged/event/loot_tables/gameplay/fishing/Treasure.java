package net.journeyreforged.event.loot_tables.gameplay.fishing;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;

public class Treasure {
    private static final Identifier FISHING_TREASURE = new Identifier("minecraft", "gameplay/fishing/treasure");

    public static void modifyLootTables() {

        //Adds a new pool to the Shipwreck Treasure loottable
        LootTableEvents.MODIFY.register(((ResourceManager, manager, id, supplier, setter) -> {
            if (FISHING_TREASURE.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(ItemEntry.builder(Items.NAME_TAG))
                        .with(ItemEntry.builder(Items.SADDLE))
                        .with(ItemEntry.builder(ItemRegistry.getItem("pearl")).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f))))
                        .with(ItemEntry.builder(Items.BOW)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.0f, 0.25f)))
                                .apply(EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30)).allowTreasureEnchantments()))
                        .with(ItemEntry.builder(Items.FISHING_ROD)
                                .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.0f, 0.25f)))
                                .apply(EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30)).allowTreasureEnchantments()))
                        .with(ItemEntry.builder(Items.BOOK)
                                .apply(EnchantWithLevelsLootFunction.builder(ConstantLootNumberProvider.create(30)).allowTreasureEnchantments()))
                        .with(ItemEntry.builder(Items.NAUTILUS_SHELL));

                supplier.pool(poolBuilder);
            }
        }));
    }
}
