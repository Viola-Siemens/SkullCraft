package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.common.crafting.SkullChargerMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCContainerTypes {
	private static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(Registries.MENU, MODID);

	public static final DeferredHolder<MenuType<?>, MenuType<SkullChargerMenu>> SKULL_CHARGER_MENU = REGISTER.register(
			"skull_charger", () -> new MenuType<>(SkullChargerMenu::new, FeatureFlags.VANILLA_SET)
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
