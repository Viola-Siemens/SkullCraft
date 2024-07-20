package com.hexagram2021.skullcraft.common.block.cube;

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

@SuppressWarnings("deprecation")
public class CubeSkullBlock extends AbstractSkullBlock {
	public static final MapCodec<CubeSkullBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(SkullBlock.Type.CODEC.fieldOf("kind").forGetter(AbstractSkullBlock::getType), propertiesCodec())
					.apply(instance, CubeSkullBlock::new)
	);
	public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
	protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);

	public CubeSkullBlock(SkullBlock.Type type, Properties props) {
		super(type, props);
		this.registerDefaultState(this.stateDefinition.any().setValue(ROTATION, 0));
	}

	@Override
	public MapCodec<? extends CubeSkullBlock> codec() {
		return CODEC;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos blockPos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState blockState, BlockGetter level, BlockPos blockPos) {
		return Shapes.empty();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(ROTATION, Mth.floor(((double)context.getRotation() * 16.0D / 360.0D) + 0.5D) & 15);
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

	public enum Types implements SkullBlock.Type {
		SLIME("skullcraft:slime"),
		LAVASLIME("skullcraft:lavaslime"),
		BLAZE("skullcraft:blaze"),
		SPIDER("skullcraft:spider"),
		CAVE_SPIDER("skullcraft:cave_spider"),
		PIG("skullcraft:pig"),
		ENDERMAN("skullcraft:enderman"),
		SNOW_GOLEM("skullcraft:snow_golem"),
		TECHNOBLADE("skullcraft:technoblade");

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
