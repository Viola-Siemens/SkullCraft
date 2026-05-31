package com.hexagram2021.skullcraft.common.register;

import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 物品注册类，负责注册模组中所有自定义物品，包括各类头颅物品、Kopis 剑及其组件喵~
 *
 * @author liudongyu
 */
@SuppressWarnings("unused")
public final class SCItems {
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(Registries.ITEM, MODID);

	/** 头颅充能器物品喵~ */
	public static final ItemEntry<BlockItem> SKULL_CHARGER = new ItemEntry<>(
			"skull_charger", new Item.Properties().useBlockDescriptionPrefix(), props -> new BlockItem(SCBlocks.SKULL_CHARGER.get(), props) {
				@Override
				public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, List<Component> components, TooltipFlag flag) {
					components.add(Component.translatable("desc.skullcraft.skull_charger").withStyle(ChatFormatting.GRAY));
				}
			}
	);

	/** Kopis 剑的工具等级喵~ */
	public static final ToolMaterial KOPIS_TIER = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1145, 0.0F, 6.0F, 10, SCItemTags.KOPIS_TOOL_MATERIALS);

	/** Kopis 剑物品喵~ */
	public static final ItemEntry<SwordItem> KOPIS = new ItemEntry<>("kopis", new Item.Properties(), props -> new SwordItem(KOPIS_TIER, 3, -3.6F, props) {
		@Override
		public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, List<Component> components, TooltipFlag flag) {
			components.add(Component.translatable("desc.skullcraft.kopis").withStyle(ChatFormatting.GRAY));
		}
	});
	/** Kopis 剑刃物品喵~ */
	public static final ItemEntry<Item> KOPIS_BLADE = new ItemEntry<>(
			"kopis_blade", new Item.Properties(), Item::new
	);
	/** Kopis 剑柄物品喵~ */
	public static final ItemEntry<Item> KOPIS_HILT = new ItemEntry<>(
			"kopis_hilt", new Item.Properties(), Item::new
	);
	/** 头颅附魔珠物品喵~ */
	public static final ItemEntry<Item> SKULL_ENCHANTING_BEAD = new ItemEntry<>(
			"skull_enchanting_bead", new Item.Properties(), Item::new
	);

	/**
	 * 人形生物头颅物品集合喵~
	 */
	public static class HumanSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> VILLAGER_HEAD = new ItemEntry<>(
				"villager_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.VILLAGER_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ILLAGER_HEAD = new ItemEntry<>(
				"illager_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.ILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ILLAGER_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WITCH_HEAD = new ItemEntry<>(
				"witch_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.WITCH_HEAD.get(), SCBlocks.HumanSkulls.WITCH_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> IRON_GOLEM_HEAD = new ItemEntry<>(
				"iron_golem_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.IRON_GOLEM_HEAD.get(), SCBlocks.HumanSkulls.IRON_GOLEM_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ZOMBIE_VILLAGER_HEAD = new ItemEntry<>(
				"zombie_villager_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_WALL_HEAD.get(), Direction.DOWN, props
				)
		);

		private HumanSkulls() {}

		private static void init() {
			// Lazy init
		}
	}

	/**
	 * 立方体形生物头颅物品集合喵~
	 */
	public static class CubeSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> SLIME_HEAD = new ItemEntry<>(
				"slime_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.SLIME_HEAD.get(), SCBlocks.CubeSkulls.SLIME_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> LAVASLIME_HEAD = new ItemEntry<>(
				"lavaslime_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.LAVASLIME_HEAD.get(), SCBlocks.CubeSkulls.LAVASLIME_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> BLAZE_HEAD = new ItemEntry<>(
				"blaze_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.BLAZE_HEAD.get(), SCBlocks.CubeSkulls.BLAZE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> SPIDER_HEAD = new ItemEntry<>(
				"spider_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.SPIDER_HEAD.get(), SCBlocks.CubeSkulls.SPIDER_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> CAVE_SPIDER_HEAD = new ItemEntry<>(
				"cave_spider_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.CAVE_SPIDER_HEAD.get(), SCBlocks.CubeSkulls.CAVE_SPIDER_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> PIG_HEAD = new ItemEntry<>(
				"pig_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.PIG_HEAD.get(), SCBlocks.CubeSkulls.PIG_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WOLF_HEAD = new ItemEntry<>(
				"wolf_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.WOLF_HEAD.get(), SCBlocks.CubeSkulls.WOLF_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WOLF_ASHEN_HEAD = new ItemEntry<>(
				"wolf_ashen_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.WOLF_ASHEN_HEAD.get(), SCBlocks.CubeSkulls.WOLF_ASHEN_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WOLF_BLACK_HEAD = new ItemEntry<>(
				"wolf_black_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.WOLF_BLACK_HEAD.get(), SCBlocks.CubeSkulls.WOLF_BLACK_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WOLF_CHESTNUT_HEAD = new ItemEntry<>(
				"wolf_chestnut_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.WOLF_CHESTNUT_HEAD.get(), SCBlocks.CubeSkulls.WOLF_CHESTNUT_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WOLF_RUSTY_HEAD = new ItemEntry<>(
				"wolf_rusty_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.WOLF_RUSTY_HEAD.get(), SCBlocks.CubeSkulls.WOLF_RUSTY_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WOLF_SNOWY_HEAD = new ItemEntry<>(
				"wolf_snowy_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.WOLF_SNOWY_HEAD.get(), SCBlocks.CubeSkulls.WOLF_SNOWY_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WOLF_SPOTTED_HEAD = new ItemEntry<>(
				"wolf_spotted_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.WOLF_SPOTTED_HEAD.get(), SCBlocks.CubeSkulls.WOLF_SPOTTED_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WOLF_STRIPED_HEAD = new ItemEntry<>(
				"wolf_striped_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.WOLF_STRIPED_HEAD.get(), SCBlocks.CubeSkulls.WOLF_STRIPED_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WOLF_WOODS_HEAD = new ItemEntry<>(
				"wolf_woods_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.WOLF_WOODS_HEAD.get(), SCBlocks.CubeSkulls.WOLF_WOODS_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ANGRY_WOLF_HEAD = new ItemEntry<>(
				"angry_wolf_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ANGRY_WOLF_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ANGRY_WOLF_ASHEN_HEAD = new ItemEntry<>(
				"angry_wolf_ashen_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ANGRY_WOLF_ASHEN_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_ASHEN_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ANGRY_WOLF_BLACK_HEAD = new ItemEntry<>(
				"angry_wolf_black_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ANGRY_WOLF_BLACK_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_BLACK_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ANGRY_WOLF_CHESTNUT_HEAD = new ItemEntry<>(
				"angry_wolf_chestnut_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ANGRY_WOLF_CHESTNUT_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_CHESTNUT_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ANGRY_WOLF_RUSTY_HEAD = new ItemEntry<>(
				"angry_wolf_rusty_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ANGRY_WOLF_RUSTY_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_RUSTY_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ANGRY_WOLF_SNOWY_HEAD = new ItemEntry<>(
				"angry_wolf_snowy_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ANGRY_WOLF_SNOWY_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_SNOWY_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ANGRY_WOLF_SPOTTED_HEAD = new ItemEntry<>(
				"angry_wolf_spotted_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ANGRY_WOLF_SPOTTED_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_SPOTTED_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ANGRY_WOLF_STRIPED_HEAD = new ItemEntry<>(
				"angry_wolf_striped_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ANGRY_WOLF_STRIPED_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_STRIPED_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ANGRY_WOLF_WOODS_HEAD = new ItemEntry<>(
				"angry_wolf_woods_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ANGRY_WOLF_WOODS_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_WOODS_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ENDERMAN_HEAD = new ItemEntry<>(
				"enderman_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ENDERMAN_HEAD.get(), SCBlocks.CubeSkulls.ENDERMAN_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> SNOW_GOLEM_HEAD = new ItemEntry<>(
				"snow_golem_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.SNOW_GOLEM_HEAD.get(), SCBlocks.CubeSkulls.SNOW_GOLEM_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> BREEZE_HEAD = new ItemEntry<>(
				"breeze_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.BREEZE_HEAD.get(), SCBlocks.CubeSkulls.BREEZE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> TECHNOBLADE_HEAD = new ItemEntry<>(
				"technoblade_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.TECHNOBLADE_HEAD.get(), SCBlocks.CubeSkulls.TECHNOBLADE_WALL_HEAD.get(), Direction.DOWN, props
				) {
					@Override
					public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, List<Component> components, TooltipFlag flag) {
						components.add(Component.translatable("desc.skullcraft.technoblade_head").withStyle(ChatFormatting.GRAY));
					}
				}
		);

		private CubeSkulls() {}

		private static void init() {
			// Lazy init
		}
	}

	/**
	 * 小型立方体形生物头颅物品集合喵~
	 */
	public static class SmallCubeSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> SHEEP_HEAD = new ItemEntry<>(
				"sheep_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.SHEEP_HEAD.get(), SCBlocks.SmallCubeSkulls.SHEEP_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> BAT_HEAD = new ItemEntry<>(
				"bat_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.BAT_HEAD.get(), SCBlocks.SmallCubeSkulls.BAT_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> SHULKER_HEAD = new ItemEntry<>(
				"shulker_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.SHULKER_HEAD.get(), SCBlocks.SmallCubeSkulls.SHULKER_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ALLAY_HEAD = new ItemEntry<>(
				"allay_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.ALLAY_HEAD.get(), SCBlocks.SmallCubeSkulls.ALLAY_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> VEX_HEAD = new ItemEntry<>(
				"vex_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.VEX_HEAD.get(), SCBlocks.SmallCubeSkulls.VEX_WALL_HEAD.get(), Direction.DOWN, props
				)
		);


		private SmallCubeSkulls() {}

		private static void init() {
			// Lazy init
		}
	}

	/**
	 * 牛形生物头颅物品集合喵~
	 */
	public static class CowSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> COW_HEAD = new ItemEntry<>(
				"cow_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CowSkulls.COW_HEAD.get(), SCBlocks.CowSkulls.COW_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> RED_MOOSHROOM_HEAD = new ItemEntry<>(
				"red_mooshroom_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CowSkulls.RED_MOOSHROOM_HEAD.get(), SCBlocks.CowSkulls.RED_MOOSHROOM_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> BROWN_MOOSHROOM_HEAD = new ItemEntry<>(
				"brown_mooshroom_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.CowSkulls.BROWN_MOOSHROOM_HEAD.get(), SCBlocks.CowSkulls.BROWN_MOOSHROOM_WALL_HEAD.get(), Direction.DOWN, props
				)
		);


		private CowSkulls() {}

		private static void init() {
			// Lazy init
		}
	}

	/**
	 * 猪灵形生物头颅物品集合喵~
	 */
	public static class PiglinSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> PIGLIN_BRUTE_HEAD = new ItemEntry<>(
				"piglin_brute_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.PiglinSkulls.PIGLIN_BRUTE_HEAD.get(), SCBlocks.PiglinSkulls.PIGLIN_BRUTE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ZOMBIFIED_PIGLIN_HEAD = new ItemEntry<>(
				"zombified_piglin_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.PiglinSkulls.ZOMBIFIED_PIGLIN_HEAD.get(), SCBlocks.PiglinSkulls.ZOMBIFIED_PIGLIN_WALL_HEAD.get(), Direction.DOWN, props
				)
		);

		private PiglinSkulls() {}

		private static void init() {
			// Lazy init
		}
	}

	/**
	 * 马形生物头颅物品集合喵~
	 */
	public static class HorseSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> BLACK_HORSE_HEAD = new ItemEntry<>(
				"black_horse_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.BLACK_HORSE_HEAD.get(), SCBlocks.HorseSkulls.BLACK_HORSE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> BROWN_HORSE_HEAD = new ItemEntry<>(
				"brown_horse_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.BROWN_HORSE_HEAD.get(), SCBlocks.HorseSkulls.BROWN_HORSE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> CHESTNUT_HORSE_HEAD = new ItemEntry<>(
				"chestnut_horse_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.CHESTNUT_HORSE_HEAD.get(), SCBlocks.HorseSkulls.CHESTNUT_HORSE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> CREAMY_HORSE_HEAD = new ItemEntry<>(
				"creamy_horse_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.CREAMY_HORSE_HEAD.get(), SCBlocks.HorseSkulls.CREAMY_HORSE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> DARKBROWN_HORSE_HEAD = new ItemEntry<>(
				"darkbrown_horse_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.DARKBROWN_HORSE_HEAD.get(), SCBlocks.HorseSkulls.DARKBROWN_HORSE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> GRAY_HORSE_HEAD = new ItemEntry<>(
				"gray_horse_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.GRAY_HORSE_HEAD.get(), SCBlocks.HorseSkulls.GRAY_HORSE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WHITE_HORSE_HEAD = new ItemEntry<>(
				"white_horse_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.WHITE_HORSE_HEAD.get(), SCBlocks.HorseSkulls.WHITE_HORSE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> DONKEY_HEAD = new ItemEntry<>(
				"donkey_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.DONKEY_HEAD.get(), SCBlocks.HorseSkulls.DONKEY_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> MULE_HEAD = new ItemEntry<>(
				"mule_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.MULE_HEAD.get(), SCBlocks.HorseSkulls.MULE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> SKELETON_HORSE_HEAD = new ItemEntry<>(
				"skeleton_horse_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.SKELETON_HORSE_HEAD.get(), SCBlocks.HorseSkulls.SKELETON_HORSE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ZOMBIE_HORSE_HEAD = new ItemEntry<>(
				"zombie_horse_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HorseSkulls.ZOMBIE_HORSE_HEAD.get(), SCBlocks.HorseSkulls.ZOMBIE_HORSE_WALL_HEAD.get(), Direction.DOWN, props
				)
		);

		private HorseSkulls() {}

		private static void init() {
			// Lazy init
		}
	}

	/**
	 * 监守者头颅物品集合喵~
	 */
	public static class WardenSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> WARDEN_HEAD = new ItemEntry<>(
				"warden_head", new Item.Properties().rarity(Rarity.RARE).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.WardenSkulls.WARDEN_HEAD.get(), SCBlocks.WardenSkulls.WARDEN_WALL_HEAD.get(), Direction.DOWN, props
				) {
					@Override
					public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotIndex, boolean selected) {
						if(entity instanceof Player player && player.tickCount % 20 == 0) {
							Inventory inv = player.getInventory();
							if (slotIndex < inv.items.size() || slotIndex >= inv.items.size() + inv.armor.size()) {
								return;
							}
							if (!player.hasEffect(MobEffects.DARKNESS) && level.random.nextInt(4) == 0) {
								player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 0, false, false, true));
							}
							player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 2, false, false, true));
						}
					}
				}
		);

		private WardenSkulls() {}

		private static void init() {
			// Lazy init
		}
	}

	/**
	 * 疣猪兽形生物头颅物品集合喵~
	 */
	public static class HoglinSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> HOGLIN_HEAD = new ItemEntry<>(
				"hoglin_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HoglinSkulls.HOGLIN_HEAD.get(), SCBlocks.HoglinSkulls.HOGLIN_WALL_HEAD.get(), Direction.DOWN, props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ZOGLIN_HEAD = new ItemEntry<>(
				"zoglin_head", new Item.Properties().rarity(Rarity.UNCOMMON).useBlockDescriptionPrefix().equippableUnswappable(EquipmentSlot.HEAD), props -> new StandingAndWallBlockItem(
						SCBlocks.HoglinSkulls.ZOGLIN_HEAD.get(), SCBlocks.HoglinSkulls.ZOGLIN_WALL_HEAD.get(), Direction.DOWN, props
				)
		);

		private HoglinSkulls() {}

		private static void init() {
			// Lazy init
		}
	}

	/**
	 * 将物品注册到事件总线，并触发所有内部类的延迟初始化喵~
	 *
	 * @param bus 模组事件总线喵~
	 */
	public static void init(IEventBus bus) {
		REGISTER.register(bus);
		HumanSkulls.init();
		CubeSkulls.init();
		SmallCubeSkulls.init();
		CowSkulls.init();
		PiglinSkulls.init();
		HorseSkulls.init();
		WardenSkulls.init();
		HoglinSkulls.init();
	}

	private SCItems() {
	}

	/**
	 * 物品注册项封装类，同时实现 {@link Supplier} 和 {@link ItemLike} 接口，
	 * 并自动追踪所有已注册的物品喵~
	 *
	 * @param <T> 物品类型喵~
	 */
	public static final class ItemEntry<T extends Item> implements Supplier<T>, ItemLike {
		/** 所有已注册物品的列表喵~ */
		private static final List<ItemEntry<? extends Item>> REGISTERED_ITEMS = Lists.newArrayList();

		private final DeferredHolder<Item, T> item;

		/**
		 * 构造物品注册项，并自动加入已注册列表喵~
		 *
		 * @param name       物品注册名喵~
		 * @param properties 物品属性喵~
		 * @param make       物品构造工厂喵~
		 */
		public ItemEntry(String name, Item.Properties properties, Function<Item.Properties, T> make) {
			this.item = REGISTER.register(name, () -> make.apply(properties.setId(
					ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, name))
			)));

			REGISTERED_ITEMS.add(this);
		}

		@Override
		public T get() {
			return this.item.get();
		}

		/**
		 * 获取物品的注册 ID 喵~
		 *
		 * @return 物品注册 ID 喵~
		 */
		public ResourceLocation getId() {
			return this.item.getId();
		}

		@Override
		public Item asItem() {
			return this.item.get();
		}

		/**
		 * 获取所有已注册物品的不可修改列表喵~
		 *
		 * @return 已注册物品列表喵~
		 */
		public static List<ItemEntry<? extends Item>> registeredItems() {
			return Collections.unmodifiableList(REGISTERED_ITEMS);
		}
	}
}
