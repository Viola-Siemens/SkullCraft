package com.hexagram2021.skullcraft.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 附魔注册键定义类，定义模组中所有自定义附魔的资源键喵~
 *
 * @author liudongyu
 */
public final class SCEnchantments {
	/** 战斗保护附魔喵~ */
	public static final ResourceKey<Enchantment> COMBAT_PROTECTION = key("combat_protection");
	/** 地面打击附魔喵~ */
	public static final ResourceKey<Enchantment> GROUND_STRIKE = key("ground_strike");
	/** 耐力提升附魔喵~ */
	public static final ResourceKey<Enchantment> STAMINA_BOOST = key("stamina_boost");

	private static ResourceKey<Enchantment> key(String name) {
		return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MODID, name));
	}

	private SCEnchantments() {
	}
}
