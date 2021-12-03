package net.journeyreforged.gear.daggers;

import net.journeyreforged.journeyreforged;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class GoldDaggerBase extends SwordItem {

    public GoldDaggerBase(ToolMaterial material) {
        super(material, -1, -2.0f, new Item.Settings().group(journeyreforged.TEST_GROUP));
    }
}
