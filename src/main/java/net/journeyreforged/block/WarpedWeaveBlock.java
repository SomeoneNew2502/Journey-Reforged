package net.journeyreforged.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// Custom block class for WARPED_WEAVE to handle fall damage reduction and non-flammability
public static class WarpedWeaveBlock extends Block {
    public WarpedWeaveBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 0.2f); // Reduces fall damage like hay bales
    }
}
