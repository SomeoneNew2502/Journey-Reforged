package net.journeyreforged.util;

import net.journeyreforged.JourneyReforged;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> SHEARABLE_WARPED_STEM =
                createTag("shearable_warped_stem");
        public static final TagKey<Block> NETHER_WOOL =
                createTag("nether_wool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(JourneyReforged.MODID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(JourneyReforged.MODID, name));
        }
    }
}
