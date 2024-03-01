package net.journeyreforged.mixin.experience;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonExpMixin extends MobEntity {

    protected WitherSkeletonExpMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Inject(method = "<init>", at = @At("TAIL"))
    public void tick(CallbackInfo info) {
        this.experiencePoints = 10;
    }
}
