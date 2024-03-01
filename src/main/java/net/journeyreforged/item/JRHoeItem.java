package net.journeyreforged.item;

import net.journeyreforged.JourneyReforged;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class JRHoeItem extends HoeItem {
    public JRHoeItem(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Settings());
    }
}
