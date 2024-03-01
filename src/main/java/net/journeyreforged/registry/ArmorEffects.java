package net.journeyreforged.registry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import net.journeyreforged.item.gear.armor.PrismarineArmorMaterial;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ArmorEffects extends ArmorItem {
    private static final double SPEED_INCREASE_PER_PIECE = 0.04; // 4% speed increase per piece
    private static final UUID MOVEMENT_SPEED_BOOTS_UUID = UUID.fromString("8d8c0e8e-d4f4-4c8d-8d4e-47b8f88e081b");
    private static final UUID MOVEMENT_SPEED_LEGGINGS_UUID = UUID.fromString("3b91b77a-a12e-4b0c-88d2-e37fe6bfaaef");
    private static final UUID MOVEMENT_SPEED_CHESTPLATE_UUID = UUID.fromString("d22b2d3c-947a-456b-9db1-1b12fd123456");
    private static final UUID MOVEMENT_SPEED_HELMET_UUID = UUID.fromString("6ac8e2f4-3c97-4b23-876b-6a9b5b995bba");
    private static final Map<ArmorMaterial, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            Map.of(
                    PrismarineArmorMaterial.INSTANCE, List.of(
                            new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 2, 0, false, false, true),
                            new StatusEffectInstance(StatusEffects.WATER_BREATHING, 2, 0, false, false, true)
                    )
            );
    public ArmorEffects(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player && hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player);
            }
            if(entity instanceof PlayerEntity player) {
                applyMovementSpeedModifier(player);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        // Only add custom tooltip for Prismarine armor
        if (this.material == PrismarineArmorMaterial.INSTANCE) {
            EquipmentSlot slot = ((ArmorItem) stack.getItem()).getSlotType();
            switch (slot) {
                case HEAD:
                    tooltip.add(Text.literal("When on Head:").formatted(Formatting.GRAY));
                    tooltip.add(Text.translatable("tooltip.prismarine_armor.speed").formatted(Formatting.BLUE));
                    break;
                case CHEST:
                    tooltip.add(Text.translatable("When on Body:").formatted(Formatting.GRAY));
                    tooltip.add(Text.translatable("tooltip.prismarine_armor.speed").formatted(Formatting.BLUE));
                    break;
                case LEGS:
                    tooltip.add(Text.translatable("When on Legs:").formatted(Formatting.GRAY));
                    tooltip.add(Text.translatable("tooltip.prismarine_armor.speed").formatted(Formatting.BLUE));
                    break;
                case FEET:
                    tooltip.add(Text.translatable("When on Feet:").formatted(Formatting.GRAY));
                    tooltip.add(Text.translatable("tooltip.prismarine_armor.speed").formatted(Formatting.BLUE));
                    break;
            }
        }
        super.appendTooltip(stack, world, tooltip, context);
    }


    private void applyMovementSpeedModifier(PlayerEntity player) {
        // This example assumes you have some method to check if a specific armor piece is worn
        // and apply/remove modifiers accordingly.

        applyOrRemoveSpeedModifierForArmorPiece(player, EquipmentSlot.FEET, MOVEMENT_SPEED_BOOTS_UUID);
        applyOrRemoveSpeedModifierForArmorPiece(player, EquipmentSlot.LEGS, MOVEMENT_SPEED_LEGGINGS_UUID);
        applyOrRemoveSpeedModifierForArmorPiece(player, EquipmentSlot.CHEST, MOVEMENT_SPEED_CHESTPLATE_UUID);
        applyOrRemoveSpeedModifierForArmorPiece(player, EquipmentSlot.HEAD, MOVEMENT_SPEED_HELMET_UUID);
    }
    // Assuming this method is called appropriately within your code
    private void applyOrRemoveSpeedModifierForArmorPiece(PlayerEntity player, EquipmentSlot slot, UUID uuid) {
        ItemStack armorPiece = player.getEquippedStack(slot);
        boolean isPrismarineArmor = armorPiece.getItem() instanceof ArmorItem && ((ArmorItem) armorPiece.getItem()).getMaterial() instanceof PrismarineArmorMaterial;

        // Retrieve the player's attribute instance for movement speed
        var movementSpeedAttr = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);

        if (movementSpeedAttr == null) {
            return; // Exit if the movement speed attribute is not found for some reason
        }

        EntityAttributeModifier modifier = new EntityAttributeModifier(uuid, "Prismarine speed boost for " + slot.getName(), SPEED_INCREASE_PER_PIECE, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

        // Check whether the modifier is already applied
        boolean hasModifier = movementSpeedAttr.hasModifier(modifier);

        if (isPrismarineArmor && !hasModifier) {
            // Apply the modifier if the armor is worn and not already applied
            movementSpeedAttr.addTemporaryModifier(modifier);
        } else if (!isPrismarineArmor && hasModifier) {
            // Remove the modifier if the armor is not worn
            movementSpeedAttr.removeModifier(uuid);
        }
    }


    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                for (StatusEffectInstance effect : mapStatusEffects) {
                    // Check the specific condition for DOLPHINS_GRACE
                    if (effect.getEffectType() == StatusEffects.DOLPHINS_GRACE) {
                        if (player.isInSwimmingPose()) {
                            addStatusEffectForMaterial(player, effect);
                        }
                    } else if (effect.getEffectType() == StatusEffects.WATER_BREATHING) {
                        if (player.isSubmergedInWater()) {
                            // Apply other effects without the swimming pose condition
                            addStatusEffectForMaterial(player, effect);
                        }
                    }
                }
            }
        }
    }


    private void addStatusEffectForMaterial(PlayerEntity player, StatusEffectInstance effect) {
        boolean hasPlayerEffect = player.hasStatusEffect(effect.getEffectType());

        if(!hasPlayerEffect) {
            player.addStatusEffect(new StatusEffectInstance(effect));
        }
    }
    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
        ArmorItem chestplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && chestplate.getMaterial() == material && leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
