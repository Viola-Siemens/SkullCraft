package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.common.crafting.SkullChargerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCContainerTypes {
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);

	public static final RegistryObject<MenuType<SkullChargerMenu>> SKULL_CHARGER_MENU = REGISTER.register(
			"skull_charger", () -> new MenuType<>(SkullChargerMenu::new)
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
