package com.hexagram2021.skullcraft.common.block.entity;

import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CowSkullBlockEntity extends AbstractSkullCraftSkullBlockEntity<CowSkullBlockEntity> {
	public CowSkullBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(SCBlockEntities.COW_SKULL, blockPos, blockState);
	}
}
