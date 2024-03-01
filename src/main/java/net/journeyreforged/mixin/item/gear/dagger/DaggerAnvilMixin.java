package net.journeyreforged.mixin.item.gear.dagger;

import net.journeyreforged.item.gear.dagger.*;
import net.journeyreforged.registry.EnchantmentRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public abstract class DaggerAnvilMixin {

    // Redirect the call to EnchantmentHelper.get to manipulate the map of enchantments based on your conditions
    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;get(Lnet/minecraft/item/ItemStack;)Ljava/util/Map;"))
    private Map<Enchantment, Integer> onGetEnchantments(ItemStack stack) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
        ItemStack input1 = ((AnvilScreenHandler) (Object) this).getSlot(0).getStack();

        // Conditionally remove enchantments based on your criteria
        if (!input1.isEmpty() && input1.getItem() instanceof DaggerItem) {
            enchantments.remove(Enchantments.SWEEPING);
            enchantments.remove(Enchantments.LOYALTY);
        } else {
            enchantments.remove(EnchantmentRegistry.REPOSSESSION);
        }
        return enchantments;
    }
}
