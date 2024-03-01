package net.journeyreforged.damagesource;

// A simplified custom damage source example


import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class daggerDamage {
    public static final RegistryKey<DamageType> dagger_projectile = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("journeyreforged", "dagger_projectile"));

    public static DamageSource of(World world, RegistryKey<DamageType> key, Entity owner) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key), owner);
    }
}
