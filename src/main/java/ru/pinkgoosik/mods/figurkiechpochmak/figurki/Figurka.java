package ru.pinkgoosik.mods.figurkiechpochmak.figurki;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import ru.pinkgoosik.mods.figurkiechpochmak.FigurkiEchpochmak;


public class Figurka extends HorizontalFacingBlock implements Waterloggable{

    public static final DirectionProperty FACING;
    public static final BooleanProperty WATERLOGGED;

    public Figurka(Settings settings){
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.25f, 0f, 0.25f, 0.75f, 0.95f, 0.75f);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction dir = ctx.getPlayerFacing();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        BlockState blockState = this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        switch(dir) {
            case NORTH:
                return blockState.with(FACING, Direction.SOUTH);
            case SOUTH:
                return blockState.with(FACING, Direction.NORTH);
            case EAST:
                return blockState.with(FACING, Direction.WEST);
            case WEST:
                return blockState.with(FACING, Direction.EAST);
            default:
                return blockState.with(FACING, ctx.getPlayerFacing());
        }
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.get(WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }
    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    public static boolean isReceivingTetsaPower(BlockPos pos, World world) {
        Block block = FigurkiEchpochmak.MINI_TETSA.getDefaultState().getBlock();
        if (world.getBlockState(pos.down()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.up()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.north()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.south()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.west()).getBlock().equals(block)) {
            return true;
        } else {
            return world.getBlockState(pos.east()).getBlock().equals(block);
        }
    }
    public static boolean isReceivingGoosikPower(BlockPos pos, World world) {
        Block block = FigurkiEchpochmak.MINI_GOOSIK.getDefaultState().getBlock();
        if (world.getBlockState(pos.down()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.up()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.north()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.south()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.west()).getBlock().equals(block)) {
            return true;
        } else {
            return world.getBlockState(pos.east()).getBlock().equals(block);
        }
    }
    public static boolean isReceivingSomikPower(BlockPos pos, World world) {
        Block block = FigurkiEchpochmak.MINI_SOMIK.getDefaultState().getBlock();
        if (world.getBlockState(pos.down()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.up()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.north()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.south()).getBlock().equals(block)) {
            return true;
        } else if (world.getBlockState(pos.west()).getBlock().equals(block)) {
            return true;
        } else {
            return world.getBlockState(pos.east()).getBlock().equals(block);
        }
    }
    static {
        FACING = HorizontalFacingBlock.FACING;
        WATERLOGGED = Properties.WATERLOGGED;
    }
}