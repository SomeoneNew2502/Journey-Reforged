package net.journeyreforged.item.misc;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import net.minecraft.util.Formatting;

public class PrismarineUpgradeSmithingTemplateItem extends Item {
    public PrismarineUpgradeSmithingTemplateItem(Item.Settings settings) {
        super(settings);
        // Initialization code here
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        // Custom tooltip implementation
        tooltip.add(Text.translatable("item.journeyreforged.prismarine_upgrade_smithing_template.title").formatted(Formatting.GRAY));
        tooltip.add(Text.empty()); // Empty line
        tooltip.add(Text.translatable("item.journeyreforged.prismarine_upgrade_smithing_template.applies_to_1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.journeyreforged.prismarine_upgrade_smithing_template.applies_to_2").formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("item.journeyreforged.prismarine_upgrade_smithing_template.ingredients_1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.journeyreforged.prismarine_upgrade_smithing_template.ingredients_2").formatted(Formatting.BLUE));
    }
}
