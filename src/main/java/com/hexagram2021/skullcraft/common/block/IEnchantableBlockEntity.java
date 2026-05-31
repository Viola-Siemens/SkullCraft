package com.hexagram2021.skullcraft.common.block;

import net.minecraft.world.item.enchantment.ItemEnchantments;

import javax.annotation.Nullable;

/**
 * 可附魔方块实体接口，为头颅方块实体提供附魔数据的存取能力喵~
 *
 * @author liudongyu
 */
public interface IEnchantableBlockEntity {
	/**
	 * 设置头颅的附魔数据喵~
	 *
	 * @param enchantments 附魔数据，可为 {@code null} 喵~
	 */
	void skullcraft$setEnchantments(@Nullable ItemEnchantments enchantments);
	/**
	 * 获取头颅的附魔数据喵~
	 *
	 * @return 附魔数据，可能为 {@code null} 喵~
	 */
	@Nullable
	ItemEnchantments skullcraft$getEnchantments();

	/**
	 * 设置头颅的修复惩罚喵~
	 *
	 * @param repairCost 修复惩罚值，可为 {@code null} 喵~
	 */
	void skullcraft$setRepairCost(@Nullable Integer repairCost);
	/**
	 * 获取头颅的修复惩罚喵~
	 *
	 * @return 修复惩罚值，可能为 {@code null} 喵~
	 */
	@Nullable
	Integer skullcraft$getRepairCost();
}
