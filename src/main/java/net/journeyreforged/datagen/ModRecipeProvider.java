package net.journeyreforged.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.journeyreforged.registry.BlockRegistry;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> GLASS_SMELTABLES = List.of(Blocks.RED_SAND);
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        offerBlasting(exporter, GLASS_SMELTABLES, RecipeCategory.MISC, Blocks.GLASS, 0.1F, 160, "sand");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ItemRegistry.getItem("prismarine_ingot"), 1)
                .input(ItemRegistry.getItem("elder_guardian_scale"), 4) // Inputting elder guardian scales 4 times
                .input(Items.COPPER_INGOT, 4) // Inputting copper bulbs 4 times
                // Assuming you have a method to generate an advancement criterion for having the item
                .criterion("has_elder_guardian_scale", conditionsFromItem(ItemRegistry.getItem("elder_guardian_scale")))
                .criterion("has_copper_ingot", conditionsFromItem(Items.COPPER_INGOT))
                // The recipe ID needs to be unique within your mod. Adjust "yourmodid" and "prismarine_ingot_recipe" accordingly.
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_ingot"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.STRING, 1)
                .input(ItemRegistry.getItem("warped_thread"), 2)
                .criterion("has_warped_thread", conditionsFromItem(ItemRegistry.getItem("warped_thread")))
                .offerTo(exporter, new Identifier("journeyreforged", "string"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, BlockRegistry.PEARL_BLOCK, 1)
                .pattern("PP ")
                .pattern("PP ")
                .pattern("   ")
                .input('P', ItemRegistry.getItem("pearl"))
                .criterion(hasItem(ItemRegistry.getItem("pearl")), conditionsFromItem(ItemRegistry.getItem("pearl")))
                .criterion(hasItem(BlockRegistry.PEARL_BLOCK), conditionsFromItem(BlockRegistry.PEARL_BLOCK))
                .offerTo(exporter, new Identifier("journeyreforged", "pearl_block"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ItemRegistry.getItem("pearl"), 4)
                .input(BlockRegistry.PEARL_BLOCK)
                .criterion(hasItem(ItemRegistry.getItem("pearl")), conditionsFromItem(ItemRegistry.getItem("pearl")))
                .criterion(hasItem(BlockRegistry.PEARL_BLOCK), conditionsFromItem(BlockRegistry.PEARL_BLOCK))
                .offerTo(exporter, new Identifier("journeyreforged", "pearl"));

        // For turning Dethreaded Warped Stem into Dethreaded Warped Hyphae
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, BlockRegistry.DETHREADED_WARPED_HYPHAE, 3)
                .pattern("WW ")
                .pattern("WW ")
                .pattern("   ")
                .input('W', ItemRegistry.getItem("dethreaded_warped_stem"))
                .criterion(hasItem(ItemRegistry.getItem("pearl")), conditionsFromItem(ItemRegistry.getItem("pearl")))
                .criterion(hasItem(BlockRegistry.DETHREADED_WARPED_HYPHAE), conditionsFromItem(BlockRegistry.DETHREADED_WARPED_HYPHAE))
                .offerTo(exporter, new Identifier("journeyreforged", "dethreaded_warped_hyphae"));

        // For crafting planks out of Dethreaded Warped Hyphae
        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ItemRegistry.getItem("warped_planks"), 4)
                .input(BlockRegistry.DETHREADED_WARPED_HYPHAE)
                .criterion(hasItem(ItemRegistry.getItem("warped_planks")), conditionsFromItem(ItemRegistry.getItem("warped_planks")))
                .criterion(hasItem(BlockRegistry.DETHREADED_WARPED_HYPHAE), conditionsFromItem(BlockRegistry.DETHREADED_WARPED_HYPHAE))
                .offerTo(exporter, new Identifier("journeyreforged", "warped_planks"));

        // For compacting prismarine ingot to prismarine alloy block
        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.BUILDING_BLOCKS, // reverseCategory
                ItemRegistry.getItem("prismarine_ingot"), // baseItem
                RecipeCategory.DECORATIONS, // compactingCategory
                BlockRegistry.PRISMARINE_ALLOY_BLOCK, // compactItem
                "prismarine_alloy_block_compact", // compactingId, custom ID for compacting recipe
                null, // compactingGroup
                "prismarine_ingot_from_alloy_block", // reverseId, custom ID for decompressing recipe
                null // reverseGroup
        );

        // For compacting prismarine nugget to prismarine ingot
        offerReversibleCompactingRecipes(
                exporter,
                RecipeCategory.BUILDING_BLOCKS, // reverseCategory
                ItemRegistry.getItem("prismarine_nugget"), // baseItem
                RecipeCategory.DECORATIONS, // compactingCategory
                ItemRegistry.getItem("prismarine_ingot"), // compactItem
                "prismarine_ingot_compact", // compactingId, custom ID for compacting recipe
                null, // compactingGroup
                "prismarine_nugget_from_ingot", // reverseId, custom ID for decompressing recipe
                null // reverseGroup
        );

        // One-way recipe for crafting warped thread into warped weave
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, BlockRegistry.WARPED_WEAVE, 1)
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .input('T', ItemRegistry.getItem("warped_thread"))
                .criterion(hasItem(ItemRegistry.getItem("warped_thread")), conditionsFromItem(ItemRegistry.getItem("warped_thread")))
                .criterion(hasItem(BlockRegistry.WARPED_WEAVE), conditionsFromItem(BlockRegistry.WARPED_WEAVE))
                .offerTo(exporter, new Identifier("journeyreforged", "warped_weave"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.TRIDENT, 1)
                .pattern("NNN")
                .pattern(" I ")
                .pattern(" I ")
                .input('N', ItemRegistry.getItem("prismarine_nugget"))
                .input('I', ItemRegistry.getItem("prismarine_ingot"))
                .criterion(hasItem(Items.TRIDENT), conditionsFromItem(Items.TRIDENT))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_nugget")), conditionsFromItem(ItemRegistry.getItem("prismarine_nugget")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_ingot")), conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .offerTo(exporter, new Identifier(getRecipeName(Items.TRIDENT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CHAIN, 2)
                .pattern(" N ")
                .pattern(" I ")
                .pattern(" N ")
                .input('N', Items.IRON_NUGGET)
                .input('I', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(Items.CHAIN)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CHAINMAIL_HELMET, 1)
                .pattern("CCC")
                .pattern("C C")
                .pattern("   ")
                .input('C', Items.CHAIN)
                .criterion(hasItem(Items.CHAIN), conditionsFromItem(Items.CHAIN))
                .criterion(hasItem(Items.CHAINMAIL_HELMET), conditionsFromItem(Items.CHAINMAIL_HELMET))
                .offerTo(exporter, new Identifier(getRecipeName(Items.CHAINMAIL_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CHAINMAIL_CHESTPLATE, 1)
                .pattern("C C")
                .pattern("CCC")
                .pattern("CCC")
                .input('C', Items.CHAIN)
                .criterion(hasItem(Items.CHAIN), conditionsFromItem(Items.CHAIN))
                .criterion(hasItem(Items.CHAINMAIL_CHESTPLATE), conditionsFromItem(Items.CHAINMAIL_CHESTPLATE))
                .offerTo(exporter, new Identifier(getRecipeName(Items.CHAINMAIL_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CHAINMAIL_LEGGINGS, 1)
                .pattern("CCC")
                .pattern("C C")
                .pattern("C C")
                .input('C', Items.CHAIN)
                .criterion(hasItem(Items.CHAIN), conditionsFromItem(Items.CHAIN))
                .criterion(hasItem(Items.CHAINMAIL_LEGGINGS), conditionsFromItem(Items.CHAINMAIL_LEGGINGS))
                .offerTo(exporter, new Identifier(getRecipeName(Items.CHAINMAIL_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CHAINMAIL_BOOTS, 1)
                .pattern("   ")
                .pattern("C C")
                .pattern("C C")
                .input('C', Items.CHAIN)
                .criterion(hasItem(Items.CHAIN), conditionsFromItem(Items.CHAIN))
                .criterion(hasItem(Items.CHAINMAIL_BOOTS), conditionsFromItem(Items.CHAINMAIL_BOOTS))
                .offerTo(exporter, new Identifier(getRecipeName(Items.CHAINMAIL_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LEAD, 1)
                .pattern("SS ")
                .pattern("SS ")
                .pattern("  S")
                .input('S', Items.STRING)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.LEAD), conditionsFromItem(Items.LEAD))
                .offerTo(exporter, new Identifier(getRecipeName(Items.LEAD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.ENDER_PEARL, 1)
                .pattern(" S ")
                .pattern("SPS")
                .pattern(" S ")
                .input('P', ItemRegistry.getItem("pearl"))
                .input('S', ItemRegistry.getItem("warped_thread"))
                 .criterion(hasItem(ItemRegistry.getItem("pearl")), conditionsFromItem(ItemRegistry.getItem("pearl")))
                 .criterion(hasItem(ItemRegistry.getItem("warped_thread")), conditionsFromItem(ItemRegistry.getItem("warped_thread")))
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                .offerTo(exporter, new Identifier("journeyreforged", "ender_pearl"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ItemRegistry.getItem("wooden_dagger"), 1)
                .pattern("   ")
                .pattern(" W ")
                .pattern(" S ")
                .input('W', TagKey.of(Registries.ITEM.getKey(), new Identifier("minecraft:planks")))
                .input('S', Items.STICK)
                .criterion("has_planks", conditionsFromTag(TagKey.of(Registries.ITEM.getKey(), new Identifier("minecraft:planks"))))
                .criterion(hasItem(ItemRegistry.getItem("wooden_dagger")), conditionsFromItem(ItemRegistry.getItem("wooden_dagger")))
                .offerTo(exporter, new Identifier("journeyreforged", "wooden_dagger"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ItemRegistry.getItem("stone_dagger"), 1)
                .pattern("   ")
                .pattern(" C ")
                .pattern(" S ")
                .input('C', TagKey.of(Registries.ITEM.getKey(), new Identifier("minecraft:base_stone_overworld")))
                .input('S', Items.STICK)
                .criterion("has_base_stone_overworld", conditionsFromTag(TagKey.of(Registries.ITEM.getKey(), new Identifier("minecraft:base_stone_overworld"))))
                .criterion(hasItem(ItemRegistry.getItem("stone_dagger")), conditionsFromItem(ItemRegistry.getItem("stone_dagger")))
                .offerTo(exporter, new Identifier("journeyreforged", "stone_dagger"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ItemRegistry.getItem("golden_dagger"), 1)
                .pattern("   ")
                .pattern(" G ")
                .pattern(" S ")
                .input('G', Items.GOLD_INGOT)
                .input('S', Items.STICK)
                .criterion("has_gold_ingot", conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ItemRegistry.getItem("golden_dagger")), conditionsFromItem(ItemRegistry.getItem("golden_dagger")))
                .offerTo(exporter, new Identifier("journeyreforged", "golden_dagger"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ItemRegistry.getItem("iron_dagger"), 1)
                .pattern("   ")
                .pattern(" I ")
                .pattern(" S ")
                .input('I', Items.IRON_INGOT)
                .input('S', Items.STICK)
                .criterion("has_iron_ingot", conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ItemRegistry.getItem("iron_dagger")), conditionsFromItem(ItemRegistry.getItem("iron_dagger")))
                .offerTo(exporter, new Identifier("journeyreforged", "iron_dagger"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ItemRegistry.getItem("diamond_dagger"), 1)
                .pattern("   ")
                .pattern(" D ")
                .pattern(" S ")
                .input('D', Items.DIAMOND)
                .input('S', Items.STICK)
                .criterion("has_diamond", conditionsFromItem(Items.DIAMOND))
                .criterion(hasItem(ItemRegistry.getItem("diamond_dagger")), conditionsFromItem(ItemRegistry.getItem("diamond_dagger")))
                .offerTo(exporter, new Identifier("journeyreforged", "diamond_dagger"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.ofItems(ItemRegistry.getItem("diamond_dagger")), // Base item
                        Ingredient.ofItems(Items.NETHERITE_INGOT), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("netherite_dagger") // Result item
                )
                .criterion("has_netherite_ingot", conditionsFromItem(Items.NETHERITE_INGOT))
                .criterion(hasItem(ItemRegistry.getItem("netherite_dagger")), conditionsFromItem(ItemRegistry.getItem("netherite_dagger")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "netherite_dagger"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(ItemRegistry.getItem("diamond_dagger")), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_dagger") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_dagger")), conditionsFromItem(ItemRegistry.getItem("prismarine_dagger")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_dagger"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(Items.DIAMOND_SWORD), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_sword") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_sword")), conditionsFromItem(ItemRegistry.getItem("prismarine_sword")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_sword"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(Items.DIAMOND_PICKAXE), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_pickaxe") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_pickaxe")), conditionsFromItem(ItemRegistry.getItem("prismarine_pickaxe")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_pickaxe"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(Items.DIAMOND_AXE), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_axe") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_axe")), conditionsFromItem(ItemRegistry.getItem("prismarine_axe")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_axe"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(Items.DIAMOND_SHOVEL), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_shovel") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_shovel")), conditionsFromItem(ItemRegistry.getItem("prismarine_shovel")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_shovel"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(Items.DIAMOND_HOE), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_hoe") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_hoe")), conditionsFromItem(ItemRegistry.getItem("prismarine_hoe")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_hoe"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(Items.DIAMOND_HELMET), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_helmet") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_helmet")), conditionsFromItem(ItemRegistry.getItem("prismarine_helmet")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_helmet"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(Items.DIAMOND_CHESTPLATE), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_chestplate") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_chestplate")), conditionsFromItem(ItemRegistry.getItem("prismarine_chestplate")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_chestplate"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(Items.DIAMOND_LEGGINGS), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_leggings") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_leggings")), conditionsFromItem(ItemRegistry.getItem("prismarine_leggings")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_leggings"));

        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_upgrade_smithing_template")),
                        Ingredient.ofItems(Items.DIAMOND_BOOTS), // Base item
                        Ingredient.ofItems(ItemRegistry.getItem("prismarine_ingot")), // Addition item
                        RecipeCategory.COMBAT, // Recipe category
                        ItemRegistry.getItem("prismarine_boots") // Result item
                )
                .criterion("has_prismarine_ingot", conditionsFromItem(ItemRegistry.getItem("prismarine_ingot")))
                .criterion(hasItem(ItemRegistry.getItem("prismarine_boots")), conditionsFromItem(ItemRegistry.getItem("prismarine_boots")))
                // Provide a unique recipe ID
                .offerTo(exporter, new Identifier("journeyreforged", "prismarine_boots"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.WARPED_PLANKS, 4)
                .input(ItemRegistry.getItem("dethreaded_warped_stem"))
                .criterion(hasItem(ItemRegistry.getItem("dethreaded_warped_stem")), conditionsFromItem(ItemRegistry.getItem("dethreaded_warped_stem")))
                .offerTo(exporter, new Identifier("journeyreforged", "warped_planks"));
    }

    public static void offerPlanksRecipe(RecipeExporter exporter, ItemConvertible output, TagKey<Item> input, int count) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, count).input(input).group("planks").criterion("has_logs", (AdvancementCriterion) RecipeProvider.conditionsFromTag(input)).offerTo(exporter);
    }
}
