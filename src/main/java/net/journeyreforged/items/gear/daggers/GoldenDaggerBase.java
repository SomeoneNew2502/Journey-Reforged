package net.journeyreforged.items.gear.daggers;

import net.journeyreforged.journeyreforged;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class GoldenDaggerBase extends SwordItem {

    public GoldenDaggerBase(ToolMaterial material) {
        super(material, -1, -2.0f, new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP));
    }
}
