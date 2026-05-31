package com.hexagram2021.skullcraft.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 附魔标签定义类，定义模组中所有自定义附魔标签喵~
 *
 * @author liudongyu
 */
public final class SCEnchantmentTags {
	/** 可在头颅充能器中使用的附魔标签喵~ */
	public static final TagKey<Enchantment> IN_SKULL_CHARGER = create("in_skull_charger");

	private SCEnchantmentTags() {
	}

	@SuppressWarnings("SameParameterValue")
	private static TagKey<Enchantment> create(String name) {
		return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MODID, name));
	}
}
