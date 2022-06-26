package net.journeyreforged.registries;

import net.journeyreforged.journeyreforged;
import net.journeyreforged.items.gear.armor.BaseArmor;
import net.journeyreforged.items.gear.armor.PrismarineArmorMaterial;
import net.journeyreforged.items.gear.daggers.DaggerBase;
import net.journeyreforged.items.gear.daggers.DiamondDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.GoldenDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.IronDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.NetheriteDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.PrismarineDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.StoneDaggerToolMaterial;
import net.journeyreforged.items.gear.daggers.WoodenDaggerToolMaterial;
import net.journeyreforged.registries.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    
    public static void init() {
    }

    public static final ArmorMaterial PRISMARINE_ARMOR = new PrismarineArmorMaterial();

    public static final Item WOODEN_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "wooden_dagger"), new DaggerBase(new WoodenDaggerToolMaterial()));
    public static final Item STONE_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "stone_dagger"), new DaggerBase(new StoneDaggerToolMaterial()));
    public static final Item GOLDEN_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "golden_dagger"), new DaggerBase(new GoldenDaggerToolMaterial()));
    public static final Item IRON_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "iron_dagger"), new DaggerBase(new IronDaggerToolMaterial()));
    public static final Item DIAMOND_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "diamond_dagger"), new DaggerBase(new DiamondDaggerToolMaterial()));
    public static final Item NETHERITE_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "netherite_dagger"), new DaggerBase(new NetheriteDaggerToolMaterial()));
    public static final Item PRISMARINE_DAGGER = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_dagger"), new DaggerBase(new PrismarineDaggerToolMaterial()));

    public static final Item PRISMARINE_INGOT = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_ingot"), new Item(new Item.Settings().group(journeyreforged.JOURNEY_REFORGED_ITEM_GROUP)));

    public static final Item PRISMARINE_HELMET = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_helmet"), new BaseArmor(PRISMARINE_ARMOR, EquipmentSlot.HEAD));
    public static final Item PRISMARINE_CHESTPLATE = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_chestplate"), new BaseArmor(PRISMARINE_ARMOR, EquipmentSlot.CHEST));
    public static final Item PRISMARINE_LEGGINGS = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_leggings"), new BaseArmor(PRISMARINE_ARMOR, EquipmentSlot.LEGS));
    public static final Item PRISMARINE_BOOTS = Registry.register(Registry.ITEM, new Identifier(journeyreforged.MODID, "prismarine_boots"), new BaseArmor(PRISMARINE_ARMOR, EquipmentSlot.FEET));
}
