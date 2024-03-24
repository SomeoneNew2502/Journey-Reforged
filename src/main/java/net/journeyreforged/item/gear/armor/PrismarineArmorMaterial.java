package net.journeyreforged.item.gear.armor;

import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class PrismarineArmorMaterial implements ArmorMaterial {
    public static final PrismarineArmorMaterial INSTANCE = new PrismarineArmorMaterial();
    private static final int[] BASE_DURABILITY = new int[] {11, 16, 15, 13};
    private static final int[] PROTECTION_VALUES = new int[] {3, 5, 4, 3};
    private int X = 33;

    @Override
    public int getDurability(ArmorItem.Type type) {
        switch (type) {
            case HELMET:
                return BASE_DURABILITY[0] * X; // Replace X with the multiplier for the material
            case CHESTPLATE:
                return BASE_DURABILITY[1] * X; // Replace X with the multiplier for the material
            case LEGGINGS:
                return BASE_DURABILITY[2] * X; // Replace X with the multiplier for the material
            case BOOTS:
                return BASE_DURABILITY[3] * X; // Replace X with the multiplier for the material
            default:
                return 0;
        }
    }


    @Override
    public int getProtection(ArmorItem.Type type) {
        switch (type) {
            case HELMET:
                return PROTECTION_VALUES[0];
            case CHESTPLATE:
                return PROTECTION_VALUES[1];
            case LEGGINGS:
                return PROTECTION_VALUES[2];
            case BOOTS:
                return PROTECTION_VALUES[3];
            default:
                return 0;
        }
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
        return Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot"));
    }
    @Override
    public String getName() {
        return "prismarine";
    }
    @Override
    public float getToughness() {
        return 3;
    }
    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
