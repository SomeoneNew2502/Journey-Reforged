package net.journeyreforged;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.journeyreforged.registries.BlockRegistry;
import net.journeyreforged.registries.EntityRegistry;
import net.journeyreforged.registries.ItemRegistry;
import net.journeyreforged.registries.LootTableRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class journeyreforged implements ModInitializer {

    public static final String MODID = "journeyreforged";

    //Test
    //Creates a custom Creative Tab with a Diamond Dagger icon
    public static final ItemGroup JOURNEY_REFORGED_ITEM_GROUP = FabricItemGroupBuilder.create(
        new Identifier(MODID, "journeyreforged"))
            .icon(() -> new ItemStack(ItemRegistry.PRISMARINE_PICKAXE))
            .build();


    @Override
    public void onInitialize() {
        ItemRegistry.init();
        BlockRegistry.init();
        EntityRegistry.init();
        LootTableRegistry.init();
    }
}