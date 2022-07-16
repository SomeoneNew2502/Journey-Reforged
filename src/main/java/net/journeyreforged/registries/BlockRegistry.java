package net.journeyreforged.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.journeyreforged.journeyreforged;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
    
    public static final Block PEARL_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f).resistance(6.0f));

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier(journeyreforged.MODID, "pearl_block"), PEARL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "pearl_block"), new BlockItem(PEARL_BLOCK, new FabricItemSettings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP)));
    }
}
