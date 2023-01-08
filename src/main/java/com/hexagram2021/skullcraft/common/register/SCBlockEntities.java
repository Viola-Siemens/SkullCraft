package com.hexagram2021.skullcraft.common.register;

import com.google.common.collect.ImmutableSet;
import com.hexagram2021.skullcraft.common.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

	public static final RegistryObject<BlockEntityType<HumanSkullBlockEntity>> HUMAN_SKULL = REGISTER.register(
			"human_skull", () -> new BlockEntityType<>(
					HumanSkullBlockEntity::new, ImmutableSet.of(
							SCBlocks.HumanSkulls.VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.VILLAGER_WALL_HEAD.get(),
							SCBlocks.HumanSkulls.ILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ILLAGER_WALL_HEAD.get(),
							SCBlocks.HumanSkulls.WITCH_HEAD.get(), SCBlocks.HumanSkulls.WITCH_WALL_HEAD.get(),
							SCBlocks.HumanSkulls.IRON_GOLEM_HEAD.get(), SCBlocks.HumanSkulls.IRON_GOLEM_WALL_HEAD.get(),
							SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_WALL_HEAD.get()
					), null
			)
	);
	public static final RegistryObject<BlockEntityType<CubeSkullBlockEntity>> CUBE_SKULL = REGISTER.register(
			"cube_skull", () -> new BlockEntityType<>(
					CubeSkullBlockEntity::new, ImmutableSet.of(
							SCBlocks.CubeSkulls.SLIME_HEAD.get(), SCBlocks.CubeSkulls.SLIME_WALL_HEAD.get(),
							SCBlocks.CubeSkulls.LAVASLIME_HEAD.get(), SCBlocks.CubeSkulls.LAVASLIME_WALL_HEAD.get(),
							SCBlocks.CubeSkulls.BLAZE_HEAD.get(), SCBlocks.CubeSkulls.BLAZE_WALL_HEAD.get(),
							SCBlocks.CubeSkulls.SPIDER_HEAD.get(), SCBlocks.CubeSkulls.SPIDER_WALL_HEAD.get(),
							SCBlocks.CubeSkulls.CAVE_SPIDER_HEAD.get(), SCBlocks.CubeSkulls.CAVE_SPIDER_WALL_HEAD.get(),
							SCBlocks.CubeSkulls.PIG_HEAD.get(), SCBlocks.CubeSkulls.PIG_WALL_HEAD.get(),
							SCBlocks.CubeSkulls.ENDERMAN_HEAD.get(), SCBlocks.CubeSkulls.ENDERMAN_WALL_HEAD.get(),
							SCBlocks.CubeSkulls.SNOW_GOLEM_HEAD.get(), SCBlocks.CubeSkulls.SNOW_GOLEM_WALL_HEAD.get(),
							SCBlocks.CubeSkulls.TECHNOBLADE_HEAD.get(), SCBlocks.CubeSkulls.TECHNOBLADE_WALL_HEAD.get()
					), null
			)
	);
	public static final RegistryObject<BlockEntityType<SmallCubeSkullBlockEntity>> SMALL_CUBE_SKULL = REGISTER.register(
			"small_cube_skull", () -> new BlockEntityType<>(
					SmallCubeSkullBlockEntity::new, ImmutableSet.of(
							SCBlocks.SmallCubeSkulls.SHEEP_HEAD.get(), SCBlocks.SmallCubeSkulls.SHEEP_WALL_HEAD.get(),
							SCBlocks.SmallCubeSkulls.BAT_HEAD.get(), SCBlocks.SmallCubeSkulls.BAT_WALL_HEAD.get(),
							SCBlocks.SmallCubeSkulls.SHULKER_HEAD.get(), SCBlocks.SmallCubeSkulls.SHULKER_WALL_HEAD.get(),
							SCBlocks.SmallCubeSkulls.ALLAY_HEAD.get(), SCBlocks.SmallCubeSkulls.ALLAY_WALL_HEAD.get(),
							SCBlocks.SmallCubeSkulls.VEX_HEAD.get(), SCBlocks.SmallCubeSkulls.VEX_WALL_HEAD.get()
					), null
			)
	);
	public static final RegistryObject<BlockEntityType<CowSkullBlockEntity>> COW_SKULL = REGISTER.register(
			"cow_skull", () -> new BlockEntityType<>(
					CowSkullBlockEntity::new, ImmutableSet.of(
							SCBlocks.CowSkulls.COW_HEAD.get(), SCBlocks.CowSkulls.COW_WALL_HEAD.get(),
							SCBlocks.CowSkulls.RED_MOOSHROOM_HEAD.get(), SCBlocks.CowSkulls.RED_MOOSHROOM_WALL_HEAD.get(),
							SCBlocks.CowSkulls.BROWN_MOOSHROOM_HEAD.get(), SCBlocks.CowSkulls.BROWN_MOOSHROOM_WALL_HEAD.get()
					), null
			)
	);
	public static final RegistryObject<BlockEntityType<PiglinSkullBlockEntity>> PIGLIN_SKULL = REGISTER.register(
			"piglin_skull", () -> new BlockEntityType<>(
					PiglinSkullBlockEntity::new, ImmutableSet.of(
							SCBlocks.PiglinSkulls.PIGLIN_BRUTE_HEAD.get(), SCBlocks.PiglinSkulls.PIGLIN_BRUTE_WALL_HEAD.get(),
							SCBlocks.PiglinSkulls.ZOMBIFIED_PIGLIN_HEAD.get(), SCBlocks.PiglinSkulls.ZOMBIFIED_PIGLIN_WALL_HEAD.get()
					), null
			)
	);
	public static final RegistryObject<BlockEntityType<WardenSkullBlockEntity>> WARDEN_SKULL = REGISTER.register(
			"warden_skull", () -> new BlockEntityType<>(
					WardenSkullBlockEntity::new, ImmutableSet.of(
							SCBlocks.WardenSkulls.WARDEN_HEAD.get(), SCBlocks.WardenSkulls.WARDEN_WALL_HEAD.get()
					), null
			)
	);

	public static final RegistryObject<BlockEntityType<SkullChargerBlockEntity>> SKULL_CHARGER = REGISTER.register(
			"skull_charger", () -> new BlockEntityType<>(
					SkullChargerBlockEntity::new, ImmutableSet.of(SCBlocks.SKULL_CHARGER.get()), null
			)
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
