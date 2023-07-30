package com.hexagram2021.skullcraft.common.block.SmallCubeSkull;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class SmallCubeWallSkullBlock extends WallSkullBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.box(5.0D, 5.0D, 10.0D, 11.0D, 11.0D, 16.0D),
			Direction.SOUTH, Block.box(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 6.0D),
			Direction.EAST, Block.box(0.0D, 5.0D, 5.0D, 6.0D, 11.0D, 11.0D),
			Direction.WEST, Block.box(10.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D)
	));

	public SmallCubeWallSkullBlock(Properties props, SkullBlock.Type type) {
		super(type, props);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos blockPos, CollisionContext context) {
		return AABBS.get(blockState.getValue(FACING));
	}
}
