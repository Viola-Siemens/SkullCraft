package com.hexagram2021.skullcraft.common.block;

import com.hexagram2021.skullcraft.common.block.entity.SkullChargerBlockEntity;
import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SkullChargerBlock extends BaseEntityBlock {
	public static final Component CONTAINER_TITLE = new TranslatableComponent("container.skull_charger");
	public SkullChargerBlock(Properties props) {
		super(props);
	}

	@Override @NotNull
	public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		}
		player.openMenu(blockState.getMenuProvider(level, blockPos));
		return InteractionResult.CONSUME;
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos blockPos, PathComputationType type) {
		return false;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
		return new SkullChargerBlockEntity(blockPos, blockState);
	}

	@Override @NotNull
	public RenderShape getRenderShape(@NotNull BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
		return createSkullChargerTicker(level, type, SCBlockEntities.SKULL_CHARGER.get());
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createSkullChargerTicker(Level level, BlockEntityType<T> type, BlockEntityType<? extends SkullChargerBlockEntity> blockEntityType) {
		return level.isClientSide ? null : createTickerHelper(type, blockEntityType, SkullChargerBlockEntity::serverTick);
	}

	@Override
	public void onRemove(BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, BlockState newBlockState, boolean b) {
		if (!blockState.is(newBlockState.getBlock())) {
			BlockEntity blockentity = level.getBlockEntity(blockPos);
			if (blockentity instanceof SkullChargerBlockEntity skullChargerBlockEntity) {
				if (level instanceof ServerLevel) {
					Containers.dropContents(level, blockPos, skullChargerBlockEntity);
				}

				level.updateNeighbourForOutputSignal(blockPos, this);
			}

			super.onRemove(blockState, level, blockPos, newBlockState, b);
		}
	}
}
