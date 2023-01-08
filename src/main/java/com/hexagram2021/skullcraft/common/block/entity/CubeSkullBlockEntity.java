package com.hexagram2021.skullcraft.common.block.entity;

import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CubeSkullBlockEntity extends AbstractSkullCraftSkullBlockEntity<CubeSkullBlockEntity> {
	public CubeSkullBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(SCBlockEntities.CUBE_SKULL, blockPos, blockState);
	}
}
