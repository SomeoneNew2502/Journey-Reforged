package net.journeyreforged.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.journeyreforged.JourneyReforged;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlockRegistry {

    public static void init() {
    }
    public static final Block PEARL_BLOCK = register("pearl_block", new Block(FabricBlockSettings.create()
            .hardness(1.5f)
            .resistance(6.0f)
            .sounds(BlockSoundGroup.STONE)));

    public static final Block PRISMARINE_ALLOY_BLOCK = register("prismarine_alloy_block", new Block(FabricBlockSettings.create()
            .hardness(5.0f)
            .resistance(6.0f)
            .sounds(BlockSoundGroup.NETHERITE)));

    public static final Block DETHREADED_WARPED_STEM = register("dethreaded_warped_stem", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WARPED_STEM)));

    public static final Block WARPED_WEAVE = register("warped_weave", new WarpedWeaveBlock(FabricBlockSettings.create()
            .hardness(0.8f)
            .resistance(0.8f)
            .sounds(BlockSoundGroup.WOOL)));

    private static Block register(String identifier, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(JourneyReforged.MODID, identifier), block);
    }
}
