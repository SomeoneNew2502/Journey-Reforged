package net.journeyreforged.item;

import net.journeyreforged.JourneyReforged;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class JRPickaxeItem extends PickaxeItem {

    public JRPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Settings());
    }
    
}
