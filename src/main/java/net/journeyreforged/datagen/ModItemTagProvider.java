package net.journeyreforged.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.journeyreforged.registry.BlockRegistry;
import net.journeyreforged.registry.EntityRegistry;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.registry.RegistryWrapper;
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
    }
}