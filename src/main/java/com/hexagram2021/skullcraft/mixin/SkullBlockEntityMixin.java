package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.common.block.Scaleable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkullBlockEntity.class)
public class SkullBlockEntityMixin implements Scaleable {
	private int scaleX;
	private int scaleY;
	private int scaleZ;

	@Inject(method = "<init>", at = @At(value = "TAIL"))
	public void initScaleXYZ(CallbackInfo ci) {
		this.scaleX = 100;
		this.scaleY = 100;
		this.scaleZ = 100;
	}

	@Inject(method = "load", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/entity/BlockEntity;load(Lnet/minecraft/nbt/CompoundTag;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
	))
	public void loadScaleXYZ(CompoundTag nbt, CallbackInfo ci) {
		if(nbt.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
			CompoundTag scaleTag = nbt.getCompound(SkullCraft.SCALE_TAG);
			this.scaleX = scaleTag.getInt("x");
			this.scaleY = scaleTag.getInt("y");
			this.scaleZ = scaleTag.getInt("z");
		} else {
			this.scaleX = this.scaleY = this.scaleZ = 100;
		}
	}

	@Inject(method = "saveAdditional", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/entity/BlockEntity;saveAdditional(Lnet/minecraft/nbt/CompoundTag;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
	))
	public void saveScaleXYZ(CompoundTag nbt, CallbackInfo ci) {
		if(this.scaleX != 100 || this.scaleY != 100 || this.scaleZ != 100) {
			CompoundTag scaleTag = new CompoundTag();
			scaleTag.putInt("x", this.scaleX);
			scaleTag.putInt("y", this.scaleY);
			scaleTag.putInt("z", this.scaleZ);
			nbt.put(SkullCraft.SCALE_TAG, scaleTag);
		}
	}

	@Override
	public void setScaleXYZ(int scaleX, int scaleY, int scaleZ) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.scaleZ = scaleZ;
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
