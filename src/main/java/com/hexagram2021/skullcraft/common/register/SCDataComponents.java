package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.common.components.SkullScale;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCDataComponents {
	private static final DeferredRegister<DataComponentType<?>> REGISTER = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MODID);

	public static final RegistryObject<DataComponentType<SkullScale>> SKULL_SCALE = REGISTER.register(
			SkullCraft.SCALE_TAG,
			() -> DataComponentType.<SkullScale>builder()
					.persistent(SkullScale.CODEC)
					.networkSynchronized(SkullScale.STREAM_CODEC)
					.build()
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
