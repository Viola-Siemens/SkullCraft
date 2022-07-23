package com.hexagram2021.skullcraft.common.block.entity;

import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HumanSkullBlockEntity extends BlockEntity {
	public HumanSkullBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(SCBlockEntities.HUMAN_SKULL.get(), blockPos, blockState);
	}
}
