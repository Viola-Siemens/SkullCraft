package com.hexagram2021.skullcraft.common.block;

import com.hexagram2021.skullcraft.common.block.entity.SkullChargerBlockEntity;
import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

/**
 * 头颅充能器方块，用于调整头颅的缩放比例并为头颅附魔喵~
 *
 * @author liudongyu
 */
public class SkullChargerBlock extends BaseEntityBlock {
	public static final MapCodec<SkullChargerBlock> CODEC = simpleCodec(SkullChargerBlock::new);
	/** 头颅充能器的容器标题喵~ */
	public static final Component CONTAINER_TITLE = Component.translatable("container.skull_charger");
	public SkullChargerBlock(Properties props) {
		super(props);
	}

	@Override
	public MapCodec<SkullChargerBlock> codec() {
		return CODEC;
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos,
											Player player, BlockHitResult blockHitResult) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		}
		player.openMenu(blockState.getMenuProvider(level, blockPos));
		return InteractionResult.CONSUME;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new SkullChargerBlockEntity(blockPos, blockState);
	}

	@SuppressWarnings("deprecation")
	@Override
	public RenderShape getRenderShape(BlockState blockState) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createSkullChargerTicker(level, type, SCBlockEntities.SKULL_CHARGER.get());
	}

	/**
	 * 创建头颅充能器的 ticker，仅在服务端运行喵~
	 *
	 * @param level           所在世界喵~
	 * @param type            方块实体类型喵~
	 * @param blockEntityType 目标方块实体类型喵~
	 * @return ticker 实例，客户端返回 {@code null} 喵~
	 */
	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createSkullChargerTicker(Level level, BlockEntityType<T> type, BlockEntityType<? extends SkullChargerBlockEntity> blockEntityType) {
		return level.isClientSide ? null : createTickerHelper(type, blockEntityType, SkullChargerBlockEntity::serverTick);
	}

	@Override
	public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean b) {
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
