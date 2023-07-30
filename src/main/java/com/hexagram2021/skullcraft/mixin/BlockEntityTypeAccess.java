package com.hexagram2021.skullcraft.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(BlockEntityType.class)
public interface BlockEntityTypeAccess {
	@Accessor("validBlocks")
	Set<Block> sc_getValidBlocks();

	@Accessor("validBlocks")
	@Mutable
	void sc_setValidBlocks(Set<Block> blocks);
}
