package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.common.components.SkullScale;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 数据组件注册类，负责注册模组中所有自定义数据组件类型喵~
 *
 * @author liudongyu
 */
public final class SCDataComponents {
	private static final DeferredRegister<DataComponentType<?>> REGISTER = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MODID);

	/** 头颅缩放比例数据组件喵~ */
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<SkullScale>> SKULL_SCALE = REGISTER.register(
			"head_scale",
			() -> DataComponentType.<SkullScale>builder()
					.persistent(SkullScale.CODEC)
					.networkSynchronized(SkullScale.STREAM_CODEC)
					.build()
	);

	/**
	 * 将数据组件类型注册到事件总线喵~
	 *
	 * @param bus 模组事件总线喵~
	 */
	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	private SCDataComponents() {
	}
}
