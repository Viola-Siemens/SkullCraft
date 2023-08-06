package com.hexagram2021.skullcraft.common;

import com.google.common.collect.ImmutableSet;
import com.hexagram2021.skullcraft.common.loot.SkullNBTOps;
import com.hexagram2021.skullcraft.common.register.*;
import com.hexagram2021.skullcraft.common.world.Villages;
import com.hexagram2021.skullcraft.mixin.BlockEntityTypeAccess;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Set;
import java.util.function.Consumer;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SCContent {
	public static void modConstruction(IEventBus bus, Consumer<Runnable> runLater) {
		SCBlocks.init(bus);
		SCItems.init(bus);
		SkullNBTOps.init(bus);
		SCContainerTypes.init(bus);
		SCBlockEntities.init(bus);
		Villages.Registers.init(bus);
		SCCreativeModeTabs.init(bus);
	}

	public static void init() {
		Villages.init();
		BlockEntityTypeAccess skullBuilderAccess = (BlockEntityTypeAccess) BlockEntityType.SKULL;
		Set<Block> skullValidBlocks = new ObjectOpenHashSet<>(skullBuilderAccess.sc_getValidBlocks());
		skullValidBlocks.addAll(ImmutableSet.of(
				SCBlocks.HumanSkulls.VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.VILLAGER_WALL_HEAD.get(),
				SCBlocks.HumanSkulls.ILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ILLAGER_WALL_HEAD.get(),
				SCBlocks.HumanSkulls.WITCH_HEAD.get(), SCBlocks.HumanSkulls.WITCH_WALL_HEAD.get(),
				SCBlocks.HumanSkulls.IRON_GOLEM_HEAD.get(), SCBlocks.HumanSkulls.IRON_GOLEM_WALL_HEAD.get(),
				SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.SLIME_HEAD.get(), SCBlocks.CubeSkulls.SLIME_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.LAVASLIME_HEAD.get(), SCBlocks.CubeSkulls.LAVASLIME_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.BLAZE_HEAD.get(), SCBlocks.CubeSkulls.BLAZE_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.SPIDER_HEAD.get(), SCBlocks.CubeSkulls.SPIDER_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.CAVE_SPIDER_HEAD.get(), SCBlocks.CubeSkulls.CAVE_SPIDER_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.PIG_HEAD.get(), SCBlocks.CubeSkulls.PIG_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ENDERMAN_HEAD.get(), SCBlocks.CubeSkulls.ENDERMAN_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.SNOW_GOLEM_HEAD.get(), SCBlocks.CubeSkulls.SNOW_GOLEM_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.TECHNOBLADE_HEAD.get(), SCBlocks.CubeSkulls.TECHNOBLADE_WALL_HEAD.get(),
				SCBlocks.SmallCubeSkulls.SHEEP_HEAD.get(), SCBlocks.SmallCubeSkulls.SHEEP_WALL_HEAD.get(),
				SCBlocks.SmallCubeSkulls.BAT_HEAD.get(), SCBlocks.SmallCubeSkulls.BAT_WALL_HEAD.get(),
				SCBlocks.SmallCubeSkulls.SHULKER_HEAD.get(), SCBlocks.SmallCubeSkulls.SHULKER_WALL_HEAD.get(),
				SCBlocks.SmallCubeSkulls.ALLAY_HEAD.get(), SCBlocks.SmallCubeSkulls.ALLAY_WALL_HEAD.get(),
				SCBlocks.SmallCubeSkulls.VEX_HEAD.get(), SCBlocks.SmallCubeSkulls.VEX_WALL_HEAD.get(),
				SCBlocks.CowSkulls.COW_HEAD.get(), SCBlocks.CowSkulls.COW_WALL_HEAD.get(),
				SCBlocks.CowSkulls.RED_MOOSHROOM_HEAD.get(), SCBlocks.CowSkulls.RED_MOOSHROOM_WALL_HEAD.get(),
				SCBlocks.CowSkulls.BROWN_MOOSHROOM_HEAD.get(), SCBlocks.CowSkulls.BROWN_MOOSHROOM_WALL_HEAD.get(),
				SCBlocks.PiglinSkulls.PIGLIN_BRUTE_HEAD.get(), SCBlocks.PiglinSkulls.PIGLIN_BRUTE_WALL_HEAD.get(),
				SCBlocks.PiglinSkulls.ZOMBIFIED_PIGLIN_HEAD.get(), SCBlocks.PiglinSkulls.ZOMBIFIED_PIGLIN_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.BLACK_HORSE_HEAD.get(), SCBlocks.HorseSkulls.BLACK_HORSE_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.BROWN_HORSE_HEAD.get(), SCBlocks.HorseSkulls.BROWN_HORSE_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.CHESTNUT_HORSE_HEAD.get(), SCBlocks.HorseSkulls.CHESTNUT_HORSE_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.CREAMY_HORSE_HEAD.get(), SCBlocks.HorseSkulls.CREAMY_HORSE_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.DARKBROWN_HORSE_HEAD.get(), SCBlocks.HorseSkulls.DARKBROWN_HORSE_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.GRAY_HORSE_HEAD.get(), SCBlocks.HorseSkulls.GRAY_HORSE_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.WHITE_HORSE_HEAD.get(), SCBlocks.HorseSkulls.WHITE_HORSE_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.DONKEY_HEAD.get(), SCBlocks.HorseSkulls.DONKEY_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.MULE_HEAD.get(), SCBlocks.HorseSkulls.MULE_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.SKELETON_HORSE_HEAD.get(), SCBlocks.HorseSkulls.SKELETON_HORSE_WALL_HEAD.get(),
				SCBlocks.HorseSkulls.ZOMBIE_HORSE_HEAD.get(), SCBlocks.HorseSkulls.ZOMBIE_HORSE_WALL_HEAD.get(),
				SCBlocks.WardenSkulls.WARDEN_HEAD.get(), SCBlocks.WardenSkulls.WARDEN_WALL_HEAD.get()
		));
		skullBuilderAccess.sc_setValidBlocks(skullValidBlocks);
	}

	@SubscribeEvent
	public static void onRegister(RegisterEvent event) {
		SCSounds.init(event);
	}
}
