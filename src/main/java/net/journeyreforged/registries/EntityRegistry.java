package net.journeyreforged.registries;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.journeyreforged.journeyreforged;
import net.journeyreforged.entities.DiamondDaggerEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {

    public static void init() {
    }
    
    public static final EntityType<DiamondDaggerEntity> DiamondDaggerEntityType = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(journeyreforged.MODID, "diamond_dagger"),
			FabricEntityTypeBuilder.<DiamondDaggerEntity>create(SpawnGroup.MISC, DiamondDaggerEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
					.trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
					.build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
	);
}
