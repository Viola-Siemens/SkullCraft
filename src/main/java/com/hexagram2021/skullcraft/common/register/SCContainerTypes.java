package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.common.crafting.SkullChargerMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 容器类型注册类，负责注册模组中所有自定义菜单类型喵~
 *
 * @author liudongyu
 */
public final class SCContainerTypes {
	private static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(Registries.MENU, MODID);

	/** 头颅充能器菜单类型喵~ */
	public static final DeferredHolder<MenuType<?>, MenuType<SkullChargerMenu>> SKULL_CHARGER_MENU = REGISTER.register(
			"skull_charger", () -> new MenuType<>(SkullChargerMenu::new, FeatureFlags.VANILLA_SET)
	);

	/**
	 * 将菜单类型注册到事件总线喵~
	 *
	 * @param bus 模组事件总线喵~
	 */
	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	private SCContainerTypes() {
	}
}
