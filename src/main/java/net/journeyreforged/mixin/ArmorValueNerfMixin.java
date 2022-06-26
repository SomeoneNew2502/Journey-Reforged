package net.journeyreforged.mixin;

import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorMaterials.class)
public abstract class ArmorValueNerfMixin {

    @Shadow @Final private String name;

    @Inject(method = "getProtectionAmount", at = @At("RETURN"), cancellable = true)
    void modifyArmorValues(EquipmentSlot slot, CallbackInfoReturnable<Integer> info) {
        if (this.name == "leather") {
            info.setReturnValue(new int[]{1, 1, 2, 1}[slot.getEntitySlotId()]);
        } else if (this.name == "chainmail") {
            info.setReturnValue(new int[]{1, 3, 3, 1}[slot.getEntitySlotId()]);
        } else if (this.name == "iron") {
            info.setReturnValue(new int[]{2, 3, 3, 2}[slot.getEntitySlotId()]);
        } else if (this.name == "gold") {
            info.setReturnValue(new int[]{1, 2, 2, 1}[slot.getEntitySlotId()]);
        } else if (this.name == "diamond") {
            info.setReturnValue(new int[]{3, 4, 5, 3}[slot.getEntitySlotId()]);
        } else if (this.name == "netherite") {
            info.setReturnValue(new int[]{3, 6, 8, 3}[slot.getEntitySlotId()]);
        } else if (this.name == "turtle") {
            info.setReturnValue(new int[]{2, 3, 3, 2}[slot.getEntitySlotId()]);
        }
    }
}
