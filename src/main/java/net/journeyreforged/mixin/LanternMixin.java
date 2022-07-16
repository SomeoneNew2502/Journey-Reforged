package net.journeyreforged.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.journeyreforged.registries.JRProperties;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(LanternBlock.class)
public abstract class LanternMixin extends Block {

    //TODO Fix lantern floating when the block supporting the lantern is destroyed, fix hit-box and model, make it possible to place on the side of fences.

    @Shadow
    @Final
    public static BooleanProperty HANGING = Properties.HANGING;
    @Shadow @Final public static BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    @Shadow @Final protected static final VoxelShape STANDING_SHAPE = VoxelShapes.union(Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 7.0, 11.0), Block.createCuboidShape(6.0, 7.0, 6.0, 10.0, 9.0, 10.0));
    @Shadow @Final protected static final VoxelShape HANGING_SHAPE = VoxelShapes.union(Block.createCuboidShape(5.0, 1.0, 5.0, 11.0, 8.0, 11.0), Block.createCuboidShape(6.0, 8.0, 6.0, 10.0, 10.0, 10.0));
    private static final Map<Direction, VoxelShape> ON_WALL_SHAPE = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.createCuboidShape(5.5, 3.0, 11.0, 10.5, 13.0, 16.0),
            Direction.SOUTH, Block.createCuboidShape(5.5, 3.0, 0.0, 10.5, 13.0, 5.0),
            Direction.WEST, Block.createCuboidShape(11.0, 3.0, 5.5, 16.0, 13.0, 10.5),
            Direction.EAST, Block.createCuboidShape(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)
    ));
    
    @Shadow
    protected static Direction attachedDirection(BlockState state) {
        return null;
    }

    public LanternMixin(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(AbstractBlock.Settings settings, CallbackInfo ci) {
        this.setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(JRProperties.ON_WALL, false));
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState;
        World world = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = world.getFluidState(blockPos);

        for (Direction direction : ctx.getPlacementDirections()) {
            blockState = this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER)
                    .with(Properties.HORIZONTAL_FACING, Direction.NORTH);

            if (world.getBlockState(blockPos.offset(ctx.getPlayerFacing())).isSideSolidFullSquare(world, blockPos.offset(ctx.getPlayerFacing()), ctx.getPlayerFacing())) {
                return blockState.with(JRProperties.ON_WALL, true).with(HANGING, true).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
            } else if (sideCoversSmallSquare(world, blockPos.offset(Direction.DOWN), Direction.UP)) {
                return blockState.with(JRProperties.ON_WALL, false).with(HANGING, false);
            } else if (sideCoversSmallSquare(world, blockPos.offset(Direction.UP), Direction.DOWN)) {
                return blockState.with(JRProperties.ON_WALL, false).with(HANGING, true);
            } else {
                for (Direction dir : DIRECTIONS) {
                    if (world.getBlockState(blockPos.offset(dir)).isSideSolidFullSquare(world, blockPos.offset(dir), dir)) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    @Inject(at = @At("RETURN"), method = "getOutlineShape", cancellable = true)
    void getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.get(JRProperties.ON_WALL)) {
            cir.setReturnValue(ON_WALL_SHAPE.get(state.get(Properties.HORIZONTAL_FACING)));
        }
    }

    @Inject(at = @At("TAIL"), method = "appendProperties")
    void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(Properties.HORIZONTAL_FACING, JRProperties.ON_WALL);
    }

    @Inject(at = @At("RETURN"), method = "canPlaceAt", cancellable = true)
    void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        boolean returnValue = sideCoversSmallSquare(world, pos.offset(attachedDirection(state).getOpposite()), attachedDirection(state));

        for (Direction dir : DIRECTIONS) {
            if (world.getBlockState(pos.offset(dir)).isSideSolidFullSquare(world, pos.offset(dir), dir)) {
                returnValue = true;
                break;
            }
        }
        cir.setReturnValue(returnValue);
    }

    @Inject(at = @At("RETURN"), method = "getStateForNeighborUpdate", cancellable = true)
    void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (state.get(JRProperties.ON_WALL) && world.getBlockState(pos.offset(state.get(Properties.HORIZONTAL_FACING).getOpposite())).getBlock() == Blocks.AIR) {
            cir.setReturnValue(Blocks.AIR.getDefaultState());
        }
    }
}
