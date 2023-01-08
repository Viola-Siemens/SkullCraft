package com.hexagram2021.skullcraft.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCSounds {
	private static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

	public static final RegistryObject<SoundEvent> SKULL_CHARGER = registerSound("ui.skull_charger");

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	@SuppressWarnings("SameParameterValue")
	private static RegistryObject<SoundEvent> registerSound(String name) {
		return REGISTER.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, name)));
	}
}
