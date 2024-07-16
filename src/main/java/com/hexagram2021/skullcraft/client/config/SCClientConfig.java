package com.hexagram2021.skullcraft.client.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SCClientConfig {
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	private static final ModConfigSpec SPEC;

	public static final ModConfigSpec.BooleanValue ENABLE_CUSTOM_SKULL_SIZE;
	public static final ModConfigSpec.BooleanValue HIDE_ORIGINAL_HEAD;
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

	public static ModConfigSpec getConfig() {
		return SPEC;
	}
}
