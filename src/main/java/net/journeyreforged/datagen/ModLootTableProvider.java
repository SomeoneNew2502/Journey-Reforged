package net.journeyreforged.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.journeyreforged.registry.BlockRegistry;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(BlockRegistry.PEARL_BLOCK);
        addDrop(BlockRegistry.PRISMARINE_ALLOY_BLOCK);
        addDrop(BlockRegistry.DETHREADED_WARPED_STEM);
        addDrop(BlockRegistry.WARPED_WEAVE);
    }
}
