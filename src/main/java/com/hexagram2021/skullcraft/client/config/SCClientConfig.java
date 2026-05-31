package com.hexagram2021.skullcraft.client.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * 客户端配置类，提供自定义头颅大小渲染、隐藏原版头部和帽子等视觉选项喵~
 *
 * @author liudongyu
 */
public class SCClientConfig {
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	private static final ModConfigSpec SPEC;

	/** 是否启用自定义头颅大小渲染喵~ */
	public static final ModConfigSpec.BooleanValue ENABLE_CUSTOM_SKULL_SIZE;
	/** 是否隐藏穿戴头颅时的原版头部渲染喵~ */
	public static final ModConfigSpec.BooleanValue HIDE_ORIGINAL_HEAD;
	/** 是否隐藏穿戴头颅时的原版帽子渲染喵~ */
	public static final ModConfigSpec.BooleanValue HIDE_ORIGINAL_HAT;

	static {
		BUILDER.push("skull_craft-client-config");
			ENABLE_CUSTOM_SKULL_SIZE = BUILDER.comment("If false, you won't see the custom size changes of skulls.").define("ENABLE_CUSTOM_SKULL_SIZE", true);
			HIDE_ORIGINAL_HEAD = BUILDER.comment("If true, the original head of players, villagers and other mobs will not be rendered when wearing a skull.").define("HIDE_ORIGINAL_HEAD", true);
			HIDE_ORIGINAL_HAT = BUILDER.comment("If true, the original hat of players, villagers and other mobs will not be rendered when wearing a skull.").define("HIDE_ORIGINAL_HAT", true);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

	private SCClientConfig() {}

	/**
	 * 获取客户端配置规范喵~
	 *
	 * @return 配置规范喵~
	 */
	public static ModConfigSpec getConfig() {
		return SPEC;
	}
}
