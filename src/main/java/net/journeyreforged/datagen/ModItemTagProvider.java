package net.journeyreforged.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.journeyreforged.registry.BlockRegistry;
import net.journeyreforged.registry.EntityRegistry;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ItemRegistry.getItem("prismarine_ingot"));

        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ItemRegistry.getItem("prismarine_upgrade_smithing_template"));

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(BlockRegistry.DETHREADED_WARPED_STEM.asItem());

        getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS)
                .add(Blocks.STONE.asItem())
                .add(Blocks.SANDSTONE.asItem())
                .add(Blocks.ANDESITE.asItem())
                .add(Blocks.SANDSTONE.asItem())
                .add(Blocks.DIORITE.asItem())
                .add(Blocks.GRANITE.asItem())
                .add(Blocks.CALCITE.asItem())
                .add(Blocks.BASALT.asItem())
                .add(Blocks.SMOOTH_BASALT.asItem())
                .add(Blocks.DEEPSLATE.asItem())

                //Don't mind me
                .add(Blocks.TERRACOTTA.asItem())
                .add(Blocks.WHITE_TERRACOTTA.asItem())
                .add(Blocks.LIGHT_GRAY_TERRACOTTA.asItem())
                .add(Blocks.GRAY_TERRACOTTA.asItem())
                .add(Blocks.BLACK_TERRACOTTA.asItem())
                .add(Blocks.BROWN_TERRACOTTA.asItem())
                .add(Blocks.RED_TERRACOTTA.asItem())
                .add(Blocks.ORANGE_TERRACOTTA.asItem())
                .add(Blocks.YELLOW_TERRACOTTA.asItem())
                .add(Blocks.LIME_TERRACOTTA.asItem())
                .add(Blocks.GREEN_TERRACOTTA.asItem())
                .add(Blocks.CYAN_TERRACOTTA.asItem())
                .add(Blocks.LIGHT_BLUE_TERRACOTTA.asItem())
                .add(Blocks.BLUE_TERRACOTTA.asItem())
                .add(Blocks.PURPLE_TERRACOTTA.asItem())
                .add(Blocks.MAGENTA_TERRACOTTA.asItem())
                .add(Blocks.PINK_TERRACOTTA.asItem());
    }
}