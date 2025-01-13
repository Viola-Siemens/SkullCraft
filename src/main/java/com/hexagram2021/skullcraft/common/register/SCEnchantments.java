package com.hexagram2021.skullcraft.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public final class SCEnchantments {
	public static final ResourceKey<Enchantment> COMBAT_PROTECTION = key("combat_protection");
	public static final ResourceKey<Enchantment> GROUND_STRIKE = key("ground_strike");

	private static ResourceKey<Enchantment> key(String name) {
		return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MODID, name));
	}

	private SCEnchantments() {
	}
}
