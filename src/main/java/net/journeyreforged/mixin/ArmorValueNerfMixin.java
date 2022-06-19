package net.journeyreforged.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;

import net.minecraft.item.ArmorMaterials;

@Mixin(ArmorMaterials.class)
public class ArmorValueNerfMixin {
    
    @ModifyConstant(method = "Lnet/minecraft/item/ArmorMaterials;method_7688()Lnet/minecraft/recipe/Ingredient;", constant = @Constant(intValue = 3))
    public int injected(int value) {
        return 1;
    }
}
