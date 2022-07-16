package net.journeyreforged.registries;

import net.journeyreforged.events.loot_tables.chests.Abandoned_Mineshaft;
import net.journeyreforged.events.loot_tables.chests.Buried_Treasure;
import net.journeyreforged.events.loot_tables.chests.End_City;
import net.journeyreforged.events.loot_tables.chests.Jungle_Temple;
import net.journeyreforged.events.loot_tables.chests.Mending_Book;
import net.journeyreforged.events.loot_tables.chests.Nether_Bridge;
import net.journeyreforged.events.loot_tables.chests.Pillager_Outpost;
import net.journeyreforged.events.loot_tables.chests.Shipwreck_Treasure;
import net.journeyreforged.events.loot_tables.chests.Stronghold_Corridor;
import net.journeyreforged.events.loot_tables.chests.Stronghold_Crossing;
import net.journeyreforged.events.loot_tables.chests.Underwater_Ruins;
import net.journeyreforged.events.loot_tables.chests.Woodland_Mansion;
import net.journeyreforged.events.loot_tables.chests.village.Village_Armorer;
import net.journeyreforged.events.loot_tables.chests.village.Village_Butcher;
import net.journeyreforged.events.loot_tables.chests.village.Village_Cartographer;
import net.journeyreforged.events.loot_tables.chests.village.Village_Desert_House;
import net.journeyreforged.events.loot_tables.chests.village.Village_Fisher;
import net.journeyreforged.events.loot_tables.chests.village.Village_Plains_House;
import net.journeyreforged.events.loot_tables.chests.village.Village_Savanna_House;
import net.journeyreforged.events.loot_tables.chests.village.Village_Snowy_House;
import net.journeyreforged.events.loot_tables.chests.village.Village_Taiga_House;

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
        End_City.modifyLootTables();
        Jungle_Temple.modifyLootTables();
        Nether_Bridge.modifyLootTables();
        Pillager_Outpost.modifyLootTables();
        Shipwreck_Treasure.modifyLootTables();
        Stronghold_Corridor.modifyLootTables();
        Stronghold_Crossing.modifyLootTables();
        Underwater_Ruins.modifyLootTables();
        Woodland_Mansion.modifyLootTables();
        
        Mending_Book.modifyLootTables();
    }
}
