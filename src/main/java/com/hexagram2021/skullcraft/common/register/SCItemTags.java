package com.hexagram2021.skullcraft.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 物品标签定义类，定义模组中所有自定义物品标签喵~
 *
 * @author liudongyu
 */
public final class SCItemTags {
	/** Kopis 剑工具材料标签喵~ */
	public static final TagKey<Item> KOPIS_TOOL_MATERIALS = create("kopis_tool_materials");
	/** 可附魔头颅标签喵~ */
	public static final TagKey<Item> ENCHANTABLE_SKULLS = create("enchantable/skulls");

	private SCItemTags() {
	}

	private static TagKey<Item> create(String name) {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, name));
	}
}
