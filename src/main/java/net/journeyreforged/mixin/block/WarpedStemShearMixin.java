package net.journeyreforged.mixin.block;

import net.journeyreforged.registry.BlockRegistry;
import net.journeyreforged.registry.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ServerPlayerInteractionManager.class)
public class WarpedStemShearMixin {
    @Inject(method = "interactBlock", at = @At(value = "HEAD"), cancellable = true)
    private void onInteractBlock(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        BlockState blockState = world.getBlockState(hitResult.getBlockPos());
        if (blockState.isOf(Blocks.WARPED_STEM) && stack.getItem() instanceof ShearsItem) {
            ItemStack itemStack = player.getStackInHand(hand);

            // Check if the player is holding shears and the block is a warped stem
            if (itemStack.getItem() instanceof ShearsItem && blockState.isOf(Blocks.WARPED_STEM)) {
                // Logic to replace the warped stem with dethreaded warped stem
                world.setBlockState(hitResult.getBlockPos(), BlockRegistry.DETHREADED_WARPED_STEM.getDefaultState().with(Properties.AXIS, blockState.get(Properties.AXIS)), 3);

                // Correcting the spawn location calculation using hitResult.getBlockPos()
                BlockPos pos = hitResult.getBlockPos(); // Define pos using hitResult
                double x = pos.getX() + 0.5; // Centering the spawn location to the middle of the block
                double y = pos.getY() + 0.5;
                double z = pos.getZ() + 0.5;

                world.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);

                // Modify this part of your code
                Random random = new Random();
                int dropAmount = 1 + random.nextInt(3); // This will generate 1, 2, or 3

                // Preparing the item stack to drop with the random amount
                ItemStack itemToDrop = new ItemStack(ItemRegistry.getItem("warped_thread"), dropAmount);

                // The rest of your code for spawning the item remains the same
                ItemScatterer.spawn(world, x, y, z, itemToDrop);

                // Damage the shears
                itemStack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));

                // Prevent further processing and return success
                cir.setReturnValue(ActionResult.SUCCESS);
            }
            // Check if the player is holding shears and the block is a warped hyphae
            if (itemStack.getItem() instanceof ShearsItem && blockState.isOf(Blocks.WARPED_HYPHAE)) {
                // Logic to replace the warped stem with dethreaded warped stem
                world.setBlockState(hitResult.getBlockPos(), BlockRegistry.DETHREADED_WARPED_HYPHAE.getDefaultState().with(Properties.AXIS, blockState.get(Properties.AXIS)), 3);

                // Correcting the spawn location calculation using hitResult.getBlockPos()
                BlockPos pos = hitResult.getBlockPos(); // Define pos using hitResult
                double x = pos.getX() + 0.5; // Centering the spawn location to the middle of the block
                double y = pos.getY() + 0.5;
                double z = pos.getZ() + 0.5;

                world.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);

                // Modify this part of your code
                Random random = new Random();
                int dropAmount = 1 + random.nextInt(3); // This will generate 1, 2, or 3

                // Preparing the item stack to drop with the random amount
                ItemStack itemToDrop = new ItemStack(ItemRegistry.getItem("warped_thread"), dropAmount);

                // The rest of your code for spawning the item remains the same
                ItemScatterer.spawn(world, x, y, z, itemToDrop);

                // Damage the shears
                itemStack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));

                // Prevent further processing and return success
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
