package net.journeyreforged;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.journeyreforged.gear.daggers.DiamondDaggerBase;
import net.journeyreforged.gear.daggers.DiamondDaggerToolMaterial;
import net.journeyreforged.gear.daggers.GoldDaggerBase;
import net.journeyreforged.gear.daggers.GoldDaggerToolMaterial;
import net.journeyreforged.gear.daggers.IronDaggerBase;
import net.journeyreforged.gear.daggers.IronDaggerToolMaterial;
import net.journeyreforged.gear.daggers.NetheriteDaggerBase;
import net.journeyreforged.gear.daggers.NetheriteDaggerToolMaterial;
import net.journeyreforged.gear.daggers.StoneDaggerBase;
import net.journeyreforged.gear.daggers.StoneDaggerToolMaterial;
import net.journeyreforged.gear.daggers.WoodenDaggerBase;
import net.journeyreforged.gear.daggers.WoodenDaggerToolMaterial;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class journeyreforged implements ModInitializer {

    public static final String MODID = "journeyreforged";
    
    public static final ItemGroup TEST_GROUP = FabricItemGroupBuilder.create(
        new Identifier("testgroup", "everything"))
            .icon(() -> new ItemStack(Blocks.COBBLESTONE))
            .build();

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "wooden_dagger"), new WoodenDaggerBase(new WoodenDaggerToolMaterial()));
        Registry.register(Registry.ITEM, new Identifier(MODID, "stone_dagger"), new StoneDaggerBase(new StoneDaggerToolMaterial()));
        Registry.register(Registry.ITEM, new Identifier(MODID, "golden_dagger"), new GoldDaggerBase(new GoldDaggerToolMaterial()));
        Registry.register(Registry.ITEM, new Identifier(MODID, "iron_dagger"), new IronDaggerBase(new IronDaggerToolMaterial()));
        Registry.register(Registry.ITEM, new Identifier(MODID, "diamond_dagger"), new DiamondDaggerBase(new DiamondDaggerToolMaterial()));
        Registry.register(Registry.ITEM, new Identifier(MODID, "netherite_dagger"), new NetheriteDaggerBase(new NetheriteDaggerToolMaterial()));
    }
}
