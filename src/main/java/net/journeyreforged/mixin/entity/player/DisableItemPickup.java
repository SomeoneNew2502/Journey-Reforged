package net.journeyreforged.mixin.entity.player;

import net.journeyreforged.util.CooldownManager;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class DisableItemPickup {

    /*

    The following version is still the one that works the best. I'll try to explain it more clearly.
    It works, because:
    - I can't pickup items for 5s after throwing the dagger, excluding the dagger itself.
    - Picking up the dagger lets me pickup items normally again even before the 5s is up.
    - I can pick up items into slots which slot index is before the slot index that of where the dagger was thrown from

    It doesn't work, because:
    - I can't pickup items into slots that have an index number after the slot, which the dagger was thrown from. Your task is to fix this.

    package net.journeyreforged.mixin.entity.player;

    import net.journeyreforged.util.CooldownManager;
    import net.minecraft.entity.ItemEntity;
    import net.minecraft.entity.player.PlayerEntity;
    import net.minecraft.item.ItemStack;
    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;
    import org.spongepowered.asm.mixin.Mixin;
    import org.spongepowered.asm.mixin.injection.At;
    import org.spongepowered.asm.mixin.injection.Inject;
    import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

    @Mixin(ItemEntity.class)
    public abstract class DisableItemPickup {

    // Logger for debugging
    private static final Logger LOGGER = LogManager.getLogger();

    @Inject(method = "onPlayerCollision", at = @At("HEAD"), cancellable = true)
    public void onPlayerCollision(PlayerEntity player, CallbackInfo ci) {
        ItemEntity self = (ItemEntity)(Object)this;
        ItemStack itemStack = self.getStack();

        // Debugging - print item and player
        LOGGER.debug("Attempting to pick up: " + itemStack.getItem().toString() + " by player: " + player.getName().getString());

        // Find the slot index that the item would be placed into.
        int slotIndex = -1; // Default value indicating not found or not applicable.

        // You would need to find the appropriate slot index based on your game logic.
        // For simplicity, let's consider the first empty slot or the slot with the same item that is not full.
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stackInSlot = player.getInventory().getStack(i);
            if (stackInSlot.isEmpty() || (ItemStack.canCombine(stackInSlot, itemStack) && stackInSlot.getCount() < stackInSlot.getMaxCount())) {
                slotIndex = i;
                // Debugging - found suitable slot
                LOGGER.debug("Found suitable slot index: " + slotIndex + " for item: " + itemStack.getItem().toString());
                break;
            }
        }

        if (slotIndex != -1 && CooldownManager.isSlotLocked(player, slotIndex)) {
            // Debugging - slot is locked
            LOGGER.debug("Slot index: " + slotIndex + " is locked. Blocking pickup for item: " + itemStack.getItem().toString());
            // Prevent the original method from executing, effectively blocking the pickup
            ci.cancel();
        } else {
            // Debugging - item pickup proceeding
            LOGGER.debug("Proceeding with item pickup for: " + itemStack.getItem().toString());
        }
    }
    }
    */

    //THIS DOES NOT WORK. MOST RECENT GPT4 CODE DUMP

    private static final Logger LOGGER = LogManager.getLogger();

    @Inject(method = "onPlayerCollision", at = @At("HEAD"), cancellable = true)
    public void onPlayerCollision(PlayerEntity player, CallbackInfo ci) {
        ItemEntity self = (ItemEntity)(Object)this;
        ItemStack itemStack = self.getStack();

        if (itemStack.isEmpty()) {
            return; // Skip if the item stack is empty.
        }

        // Iterate only through the hotbar slots if specific to your use case.
        for (int i = 0; i < player.getInventory().main.size(); ++i) {
            ItemStack stackInSlot = player.getInventory().getStack(i);

            // If the slot is locked, determine if the item should be blocked.
            if (CooldownManager.isSlotLocked(player, i)) {
                // If the slot is locked, check if it's reserved for a different item.
                if (!stackInSlot.isEmpty() && !ItemStack.canCombine(stackInSlot, itemStack) && CooldownManager.isCorrectItemForLockedSlot(player, i, itemStack)) {
                    LOGGER.debug("Pickup blocked for item: " + itemStack.getItem().toString() + " into locked slot: " + i);
                    ci.cancel();
                    return;
                } else if (stackInSlot.isEmpty() && !CooldownManager.isCorrectItemForLockedSlot(player, i, itemStack)) {
                    // Block pickup into empty locked slots not reserved for this item.
                    LOGGER.debug("Pickup blocked for empty slot: " + i + " not reserved for this item.");
                    ci.cancel();
                    return;
                }
            }
        }
    }
}