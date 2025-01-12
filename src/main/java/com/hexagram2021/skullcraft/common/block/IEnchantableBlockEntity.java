package com.hexagram2021.skullcraft.common.block;

import net.minecraft.world.item.enchantment.ItemEnchantments;

import javax.annotation.Nullable;

public interface IEnchantableBlockEntity {
	void skullcraft$setEnchantments(@Nullable ItemEnchantments enchantments);
	@Nullable
	ItemEnchantments skullcraft$getEnchantments();

	void skullcraft$setRepairCost(@Nullable Integer repairCost);
	@Nullable
	Integer skullcraft$getRepairCost();
}
