package net.journeyreforged.registries;

import net.journeyreforged.items.JRAxeItem;
import net.journeyreforged.items.JRHoeItem;
import net.journeyreforged.items.JRPickaxeItem;
import net.journeyreforged.items.JRToolMaterials;
import net.journeyreforged.journeyreforged;
import net.journeyreforged.items.gear.armor.BaseArmor;
import net.journeyreforged.items.gear.armor.PrismarineArmorMaterial;
import net.journeyreforged.items.gear.daggers.DaggerBase;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    
    public static void init() {
    }

    public static final ArmorMaterial PRISMARINE_ARMOR = new PrismarineArmorMaterial();

    public static final Item WOODEN_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "wooden_dagger"), new DaggerBase(ToolMaterials.WOOD));
    public static final Item STONE_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "stone_dagger"), new DaggerBase(ToolMaterials.STONE));
    public static final Item GOLDEN_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "golden_dagger"), new DaggerBase(ToolMaterials.GOLD));
    public static final Item IRON_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "iron_dagger"), new DaggerBase(ToolMaterials.IRON));
    public static final Item DIAMOND_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "diamond_dagger"), new DaggerBase(ToolMaterials.DIAMOND));
    public static final Item NETHERITE_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "netherite_dagger"), new DaggerBase(ToolMaterials.NETHERITE, new Item.Settings().fireproof()));
    public static final Item PRISMARINE_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_dagger"), new DaggerBase(JRToolMaterials.PRISMARINE));

    public static final Item PRISMARINE_NUGGET = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_nugget"), new Item(new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP)));
    public static final Item PRISMARINE_INGOT = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_ingot"), new Item(new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP)));

    public static final Item PRISMARINE_HELMET = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_helmet"), new BaseArmor(PRISMARINE_ARMOR, EquipmentSlot.HEAD));
    public static final Item PRISMARINE_CHESTPLATE = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_chestplate"), new BaseArmor(PRISMARINE_ARMOR, EquipmentSlot.CHEST));
    public static final Item PRISMARINE_LEGGINGS = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_leggings"), new BaseArmor(PRISMARINE_ARMOR, EquipmentSlot.LEGS));
    public static final Item PRISMARINE_BOOTS = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_boots"), new BaseArmor(PRISMARINE_ARMOR, EquipmentSlot.FEET));

    public static final Item PRISMARINE_SWORD = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_sword"), new SwordItem(JRToolMaterials.PRISMARINE, 2, -2.4f, new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP)));
    public static final Item PRISMARINE_PICKAXE = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_pickaxe"), new JRPickaxeItem(JRToolMaterials.PRISMARINE, 1, -2.8f, new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP)));
    public static final Item PRISMARINE_AXE = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_axe"), new JRAxeItem(JRToolMaterials.PRISMARINE,5, -3.0f, new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP)));
    public static final Item PRISMARINE_SHOVEL = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_shovel"), new ShovelItem(JRToolMaterials.PRISMARINE, 1.5f, -3.0f, new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP)));
    public static final Item PRISMARINE_HOE = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_hoe"), new JRHoeItem(JRToolMaterials.PRISMARINE, -3, 0f, new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP)));

}
