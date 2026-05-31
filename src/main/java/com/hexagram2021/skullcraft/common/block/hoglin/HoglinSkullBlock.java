package com.hexagram2021.skullcraft.common.block.hoglin;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * 疣猪兽头颅方块，支持 16 向旋转放置喵~
 * @author liudongyu
 */
@SuppressWarnings("deprecation")
public class HoglinSkullBlock extends AbstractSkullBlock {
	public static final MapCodec<HoglinSkullBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(SkullBlock.Type.CODEC.fieldOf("kind").forGetter(AbstractSkullBlock::getType), propertiesCodec())
					.apply(instance, HoglinSkullBlock::new)
	);
	public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
	protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

	public HoglinSkullBlock(SkullBlock.Type type, Properties props) {
		super(type, props);
		this.registerDefaultState(this.stateDefinition.any().setValue(ROTATION, 0));
	}

	@Override
	public MapCodec<? extends HoglinSkullBlock> codec() {
		return CODEC;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos blockPos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState blockState) {
		return Shapes.empty();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(ROTATION, Mth.floor((context.getRotation() * 16.0D / 360.0D) + 0.5D) & 15);
	}

	@Override
	public BlockState rotate(BlockState blockState, Rotation rotation) {
		return blockState.setValue(ROTATION, rotation.rotate(blockState.getValue(ROTATION), 16));
	}

	@Override
	public BlockState mirror(BlockState blockState, Mirror mirror) {
		return blockState.setValue(ROTATION, mirror.mirror(blockState.getValue(ROTATION), 16));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(ROTATION);
	}

	/**
	 * 疣猪兽形生物头颅方块类型枚举
	 * @author liudongyu
	 */
	public enum Types implements SkullBlock.Type {
		HOGLIN("skullcraft:hoglin"),
		ZOGLIN("skullcraft:zoglin");

		private final String name;

		Types(String name) {
			this.name = name;
			TYPES.put(name, this);
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}
