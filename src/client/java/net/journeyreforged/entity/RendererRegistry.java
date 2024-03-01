package net.journeyreforged.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.journeyreforged.JourneyReforged;
import net.journeyreforged.entity.renderer.gear.dagger.*;
import net.journeyreforged.item.gear.dagger.ThrowableDaggerEntity;
import net.journeyreforged.registry.EntityRegistry;
import net.minecraft.util.Identifier;

public class RendererRegistry {
    public static void register() {
        EntityRendererRegistry.register(EntityRegistry.THROWABLE_DAGGER, ThrowableDaggerRenderer::new);
    }
}