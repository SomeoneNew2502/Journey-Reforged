package net.journeyreforged.item;

import net.journeyreforged.JourneyReforged;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class JRSwordItem extends SwordItem {
    public JRSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed) {
        super(toolMaterial, attackDamage, attackSpeed, new Settings());
    }
}
