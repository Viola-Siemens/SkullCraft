package com.hexagram2021.skullcraft.common.block.entity;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.common.block.Scaleable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

public abstract class AbstractSkullCraftSkullBlockEntity<T extends BlockEntity> extends BlockEntity implements Scaleable {
	public AbstractSkullCraftSkullBlockEntity(RegistryObject<BlockEntityType<T>> type, BlockPos blockPos, BlockState blockState) {
		super(type.get(), blockPos, blockState);
		this.scaleX = 100;
		this.scaleY = 100;
		this.scaleZ = 100;
	}

	private int scaleX;
	private int scaleY;
	private int scaleZ;

	public void setScaleXYZ(int scaleX, int scaleY, int scaleZ) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.scaleZ = scaleZ;
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag nbt) {
		super.saveAdditional(nbt);
		if(this.scaleX != 100 || this.scaleY != 100 || this.scaleZ != 100) {
			CompoundTag scaleTag = new CompoundTag();
			scaleTag.putInt("x", this.scaleX);
			scaleTag.putInt("y", this.scaleY);
			scaleTag.putInt("z", this.scaleZ);
			nbt.put(SkullCraft.SCALE_TAG, scaleTag);
		}
	}

	@Override
	public void load(@Nonnull CompoundTag nbt) {
		super.load(nbt);
		if(nbt.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
			CompoundTag scaleTag = nbt.getCompound(SkullCraft.SCALE_TAG);
			this.scaleX = scaleTag.getInt("x");
			this.scaleY = scaleTag.getInt("y");
			this.scaleZ = scaleTag.getInt("z");
		} else {
			this.scaleX = this.scaleY = this.scaleZ = 100;
		}
	}

	@Override
	public int getScaleX() {
		return this.scaleX;
	}
	@Override
	public int getScaleY() {
		return this.scaleY;
	}
	@Override
	public int getScaleZ() {
		return this.scaleZ;
	}
}
