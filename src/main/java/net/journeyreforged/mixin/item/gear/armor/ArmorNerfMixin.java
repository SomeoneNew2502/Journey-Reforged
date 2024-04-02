package net.journeyreforged.mixin.item.gear.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorMaterials.class)
public abstract class ArmorNerfMixin {

    @Shadow @Final private String name;

    @Inject(method = "getProtection(Lnet/minecraft/item/ArmorItem$Type;)I", at = @At("RETURN"), cancellable = true)
    private void modifyArmorValues(ArmorItem.Type type, CallbackInfoReturnable<Integer> cir) {
        int newValue = cir.getReturnValue();
        switch (this.name) {
            case "gold":
                newValue = switch (type) {
                    case HELMET, BOOTS -> 1;
                    case CHESTPLATE -> 3;
                    case LEGGINGS -> 2;
                };
                break;
            case "chainmail":
                newValue = switch (type) {
                    case HELMET -> 2;
                    case CHESTPLATE, LEGGINGS -> 3;
                    case BOOTS -> 1;
                };
                break;
            case "iron":
                newValue = switch (type) {
                    case HELMET, LEGGINGS -> 3;
                    case CHESTPLATE -> 4;
                    case BOOTS -> 2;
                };
                break;

            case "diamond":
                newValue = switch (type) {
                    case HELMET, BOOTS -> 3;
                    case CHESTPLATE -> 5;
                    case LEGGINGS -> 4;
                };
                break;
        }
        cir.setReturnValue(newValue);
    }
}
