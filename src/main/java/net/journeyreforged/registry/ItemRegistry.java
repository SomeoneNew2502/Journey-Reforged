package net.journeyreforged.registry;

import net.journeyreforged.JourneyReforged;
import net.journeyreforged.item.*;
import net.journeyreforged.item.gear.armor.PrismarineArmorMaterial;
import net.journeyreforged.item.gear.dagger.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.journeyreforged.item.gear.dagger.ThrowableDaggerEntity.DaggerType;

import java.util.*;

public class ItemRegistry {

    // List to keep track of all registered items
    private static final Map<String, Item> ITEMS = new LinkedHashMap<>();

    public static void init() {
        register("pearl_block", new BlockItem(BlockRegistry.PEARL_BLOCK, new Item.Settings()));
        register("prismarine_alloy_block", new BlockItem(BlockRegistry.PRISMARINE_ALLOY_BLOCK, new Item.Settings()));
        register("dethreaded_warped_stem", new BlockItem(BlockRegistry.DETHREADED_WARPED_STEM, new Item.Settings()));

        register("wooden_dagger", new DaggerItem(DaggerType.WOOD, new Item.Settings()));
        register("stone_dagger", new DaggerItem(DaggerType.STONE, new Item.Settings()));
        register("golden_dagger", new DaggerItem(DaggerType.GOLD, new Item.Settings()));
        register("iron_dagger", new DaggerItem(DaggerType.IRON, new Item.Settings()));
        register("diamond_dagger", new DaggerItem(DaggerType.DIAMOND, new Item.Settings()));
        register("netherite_dagger", new DaggerItem(DaggerType.NETHERITE, new Item.Settings().fireproof()));
        register("prismarine_dagger", new DaggerItem(DaggerType.PRISMARINE, new Item.Settings()));
        register("prismarine_nugget", new JRItem());
        register("prismarine_ingot", new JRItem());

        register("prismarine_sword", new JRSwordItem(JRToolMaterials.PRISMARINE, 2, -2.4f));
        register("prismarine_pickaxe", new JRPickaxeItem(JRToolMaterials.PRISMARINE, 1, -2.8f));
        register("prismarine_axe", new JRAxeItem(JRToolMaterials.PRISMARINE, 5, -3.0f));
        register("prismarine_shovel", new JRShovelItem(JRToolMaterials.PRISMARINE, 1.5f, -3.0f));
        register("prismarine_hoe", new JRHoeItem(JRToolMaterials.PRISMARINE, -3, 0f));

        register("prismarine_helmet", new ArmorEffects(PrismarineArmorMaterial.INSTANCE, ArmorItem.Type.HELMET, new Item.Settings()));
        register("prismarine_chestplate", new ArmorEffects(PrismarineArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
        register("prismarine_leggings", new ArmorEffects(PrismarineArmorMaterial.INSTANCE, ArmorItem.Type.LEGGINGS, new Item.Settings()));
        register("prismarine_boots", new ArmorEffects(PrismarineArmorMaterial.INSTANCE, ArmorItem.Type.BOOTS, new Item.Settings()));

        register("pearl", new JRItem());
        register("warped_thread", new JRItem());
        register("elder_guardian_scale", new JRItem());
    }



    private static Item register(String identifier, Item item) {
        ITEMS.put(identifier, item);
        return Registry.register(Registries.ITEM, new Identifier(JourneyReforged.MODID, identifier), item);
    }

    // Method to get a single item by name
    public static Item getItem(String name) {
        return ITEMS.get(name);
    }

    // Method to expose all registered items
    public static Map<String, Item> getItems() {
        return ITEMS;
    }
}