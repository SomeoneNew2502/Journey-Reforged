package net.journeyreforged.mixin.accessor;

import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemEntity.class)
public interface ItemEntityAccessor {
    @Accessor("itemAge")
    void setItemAge(int age);

    @Accessor("itemAge")
    int getItemAge();
}

