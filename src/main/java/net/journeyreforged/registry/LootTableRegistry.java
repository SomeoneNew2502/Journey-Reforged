package net.journeyreforged.registry;

import net.journeyreforged.event.loot_tables.chest.*;
import net.journeyreforged.event.loot_tables.chest.village.Village_Armorer;
import net.journeyreforged.event.loot_tables.chest.village.Village_Butcher;
import net.journeyreforged.event.loot_tables.chest.village.Village_Cartographer;
import net.journeyreforged.event.loot_tables.chest.village.Village_Desert_House;
import net.journeyreforged.event.loot_tables.chest.village.Village_Fisher;
import net.journeyreforged.event.loot_tables.chest.village.Village_Plains_House;
import net.journeyreforged.event.loot_tables.chest.village.Village_Savanna_House;
import net.journeyreforged.event.loot_tables.chest.village.Village_Snowy_House;
import net.journeyreforged.event.loot_tables.chest.village.Village_Taiga_House;
import net.journeyreforged.event.loot_tables.entity.Elder_Guardian;
import net.journeyreforged.event.loot_tables.gameplay.fishing.Treasure;

public class LootTableRegistry {

    public static void init() {

        Village_Armorer.modifyLootTables();
        Village_Butcher.modifyLootTables();
        Village_Cartographer.modifyLootTables();
        Village_Fisher.modifyLootTables();
        Village_Desert_House.modifyLootTables();
        Village_Plains_House.modifyLootTables();
        Village_Savanna_House.modifyLootTables();
        Village_Snowy_House.modifyLootTables();
        Village_Taiga_House.modifyLootTables();

        Abandoned_Mineshaft.modifyLootTables();
        Buried_Treasure.modifyLootTables();
        Desert_Pyramid.modifyLootTables();
        End_City.modifyLootTables();
        Jungle_Temple.modifyLootTables();
        Nether_Bridge.modifyLootTables();
        Pillager_Outpost.modifyLootTables();
        Shipwreck_Treasure.modifyLootTables();
        Simple_Dungeon.modifyLootTables();
        Stronghold_Corridor.modifyLootTables();
        Stronghold_Crossing.modifyLootTables();
        Underwater_Ruins.modifyLootTables();
        Woodland_Mansion.modifyLootTables();

        Mending_Book.modifyLootTables();

        Elder_Guardian.modifyLootTables();

        Treasure.modifyLootTables();


    }
}
