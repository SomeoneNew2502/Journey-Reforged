package net.journeyreforged.mixin.entity.creeper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreeperEntity.class)
public abstract class CreeperExplosionMixin {

    @Redirect(method = "explode()V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFFLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"))
    private Explosion onCreeperExplode(World instance, Entity entity, double x, double y, double z, float power, World.ExplosionSourceType explosionSourceType) {
        boolean createFire = entity.isOnFire();
        // Assuming there's a method variant that allows specifying `createFire` in some way, or implement your own logic to handle fire creation.
        return instance.createExplosion(entity, x, y, z, power, createFire, explosionSourceType);
    }
}

