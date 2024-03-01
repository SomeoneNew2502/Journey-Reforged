package net.journeyreforged.worldgen.biome.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class SimpleDungeon {
    public static void init() {
        Identifier deepModificationId = new Identifier("journeyreforged", "remove_monster_room_deep");
        Identifier modificationId = new Identifier("journeyreforged", "remove_monster_room");
        RegistryKey<PlacedFeature> deepMonsterRoomKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("minecraft", "monster_room_deep"));
        RegistryKey<PlacedFeature> monsterRoomKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("minecraft", "monster_room"));

        BiomeModifications.create(deepModificationId)
                .add(ModificationPhase.REMOVALS,
                        context -> true, // This applies the modification to all biomes; adjust as needed
                        context -> {
                            context.getGenerationSettings().removeFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, deepMonsterRoomKey);
                        }
                );
        /*BiomeModifications.create(deepModificationId)
                .add(ModificationPhase.REMOVALS,
                        context -> true, // This applies the modification to all biomes; adjust as needed
                        context -> {
                            context.getGenerationSettings().removeFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, deepMonsterRoomKey);
                        }
                );*/
    }
}
