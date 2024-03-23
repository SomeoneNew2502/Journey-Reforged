package net.journeyreforged;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.journeyreforged.registry.*;
import net.journeyreforged.util.function.EnchantWithLevelRangeSerializer;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
public class JourneyReforged implements ModInitializer {

	public static final String MODID = "journeyreforged";
	public static final LootFunctionType ENCHANT_WITH_LEVEL_RANGE_LOOT_FUNCTION = new LootFunctionType(EnchantWithLevelRangeSerializer.CODEC);

	public static final ItemGroup JOURNEY_REFORGED_ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(ItemRegistry.getItem("prismarine_pickaxe")))
			.displayName(Text.translatable("itemGroup.journey_reforged"))
			.entries((context, entries) -> {
				// Iterate over the registered items list and add them to the entries
				ItemRegistry.getItems().values().forEach(item -> entries.add(new ItemStack(item)));
			})
			.build();

	@Override
	public void onInitialize() {
		BlockRegistry.init();
		ItemRegistry.init();
		EntityRegistry.init();
		LootTableRegistry.init();
		EnchantmentRegistry.init();
		ListenerRegistry.init();

		Registry.register(Registries.LOOT_FUNCTION_TYPE, new Identifier("journeyreforged", "enchant_with_level_range"), ENCHANT_WITH_LEVEL_RANGE_LOOT_FUNCTION);

		Registry.register(Registries.ITEM_GROUP, new Identifier("journeyreforged", "journey_reforged"), JOURNEY_REFORGED_ITEM_GROUP);

		StrippableBlockRegistry.register(BlockRegistry.DETHREADED_WARPED_STEM, Blocks.STRIPPED_WARPED_STEM);

		FlammableBlockRegistry.getDefaultInstance().add(BlockRegistry.DETHREADED_WARPED_STEM, 5, 5);
	}
}