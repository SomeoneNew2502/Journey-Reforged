package net.journeyreforged.items.gear.armor;

import net.journeyreforged.registries.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class PrismarineArmorMaterial implements ArmorMaterial {
    
    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_AMOUNTS = new int[] {2, 5, 6, 3};
    @Override
    public int getDurability(EquipmentSlot arg0) {
        return BASE_DURABILITY[arg0.getEntitySlotId()]*25;
    }
    @Override
    public int getProtectionAmount(EquipmentSlot arg0) {
        return PROTECTION_AMOUNTS[arg0.getEntitySlotId()];
    }
    @Override
    public int getEnchantability() {
        return 15;
    }
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ItemRegistry.PRISMARINE_INGOT);
    }
    @Override
    public String getName() {
        return "prismarine";
    }
    @Override
    public float getToughness() {
        return 2;
    }
    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
