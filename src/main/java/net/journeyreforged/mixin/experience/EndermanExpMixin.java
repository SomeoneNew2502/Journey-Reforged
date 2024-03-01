package net.journeyreforged.mixin.experience;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

@Mixin(EndermanEntity.class)
public abstract class EndermanExpMixin extends MobEntity {

    protected EndermanExpMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Inject(method = "<init>", at = @At("TAIL"))
    public void tick(CallbackInfo info) {
        this.experiencePoints = 9;
    }
}
