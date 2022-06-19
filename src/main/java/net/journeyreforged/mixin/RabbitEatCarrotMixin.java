package net.journeyreforged.mixin;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//Specifies that the target location is inside the static EatCarrotCropGoal class
@Mixin(targets = "net/minecraft/entity/passive/RabbitEntity$EatCarrotCropGoal")
public abstract class RabbitEatCarrotMixin extends AnimalEntity {

    protected RabbitEatCarrotMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(CallbackInfo ci) {
        //this.lovePlayer(player);
        System.out.println("This is working");
    }
}
