package net.journeyreforged.mixin.entity.player;

import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class DisableSweepAttackMixin {

    @Redirect(method = "attack",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private Item onGetItem(ItemStack instance) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (instance.getItem() instanceof SwordItem && (instance.getItem() == ItemRegistry.getItem("wooden_dagger") || instance.getItem() == ItemRegistry.getItem("stone_dagger") || instance.getItem() == ItemRegistry.getItem("golden_dagger") || instance.getItem() == ItemRegistry.getItem("iron_dagger") || instance.getItem() == ItemRegistry.getItem("diamond_dagger") || instance.getItem() == ItemRegistry.getItem("netherite_dagger") || instance.getItem() == ItemRegistry.getItem("prismarine_dagger"))) {
            // Return a dummy item that is not an instance of SwordItem to prevent setting bl42 to true
            return Items.AIR;
        }
        return instance.getItem();
    }
}



