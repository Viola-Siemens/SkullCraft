package com.hexagram2021.skullcraft.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SCSounds {
	static Set<SoundEvent> registeredEvents = new HashSet<>();

	public static final SoundEvent SKULL_CHARGER = registerSound("ui.skull_charger");

	private static SoundEvent registerSound(String name) {
		ResourceLocation location = new ResourceLocation(MODID, name);
		SoundEvent event = new SoundEvent(location);
		registeredEvents.add(event.setRegistryName(location));
		return event;
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> evt) {
		for(SoundEvent event : registeredEvents)
			evt.getRegistry().register(event);
	}
}
