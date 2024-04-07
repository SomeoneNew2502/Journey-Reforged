package net.journeyreforged.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.journeyreforged.registry.BlockRegistry;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.PEARL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.PRISMARINE_ALLOY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistry.WARPED_WEAVE);
        blockStateModelGenerator.registerLog(BlockRegistry.DETHREADED_WARPED_STEM).log(BlockRegistry.DETHREADED_WARPED_STEM);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegistry.getItem("wooden_dagger"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("stone_dagger"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("golden_dagger"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("iron_dagger"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("diamond_dagger"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("netherite_dagger"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_dagger"), Models.HANDHELD);

        itemModelGenerator.register(ItemRegistry.getItem("prismarine_nugget"), Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_ingot"), Models.GENERATED);

        itemModelGenerator.register(ItemRegistry.getItem("prismarine_sword"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_pickaxe"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_axe"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_shovel"), Models.HANDHELD);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_hoe"), Models.HANDHELD);

        itemModelGenerator.register(ItemRegistry.getItem("prismarine_helmet"), Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_chestplate"), Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_leggings"), Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_boots"), Models.GENERATED);

        itemModelGenerator.register(ItemRegistry.getItem("pearl"), Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.getItem("warped_thread"), Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.getItem("elder_guardian_scale"), Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.getItem("prismarine_upgrade_smithing_template"), Models.GENERATED);


    }
}
