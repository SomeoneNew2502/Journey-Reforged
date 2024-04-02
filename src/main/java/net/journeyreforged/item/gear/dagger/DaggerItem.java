package net.journeyreforged.item.gear.dagger;

import net.journeyreforged.registry.ItemRegistry;
import net.journeyreforged.util.CooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.journeyreforged.registry.EntityRegistry;
import net.journeyreforged.item.gear.dagger.ThrowableDaggerEntity.DaggerType;

public class DaggerItem extends SwordItem {
private final DaggerType daggerType;
    public DaggerItem(DaggerType daggerType, Settings settings) {
        super(daggerType.getMaterial(), 1, -2.0f, settings);
        this.daggerType = daggerType;
    }

    // Inside DaggerItem class
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        int slotIndex = hand == Hand.MAIN_HAND ? user.getInventory().selectedSlot : -1; // -1 for offhand

        // Dynamically get the item based on the daggerType
        Item daggerItem = ItemRegistry.getItem(this.daggerType.getModelKey());
        //System.out.println(user.getItemCooldownManager().isCoolingDown(daggerItem));

        if (!user.getItemCooldownManager().isCoolingDown(daggerItem)) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WITCH_THROW, user.getSoundCategory(), 0.5F, 1.0F);
            if (!world.isClient) {
                ThrowableDaggerEntity daggerEntity = new ThrowableDaggerEntity(EntityRegistry.THROWABLE_DAGGER, world);
                daggerEntity.setDaggerType(this.daggerType); // Set the daggerType based on the DaggerItem's type
                NbtCompound nbt = new NbtCompound();
                daggerEntity.readNbt(nbt); // You may not need to manually set "DaggerType" here since it's set by setDaggerType()

                daggerEntity.initialize(user); // Initialize the entity
                daggerEntity.setItem(itemStack.copy()); // Copy the item stack to retain enchantments
                daggerEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
                world.spawnEntity(daggerEntity);

                if (!user.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
            }

            // Apply the cooldown dynamically based on the dagger's type
            user.getItemCooldownManager().set(daggerItem, 20); // 20 ticks cooldown

            // After throwing, lock the slot for this specific itemStack
            if (!world.isClient && slotIndex != -1) {
                CooldownManager.lockSlot(user, slotIndex, itemStack.copy());
            }

            return TypedActionResult.success(itemStack, world.isClient());
        }


        return TypedActionResult.fail(itemStack);
    }



    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true; // Make the dagger enchantable.
    }

    @Override
    public int getEnchantability() {
        return 10; // Set enchantability level (you might adjust this value based on your item's material and balance).
    }
}
