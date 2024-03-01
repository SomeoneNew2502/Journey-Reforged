package net.journeyreforged.mixin.experience;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public abstract class ZombieExpMixin extends MobEntity {

    protected ZombieExpMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void tick(CallbackInfo info) {
        int zombieexp = 0;

        //Gets executed only once. Checks if the Entity is a Baby & sets the amount of XP dropped accordingly
        if (zombieexp == 0) {
            if (this.isBaby()) {
                this.experiencePoints = 15;
            } else {
                this.experiencePoints = 7;
            }
            zombieexp++;
        }
    }
}