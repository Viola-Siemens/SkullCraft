package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.common.block.CowSkull.*;
import com.hexagram2021.skullcraft.common.block.CubeSkull.*;
import com.hexagram2021.skullcraft.common.block.HumanSkull.*;
import com.hexagram2021.skullcraft.common.block.PiglinSkull.*;
import com.hexagram2021.skullcraft.common.block.SkullChargerBlock;
import com.hexagram2021.skullcraft.common.block.SmallCubeSkull.*;
import com.hexagram2021.skullcraft.common.block.WardenSkull.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCBlocks {
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

	private static final Supplier<BlockBehaviour.Properties> SKULL_CHARGER_PROPERTIES = () ->
			BlockBehaviour.Properties.of(Material.HEAVY_METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LODESTONE);

	private static final Supplier<BlockBehaviour.Properties> SKULL_PROPERTIES = () ->
			BlockBehaviour.Properties.of(Material.DECORATION).strength(1.0F);
	private static final Supplier<BlockBehaviour.Properties> BOSS_SKULL_PROPERTIES = () ->
			BlockBehaviour.Properties.of(Material.DECORATION).strength(2.0F);

	public static final BlockEntry<Block> SKULL_CHARGER = new BlockEntry<>(
			"skull_charger", SKULL_CHARGER_PROPERTIES, SkullChargerBlock::new
	);

	public static class HumanSkulls {
		public static final BlockEntry<HumanSkullBlock> VILLAGER_HEAD = new BlockEntry<>(
				"villager_head", SKULL_PROPERTIES, (props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.VILLAGER)
		);
		public static final BlockEntry<HumanSkullBlock> ILLAGER_HEAD = new BlockEntry<>(
				"illager_head", SKULL_PROPERTIES, (props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.ILLAGER)
		);
		public static final BlockEntry<HumanSkullBlock> WITCH_HEAD = new BlockEntry<>(
				"witch_head", SKULL_PROPERTIES, (props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.WITCH)
		);
		public static final BlockEntry<HumanSkullBlock> IRON_GOLEM_HEAD = new BlockEntry<>(
				"iron_golem_head", SKULL_PROPERTIES, (props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.IRON_GOLEM)
		);
		public static final BlockEntry<HumanSkullBlock> ZOMBIE_VILLAGER_HEAD = new BlockEntry<>(
				"zombie_villager_head", SKULL_PROPERTIES, (props) -> new HumanSkullBlock(props, HumanSkullBlock.Types.ZOMBIE_VILLAGER)
		);

		public static final BlockEntry<HumanWallSkullBlock> VILLAGER_WALL_HEAD = new BlockEntry<>(
				"villager_wall_head", SKULL_PROPERTIES, (props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.VILLAGER)
		);
		public static final BlockEntry<HumanWallSkullBlock> ILLAGER_WALL_HEAD = new BlockEntry<>(
				"illager_wall_head", SKULL_PROPERTIES, (props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.ILLAGER)
		);
		public static final BlockEntry<HumanWallSkullBlock> WITCH_WALL_HEAD = new BlockEntry<>(
				"witch_wall_head", SKULL_PROPERTIES, (props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.WITCH)
		);
		public static final BlockEntry<HumanWallSkullBlock> IRON_GOLEM_WALL_HEAD = new BlockEntry<>(
				"iron_golem_wall_head", SKULL_PROPERTIES, (props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.IRON_GOLEM)
		);
		public static final BlockEntry<HumanWallSkullBlock> ZOMBIE_VILLAGER_WALL_HEAD = new BlockEntry<>(
				"zombie_villager_wall_head", SKULL_PROPERTIES, (props) -> new HumanWallSkullBlock(props, HumanSkullBlock.Types.ZOMBIE_VILLAGER)
		);

		private HumanSkulls() {}

		private static void init() {
		}
	}

	public static class CubeSkulls {
		public static final BlockEntry<CubeSkullBlock> SLIME_HEAD = new BlockEntry<>(
				"slime_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.SLIME)
		);
		public static final BlockEntry<CubeSkullBlock> LAVASLIME_HEAD = new BlockEntry<>(
				"lavaslime_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.LAVASLIME)
		);
		public static final BlockEntry<CubeSkullBlock> BLAZE_HEAD = new BlockEntry<>(
				"blaze_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.BLAZE)
		);
		public static final BlockEntry<CubeSkullBlock> SPIDER_HEAD = new BlockEntry<>(
				"spider_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.SPIDER)
		);
		public static final BlockEntry<CubeSkullBlock> CAVE_SPIDER_HEAD = new BlockEntry<>(
				"cave_spider_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.CAVE_SPIDER)
		);
		public static final BlockEntry<CubeSkullBlock> PIG_HEAD = new BlockEntry<>(
				"pig_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.PIG)
		);
		public static final BlockEntry<CubeSkullBlock> VEX_HEAD = new BlockEntry<>(
				"vex_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.VEX)
		);
		public static final BlockEntry<CubeSkullBlock> ENDERMAN_HEAD = new BlockEntry<>(
				"enderman_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.ENDERMAN)
		);
		public static final BlockEntry<CubeSkullBlock> SNOW_GOLEM_HEAD = new BlockEntry<>(
				"snow_golem_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.SNOW_GOLEM)
		);
		public static final BlockEntry<CubeSkullBlock> TECHNOBLADE_HEAD = new BlockEntry<>(
				"technoblade_head", SKULL_PROPERTIES, (props) -> new CubeSkullBlock(props, CubeSkullBlock.Types.TECHNOBLADE)
		);

		public static final BlockEntry<CubeWallSkullBlock> SLIME_WALL_HEAD = new BlockEntry<>(
				"slime_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.SLIME)
		);
		public static final BlockEntry<CubeWallSkullBlock> LAVASLIME_WALL_HEAD = new BlockEntry<>(
				"lavaslime_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.LAVASLIME)
		);
		public static final BlockEntry<CubeWallSkullBlock> BLAZE_WALL_HEAD = new BlockEntry<>(
				"blaze_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.BLAZE)
		);
		public static final BlockEntry<CubeWallSkullBlock> SPIDER_WALL_HEAD = new BlockEntry<>(
				"spider_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.SPIDER)
		);
		public static final BlockEntry<CubeWallSkullBlock> CAVE_SPIDER_WALL_HEAD = new BlockEntry<>(
				"cave_spider_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.CAVE_SPIDER)
		);
		public static final BlockEntry<CubeWallSkullBlock> PIG_WALL_HEAD = new BlockEntry<>(
				"pig_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.PIG)
		);
		public static final BlockEntry<CubeWallSkullBlock> VEX_WALL_HEAD = new BlockEntry<>(
				"vex_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.VEX)
		);
		public static final BlockEntry<CubeWallSkullBlock> ENDERMAN_WALL_HEAD = new BlockEntry<>(
				"enderman_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.ENDERMAN)
		);
		public static final BlockEntry<CubeWallSkullBlock> SNOW_GOLEM_WALL_HEAD = new BlockEntry<>(
				"snow_golem_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.SNOW_GOLEM)
		);
		public static final BlockEntry<CubeWallSkullBlock> TECHNOBLADE_WALL_HEAD = new BlockEntry<>(
				"technoblade_wall_head", SKULL_PROPERTIES, (props) -> new CubeWallSkullBlock(props, CubeSkullBlock.Types.TECHNOBLADE)
		);


		private CubeSkulls() {}

		private static void init() {
		}
	}

	public static class SmallCubeSkulls {
		public static final BlockEntry<SmallCubeSkullBlock> SHEEP_HEAD = new BlockEntry<>(
				"sheep_head", SKULL_PROPERTIES, (props) -> new SmallCubeSkullBlock(props, SmallCubeSkullBlock.Types.SHEEP)
		);
		public static final BlockEntry<SmallCubeSkullBlock> BAT_HEAD = new BlockEntry<>(
				"bat_head", SKULL_PROPERTIES, (props) -> new SmallCubeSkullBlock(props, SmallCubeSkullBlock.Types.BAT)
		);
		public static final BlockEntry<SmallCubeSkullBlock> SHULKER_HEAD = new BlockEntry<>(
				"shulker_head", SKULL_PROPERTIES, (props) -> new SmallCubeSkullBlock(props, SmallCubeSkullBlock.Types.SHULKER)
		);
		public static final BlockEntry<SmallCubeSkullBlock> ALLAY_HEAD = new BlockEntry<>(
				"allay_head", SKULL_PROPERTIES, (props) -> new SmallCubeSkullBlock(props, SmallCubeSkullBlock.Types.ALLAY)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> SHEEP_WALL_HEAD = new BlockEntry<>(
				"sheep_wall_head", SKULL_PROPERTIES, (props) -> new SmallCubeWallSkullBlock(props, SmallCubeSkullBlock.Types.SHEEP)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> BAT_WALL_HEAD = new BlockEntry<>(
				"bat_wall_head", SKULL_PROPERTIES, (props) -> new SmallCubeWallSkullBlock(props, SmallCubeSkullBlock.Types.BAT)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> SHULKER_WALL_HEAD = new BlockEntry<>(
				"shulker_wall_head", SKULL_PROPERTIES, (props) -> new SmallCubeWallSkullBlock(props, SmallCubeSkullBlock.Types.SHULKER)
		);
		public static final BlockEntry<SmallCubeWallSkullBlock> ALLAY_WALL_HEAD = new BlockEntry<>(
				"allay_wall_head", SKULL_PROPERTIES, (props) -> new SmallCubeWallSkullBlock(props, SmallCubeSkullBlock.Types.ALLAY)
		);

		private SmallCubeSkulls() {}

		private static void init() {
		}
	}

	public static class CowSkulls {
		public static final BlockEntry<CowSkullBlock> COW_HEAD = new BlockEntry<>(
				"cow_head", SKULL_PROPERTIES, (props) -> new CowSkullBlock(props, CowSkullBlock.Types.COW)
		);
		public static final BlockEntry<CowSkullBlock> RED_MOOSHROOM_HEAD = new BlockEntry<>(
				"red_mooshroom_head", SKULL_PROPERTIES, (props) -> new CowSkullBlock(props, CowSkullBlock.Types.RED_MOOSHROOM)
		);
		public static final BlockEntry<CowSkullBlock> BROWN_MOOSHROOM_HEAD = new BlockEntry<>(
				"brown_mooshroom_head", SKULL_PROPERTIES, (props) -> new CowSkullBlock(props, CowSkullBlock.Types.BROWN_MOOSHROOM)
		);
		public static final BlockEntry<CowWallSkullBlock> COW_WALL_HEAD = new BlockEntry<>(
				"cow_wall_head", SKULL_PROPERTIES, (props) -> new CowWallSkullBlock(props, CowSkullBlock.Types.COW)
		);
		public static final BlockEntry<CowWallSkullBlock> RED_MOOSHROOM_WALL_HEAD = new BlockEntry<>(
				"red_mooshroom_wall_head", SKULL_PROPERTIES, (props) -> new CowWallSkullBlock(props, CowSkullBlock.Types.RED_MOOSHROOM)
		);
		public static final BlockEntry<CowWallSkullBlock> BROWN_MOOSHROOM_WALL_HEAD = new BlockEntry<>(
				"brown_mooshroom_wall_head", SKULL_PROPERTIES, (props) -> new CowWallSkullBlock(props, CowSkullBlock.Types.BROWN_MOOSHROOM)
		);

		private CowSkulls() {}

		private static void init() {
		}
	}

	public static class PiglinSkulls {
		public static final BlockEntry<PiglinSkullBlock> PIGLIN_HEAD = new BlockEntry<>(
				"piglin_head", SKULL_PROPERTIES, (props) -> new PiglinSkullBlock(props, PiglinSkullBlock.Types.PIGLIN)
		);
		public static final BlockEntry<PiglinSkullBlock> PIGLIN_BRUTE_HEAD = new BlockEntry<>(
				"piglin_brute_head", SKULL_PROPERTIES, (props) -> new PiglinSkullBlock(props, PiglinSkullBlock.Types.PIGLIN_BRUTE)
		);
		public static final BlockEntry<PiglinSkullBlock> ZOMBIFIED_PIGLIN_HEAD = new BlockEntry<>(
				"zombified_piglin_head", SKULL_PROPERTIES, (props) -> new PiglinSkullBlock(props, PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN)
		);
		public static final BlockEntry<PiglinWallSkullBlock> PIGLIN_WALL_HEAD = new BlockEntry<>(
				"piglin_wall_head", SKULL_PROPERTIES, (props) -> new PiglinWallSkullBlock(props, PiglinSkullBlock.Types.PIGLIN)
		);
		public static final BlockEntry<PiglinWallSkullBlock> PIGLIN_BRUTE_WALL_HEAD = new BlockEntry<>(
				"piglin_brute_wall_head", SKULL_PROPERTIES, (props) -> new PiglinWallSkullBlock(props, PiglinSkullBlock.Types.PIGLIN_BRUTE)
		);
		public static final BlockEntry<PiglinWallSkullBlock> ZOMBIFIED_PIGLIN_WALL_HEAD = new BlockEntry<>(
				"zombified_piglin_wall_head", SKULL_PROPERTIES, (props) -> new PiglinWallSkullBlock(props, PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN)
		);

		private PiglinSkulls() {}

		private static void init() {
		}
	}

	public static class WardenSkulls {
		public static final BlockEntry<WardenSkullBlock> WARDEN_HEAD = new BlockEntry<>(
				"warden_head", BOSS_SKULL_PROPERTIES, (props) -> new WardenSkullBlock(props, WardenSkullBlock.Types.WARDEN)
		);
		public static final BlockEntry<WardenWallSkullBlock> WARDEN_WALL_HEAD = new BlockEntry<>(
				"warden_wall_head", BOSS_SKULL_PROPERTIES, (props) -> new WardenWallSkullBlock(props, WardenSkullBlock.Types.WARDEN)
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

		@Nonnull
		@Override
		public Item asItem() {
			return this.get().asItem();
		}
	}
}
