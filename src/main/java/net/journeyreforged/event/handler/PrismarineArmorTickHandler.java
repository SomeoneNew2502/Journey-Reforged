package net.journeyreforged.event.handler;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class PrismarineArmorTickHandler {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerWorld world : server.getWorlds()) {
                for (PlayerEntity player : world.getPlayers()) {
                    net.journeyreforged.registry.ArmorEffects.updatePlayerAttributes(player);
                }
            }
        });
    }
}

