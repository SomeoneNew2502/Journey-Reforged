package net.journeyreforged.item;

import net.journeyreforged.JourneyReforged;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class JRShovelItem extends ShovelItem {
    public JRShovelItem(ToolMaterial material, float attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed, new Settings());
    }
}
