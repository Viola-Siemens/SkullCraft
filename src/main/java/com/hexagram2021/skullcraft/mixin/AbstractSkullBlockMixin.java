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

/**
 * 抽象头颅方块 Mixin，在方块放置时将物品中的缩放比例和附魔数据写入方块实体喵~
 */
@Mixin(Block.class)
public class AbstractSkullBlockMixin {
	/**
	 * 在方块放置时将物品中的缩放比例和附魔数据写入方块实体
	 * @param level 世界
	 * @param blockPos 方块位置
	 * @param blockState 方块状态
	 * @param entity 放置方块的玩家
	 * @param itemStack 放置方块的玩家手持的物品
	 * @param ci 回调信息
	 */
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
