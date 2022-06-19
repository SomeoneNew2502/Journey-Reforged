package net.journeyreforged.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

@Mixin(LivingEntity.class)
public class ArmorNerfMixin {
	
    /* @Inject(at = @At("HEAD"), method = "getDamageLeft")
	private static void init(CallbackInfoReturnable<Boolean> info) {
		System.out.println("This is working");
	}

	@ModifyVariable(at = @At("STORE"), method = "getDamageLeft", ordinal = 0)
	private float injected(float damage, float armor, float armorToughness) {
		return f = 2.0f + armorToughness / 4.0f;
	}

	@ModifyConstant(method = "getDamageLeft", constant = @Constant(floatValue = 4.0f))
	private float injected(float value) {
		return 4.0f;
	} */

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(FFF)F"), method = "applyArmorToDamage")
	public float getDamageLeft(float damage, float armor, float armorToughness) {

		/* float f = 2.0f + armorToughness / 4.0f;

		System.out.println("Armor Toughness: " + armorToughness);
		System.out.println("f: " + f);
		System.out.println("Armor: " + armor);
		System.out.println("Damage: " + damage);

        float g = MathHelper.clamp(armor - (2 * damage) / f, armor * 0.2f, 20.0f);

		System.out.println("g: " + g);

		damage = damage * (1.0f - g / 50.0f);

		System.out.println("return: " + damage);
		
		return damage; */
        // return damage * (1.0f - g / 50.0f);

		damage = damage * (1 - armor / 50 + (4 * damage) / (200 + 25 * armorToughness));
		
		return damage;
	}
}
