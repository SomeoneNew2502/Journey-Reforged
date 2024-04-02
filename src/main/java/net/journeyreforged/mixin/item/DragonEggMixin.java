package net.journeyreforged.mixin.item;

import net.journeyreforged.mixin.accessor.ItemEntityAccessor;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class DragonEggMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        ItemEntity thisEntity = (ItemEntity)(Object)this;
        if (!thisEntity.getStack().isEmpty() && thisEntity.getStack().getItem() == Items.DRAGON_EGG) {
            cir.setReturnValue(false); // Cancel any damage if the item is a Dragon Egg
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        ItemEntity thisEntity = (ItemEntity)(Object)this;
        if (thisEntity.getStack().getItem() == Items.DRAGON_EGG) {
            ItemEntityAccessor accessor = (ItemEntityAccessor) thisEntity;
            if (accessor.getItemAge() > 5800) {
                accessor.setItemAge(0); // Reset age to prevent despawning
            }

            if (thisEntity.getY() < -64) {
                thisEntity.setPosition(thisEntity.getX(), 128, thisEntity.getZ()); // Adjust position to avoid void damage
            }
        }
    }
}