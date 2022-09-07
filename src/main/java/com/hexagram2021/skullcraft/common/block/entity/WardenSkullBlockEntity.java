package com.hexagram2021.skullcraft.common.block.entity;

import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class WardenSkullBlockEntity extends AbstractSkullCraftSkullBlockEntity<WardenSkullBlockEntity> {
	public WardenSkullBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(SCBlockEntities.WARDEN_SKULL, blockPos, blockState);
	}
}
