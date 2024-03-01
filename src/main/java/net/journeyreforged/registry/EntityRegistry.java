package net.journeyreforged.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.journeyreforged.item.gear.dagger.*;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityRegistry {
    public static EntityType<ThrowableDaggerEntity> THROWABLE_DAGGER;

    public static void init() {
        THROWABLE_DAGGER = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier("journeyreforged", "throwable_dagger"),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, ThrowableDaggerEntity::new)
                        .dimensions(EntityDimensions.fixed(0.1F, 0.1F))
                        .trackRangeChunks(4)
                        .trackedUpdateRate(10)
                        .build()
        );
    }
}