package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.common.block.cow.*;
import com.hexagram2021.skullcraft.common.block.cube.*;
import com.hexagram2021.skullcraft.common.block.hoglin.*;
import com.hexagram2021.skullcraft.common.block.horse.*;
import com.hexagram2021.skullcraft.common.block.human.*;
import com.hexagram2021.skullcraft.common.block.piglin.*;
import com.hexagram2021.skullcraft.common.block.SkullChargerBlock;
import com.hexagram2021.skullcraft.common.block.small_cube.*;
import com.hexagram2021.skullcraft.common.block.warden.*;
import net.minecraft.core.registries.Registries;
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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCBlocks {
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(Registries.BLOCK, MODID);

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
				(props) -> new HumanSkullBlock(HumanSkullBlock.Types.VILLAGER, props)
		);
		public static final BlockEntry<HumanSkullBlock> ILLAGER_HEAD = new BlockEntry<>(
				"illager_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ILLAGER),
				(props) -> new HumanSkullBlock(HumanSkullBlock.Types.ILLAGER, props)
		);
		public static final BlockEntry<HumanSkullBlock> WITCH_HEAD = new BlockEntry<>(
				"witch_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WITCH),
				(props) -> new HumanSkullBlock(HumanSkullBlock.Types.WITCH, props)
		);
		public static final BlockEntry<HumanSkullBlock> IRON_GOLEM_HEAD = new BlockEntry<>(
				"iron_golem_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_IRON_GOLEM),
				(props) -> new HumanSkullBlock(HumanSkullBlock.Types.IRON_GOLEM, props)
		);
		public static final BlockEntry<HumanSkullBlock> ZOMBIE_VILLAGER_HEAD = new BlockEntry<>(
				"zombie_villager_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIE_VILLAGER),
				(props) -> new HumanSkullBlock(HumanSkullBlock.Types.ZOMBIE_VILLAGER, props)
		);

		public static final BlockEntry<HumanWallSkullBlock> VILLAGER_WALL_HEAD = new BlockEntry<>(
				"villager_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_VILLAGER),
				(props) -> new HumanWallSkullBlock(HumanSkullBlock.Types.VILLAGER, props)
		);
		public static final BlockEntry<HumanWallSkullBlock> ILLAGER_WALL_HEAD = new BlockEntry<>(
				"illager_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ILLAGER),
				(props) -> new HumanWallSkullBlock(HumanSkullBlock.Types.ILLAGER, props)
		);
		public static final BlockEntry<HumanWallSkullBlock> WITCH_WALL_HEAD = new BlockEntry<>(
				"witch_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WITCH),
				(props) -> new HumanWallSkullBlock(HumanSkullBlock.Types.WITCH, props)
		);
		public static final BlockEntry<HumanWallSkullBlock> IRON_GOLEM_WALL_HEAD = new BlockEntry<>(
				"iron_golem_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_IRON_GOLEM),
				(props) -> new HumanWallSkullBlock(HumanSkullBlock.Types.IRON_GOLEM, props)
		);
		public static final BlockEntry<HumanWallSkullBlock> ZOMBIE_VILLAGER_WALL_HEAD = new BlockEntry<>(
				"zombie_villager_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIE_VILLAGER),
				(props) -> new HumanWallSkullBlock(HumanSkullBlock.Types.ZOMBIE_VILLAGER, props)
		);

		private HumanSkulls() {}

		private static void init() {
		}
	}

	public static class CubeSkulls {
		public static final BlockEntry<CubeSkullBlock> SLIME_HEAD = new BlockEntry<>(
				"slime_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SLIME),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.SLIME, props)
		);
		public static final BlockEntry<CubeSkullBlock> LAVASLIME_HEAD = new BlockEntry<>(
				"lavaslime_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_LAVASLIME),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.LAVASLIME, props)
		);
		public static final BlockEntry<CubeSkullBlock> BLAZE_HEAD = new BlockEntry<>(
				"blaze_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_BLAZE),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.BLAZE, props)
		);
		public static final BlockEntry<CubeSkullBlock> SPIDER_HEAD = new BlockEntry<>(
				"spider_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SPIDER),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.SPIDER, props)
		);
		public static final BlockEntry<CubeSkullBlock> CAVE_SPIDER_HEAD = new BlockEntry<>(
				"cave_spider_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SPIDER),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.CAVE_SPIDER, props)
		);
		public static final BlockEntry<CubeSkullBlock> PIG_HEAD = new BlockEntry<>(
				"pig_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_PIG),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.PIG, props)
		);
		public static final BlockEntry<CubeSkullBlock> WOLF_HEAD = new BlockEntry<>(
				"wolf_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.WOLF, props)
		);
		public static final BlockEntry<CubeSkullBlock> WOLF_ASHEN_HEAD = new BlockEntry<>(
				"wolf_ashen_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.WOLF_ASHEN, props)
		);
		public static final BlockEntry<CubeSkullBlock> WOLF_BLACK_HEAD = new BlockEntry<>(
				"wolf_black_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.WOLF_BLACK, props)
		);
		public static final BlockEntry<CubeSkullBlock> WOLF_CHESTNUT_HEAD = new BlockEntry<>(
				"wolf_chestnut_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.WOLF_CHESTNUT, props)
		);
		public static final BlockEntry<CubeSkullBlock> WOLF_RUSTY_HEAD = new BlockEntry<>(
				"wolf_rusty_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.WOLF_RUSTY, props)
		);
		public static final BlockEntry<CubeSkullBlock> WOLF_SNOWY_HEAD = new BlockEntry<>(
				"wolf_snowy_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.WOLF_SNOWY, props)
		);
		public static final BlockEntry<CubeSkullBlock> WOLF_SPOTTED_HEAD = new BlockEntry<>(
				"wolf_spotted_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.WOLF_SPOTTED, props)
		);
		public static final BlockEntry<CubeSkullBlock> WOLF_STRIPED_HEAD = new BlockEntry<>(
				"wolf_striped_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.WOLF_STRIPED, props)
		);
		public static final BlockEntry<CubeSkullBlock> WOLF_WOODS_HEAD = new BlockEntry<>(
				"wolf_woods_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.WOLF_WOODS, props)
		);
		public static final BlockEntry<CubeSkullBlock> ANGRY_WOLF_HEAD = new BlockEntry<>(
				"angry_wolf_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF, props)
		);
		public static final BlockEntry<CubeSkullBlock> ANGRY_WOLF_ASHEN_HEAD = new BlockEntry<>(
				"angry_wolf_ashen_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_ASHEN, props)
		);
		public static final BlockEntry<CubeSkullBlock> ANGRY_WOLF_BLACK_HEAD = new BlockEntry<>(
				"angry_wolf_black_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_BLACK, props)
		);
		public static final BlockEntry<CubeSkullBlock> ANGRY_WOLF_CHESTNUT_HEAD = new BlockEntry<>(
				"angry_wolf_chestnut_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_CHESTNUT, props)
		);
		public static final BlockEntry<CubeSkullBlock> ANGRY_WOLF_RUSTY_HEAD = new BlockEntry<>(
				"angry_wolf_rusty_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_RUSTY, props)
		);
		public static final BlockEntry<CubeSkullBlock> ANGRY_WOLF_SNOWY_HEAD = new BlockEntry<>(
				"angry_wolf_snowy_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_SNOWY, props)
		);
		public static final BlockEntry<CubeSkullBlock> ANGRY_WOLF_SPOTTED_HEAD = new BlockEntry<>(
				"angry_wolf_spotted_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_SPOTTED, props)
		);
		public static final BlockEntry<CubeSkullBlock> ANGRY_WOLF_STRIPED_HEAD = new BlockEntry<>(
				"angry_wolf_striped_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_STRIPED, props)
		);
		public static final BlockEntry<CubeSkullBlock> ANGRY_WOLF_WOODS_HEAD = new BlockEntry<>(
				"angry_wolf_woods_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_WOODS, props)
		);
		public static final BlockEntry<CubeSkullBlock> ENDERMAN_HEAD = new BlockEntry<>(
				"enderman_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ENDERMAN),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.ENDERMAN, props)
		);
		public static final BlockEntry<CubeSkullBlock> SNOW_GOLEM_HEAD = new BlockEntry<>(
				"snow_golem_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SNOW_GOLEM),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.SNOW_GOLEM, props)
		);
		public static final BlockEntry<CubeSkullBlock> TECHNOBLADE_HEAD = new BlockEntry<>(
				"technoblade_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_TECHNOBLADE),
				(props) -> new CubeSkullBlock(CubeSkullBlock.Types.TECHNOBLADE, props)
		);

		public static final BlockEntry<CubeWallSkullBlock> SLIME_WALL_HEAD = new BlockEntry<>(
				"slime_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SLIME),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.SLIME, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> LAVASLIME_WALL_HEAD = new BlockEntry<>(
				"lavaslime_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_LAVASLIME),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.LAVASLIME, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> BLAZE_WALL_HEAD = new BlockEntry<>(
				"blaze_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_BLAZE),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.BLAZE, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> SPIDER_WALL_HEAD = new BlockEntry<>(
				"spider_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SPIDER),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.SPIDER, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> CAVE_SPIDER_WALL_HEAD = new BlockEntry<>(
				"cave_spider_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SPIDER),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.CAVE_SPIDER, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> PIG_WALL_HEAD = new BlockEntry<>(
				"pig_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_PIG),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.PIG, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> WOLF_WALL_HEAD = new BlockEntry<>(
				"wolf_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.WOLF, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> WOLF_ASHEN_WALL_HEAD = new BlockEntry<>(
				"wolf_ashen_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.WOLF_ASHEN, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> WOLF_BLACK_WALL_HEAD = new BlockEntry<>(
				"wolf_black_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.WOLF_BLACK, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> WOLF_CHESTNUT_WALL_HEAD = new BlockEntry<>(
				"wolf_chestnut_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.WOLF_CHESTNUT, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> WOLF_RUSTY_WALL_HEAD = new BlockEntry<>(
				"wolf_rusty_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.WOLF_RUSTY, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> WOLF_SNOWY_WALL_HEAD = new BlockEntry<>(
				"wolf_snowy_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.WOLF_SNOWY, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> WOLF_SPOTTED_WALL_HEAD = new BlockEntry<>(
				"wolf_spotted_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.WOLF_SPOTTED, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> WOLF_STRIPED_WALL_HEAD = new BlockEntry<>(
				"wolf_striped_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.WOLF_STRIPED, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> WOLF_WOODS_WALL_HEAD = new BlockEntry<>(
				"wolf_woods_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.WOLF_WOODS, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ANGRY_WOLF_WALL_HEAD = new BlockEntry<>(
				"angry_wolf_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ANGRY_WOLF_ASHEN_WALL_HEAD = new BlockEntry<>(
				"angry_wolf_ashen_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_ASHEN, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ANGRY_WOLF_BLACK_WALL_HEAD = new BlockEntry<>(
				"angry_wolf_black_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_BLACK, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ANGRY_WOLF_CHESTNUT_WALL_HEAD = new BlockEntry<>(
				"angry_wolf_chestnut_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_CHESTNUT, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ANGRY_WOLF_RUSTY_WALL_HEAD = new BlockEntry<>(
				"angry_wolf_rusty_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_RUSTY, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ANGRY_WOLF_SNOWY_WALL_HEAD = new BlockEntry<>(
				"angry_wolf_snowy_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_SNOWY, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ANGRY_WOLF_SPOTTED_WALL_HEAD = new BlockEntry<>(
				"angry_wolf_spotted_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_SPOTTED, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ANGRY_WOLF_STRIPED_WALL_HEAD = new BlockEntry<>(
				"angry_wolf_striped_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_STRIPED, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ANGRY_WOLF_WOODS_WALL_HEAD = new BlockEntry<>(
				"angry_wolf_woods_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ANGRY_WOLF),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ANGRY_WOLF_WOODS, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> ENDERMAN_WALL_HEAD = new BlockEntry<>(
				"enderman_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ENDERMAN),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.ENDERMAN, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> SNOW_GOLEM_WALL_HEAD = new BlockEntry<>(
				"snow_golem_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SNOW_GOLEM),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.SNOW_GOLEM, props)
		);
		public static final BlockEntry<CubeWallSkullBlock> TECHNOBLADE_WALL_HEAD = new BlockEntry<>(
				"technoblade_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_TECHNOBLADE),
				(props) -> new CubeWallSkullBlock(CubeSkullBlock.Types.TECHNOBLADE, props)
		);


		private CubeSkulls() {}

		private static void init() {
		}
	}

	public static class SmallCubeSkulls {
		public static final BlockEntry<SmallCubeSkullBlock> SHEEP_HEAD = new BlockEntry<>(
				"sheep_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SHEEP),
				(props) -> new SmallCubeSkullBlock(SmallCubeSkullBlock.Types.SHEEP, props)
		);
		public static final BlockEntry<SmallCubeSkullBlock> BAT_HEAD = new BlockEntry<>(
				"bat_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_BAT),
				(props) -> new SmallCubeSkullBlock(SmallCubeSkullBlock.Types.BAT, props)
		);
		public static final BlockEntry<SmallCubeSkullBlock> SHULKER_HEAD = new BlockEntry<>(
				"shulker_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SHULKER),
				(props) -> new SmallCubeSkullBlock(SmallCubeSkullBlock.Types.SHULKER, props)
		);
		public static final BlockEntry<SmallCubeSkullBlock> ALLAY_HEAD = new BlockEntry<>(
				"allay_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ALLAY),
				(props) -> new SmallCubeSkullBlock(SmallCubeSkullBlock.Types.ALLAY, props)
		);
		public static final BlockEntry<SmallCubeSkullBlock> VEX_HEAD = new BlockEntry<>(
				"vex_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_VEX),
				(props) -> new SmallCubeSkullBlock(SmallCubeSkullBlock.Types.VEX, props)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> SHEEP_WALL_HEAD = new BlockEntry<>(
				"sheep_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SHEEP),
				(props) -> new SmallCubeWallSkullBlock(SmallCubeSkullBlock.Types.SHEEP, props)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> BAT_WALL_HEAD = new BlockEntry<>(
				"bat_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_BAT),
				(props) -> new SmallCubeWallSkullBlock(SmallCubeSkullBlock.Types.BAT, props)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> SHULKER_WALL_HEAD = new BlockEntry<>(
				"shulker_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SHULKER),
				(props) -> new SmallCubeWallSkullBlock(SmallCubeSkullBlock.Types.SHULKER, props)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> ALLAY_WALL_HEAD = new BlockEntry<>(
				"allay_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ALLAY),
				(props) -> new SmallCubeWallSkullBlock(SmallCubeSkullBlock.Types.ALLAY, props)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> VEX_WALL_HEAD = new BlockEntry<>(
				"vex_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_VEX),
				(props) -> new SmallCubeWallSkullBlock(SmallCubeSkullBlock.Types.VEX, props)
		);

		private SmallCubeSkulls() {}

		private static void init() {
		}
	}

	public static class CowSkulls {
		public static final BlockEntry<CowSkullBlock> COW_HEAD = new BlockEntry<>(
				"cow_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowSkullBlock(CowSkullBlock.Types.COW, props)
		);
		public static final BlockEntry<CowSkullBlock> RED_MOOSHROOM_HEAD = new BlockEntry<>(
				"red_mooshroom_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowSkullBlock(CowSkullBlock.Types.RED_MOOSHROOM, props)
		);
		public static final BlockEntry<CowSkullBlock> BROWN_MOOSHROOM_HEAD = new BlockEntry<>(
				"brown_mooshroom_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowSkullBlock(CowSkullBlock.Types.BROWN_MOOSHROOM, props)
		);
		public static final BlockEntry<CowWallSkullBlock> COW_WALL_HEAD = new BlockEntry<>(
				"cow_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowWallSkullBlock(CowSkullBlock.Types.COW, props)
		);
		public static final BlockEntry<CowWallSkullBlock> RED_MOOSHROOM_WALL_HEAD = new BlockEntry<>(
				"red_mooshroom_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowWallSkullBlock(CowSkullBlock.Types.RED_MOOSHROOM, props)
		);
		public static final BlockEntry<CowWallSkullBlock> BROWN_MOOSHROOM_WALL_HEAD = new BlockEntry<>(
				"brown_mooshroom_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_COW),
				(props) -> new CowWallSkullBlock(CowSkullBlock.Types.BROWN_MOOSHROOM, props)
		);

		private CowSkulls() {}

		private static void init() {
		}
	}

	public static class PiglinSkulls {
		public static final BlockEntry<PiglinSkullBlock> PIGLIN_BRUTE_HEAD = new BlockEntry<>(
				"piglin_brute_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_PIGLIN_BRUTE),
				(props) -> new PiglinSkullBlock(PiglinSkullBlock.Types.PIGLIN_BRUTE, props)
		);
		public static final BlockEntry<PiglinSkullBlock> ZOMBIFIED_PIGLIN_HEAD = new BlockEntry<>(
				"zombified_piglin_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIFIED_PIGLIN),
				(props) -> new PiglinSkullBlock(PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN, props)
		);
		public static final BlockEntry<PiglinWallSkullBlock> PIGLIN_BRUTE_WALL_HEAD = new BlockEntry<>(
				"piglin_brute_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_PIGLIN_BRUTE),
				(props) -> new PiglinWallSkullBlock(PiglinSkullBlock.Types.PIGLIN_BRUTE, props)
		);
		public static final BlockEntry<PiglinWallSkullBlock> ZOMBIFIED_PIGLIN_WALL_HEAD = new BlockEntry<>(
				"zombified_piglin_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIFIED_PIGLIN),
				(props) -> new PiglinWallSkullBlock(PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN, props)
		);

		private PiglinSkulls() {}

		private static void init() {
		}
	}

	public static class HorseSkulls {
		public static final BlockEntry<HorseSkullBlock> BLACK_HORSE_HEAD = new BlockEntry<>(
				"black_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.BLACK_HORSE, props)
		);
		public static final BlockEntry<HorseSkullBlock> BROWN_HORSE_HEAD = new BlockEntry<>(
				"brown_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.BROWN_HORSE, props)
		);
		public static final BlockEntry<HorseSkullBlock> CHESTNUT_HORSE_HEAD = new BlockEntry<>(
				"chestnut_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.CHESTNUT_HORSE, props)
		);
		public static final BlockEntry<HorseSkullBlock> CREAMY_HORSE_HEAD = new BlockEntry<>(
				"creamy_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.CREAMY_HORSE, props)
		);
		public static final BlockEntry<HorseSkullBlock> DARKBROWN_HORSE_HEAD = new BlockEntry<>(
				"darkbrown_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.DARKBROWN_HORSE, props)
		);
		public static final BlockEntry<HorseSkullBlock> GRAY_HORSE_HEAD = new BlockEntry<>(
				"gray_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.GRAY_HORSE, props)
		);
		public static final BlockEntry<HorseSkullBlock> WHITE_HORSE_HEAD = new BlockEntry<>(
				"white_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.WHITE_HORSE, props)
		);
		public static final BlockEntry<HorseSkullBlock> DONKEY_HEAD = new BlockEntry<>(
				"donkey_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_DONKEY),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.DONKEY, props)
		);
		public static final BlockEntry<HorseSkullBlock> MULE_HEAD = new BlockEntry<>(
				"mule_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_MULE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.MULE, props)
		);
		public static final BlockEntry<HorseSkullBlock> SKELETON_HORSE_HEAD = new BlockEntry<>(
				"skeleton_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SKELETON_HORSE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.SKELETON_HORSE, props)
		);
		public static final BlockEntry<HorseSkullBlock> ZOMBIE_HORSE_HEAD = new BlockEntry<>(
				"zombie_horse_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIE_HORSE),
				(props) -> new HorseSkullBlock(HorseSkullBlock.Types.ZOMBIE_HORSE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> BLACK_HORSE_WALL_HEAD = new BlockEntry<>(
				"black_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.BLACK_HORSE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> BROWN_HORSE_WALL_HEAD = new BlockEntry<>(
				"brown_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.BROWN_HORSE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> CHESTNUT_HORSE_WALL_HEAD = new BlockEntry<>(
				"chestnut_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.CHESTNUT_HORSE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> CREAMY_HORSE_WALL_HEAD = new BlockEntry<>(
				"creamy_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.CREAMY_HORSE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> DARKBROWN_HORSE_WALL_HEAD = new BlockEntry<>(
				"darkbrown_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.DARKBROWN_HORSE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> GRAY_HORSE_WALL_HEAD = new BlockEntry<>(
				"gray_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.GRAY_HORSE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> WHITE_HORSE_WALL_HEAD = new BlockEntry<>(
				"white_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HORSE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.WHITE_HORSE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> DONKEY_WALL_HEAD = new BlockEntry<>(
				"donkey_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_DONKEY),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.DONKEY, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> MULE_WALL_HEAD = new BlockEntry<>(
				"mule_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_MULE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.MULE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> SKELETON_HORSE_WALL_HEAD = new BlockEntry<>(
				"skeleton_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_SKELETON_HORSE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.SKELETON_HORSE, props)
		);
		public static final BlockEntry<HorseWallSkullBlock> ZOMBIE_HORSE_WALL_HEAD = new BlockEntry<>(
				"zombie_horse_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOMBIE_HORSE),
				(props) -> new HorseWallSkullBlock(HorseSkullBlock.Types.ZOMBIE_HORSE, props)
		);

		private HorseSkulls() {}

		private static void init() {
		}
	}

	public static class WardenSkulls {
		public static final BlockEntry<WardenSkullBlock> WARDEN_HEAD = new BlockEntry<>(
				"warden_head", BOSS_SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WARDEN),
				(props) -> new WardenSkullBlock(WardenSkullBlock.Types.WARDEN, props)
		);
		public static final BlockEntry<WardenWallSkullBlock> WARDEN_WALL_HEAD = new BlockEntry<>(
				"warden_wall_head", BOSS_SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_WARDEN),
				(props) -> new WardenWallSkullBlock(WardenSkullBlock.Types.WARDEN, props)
		);

		private WardenSkulls() {}

		private static void init() {
		}
	}

	public static class HoglinSkulls {
		public static final BlockEntry<HoglinSkullBlock> HOGLIN_HEAD = new BlockEntry<>(
				"hoglin_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HOGLIN),
				(props) -> new HoglinSkullBlock(HoglinSkullBlock.Types.HOGLIN, props)
		);
		public static final BlockEntry<HoglinSkullBlock> ZOGLIN_HEAD = new BlockEntry<>(
				"zoglin_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOGLIN),
				(props) -> new HoglinSkullBlock(HoglinSkullBlock.Types.ZOGLIN, props)
		);
		public static final BlockEntry<HoglinWallSkullBlock> HOGLIN_WALL_HEAD = new BlockEntry<>(
				"hoglin_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_HOGLIN),
				(props) -> new HoglinWallSkullBlock(HoglinSkullBlock.Types.HOGLIN, props)
		);
		public static final BlockEntry<HoglinWallSkullBlock> ZOGLIN_WALL_HEAD = new BlockEntry<>(
				"zoglin_wall_head", SKULL_PROPERTIES.apply(SCNoteBlockInstruments.NOTE_BLOCK_IMITATE_ZOGLIN),
				(props) -> new HoglinWallSkullBlock(HoglinSkullBlock.Types.ZOGLIN, props)
		);

		private HoglinSkulls() {}

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
		HoglinSkulls.init();
	}

	@SuppressWarnings("unused")
	public static final class BlockEntry<T extends Block> implements Supplier<T>, ItemLike {
		private final DeferredHolder<Block, T> block;
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
