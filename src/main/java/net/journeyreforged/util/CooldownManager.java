package net.journeyreforged.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private static final Map<UUID, Map<Integer, Long>> lockedSlots = new HashMap<>();
    private static final Map<UUID, Map<Integer, ItemStack>> lockedItems = new HashMap<>();

    public static void lockSlot(PlayerEntity player, int slotIndex, ItemStack itemStack) {
        UUID playerUUID = player.getUuid();
        lockedSlots.computeIfAbsent(playerUUID, k -> new HashMap<>()).put(slotIndex, System.currentTimeMillis());
        lockedItems.computeIfAbsent(playerUUID, k -> new HashMap<>()).put(slotIndex, itemStack);
    }

    public static boolean isSlotLocked(PlayerEntity player, int slotIndex) {
        UUID playerUUID = player.getUuid();
        Map<Integer, Long> slots = lockedSlots.get(playerUUID);
        if (slots != null) {
            Long lockedTime = slots.get(slotIndex);
            if (lockedTime != null) {
                return System.currentTimeMillis() - lockedTime < 5000; // 5 seconds cooldown
            }
        }
        return false;
    }

    public static boolean isCorrectItemForLockedSlot(PlayerEntity player, int slotIndex, ItemStack itemStack) {
        UUID playerUUID = player.getUuid();
        Map<Integer, ItemStack> items = lockedItems.get(playerUUID);
        if (items != null && items.containsKey(slotIndex)) {
            ItemStack lockedStack = items.get(slotIndex);
            return ItemStack.canCombine(lockedStack, itemStack);
        }
        return false;
    }


}
