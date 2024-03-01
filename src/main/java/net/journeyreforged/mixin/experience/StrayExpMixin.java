package net.journeyreforged.mixin.experience;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(StrayEntity.class)
public abstract class StrayExpMixin extends MobEntity {

    protected StrayExpMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Inject(method = "<init>", at = @At("TAIL"))
    public void tick(CallbackInfo info) {
        this.experiencePoints = 7;
    }
}
