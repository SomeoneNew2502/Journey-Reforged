package net.journeyreforged.block;

import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WarpedWeaveBlock extends Block {
    public WarpedWeaveBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        // To reduce fall damage like a hay bale, apply a reduced damage multiplier.
        // Assuming DamageSource.FALL is the correct way to reference fall damage in your modding environment.
        // The damage multiplier (0.2F in this case) simulates the hay bale's behavior.
        entity.handleFallDamage(fallDistance, 0.2F, world.getDamageSources().fall());
    }
}
