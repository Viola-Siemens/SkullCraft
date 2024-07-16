package com.hexagram2021.skullcraft.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@SuppressWarnings("unused")
public class SCCreativeModeTabs {
	private static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKULL_CRAFT = register(
			"skull_craft", Component.translatable("itemGroup.skullcraft"), () -> new ItemStack(SCBlocks.SKULL_CHARGER),
			(parameters, output) -> SCItems.ItemEntry.REGISTERED_ITEMS.forEach(output::accept)
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	@SuppressWarnings("SameParameterValue")
	private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String name, Component title, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator generator) {
		return REGISTER.register(name, () -> CreativeModeTab.builder().title(title).icon(icon).displayItems(generator).withTabsBefore(CreativeModeTabs.SPAWN_EGGS).build());
	}
}
