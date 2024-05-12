package net.journeyreforged.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.journeyreforged.registry.BlockRegistry;
import net.journeyreforged.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.SHEARABLE_WARPED_STEM)
                .add(BlockRegistry.DETHREADED_WARPED_STEM)
                .add(BlockRegistry.DETHREADED_WARPED_HYPHAE);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(Blocks.OAK_LEAVES)
                .add(Blocks.SPRUCE_LEAVES)
                .add(Blocks.BIRCH_LEAVES)
                .add(Blocks.JUNGLE_LEAVES)
                .add(Blocks.ACACIA_LEAVES)
                .add(Blocks.DARK_OAK_LEAVES)
                .add(Blocks.MANGROVE_LEAVES)
                .add(Blocks.AZALEA_LEAVES)
                .add(Blocks.FLOWERING_AZALEA_LEAVES)
                .add(Blocks.CHERRY_LEAVES);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(BlockRegistry.PEARL_BLOCK)
                .add(BlockRegistry.PRISMARINE_ALLOY_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(BlockRegistry.PEARL_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockRegistry.PRISMARINE_ALLOY_BLOCK);

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(BlockRegistry.PRISMARINE_ALLOY_BLOCK);
        
        getOrCreateTagBuilder(BlockTags.DAMPENS_VIBRATIONS)
                .add(BlockRegistry.WARPED_WEAVE);

        getOrCreateTagBuilder(BlockTags.OCCLUDES_VIBRATION_SIGNALS)
                .add(BlockRegistry.WARPED_WEAVE);

        getOrCreateTagBuilder(BlockTags.WARPED_STEMS)
                .add(BlockRegistry.DETHREADED_WARPED_STEM)
                .add(BlockRegistry.DETHREADED_WARPED_HYPHAE);

        getOrCreateTagBuilder(ModTags.Blocks.NETHER_WOOL)
                .add(BlockRegistry.WARPED_WEAVE);

        getOrCreateTagBuilder(BlockTags.WOOL_CARPETS)
                .add(BlockRegistry.WARPED_CARPET);
    }
}
