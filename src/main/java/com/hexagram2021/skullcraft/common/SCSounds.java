package com.hexagram2021.skullcraft.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.HashMap;
import java.util.Map;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 模组音效注册类，定义并注册所有自定义音效事件喵~
 *
 * @author liudongyu
 */
public final class SCSounds {
	/** 已注册的音效事件映射表喵~ */
	static final Map<ResourceLocation, SoundEvent> registeredEvents = new HashMap<>();

	/** 头颅充能器 UI 音效喵~ */
	public static final SoundEvent SKULL_CHARGER = registerSound("ui.skull_charger");

	/** 音符盒模仿村民音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_VILLAGER = registerSound("block.note_block.imitate.villager");
	/** 音符盒模仿灾厄村民音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_ILLAGER = registerSound("block.note_block.imitate.illager");
	/** 音符盒模仿女巫音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_WITCH = registerSound("block.note_block.imitate.witch");
	/** 音符盒模仿铁傀儡音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_IRON_GOLEM = registerSound("block.note_block.imitate.iron_golem");
	/** 音符盒模仿僵尸村民音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_ZOMBIE_VILLAGER = registerSound("block.note_block.imitate.zombie_villager");
	/** 音符盒模仿史莱姆音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_SLIME = registerSound("block.note_block.imitate.slime");
	/** 音符盒模仿岩浆怪音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_LAVASLIME = registerSound("block.note_block.imitate.magma_cube");
	/** 音符盒模仿烈焰人音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_BLAZE = registerSound("block.note_block.imitate.blaze");
	/** 音符盒模仿蜘蛛音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_SPIDER = registerSound("block.note_block.imitate.spider");
	/** 音符盒模仿猪音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_PIG = registerSound("block.note_block.imitate.pig");
	/** 音符盒模仿末影人音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_ENDERMAN = registerSound("block.note_block.imitate.enderman");
	/** 音符盒模仿雪傀儡音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_SNOW_GOLEM = registerSound("block.note_block.imitate.snow_golem");
	/** 音符盒模仿旋风人音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_BREEZE = registerSound("block.note_block.imitate.breeze");
	/** 音符盒模仿 Technoblade 音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_TECHNOBLADE = registerSound("block.note_block.imitate.technoblade");
	/** 音符盒模仿绵羊音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_SHEEP = registerSound("block.note_block.imitate.sheep");
	/** 音符盒模仿蝙蝠音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_BAT = registerSound("block.note_block.imitate.bat");
	/** 音符盒模仿潜影贝音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_SHULKER = registerSound("block.note_block.imitate.shulker");
	/** 音符盒模仿悦灵音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_ALLAY = registerSound("block.note_block.imitate.allay");
	/** 音符盒模仿恼鬼音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_VEX = registerSound("block.note_block.imitate.vex");
	/** 音符盒模仿狼音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_WOLF = registerSound("block.note_block.imitate.wolf");
	/** 音符盒模仿愤怒的狼音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_ANGRY_WOLF = registerSound("block.note_block.imitate.angry_wolf");
	/** 音符盒模仿牛音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_COW = registerSound("block.note_block.imitate.cow");
	/** 音符盒模仿猪灵蛮兵音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_PIGLIN_BRUTE = registerSound("block.note_block.imitate.piglin_brute");
	/** 音符盒模仿僵尸猪灵音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_ZOMBIFIED_PIGLIN = registerSound("block.note_block.imitate.zombified_piglin");
	/** 音符盒模仿马音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_HORSE = registerSound("block.note_block.imitate.horse");
	/** 音符盒模仿驴音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_DONKEY = registerSound("block.note_block.imitate.donkey");
	/** 音符盒模仿骡音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_MULE = registerSound("block.note_block.imitate.mule");
	/** 音符盒模仿骷髅马音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_SKELETON_HORSE = registerSound("block.note_block.imitate.skeleton_horse");
	/** 音符盒模仿僵尸马音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_ZOMBIE_HORSE = registerSound("block.note_block.imitate.zombie_horse");
	/** 音符盒模仿监守者音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_WARDEN = registerSound("block.note_block.imitate.warden");
	/** 音符盒模仿疣猪兽音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_HOGLIN = registerSound("block.note_block.imitate.hoglin");
	/** 音符盒模仿僵尸疣猪兽音效喵~ */
	public static final SoundEvent NOTE_BLOCK_IMITATE_ZOGLIN = registerSound("block.note_block.imitate.zoglin");

	/**
	 * 注册单个音效事件喵~
	 *
	 * @param name 音效名称喵~
	 * @return 注册的音效事件喵~
	 */
	private static SoundEvent registerSound(String name) {
		ResourceLocation location = ResourceLocation.fromNamespaceAndPath(MODID, name);
		SoundEvent event = SoundEvent.createVariableRangeEvent(location);
		registeredEvents.put(location, event);
		return event;
	}

	/**
	 * 将所有已注册的音效事件提交到注册表中喵~
	 *
	 * @param event 注册事件喵~
	 */
	public static void init(RegisterEvent event) {
		event.register(Registries.SOUND_EVENT, helper -> registeredEvents.forEach(helper::register));
	}

	private SCSounds() {
	}
}
