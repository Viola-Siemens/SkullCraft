package com.hexagram2021.skullcraft.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public final class SCItemTags {
	public static final TagKey<Item> KOPIS_TOOL_MATERIALS = create("kopis_tool_materials");
	public static final TagKey<Item> ENCHANTABLE_SKULLS = create("enchantable/skulls");

	private SCItemTags() {
	}

	private static TagKey<Item> create(String name) {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, name));
	}
}
