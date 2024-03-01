package net.journeyreforged.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

@Mixin(LivingEntity.class)
public abstract class ShieldNerfMixin {

    @Unique private float realvalue;

    //Different ways of trying to change line 1077 to e.g. amount = amount / 2, but nothing is able to target it according to print
    /*@ModifyVariable(method = "damage", name = "amount", index = 2, print = true, at = @At(value = "STORE", ordinal = 1))
    public float shieldNerfStore(float amount, DamageSource source) {
        System.out.println("amount " + amount / 2);
        amount = amount / 2;
        realvalue = amount;
        return amount;
    }*/

    @ModifyVariable(method = "damage", name = "amount", ordinal = 2, print = true, at = @At(value = "STORE", ordinal = 0, opcode = Opcodes.FSTORE),
    slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damageShield(F)V", ordinal = 0),
    to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isProjectile()Z", ordinal = 0)))
    public float shieldNerfStore(float amount, DamageSource source) {
        System.out.println("amount " + amount / 2);
        return amount / 2;
    }

    /*@ModifyVariable(method = "damage", name = "amount", index = 12, print = true, at = @At(value = "LOAD", ordinal = 0))
    public float shieldNerfLoad(float amount) {
        System.out.println("realvalue " + realvalue);
        System.out.print("amount " + amount);
        return realvalue;
    }*/

    //This works; I think...
    //Supposed to set bl to false on line 1081 (bl = trigger(boolean blocked) on line 1160)
    @ModifyVariable(method = "damage", at = @At("STORE"), print = true, ordinal = 2)
    private boolean damageBlocked(boolean value) {
        return false;
    }
}
