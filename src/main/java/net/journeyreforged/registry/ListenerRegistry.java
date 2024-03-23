package net.journeyreforged.registry;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.journeyreforged.event.handler.PrismarineArmorTickHandler;

public class ListenerRegistry {

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> PrismarineArmorTickHandler.register());
    }
}
