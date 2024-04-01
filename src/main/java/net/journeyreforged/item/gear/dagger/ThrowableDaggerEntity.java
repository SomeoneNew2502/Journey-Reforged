package net.journeyreforged.item.gear.dagger;

import net.journeyreforged.damagesource.daggerDamage;
import net.journeyreforged.item.JRToolMaterials;
import net.journeyreforged.registry.EnchantmentRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.boss.dragon.phase.Phase;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.journeyreforged.registry.ItemRegistry;

public class ThrowableDaggerEntity extends ThrownItemEntity {
    public boolean hasHitEntity = false; // Flag to track if the dagger has already hit an entity
    private boolean isStuck = false;
    private int RepossessionLevel;
    private int sharpnessLevel;
    private int smiteLevel;
    private int baneOfArthropodsLevel;
    private ItemStack originalItemStack = ItemStack.EMPTY; // Add this field
    private boolean isReturning = false;
    public int age;
    private float rotationAngle = 0.0F; // Rotation angle in degrees
    private boolean executedRepossessionSound = false;
    private boolean hitEnderman = false;
    private int pickupDelay; // Ticks until the item can be picked up
    private DaggerType daggerType;

    private static final TrackedData<String> DAGGER_TYPE = DataTracker.registerData(ThrowableDaggerEntity.class, TrackedDataHandlerRegistry.STRING);

    public ThrowableDaggerEntity(EntityType<? extends ThrownItemEntity> type, World world) {
        super(type, world);
        this.age = 0;
        this.pickupDelay = 20; // Example: 20 ticks (1 second) delay before pickup
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(DAGGER_TYPE, DaggerType.IRON.name()); // Default value
    }


    // Method to initialize extra properties
    public void initialize(LivingEntity owner) {
        this.setOwner(owner);
        ItemStack mainHandStack = owner.getMainHandStack();
        ItemStack offHandStack = owner.getOffHandStack();
        ItemStack throwStack;

        // Determine which hand holds the dagger
        if (!mainHandStack.isEmpty() && mainHandStack.getItem() instanceof DaggerItem) { // Assume DaggerItem is your dagger class
            throwStack = mainHandStack;
        } else if (!offHandStack.isEmpty() && offHandStack.getItem() instanceof DaggerItem) {
            throwStack = offHandStack;
        } else {
            // Fallback in case neither hand has the dagger (should not happen normally)
            throwStack = mainHandStack; // Or handle this case differently
        }

        // Use the determined stack for enchantment levels
        this.RepossessionLevel = EnchantmentHelper.getLevel(EnchantmentRegistry.REPOSSESSION, throwStack);
        this.sharpnessLevel = EnchantmentHelper.getLevel(Enchantments.SHARPNESS, throwStack);
        this.smiteLevel = EnchantmentHelper.getLevel(Enchantments.SMITE, throwStack);
        this.baneOfArthropodsLevel = EnchantmentHelper.getLevel(Enchantments.BANE_OF_ARTHROPODS, throwStack);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.daggerType != null) {
            nbt.putString("DaggerType", this.daggerType.toString());
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("DaggerType", NbtElement.STRING_TYPE)) {
            this.daggerType = DaggerType.valueOf(nbt.getString("DaggerType"));
        }
    }

    public DaggerType getDaggerType() {
        String typeName = this.getDataTracker().get(DAGGER_TYPE);
        return DaggerType.valueOf(typeName); // Handle potential IllegalArgumentException
    }

    public enum DaggerType {
        WOOD(2.0F, "wooden_dagger", ToolMaterials.WOOD),
        STONE(3.0F, "stone_dagger", ToolMaterials.STONE),
        GOLD(2.0F, "golden_dagger", ToolMaterials.GOLD),
        IRON(4.0F, "iron_dagger", ToolMaterials.IRON),
        DIAMOND(5.0F, "diamond_dagger", ToolMaterials.DIAMOND),
        NETHERITE(6.0F, "netherite_dagger", ToolMaterials.NETHERITE),
        PRISMARINE(7.0F, "prismarine_dagger", JRToolMaterials.PRISMARINE);

        private final float damage;
        private final String modelKey;
        private final ToolMaterial material;

        DaggerType(float damage, String modelKey, ToolMaterial material) {
            this.damage = damage;
            this.modelKey = modelKey;
            this.material = material;
        }

        public float getDamage() {
            return damage;
        }

        public String getModelKey() {
            return modelKey;
        }

        public ToolMaterial getMaterial() {
            return this.material;
        }
    }

    public void setDaggerType(DaggerType daggerType) {
        this.getDataTracker().set(DAGGER_TYPE, daggerType.name());
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.getItem("iron_dagger"); // Ensure this item is correctly registered and exists.
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        if (hitResult.getType() == HitResult.Type.ENTITY && !this.isReturning && !hasHitEntity) {
            // Reduce the durability of the dagger
            if (!this.originalItemStack.isEmpty()) {
                this.originalItemStack.damage(1, this.random, null); // Assuming `random` is available. You may need to adjust this.

                // If the item breaks (durability is depleted)
                if (this.originalItemStack.isEmpty()) {
                    this.discard();
                }
            }

            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
            Entity hitEntity = entityHitResult.getEntity();

            if (hitEntity instanceof EndermanEntity) {
                LivingEntity livingEntity = (LivingEntity) hitEntity;

                float baseDamage = this.getDaggerType().getDamage(); // Define the damage amount
                // Assuming generic damage; adjust as needed
                livingEntity.damage(daggerDamage.of(this.getWorld(), daggerDamage.dagger_projectile, getOwner()), baseDamage);
                this.hitEnderman = true;
            }
            if (hitEntity instanceof EndCrystalEntity crystal) {

                float baseDamage = this.getDaggerType().getDamage(); // Define the damage amount
                // Assuming generic damage; adjust as needed
                crystal.damage(daggerDamage.of(this.getWorld(), daggerDamage.dagger_projectile, getOwner()), baseDamage);
            }

            if (hitEntity instanceof EnderDragonPart dragonPart) {
                Entity dragon = dragonPart.owner;

                if (dragon instanceof EnderDragonEntity dragonEntity) {
                    Phase currentPhase = dragonEntity.getPhaseManager().getCurrent();

                    // Assuming there are static instances or identifiable properties of PhaseType for different phases
                    if (currentPhase.getType() == PhaseType.LANDING || currentPhase.getType() == PhaseType.TAKEOFF || currentPhase.getType() == PhaseType.SITTING_FLAMING || currentPhase.getType() == PhaseType.SITTING_SCANNING) {
                        //Do nothing
                    } else {
                        float baseDamage = this.getDaggerType().getDamage(); // Define the damage amount

                        float finalDamage;
                        if (sharpnessLevel > 0) {
                            finalDamage = baseDamage + (0.5F * sharpnessLevel + 0.5F);
                            dragonEntity.damage(daggerDamage.of(this.getWorld(), daggerDamage.dagger_projectile, getOwner()), finalDamage);
                        } else {
                            dragonEntity.damage(daggerDamage.of(this.getWorld(), daggerDamage.dagger_projectile, getOwner()), baseDamage);
                        }
                    }
                }
            } else if (hitEntity instanceof LivingEntity livingEntity) {

                float baseDamage = this.getDaggerType().getDamage(); // Define the damage amount

                float finalDamage;
                if (sharpnessLevel > 0) {
                    finalDamage = baseDamage + (0.5F * sharpnessLevel + 0.5F);
                    livingEntity.damage(daggerDamage.of(this.getWorld(), daggerDamage.dagger_projectile, getOwner()), finalDamage);
                } else if (smiteLevel > 0 && livingEntity.isUndead()) {
                    finalDamage = baseDamage + (2.5F * smiteLevel);
                    livingEntity.damage(daggerDamage.of(this.getWorld(), daggerDamage.dagger_projectile, getOwner()), finalDamage);
                } else if (baneOfArthropodsLevel > 0 && isArthropod(livingEntity)) {
                    finalDamage = baseDamage + (2.5F * baneOfArthropodsLevel);
                    livingEntity.damage(daggerDamage.of(this.getWorld(), daggerDamage.dagger_projectile, getOwner()), finalDamage);

                    // Calculate the duration of the Slowness effect
                    int duration = 20 + 10 * baneOfArthropodsLevel; // Starting at 1 second, increase by 0.5 seconds per level
                    // Ensure the duration does not exceed the maximum
                    duration = Math.min(duration, 70); // Max duration at Bane of Arthropods V is 3.5 seconds or 70 ticks

                    // Apply the Slowness effect
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, duration, 3)); // Slowness IV
                } else {
                    livingEntity.damage(daggerDamage.of(this.getWorld(), daggerDamage.dagger_projectile, getOwner()), baseDamage);
                }
            }

            if (!this.hitEnderman) {
                this.hasHitEntity = true; // Update the flag to prevent further entity hits

                // Make the dagger fall like a trident after hitting an entity
                this.setVelocity(Vec3d.ZERO); // Stop the forward movement
                this.setNoGravity(false); // Allow the dagger to be affected by gravity

                // To prevent piercing through all mobs, you can make the dagger bounce off slightly or just stop
                // Example: Bounce off slightly upon hitting an entity
                Vec3d bounceDirection = this.getVelocity().multiply(-0.3, 1, -0.3); // Reverse horizontal velocity slightly
                this.setVelocity(bounceDirection);
            }

        } else if (hitResult.getType() == HitResult.Type.BLOCK && !isReturning) {
            // Handle getting stuck in a block
            this.isStuck = true;
            this.setVelocity(Vec3d.ZERO);
            }
        }

    // Getters for stuck orientation
    public boolean isStuck() {
        return isStuck;
    }

    public float getStuckYaw() {
        return 0.0F;
    }

    public float getStuckPitch() {
        return 0.0F;
    }

    // Modify the existing constructor or add a new method to set the item stack
    public void setItem(ItemStack itemStack) {
        super.setItem(itemStack);
        this.originalItemStack = itemStack; // Store the original item stack
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (!this.getWorld().isClient && (this.hasHitEntity || this.isStuck) && this.pickupDelay <= 0) {
            // Use the stored originalItemStack instead of creating a new one
            boolean pickedUp = player.getInventory().insertStack(this.originalItemStack);
            if (pickedUp) {
                player.sendPickup(this, 1);
                this.age = 0;
                getSoundCategory();
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    public void setProperties(LivingEntity user, float pitch, float yaw, float roll, float velocity, float divergence) {
        // Set the throwable entity's position and initial motion.
        float x = -MathHelper.sin(yaw * ((float)Math.PI / 180)) * MathHelper.cos(pitch * ((float)Math.PI / 180));
        float y = -MathHelper.sin((pitch + roll) * ((float)Math.PI / 180));
        float z = MathHelper.cos(yaw * ((float)Math.PI / 180)) * MathHelper.cos(pitch * ((float)Math.PI / 180));
        setVelocity(x, y, z, velocity, divergence);

        // Adjust position to originate from the thrower.
        double startX = user.getX() - (double)(MathHelper.sin(yaw * ((float)Math.PI / 180)) * 0.1F);
        double startY = user.getBodyY(0.5) + 0.2;
        double startZ = user.getZ() - (double)(MathHelper.cos(yaw * ((float)Math.PI / 180)) * 0.1F);
        updatePosition(startX, startY, startZ);
    }

    @Override
    public void tick() {
        super.tick(); // Ensure the base class tick behavior is executed first.

        // Degrees rotated per tick, adjust as needed
        float rotationSpeed = 60.0F;
        updateRotation(rotationSpeed);

        if (this.pickupDelay > 0 && (hasHitEntity || isStuck)) {
            --this.pickupDelay;
        }

        if (this.RepossessionLevel > 0 && (this.hasHitEntity || this.isStuck)) {
            this.isReturning = true; // Dagger starts returning
            this.setNoGravity(true);
            // Check if the owner is valid and the entity hasn't been removed.

            // Play the loyalty sound effect when the dagger starts returning
            if (!this.getWorld().isClient && !executedRepossessionSound) {
                executedRepossessionSound = true;
                getSoundCategory();
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_TRIDENT_RETURN, SoundCategory.PLAYERS, 10.0F, 1.0F);
            }

            if (this.getOwner() != null && !(this.getOwner().isRemoved()) && this.getWorld() instanceof ServerWorld) {
                double distanceSq = this.getOwner().getPos().distanceTo(this.getPos()); // Calculate squared distance
                double dampFactor = Math.max(1.0 - distanceSq / 100, 0.3); // Calculate damping factor

                if (this.getOwner().distanceTo(this) > 1.0) {
                    Vec3d vecToOwner = this.getOwner().getPos().add(0, this.getOwner().getEyeHeight(this.getOwner().getPose()) - 2, 0).subtract(this.getPos()).normalize();
                    this.setVelocity(vecToOwner.multiply(0.3 * this.RepossessionLevel * dampFactor)); // Apply damping
                } else {
                    this.onPlayerCollision((PlayerEntity) this.getOwner());
                }
            } else {
                if (!this.getWorld().isClient) {
                    this.dropStack(this.originalItemStack, 0.1f);
                    this.remove(Entity.RemovalReason.DISCARDED);
                }
            }
        } else {
            executedRepossessionSound = false;
        }

        if (this.RepossessionLevel == 0) {
            if (this.hasHitEntity || this.isStuck) {
                this.age++;
                if (!this.getWorld().isClient && this.age > 6000) {
                    this.remove(RemovalReason.DISCARDED); // Remove the entity
                }
            }
        }
    }
    // Method to update the rotation angle, call this in your tick method or wherever appropriate
    public void updateRotation(float delta) {
        //System.out.println(rotationAngle);
        rotationAngle += delta; // Increment the rotation angle by delta degrees
        rotationAngle %= 360; // Ensure the rotation angle stays within 0-360 degrees
    }

    // Getter method for the rotation angle
    public float getRotationAngle() {
        return rotationAngle;
    }

    // Helper method to check if an entity is an arthropod
    private boolean isArthropod(LivingEntity entity) {
        // This method checks if the entity is one of the types affected by Bane of Arthropods
        // You can use instanceof checks for each specific entity type (Spider, CaveSpider, Silverfish, Endermite)
        return entity instanceof SpiderEntity || entity instanceof SilverfishEntity || entity instanceof EndermiteEntity;
    }
}
