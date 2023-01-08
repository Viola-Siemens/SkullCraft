package com.hexagram2021.skullcraft.common.block.CubeSkull;

import com.hexagram2021.skullcraft.common.block.entity.CubeSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class CubeSkullBlock extends AbstractSkullBlock {
	public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
	protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);

	public CubeSkullBlock(Properties props, SkullBlock.Type type) {
		super(type, props);
		this.registerDefaultState(this.stateDefinition.any().setValue(ROTATION, 0));
	}

	@Override @NotNull
	public VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter level, @NotNull BlockPos blockPos, @NotNull CollisionContext context) {
		return SHAPE;
	}

	@Override @NotNull
	public VoxelShape getOcclusionShape(@NotNull BlockState blockState, @NotNull BlockGetter level, @NotNull BlockPos blockPos) {
		return Shapes.empty();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(ROTATION, Mth.floor(((double)context.getRotation() * 16.0D / 360.0D) + 0.5D) & 15);
	}

	@Override @NotNull
	public BlockState rotate(BlockState blockState, Rotation rotation) {
		return blockState.setValue(ROTATION, rotation.rotate(blockState.getValue(ROTATION), 16));
	}

	@Override @NotNull
	public BlockState mirror(BlockState blockState, Mirror mirror) {
		return blockState.setValue(ROTATION, mirror.mirror(blockState.getValue(ROTATION), 16));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ROTATION);
	}

	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
		return new CubeSkullBlockEntity(blockPos, blockState);
	}

	public enum Types implements SkullBlock.Type {
		SLIME,
		LAVASLIME,
		BLAZE,
		SPIDER,
		CAVE_SPIDER,
		PIG,
		ENDERMAN,
		SNOW_GOLEM,
		TECHNOBLADE
	}
}
