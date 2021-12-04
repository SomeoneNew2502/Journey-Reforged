package net.journeyreforged.registries;

import net.journeyreforged.journeyreforged;
import net.journeyreforged.items.gear.daggers.DiamondDaggerBase;
import net.journeyreforged.items.gear.daggers.DiamondDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.GoldenDaggerBase;
import net.journeyreforged.items.gear.daggers.GoldenDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.IronDaggerBase;
import net.journeyreforged.items.gear.daggers.IronDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.NetheriteDaggerBase;
import net.journeyreforged.items.gear.daggers.NetheriteDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.StoneDaggerBase;
import net.journeyreforged.items.gear.daggers.StoneDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.WoodenDaggerBase;
import net.journeyreforged.items.gear.daggers.WoodenDaggerToolMaterial;
import net.journeyreforged.registries.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    
    public static void init() {
        
    }

    public static final Item WOODEN_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "wooden_dagger"), new WoodenDaggerBase(new WoodenDaggerToolMaterial()));
    public static final Item STONE_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "stone_dagger"), new StoneDaggerBase(new StoneDaggerToolMaterial()));
    public static final Item GOLDEN_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "golden_dagger"), new GoldenDaggerBase(new GoldenDaggerToolMaterial()));
    public static final Item IRON_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "iron_dagger"), new IronDaggerBase(new IronDaggerToolMaterial()));
    public static final Item DIAMOND_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "diamond_dagger"), new DiamondDaggerBase(new DiamondDaggerToolMaterial()));
    public static final Item NETHERITE_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "netherite_dagger"), new NetheriteDaggerBase(new NetheriteDaggerToolMaterial()));
}
