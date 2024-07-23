package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.common.block.Scalable;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkullBlockEntity.class)
public class SkullBlockEntityMixin implements Scalable {
	@Unique
	private int skullcraft$scaleX;
	@Unique
	private int skullcraft$scaleY;
	@Unique
	private int skullcraft$scaleZ;

	@Inject(method = "<init>", at = @At(value = "TAIL"))
	public void initScaleXYZ(CallbackInfo ci) {
		this.skullcraft$scaleX = 100;
		this.skullcraft$scaleY = 100;
		this.skullcraft$scaleZ = 100;
	}

	@Inject(method = "loadAdditional", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/entity/BlockEntity;loadAdditional(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/core/HolderLookup$Provider;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
	))
	public void loadScaleXYZ(CompoundTag nbt, HolderLookup.Provider provider, CallbackInfo ci) {
		if(nbt.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
			CompoundTag scaleTag = nbt.getCompound(SkullCraft.SCALE_TAG);
			this.skullcraft$scaleX = scaleTag.getInt("x");
			this.skullcraft$scaleY = scaleTag.getInt("y");
			this.skullcraft$scaleZ = scaleTag.getInt("z");
		} else {
			this.skullcraft$scaleX = this.skullcraft$scaleY = this.skullcraft$scaleZ = 100;
		}
	}

	@Inject(method = "saveAdditional", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/entity/BlockEntity;saveAdditional(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/core/HolderLookup$Provider;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
	))
	public void saveScaleXYZ(CompoundTag nbt, HolderLookup.Provider provider, CallbackInfo ci) {
		if(this.skullcraft$scaleX != 100 || this.skullcraft$scaleY != 100 || this.skullcraft$scaleZ != 100) {
			CompoundTag scaleTag = new CompoundTag();
			scaleTag.putInt("x", this.skullcraft$scaleX);
			scaleTag.putInt("y", this.skullcraft$scaleY);
			scaleTag.putInt("z", this.skullcraft$scaleZ);
			nbt.put(SkullCraft.SCALE_TAG, scaleTag);
		}
	}

	@Override
	public void skullcraft$setScaleXYZ(int scaleX, int scaleY, int scaleZ) {
		this.skullcraft$scaleX = scaleX;
		this.skullcraft$scaleY = scaleY;
		this.skullcraft$scaleZ = scaleZ;
	}

	@Override
	public int skullcraft$getScaleX() {
		return this.skullcraft$scaleX;
	}
	@Override
	public int skullcraft$getScaleY() {
		return this.skullcraft$scaleY;
	}
	@Override
	public int skullcraft$getScaleZ() {
		return this.skullcraft$scaleZ;
	}
}
