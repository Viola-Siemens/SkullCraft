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

/**
 * 创造模式标签页注册类，负责注册模组的创造模式物品栏标签页喵~
 *
 * @author liudongyu
 */
@SuppressWarnings("unused")
public final class SCCreativeModeTabs {
	private static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

	/** SkullCraft 模组创造模式标签页喵~ */
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKULL_CRAFT = register(
			"skull_craft", Component.translatable("itemGroup.skullcraft"), () -> new ItemStack(SCBlocks.SKULL_CHARGER),
			(parameters, output) -> SCItems.ItemEntry.registeredItems().forEach(output::accept)
	);

	/**
	 * 将创造模式标签页注册到事件总线喵~
	 *
	 * @param bus 模组事件总线喵~
	 */
	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	/**
	 * 注册一个创造模式标签页喵~
	 *
	 * @param name      标签页注册名喵~
	 * @param title     标签页标题喵~
	 * @param icon      标签页图标供应商喵~
	 * @param generator 物品展示生成器喵~
	 * @return 注册后的标签页持有者喵~
	 */
	@SuppressWarnings("SameParameterValue")
	private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String name, Component title, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator generator) {
		return REGISTER.register(name, () -> CreativeModeTab.builder().title(title).icon(icon).displayItems(generator).withTabsBefore(CreativeModeTabs.SPAWN_EGGS).build());
	}

	private SCCreativeModeTabs() {
	}
}
