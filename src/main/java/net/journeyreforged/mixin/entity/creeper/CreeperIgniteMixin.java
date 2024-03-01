package net.journeyreforged.mixin.entity.creeper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CreeperEntity.class)
public abstract class CreeperIgniteMixin extends HostileEntity {

    protected CreeperIgniteMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract void ignite();

    @Override
    public boolean damage(DamageSource source, float amount) {
        // Checking if the damage source is of the explosion type
        if ("explosion.player".equals(source.getName())) {
            this.ignite();
            return false; // Prevent further damage processing if it's an explosion
        } else {
            return super.damage(source, amount);
        }
    }
}
