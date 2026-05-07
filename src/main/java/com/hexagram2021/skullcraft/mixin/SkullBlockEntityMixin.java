package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.common.block.IEnchantableBlockEntity;
import com.hexagram2021.skullcraft.common.block.IScalableBlockEntity;
import com.hexagram2021.skullcraft.common.components.SkullScale;
import com.hexagram2021.skullcraft.common.register.SCDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(SkullBlockEntity.class)
public class SkullBlockEntityMixin implements IScalableBlockEntity, IEnchantableBlockEntity {
	@Unique
	private int skullcraft$scaleX;
	@Unique
	private int skullcraft$scaleY;
	@Unique
	private int skullcraft$scaleZ;

	@Unique @Nullable
	private ItemEnchantments skullcraft$enchantments;

	@Unique @Nullable
	private Integer skullcraft$repairCost;

	@Inject(method = "<init>", at = @At(value = "TAIL"))
	public void skullcraft$initScaleXYZ(CallbackInfo ci) {
		this.skullcraft$scaleX = 100;
		this.skullcraft$scaleY = 100;
		this.skullcraft$scaleZ = 100;
		this.skullcraft$enchantments = null;
		this.skullcraft$repairCost = null;
	}

	@Inject(method = "loadAdditional", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/entity/BlockEntity;loadAdditional(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/core/HolderLookup$Provider;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
	))
	public void skullcraft$loadScaleXYZ(CompoundTag nbt, HolderLookup.Provider provider, CallbackInfo ci) {
		if(nbt.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
			CompoundTag scaleTag = nbt.getCompound(SkullCraft.SCALE_TAG);
			this.skullcraft$scaleX = scaleTag.getInt("x");
			this.skullcraft$scaleY = scaleTag.getInt("y");
			this.skullcraft$scaleZ = scaleTag.getInt("z");
		} else {
			this.skullcraft$scaleX = this.skullcraft$scaleY = this.skullcraft$scaleZ = 100;
		}
		if(nbt.contains(SkullCraft.ENCHANTMENTS_TAG, Tag.TAG_COMPOUND)) {
			this.skullcraft$enchantments = ItemEnchantments.CODEC.decode(NbtOps.INSTANCE, nbt.getCompound(SkullCraft.ENCHANTMENTS_TAG)).getOrThrow().getFirst();
		} else {
			this.skullcraft$enchantments = null;
		}
		if(nbt.contains(SkullCraft.REPAIR_COST_TAG, Tag.TAG_ANY_NUMERIC)) {
			this.skullcraft$repairCost = nbt.getInt(SkullCraft.REPAIR_COST_TAG);
		} else {
			this.skullcraft$repairCost = null;
		}
	}

	@Inject(method = "saveAdditional", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/entity/BlockEntity;saveAdditional(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/core/HolderLookup$Provider;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
	))
	public void skullcraft$saveScaleXYZ(CompoundTag nbt, HolderLookup.Provider provider, CallbackInfo ci) {
		if(this.skullcraft$scaleX != 100 || this.skullcraft$scaleY != 100 || this.skullcraft$scaleZ != 100) {
			CompoundTag scaleTag = new CompoundTag();
			scaleTag.putInt("x", this.skullcraft$scaleX);
			scaleTag.putInt("y", this.skullcraft$scaleY);
			scaleTag.putInt("z", this.skullcraft$scaleZ);
			nbt.put(SkullCraft.SCALE_TAG, scaleTag);
		}
	}

	@Inject(method = "applyImplicitComponents", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/entity/BlockEntity;applyImplicitComponents(Lnet/minecraft/world/level/block/entity/BlockEntity$DataComponentInput;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
	))
	public void skullcraft$loadSkullCraftComponents(BlockEntity.DataComponentInput componentInput, CallbackInfo ci) {
		SkullScale skullScale = componentInput.getOrDefault(SCDataComponents.SKULL_SCALE, SkullScale.DEFAULT);
		this.skullcraft$scaleX = skullScale.x();
		this.skullcraft$scaleY = skullScale.y();
		this.skullcraft$scaleZ = skullScale.z();
		this.skullcraft$enchantments = componentInput.get(DataComponents.ENCHANTMENTS);
		this.skullcraft$repairCost = componentInput.get(DataComponents.REPAIR_COST);
	}

	@Inject(method = "collectImplicitComponents", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/entity/BlockEntity;collectImplicitComponents(Lnet/minecraft/core/component/DataComponentMap$Builder;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
	))
	public void skullcraft$saveSkullCraftComponents(DataComponentMap.Builder components, CallbackInfo ci) {
		components.set(SCDataComponents.SKULL_SCALE, new SkullScale(this.skullcraft$scaleX, this.skullcraft$scaleY, this.skullcraft$scaleZ));
		components.set(DataComponents.ENCHANTMENTS, this.skullcraft$enchantments);
		components.set(DataComponents.REPAIR_COST, this.skullcraft$repairCost);
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

	@Override
	public void skullcraft$setEnchantments(@Nullable ItemEnchantments enchantments) {
		this.skullcraft$enchantments = enchantments;
	}
	@Override @Nullable
	public ItemEnchantments skullcraft$getEnchantments() {
		return this.skullcraft$enchantments;
	}

	@Override
	public void skullcraft$setRepairCost(@Nullable Integer repairCost) {
		this.skullcraft$repairCost = repairCost;
	}
	@Override @Nullable
	public Integer skullcraft$getRepairCost() {
		return this.skullcraft$repairCost;
	}
}
