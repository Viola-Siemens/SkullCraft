package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.common.block.IEnchantableBlockEntity;
import com.hexagram2021.skullcraft.common.block.IScalableBlockEntity;
import com.hexagram2021.skullcraft.common.components.SkullScale;
import com.hexagram2021.skullcraft.common.register.SCDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
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
	public void setSkullScaleAndEnchantments(Level level, BlockPos blockPos, BlockState blockState, LivingEntity entity, ItemStack itemStack, CallbackInfo ci) {
		if((Block)(Object)this instanceof AbstractSkullBlock) {
			BlockEntity blockEntity = level.getBlockEntity(blockPos);
			if (blockEntity instanceof SkullBlockEntity skullBlockEntity) {
				SkullScale skullScale = itemStack.get(SCDataComponents.SKULL_SCALE.get());
				if (skullScale != null) {
					((IScalableBlockEntity)skullBlockEntity).skullcraft$setScaleXYZ(skullScale.x(), skullScale.y(), skullScale.z());
				}
				IEnchantableBlockEntity enchantableBlockEntity = (IEnchantableBlockEntity)skullBlockEntity;
				enchantableBlockEntity.skullcraft$setEnchantments(itemStack.get(DataComponents.ENCHANTMENTS));
				enchantableBlockEntity.skullcraft$setRepairCost(itemStack.get(DataComponents.REPAIR_COST));
			}
		}
	}
}
