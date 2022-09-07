package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.common.block.Scaleable;
import com.hexagram2021.skullcraft.common.block.entity.AbstractSkullCraftSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class AbstractSkullBlockMixin {
	@Inject(method = "setPlacedBy", at = @At(value = "TAIL"))
	@SuppressWarnings("ConstantConditions")
	public void setSkullScaleXYZ(Level level, BlockPos blockPos, BlockState blockState, LivingEntity entity, ItemStack itemStack, CallbackInfo ci) {
		if((Block)(Object)this instanceof AbstractSkullBlock) {
			BlockEntity blockEntity = level.getBlockEntity(blockPos);
			if (blockEntity instanceof SkullBlockEntity skullBlockEntity) {	//Vanilla
				if (itemStack.hasTag()) {
					CompoundTag nbt = itemStack.getTag();
					if (nbt.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
						CompoundTag scaleTag = nbt.getCompound(SkullCraft.SCALE_TAG);
						((Scaleable)skullBlockEntity).setScaleXYZ(scaleTag.getInt("x"), scaleTag.getInt("y"), scaleTag.getInt("z"));
					}
				}
			} else if(blockEntity instanceof AbstractSkullCraftSkullBlockEntity<?> skullBlockEntity) {	//SC
				if (itemStack.hasTag()) {
					CompoundTag nbt = itemStack.getTag();
					if (nbt.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
						CompoundTag scaleTag = nbt.getCompound(SkullCraft.SCALE_TAG);
						((Scaleable)skullBlockEntity).setScaleXYZ(scaleTag.getInt("x"), scaleTag.getInt("y"), scaleTag.getInt("z"));
					}
				}
			}
		}
	}
}
