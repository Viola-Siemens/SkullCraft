package com.hexagram2021.skullcraft.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegisterEvent;

import java.util.HashMap;
import java.util.Map;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCSounds {
	static final Map<ResourceLocation, SoundEvent> registeredEvents = new HashMap<>();

	public static final SoundEvent SKULL_CHARGER = registerSound("ui.skull_charger");

	public static final SoundEvent NOTE_BLOCK_IMITATE_VILLAGER = registerSound("block.note_block.imitate.villager");
	public static final SoundEvent NOTE_BLOCK_IMITATE_ILLAGER = registerSound("block.note_block.imitate.illager");
	public static final SoundEvent NOTE_BLOCK_IMITATE_WITCH = registerSound("block.note_block.imitate.witch");
	public static final SoundEvent NOTE_BLOCK_IMITATE_IRON_GOLEM = registerSound("block.note_block.imitate.iron_golem");
	public static final SoundEvent NOTE_BLOCK_IMITATE_ZOMBIE_VILLAGER = registerSound("block.note_block.imitate.zombie_villager");
	public static final SoundEvent NOTE_BLOCK_IMITATE_SLIME = registerSound("block.note_block.imitate.slime");
	public static final SoundEvent NOTE_BLOCK_IMITATE_LAVASLIME = registerSound("block.note_block.imitate.magma_cube");
	public static final SoundEvent NOTE_BLOCK_IMITATE_BLAZE = registerSound("block.note_block.imitate.blaze");
	public static final SoundEvent NOTE_BLOCK_IMITATE_SPIDER = registerSound("block.note_block.imitate.spider");
	public static final SoundEvent NOTE_BLOCK_IMITATE_PIG = registerSound("block.note_block.imitate.pig");
	public static final SoundEvent NOTE_BLOCK_IMITATE_ENDERMAN = registerSound("block.note_block.imitate.enderman");
	public static final SoundEvent NOTE_BLOCK_IMITATE_SNOW_GOLEM = registerSound("block.note_block.imitate.snow_golem");
	public static final SoundEvent NOTE_BLOCK_IMITATE_TECHNOBLADE = registerSound("block.note_block.imitate.technoblade");
	public static final SoundEvent NOTE_BLOCK_IMITATE_SHEEP = registerSound("block.note_block.imitate.sheep");
	public static final SoundEvent NOTE_BLOCK_IMITATE_BAT = registerSound("block.note_block.imitate.bat");
	public static final SoundEvent NOTE_BLOCK_IMITATE_SHULKER = registerSound("block.note_block.imitate.shulker");
	public static final SoundEvent NOTE_BLOCK_IMITATE_ALLAY = registerSound("block.note_block.imitate.allay");
	public static final SoundEvent NOTE_BLOCK_IMITATE_VEX = registerSound("block.note_block.imitate.vex");
	public static final SoundEvent NOTE_BLOCK_IMITATE_COW = registerSound("block.note_block.imitate.cow");
	public static final SoundEvent NOTE_BLOCK_IMITATE_PIGLIN_BRUTE = registerSound("block.note_block.imitate.piglin_brute");
	public static final SoundEvent NOTE_BLOCK_IMITATE_ZOMBIFIED_PIGLIN = registerSound("block.note_block.imitate.zombified_piglin");
	public static final SoundEvent NOTE_BLOCK_IMITATE_WARDEN = registerSound("block.note_block.imitate.warden");

	private static SoundEvent registerSound(String name) {
		ResourceLocation location = new ResourceLocation(MODID, name);
		SoundEvent event = SoundEvent.createVariableRangeEvent(location);
		registeredEvents.put(location, event);
		return event;
	}

	public static void init(RegisterEvent event) {
		event.register(Registries.SOUND_EVENT, helper -> registeredEvents.forEach(helper::register));
	}
}
