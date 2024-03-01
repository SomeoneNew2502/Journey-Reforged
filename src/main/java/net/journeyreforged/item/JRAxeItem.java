package net.journeyreforged.item;

import net.journeyreforged.JourneyReforged;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class JRAxeItem extends AxeItem {
    public JRAxeItem(ToolMaterial material, float attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Settings());
    }
}
