package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.common.block.CowSkull.CowSkullBlock;
import com.hexagram2021.skullcraft.common.block.CowSkull.CowWallSkullBlock;
import com.hexagram2021.skullcraft.common.block.CubeSkull.CubeSkullBlock;
import com.hexagram2021.skullcraft.common.block.CubeSkull.CubeWallSkullBlock;
import com.hexagram2021.skullcraft.common.block.HorseSkull.HorseSkullBlock;
import com.hexagram2021.skullcraft.common.block.HorseSkull.HorseWallSkullBlock;
import com.hexagram2021.skullcraft.common.block.HumanSkull.HumanSkullBlock;
import com.hexagram2021.skullcraft.common.block.HumanSkull.HumanWallSkullBlock;
import com.hexagram2021.skullcraft.common.block.PiglinSkull.PiglinSkullBlock;
import com.hexagram2021.skullcraft.common.block.PiglinSkull.PiglinWallSkullBlock;
import com.hexagram2021.skullcraft.common.block.SkullChargerBlock;
import com.hexagram2021.skullcraft.common.block.SmallCubeSkull.SmallCubeSkullBlock;
import com.hexagram2021.skullcraft.common.block.SmallCubeSkull.SmallCubeWallSkullBlock;
import com.hexagram2021.skullcraft.common.block.WardenSkull.WardenSkullBlock;
import com.hexagram2021.skullcraft.common.block.WardenSkull.WardenWallSkullBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCBlocks {
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

	private static final Supplier<BlockBehaviour.Properties> SKULL_CHARGER_PROPERTIES = () ->
			BlockBehaviour.Properties.of().requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_GRAY).strength(3.5F).sound(SoundType.LODESTONE);

	private static final Function<NoteBlockInstrument, Supplier<BlockBehaviour.Properties>> SKULL_PROPERTIES = instrument -> () ->
			BlockBehaviour.Properties.of().instrument(instrument).strength(1.0F).pushReaction(PushReaction.DESTROY);
	private static final Function<NoteBlockInstrument, Supplier<BlockBehaviour.Properties>> BOSS_SKULL_PROPERTIES = instrument -> () ->
			BlockBehaviour.Properties.of().instrument(instrument).strength(2.0F).pushReaction(PushReaction.DESTROY);

	public static final BlockEntry<Block> SKULL_CHARGER = new BlockEntry<>(
			"skull_charger", SKULL_CHARGER_PROPERTIES, SkullChargerBlock::new
	);

	public static class HumanSkulls {
		public static final BlockEntry<HumanSkullBlock> VILLAGER_HEAD = new BlockEntry<>(
				"villager_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_VILLAGER),
				(props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.VILLAGER)
		);
		public static final BlockEntry<HumanSkullBlock> ILLAGER_HEAD = new BlockEntry<>(
				"illager_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ILLAGER),
				(props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.ILLAGER)
		);
		public static final BlockEntry<HumanSkullBlock> WITCH_HEAD = new BlockEntry<>(
				"witch_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WITCH),
				(props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.WITCH)
		);
		public static final BlockEntry<HumanSkullBlock> IRON_GOLEM_HEAD = new BlockEntry<>(
				"iron_golem_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_IRON_GOLEM),
				(props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.IRON_GOLEM)
		);
		public static final BlockEntry<HumanSkullBlock> ZOMBIE_VILLAGER_HEAD = new BlockEntry<>(
				"zombie_villager_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIE_VILLAGER),
				(props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.ZOMBIE_VILLAGER)
		);

		public static final BlockEntry<HumanWallSkullBlock> VILLAGER_WALL_HEAD = new BlockEntry<>(
				"villager_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_VILLAGER),
				(props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.VILLAGER)
		);
		public static final BlockEntry<HumanWallSkullBlock> ILLAGER_WALL_HEAD = new BlockEntry<>(
				"illager_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ILLAGER),
				(props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.ILLAGER)
		);
		public static final BlockEntry<HumanWallSkullBlock> WITCH_WALL_HEAD = new BlockEntry<>(
				"witch_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WITCH),
				(props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.WITCH)
		);
		public static final BlockEntry<HumanWallSkullBlock> IRON_GOLEM_WALL_HEAD = new BlockEntry<>(
				"iron_golem_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_IRON_GOLEM),
				(props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.IRON_GOLEM)
		);
		public static final BlockEntry<HumanWallSkullBlock> ZOMBIE_VILLAGER_WALL_HEAD = new BlockEntry<>(
				"zombie_villager_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIE_VILLAGER),
				(props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.ZOMBIE_VILLAGER)
		);

		private HumanSkulls() {}

		private static void init() {
		}
	}

	public static class CubeSkulls {
		public static final BlockEntry<CubeSkullBlock> SLIME_HEAD = new BlockEntry<>(
				"slime_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SLIME),
				(props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.SLIME)
		);
		public static final BlockEntry<CubeSkullBlock> LAVASLIME_HEAD = new BlockEntry<>(
				"lavaslime_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_LAVASLIME),
				(props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.LAVASLIME)
		);
		public static final BlockEntry<CubeSkullBlock> BLAZE_HEAD = new BlockEntry<>(
				"blaze_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_BLAZE),
				(props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.BLAZE)
		);
		public static final BlockEntry<CubeSkullBlock> SPIDER_HEAD = new BlockEntry<>(
				"spider_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SPIDER),
				(props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.SPIDER)
		);
		public static final BlockEntry<CubeSkullBlock> CAVE_SPIDER_HEAD = new BlockEntry<>(
				"cave_spider_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SPIDER),
				(props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.CAVE_SPIDER)
		);
		public static final BlockEntry<CubeSkullBlock> PIG_HEAD = new BlockEntry<>(
				"pig_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_PIG),
				(props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.PIG)
		);
		public static final BlockEntry<CubeSkullBlock> ENDERMAN_HEAD = new BlockEntry<>(
				"enderman_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ENDERMAN),
				(props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.ENDERMAN)
		);
		public static final BlockEntry<CubeSkullBlock> SNOW_GOLEM_HEAD = new BlockEntry<>(
				"snow_golem_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SNOW_GOLEM),
				(props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.SNOW_GOLEM)
		);
		public static final BlockEntry<CubeSkullBlock> TECHNOBLADE_HEAD = new BlockEntry<>(
				"technoblade_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_TECHNOBLADE),
				(props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.TECHNOBLADE)
		);

		public static final BlockEntry<CubeWallSkullBlock> SLIME_WALL_HEAD = new BlockEntry<>(
				"slime_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SLIME),
				(props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.SLIME)
		);
		public static final BlockEntry<CubeWallSkullBlock> LAVASLIME_WALL_HEAD = new BlockEntry<>(
				"lavaslime_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_LAVASLIME),
				(props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.LAVASLIME)
		);
		public static final BlockEntry<CubeWallSkullBlock> BLAZE_WALL_HEAD = new BlockEntry<>(
				"blaze_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_BLAZE),
				(props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.BLAZE)
		);
		public static final BlockEntry<CubeWallSkullBlock> SPIDER_WALL_HEAD = new BlockEntry<>(
				"spider_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SPIDER),
				(props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.SPIDER)
		);
		public static final BlockEntry<CubeWallSkullBlock> CAVE_SPIDER_WALL_HEAD = new BlockEntry<>(
				"cave_spider_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SPIDER),
				(props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.CAVE_SPIDER)
		);
		public static final BlockEntry<CubeWallSkullBlock> PIG_WALL_HEAD = new BlockEntry<>(
				"pig_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_PIG),
				(props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.PIG)
		);
		public static final BlockEntry<CubeWallSkullBlock> ENDERMAN_WALL_HEAD = new BlockEntry<>(
				"enderman_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ENDERMAN),
				(props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.ENDERMAN)
		);
		public static final BlockEntry<CubeWallSkullBlock> SNOW_GOLEM_WALL_HEAD = new BlockEntry<>(
				"snow_golem_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SNOW_GOLEM),
				(props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.SNOW_GOLEM)
		);
		public static final BlockEntry<CubeWallSkullBlock> TECHNOBLADE_WALL_HEAD = new BlockEntry<>(
				"technoblade_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_TECHNOBLADE),
				(props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.TECHNOBLADE)
		);


		private CubeSkulls() {}

		private static void init() {
		}
	}

	public static class SmallCubeSkulls {
		public static final BlockEntry<SmallCubeSkullBlock> SHEEP_HEAD = new BlockEntry<>(
				"sheep_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SHEEP),
				(props) -> new SmallCubeSkullBlock(props, SmallCubeSkullBlock.Types.SHEEP)
		);
		public static final BlockEntry<SmallCubeSkullBlock> BAT_HEAD = new BlockEntry<>(
				"bat_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_BAT),
				(props) -> new SmallCubeSkullBlock(props, SmallCubeSkullBlock.Types.BAT)
		);
		public static final BlockEntry<SmallCubeSkullBlock> SHULKER_HEAD = new BlockEntry<>(
				"shulker_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SHULKER),
				(props) -> new SmallCubeSkullBlock(props, SmallCubeSkullBlock.Types.SHULKER)
		);
		public static final BlockEntry<SmallCubeSkullBlock> ALLAY_HEAD = new BlockEntry<>(
				"allay_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ALLAY),
				(props) -> new SmallCubeSkullBlock(props, SmallCubeSkullBlock.Types.ALLAY)
		);
		public static final BlockEntry<SmallCubeSkullBlock> VEX_HEAD = new BlockEntry<>(
				"vex_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_VEX),
				(props) -> new SmallCubeSkullBlock(props, SmallCubeSkullBlock.Types.VEX)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> SHEEP_WALL_HEAD = new BlockEntry<>(
				"sheep_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SHEEP),
				(props) -> new SmallCubeWallSkullBlock(props, SmallCubeSkullBlock.Types.SHEEP)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> BAT_WALL_HEAD = new BlockEntry<>(
				"bat_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_BAT),
				(props) -> new SmallCubeWallSkullBlock(props, SmallCubeSkullBlock.Types.BAT)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> SHULKER_WALL_HEAD = new BlockEntry<>(
				"shulker_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SHULKER),
				(props) -> new SmallCubeWallSkullBlock(props, SmallCubeSkullBlock.Types.SHULKER)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> ALLAY_WALL_HEAD = new BlockEntry<>(
				"allay_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ALLAY),
				(props) -> new SmallCubeWallSkullBlock(props, SmallCubeSkullBlock.Types.ALLAY)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> VEX_WALL_HEAD = new BlockEntry<>(
				"vex_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_VEX),
				(props) -> new SmallCubeWallSkullBlock(props, SmallCubeSkullBlock.Types.VEX)
		);

		private SmallCubeSkulls() {}

		private static void init() {
		}
	}

	public static class CowSkulls {
		public static final BlockEntry<CowSkullBlock> COW_HEAD = new BlockEntry<>(
				"cow_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowSkullBlock(props, CowSkullBlock.Types.COW)
		);
		public static final BlockEntry<CowSkullBlock> RED_MOOSHROOM_HEAD = new BlockEntry<>(
				"red_mooshroom_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowSkullBlock(props, CowSkullBlock.Types.RED_MOOSHROOM)
		);
		public static final BlockEntry<CowSkullBlock> BROWN_MOOSHROOM_HEAD = new BlockEntry<>(
				"brown_mooshroom_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowSkullBlock(props, CowSkullBlock.Types.BROWN_MOOSHROOM)
		);
		public static final BlockEntry<CowWallSkullBlock> COW_WALL_HEAD = new BlockEntry<>(
				"cow_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowWallSkullBlock(props, CowSkullBlock.Types.COW)
		);
		public static final BlockEntry<CowWallSkullBlock> RED_MOOSHROOM_WALL_HEAD = new BlockEntry<>(
				"red_mooshroom_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowWallSkullBlock(props, CowSkullBlock.Types.RED_MOOSHROOM)
		);
		public static final BlockEntry<CowWallSkullBlock> BROWN_MOOSHROOM_WALL_HEAD = new BlockEntry<>(
				"brown_mooshroom_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowWallSkullBlock(props, CowSkullBlock.Types.BROWN_MOOSHROOM)
		);

		private CowSkulls() {}

		private static void init() {
		}
	}

	public static class PiglinSkulls {
		public static final BlockEntry<PiglinSkullBlock> PIGLIN_BRUTE_HEAD = new BlockEntry<>(
				"piglin_brute_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_PIGLIN_BRUTE),
				(props) -> new PiglinSkullBlock(props, PiglinSkullBlock.Types.PIGLIN_BRUTE)
		);
		public static final BlockEntry<PiglinSkullBlock> ZOMBIFIED_PIGLIN_HEAD = new BlockEntry<>(
				"zombified_piglin_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIFIED_PIGLIN),
				(props) -> new PiglinSkullBlock(props, PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN)
		);
		public static final BlockEntry<PiglinWallSkullBlock> PIGLIN_BRUTE_WALL_HEAD = new BlockEntry<>(
				"piglin_brute_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_PIGLIN_BRUTE),
				(props) -> new PiglinWallSkullBlock(props, PiglinSkullBlock.Types.PIGLIN_BRUTE)
		);
		public static final BlockEntry<PiglinWallSkullBlock> ZOMBIFIED_PIGLIN_WALL_HEAD = new BlockEntry<>(
				"zombified_piglin_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIFIED_PIGLIN),
				(props) -> new PiglinWallSkullBlock(props, PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN)
		);

		private PiglinSkulls() {}

		private static void init() {
		}
	}

	public static class HorseSkulls {
		public static final BlockEntry<HorseSkullBlock> BLACK_HORSE_HEAD = new BlockEntry<>(
				"black_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.BLACK_HORSE)
		);
		public static final BlockEntry<HorseSkullBlock> BROWN_HORSE_HEAD = new BlockEntry<>(
				"brown_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.BROWN_HORSE)
		);
		public static final BlockEntry<HorseSkullBlock> CHESTNUT_HORSE_HEAD = new BlockEntry<>(
				"chestnut_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.CHESTNUT_HORSE)
		);
		public static final BlockEntry<HorseSkullBlock> CREAMY_HORSE_HEAD = new BlockEntry<>(
				"creamy_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.CREAMY_HORSE)
		);
		public static final BlockEntry<HorseSkullBlock> DARKBROWN_HORSE_HEAD = new BlockEntry<>(
				"darkbrown_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.DARKBROWN_HORSE)
		);
		public static final BlockEntry<HorseSkullBlock> GRAY_HORSE_HEAD = new BlockEntry<>(
				"gray_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.GRAY_HORSE)
		);
		public static final BlockEntry<HorseSkullBlock> WHITE_HORSE_HEAD = new BlockEntry<>(
				"white_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.WHITE_HORSE)
		);
		public static final BlockEntry<HorseSkullBlock> DONKEY_HEAD = new BlockEntry<>(
				"donkey_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_DONKEY),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.DONKEY)
		);
		public static final BlockEntry<HorseSkullBlock> MULE_HEAD = new BlockEntry<>(
				"mule_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_MULE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.MULE)
		);
		public static final BlockEntry<HorseSkullBlock> SKELETON_HORSE_HEAD = new BlockEntry<>(
				"skeleton_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SKELETON_HORSE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.SKELETON_HORSE)
		);
		public static final BlockEntry<HorseSkullBlock> ZOMBIE_HORSE_HEAD = new BlockEntry<>(
				"zombie_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIE_HORSE),
				(props) -> new HorseSkullBlock(props, HorseSkullBlock.Types.ZOMBIE_HORSE)
		);
		public static final BlockEntry<HorseWallSkullBlock> BLACK_HORSE_WALL_HEAD = new BlockEntry<>(
				"black_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.BLACK_HORSE)
		);
		public static final BlockEntry<HorseWallSkullBlock> BROWN_HORSE_WALL_HEAD = new BlockEntry<>(
				"brown_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.BROWN_HORSE)
		);
		public static final BlockEntry<HorseWallSkullBlock> CHESTNUT_HORSE_WALL_HEAD = new BlockEntry<>(
				"chestnut_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.CHESTNUT_HORSE)
		);
		public static final BlockEntry<HorseWallSkullBlock> CREAMY_HORSE_WALL_HEAD = new BlockEntry<>(
				"creamy_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.CREAMY_HORSE)
		);
		public static final BlockEntry<HorseWallSkullBlock> DARKBROWN_HORSE_WALL_HEAD = new BlockEntry<>(
				"darkbrown_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.DARKBROWN_HORSE)
		);
		public static final BlockEntry<HorseWallSkullBlock> GRAY_HORSE_WALL_HEAD = new BlockEntry<>(
				"gray_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.GRAY_HORSE)
		);
		public static final BlockEntry<HorseWallSkullBlock> WHITE_HORSE_WALL_HEAD = new BlockEntry<>(
				"white_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.WHITE_HORSE)
		);
		public static final BlockEntry<HorseWallSkullBlock> DONKEY_WALL_HEAD = new BlockEntry<>(
				"donkey_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_DONKEY),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.DONKEY)
		);
		public static final BlockEntry<HorseWallSkullBlock> MULE_WALL_HEAD = new BlockEntry<>(
				"mule_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_MULE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.MULE)
		);
		public static final BlockEntry<HorseWallSkullBlock> SKELETON_HORSE_WALL_HEAD = new BlockEntry<>(
				"skeleton_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SKELETON_HORSE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.SKELETON_HORSE)
		);
		public static final BlockEntry<HorseWallSkullBlock> ZOMBIE_HORSE_WALL_HEAD = new BlockEntry<>(
				"zombie_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIE_HORSE),
				(props) -> new HorseWallSkullBlock(props, HorseSkullBlock.Types.ZOMBIE_HORSE)
		);

		private HorseSkulls() {}

		private static void init() {
		}
	}

	public static class WardenSkulls {
		public static final BlockEntry<WardenSkullBlock> WARDEN_HEAD = new BlockEntry<>(
				"warden_head", BOSS_SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WARDEN),
				(props) -> new WardenSkullBlock(props, WardenSkullBlock.Types.WARDEN)
		);
		public static final BlockEntry<WardenWallSkullBlock> WARDEN_WALL_HEAD = new BlockEntry<>(
				"warden_wall_head", BOSS_SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WARDEN),
				(props) -> new WardenWallSkullBlock(props, WardenSkullBlock.Types.WARDEN)
		);

		private WardenSkulls() {}

		private static void init() {
		}
	}

	private SCBlocks() {}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);

		HumanSkulls.init();
		CubeSkulls.init();
		SmallCubeSkulls.init();
		CowSkulls.init();
		PiglinSkulls.init();
		HorseSkulls.init();
		WardenSkulls.init();
	}

	@SuppressWarnings("unused")
	public static final class BlockEntry<T extends Block> implements Supplier<T>, ItemLike {
		private final RegistryObject<T> block;
		private final Supplier<BlockBehaviour.Properties> properties;

		public BlockEntry(String name, Supplier<BlockBehaviour.Properties> properties, Function<BlockBehaviour.Properties, T> make) {
			this.properties = properties;
			this.block = REGISTER.register(name, () -> make.apply(properties.get()));
		}

		@Override
		public T get() {
			return this.block.get();
		}

		public BlockState defaultBlockState() {
			return this.get().defaultBlockState();
		}

		public ResourceLocation getId() {
			return this.block.getId();
		}

		public BlockBehaviour.Properties getProperties() {
			return this.properties.get();
		}

		@Override
		public Item asItem() {
			return this.get().asItem();
		}
	}
}
