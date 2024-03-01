package net.journeyreforged.mixin.item.gear.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorItem.class)
public class ArmorNerfMixin {

    @Inject(method = "getProtection", at = @At("HEAD"), cancellable = true)
    private void modifyArmorProtection(CallbackInfoReturnable<Integer> cir) {
        ArmorItem item = ((ArmorItem) (Object) this);
        ArmorMaterial material = item.getMaterial();
        EquipmentSlot slot = item.getSlotType();
        System.out.println("something");
        if (material == ArmorMaterials.LEATHER) {
            switch (slot) {
                case HEAD:
                    System.out.println("Leather1");
                    cir.setReturnValue(1); // Leather Helmet: 1 armor point
                    break;
                case CHEST:
                    cir.setReturnValue(2); // Leather Chestplate: 2 armor points
                    break;
                case LEGS:
                    cir.setReturnValue(8); // Leather Leggings: 1 armor point
                    break;
                case FEET:
                    cir.setReturnValue(1); // Leather Boots: 1 armor point
                    break;
            }
        } else if (material == ArmorMaterials.GOLD) {
            switch (slot) {
                case HEAD:
                case FEET:
                    System.out.println("Gold1");
                    cir.setReturnValue(1); // Gold Helmet/Boots: 1 armor point
                    break;
                case CHEST:
                case LEGS:
                    cir.setReturnValue(2); // Gold Chestplate/Leggings: 2 armor points
                    break;
            }
        } else if (material == ArmorMaterials.CHAIN) {
            switch (slot) {
                case HEAD:
                case FEET:
                    cir.setReturnValue(1); // Chain Helmet/Boots: 1 armor point
                    break;
                case CHEST:
                case LEGS:
                    cir.setReturnValue(3); // Chain Chestplate/Leggings: 3 armor points
                    break;
            }
        } else if (material == ArmorMaterials.IRON) {
            switch (slot) {
                case HEAD:
                case FEET:
                    System.out.println("Iron1");
                    cir.setReturnValue(2); // Iron Helmet/Boots: 2 armor points
                    break;
                case CHEST:
                case LEGS:
                    cir.setReturnValue(3); // Iron Chestplate/Leggings: 3 armor points
                    break;
            }
        } else if (material == ArmorMaterials.DIAMOND) {
            switch (slot) {
                case HEAD:
                case FEET:
                    cir.setReturnValue(3); // Diamond Helmet/Boots: 3 armor points
                    break;
                case CHEST:
                    cir.setReturnValue(5); // Diamond Chestplate: 5 armor points
                    break;
                case LEGS:
                    cir.setReturnValue(4); // Diamond Leggings: 4 armor points
                    break;
            }
        } else if (material == ArmorMaterials.NETHERITE) {
            // For simplicity, this doesn't adjust toughness. A separate injection may be needed for that.
            switch (slot) {
                case HEAD:
                case FEET:
                    cir.setReturnValue(3); // Netherite Helmet/Boots: 3 armor points
                    break;
                case CHEST:
                    cir.setReturnValue(8); // Netherite Chestplate: 8 armor points
                    break;
                case LEGS:
                    System.out.println("Netherite1");
                    cir.setReturnValue(6); // Netherite Leggings: 6 armor points
                    break;
            }
        }
    }
}
