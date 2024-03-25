package net.journeyreforged.mixin.entity.player;

import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public class TotemOnHotbarMixin {


    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void checkHotbarForTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity)(Object)this;
        System.out.println(player.getItemCooldownManager().isCoolingDown(Items.TOTEM_OF_UNDYING));
        if (!player.getItemCooldownManager().isCoolingDown(Items.TOTEM_OF_UNDYING)){
            ItemStack totemStack = ItemStack.EMPTY;
            // Check hotbar slots for a Totem of Undying
            for (int i = 0; i < 9; ++i) {
                ItemStack stack = player.getInventory().getStack(i);
                if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
                    totemStack = stack;
                    break;
                }
            }

            if (!totemStack.isEmpty()) {
                // If a totem is found, manually trigger the totem effects
                totemStack.decrement(1);
                this.triggerTotemEffects(player, totemStack);
                cir.setReturnValue(true); // Prevent the original method from running
            }
            // If no totem is found in the hotbar, the method will proceed as normal
        } else cir.setReturnValue(false); // Prevent the original method from running if item is on cooldown

    }
    private void triggerTotemEffects(PlayerEntity player, ItemStack totemStack) {
        if (player instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
            Criteria.USED_TOTEM.trigger(serverPlayerEntity, totemStack);
            player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
            player.getItemCooldownManager().set(Items.TOTEM_OF_UNDYING, 600);
        }
        player.setHealth(1.0F);
        player.clearStatusEffects();
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
        player.getWorld().sendEntityStatus(player, (byte)35);
        grantAdvancement((ServerPlayerEntity)player);
    }
    //funny advancement code -_- there has got to be a better way of doing this? mixin with emit game event maybe?
    private void grantAdvancement(ServerPlayerEntity player) {
        ServerAdvancementLoader advancementManager = player.getServer().getAdvancementLoader();
        AdvancementEntry advancement = advancementManager.get(new Identifier("minecraft:adventure/totem_of_undying"));
        if (advancement != null) {
            AdvancementProgress advancementProgress = player.getAdvancementTracker().getProgress(advancement);
            if (!advancementProgress.isDone()) {
                for(String criterion : advancementProgress.getUnobtainedCriteria()) {
                    player.getAdvancementTracker().grantCriterion(advancement, criterion);
                }
            }
        }
    }
}



