package com.hexagram2021.skullcraft.common.block.entity;

import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PiglinSkullBlockEntity extends AbstractSkullCraftSkullBlockEntity<PiglinSkullBlockEntity> {
	public PiglinSkullBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(SCBlockEntities.PIGLIN_SKULL, blockPos, blockState);
	}
}
