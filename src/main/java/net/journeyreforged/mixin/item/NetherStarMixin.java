package net.journeyreforged.mixin.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class NetherStarMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        ItemEntity thisEntity = (ItemEntity)(Object)this;
        if (!thisEntity.getStack().isEmpty() && thisEntity.getStack().getItem() == Items.NETHER_STAR) {
            if (source.isIn(DamageTypeTags.IS_FIRE)) {
                // Cancel damage if the source is fire or lava
                cir.setReturnValue(false);
            }
        }
    }
}

