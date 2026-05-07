package com.hexagram2021.skullcraft.common.block.hoglin;

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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class HoglinWallSkullBlock extends WallSkullBlock {
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.box(0.0D, 4.0D, 2.0D, 16.0D, 19.0D, 16.0D),
			Direction.SOUTH, Block.box(0.0D, 4.0D, 0.0D, 16.0D, 19.0D, 14.0D),
			Direction.EAST, Block.box(0.0D, 4.0D, 0.0D, 14.0D, 19.0D, 16.0D),
			Direction.WEST, Block.box(2.0D, 4.0D, 0.0D, 16.0D, 19.0D, 16.0D)
	));

	public HoglinWallSkullBlock(SkullBlock.Type type, Properties props) {
		super(type, props);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos blockPos, CollisionContext context) {
		return AABBS.get(blockState.getValue(FACING));
	}
}
