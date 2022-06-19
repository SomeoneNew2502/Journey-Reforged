package net.journeyreforged.items.gear.armor;

import net.journeyreforged.journeyreforged;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

public class BaseArmor extends ArmorItem {
    
    public BaseArmor(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP));
    }
}
