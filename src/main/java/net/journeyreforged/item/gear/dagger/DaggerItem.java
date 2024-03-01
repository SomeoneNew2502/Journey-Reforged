package net.journeyreforged.item.gear.dagger;

import net.journeyreforged.JourneyReforged;
import net.journeyreforged.registry.EnchantmentRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
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
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TRIDENT_THROW, user.getSoundCategory(), 0.5F, 1.0F);
        if (!world.isClient) {
            ThrowableDaggerEntity daggerEntity = new ThrowableDaggerEntity(EntityRegistry.THROWABLE_DAGGER, world);
            daggerEntity.setDaggerType(this.daggerType); // Directly set the daggerType based on the DaggerItem's type
            NbtCompound nbt = new NbtCompound();
            nbt.putString("DaggerType", this.daggerType.toString()); // 'this.type' refers to the DaggerItem's material type
            daggerEntity.readNbt(nbt);


            daggerEntity.initialize(user); // 'user' is the LivingEntity that is using/spawning the dagger
            daggerEntity.setItem(itemStack.copy()); // Ensure the thrown dagger retains enchantments
            daggerEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(daggerEntity);
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
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
