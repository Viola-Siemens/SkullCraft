package com.hexagram2021.skullcraft.common.block.entity;

import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SmallCubeSkullBlockEntity extends AbstractSkullCraftSkullBlockEntity<SmallCubeSkullBlockEntity> {
	public SmallCubeSkullBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(SCBlockEntities.SMALL_CUBE_SKULL, blockPos, blockState);
	}
}
