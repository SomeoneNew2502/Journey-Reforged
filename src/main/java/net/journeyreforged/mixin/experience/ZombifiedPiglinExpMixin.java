package net.journeyreforged.mixin.experience;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(ZombifiedPiglinEntity.class)
public abstract class ZombifiedPiglinExpMixin extends MobEntity {

    protected ZombifiedPiglinExpMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Inject(method = "mobTick", at = @At("TAIL"))
    public void tick(CallbackInfo info) {
        int piglinexp = 0;
        
        //Gets executed only once. Checks if the Entity is a Baby & sets the amount of XP dropped accordingly
        if (piglinexp == 0) {
            if (this.isBaby()) {
                this.experiencePoints = 15;
            } else {
                this.experiencePoints = 7;
            }
            piglinexp++;
        }
    }
}