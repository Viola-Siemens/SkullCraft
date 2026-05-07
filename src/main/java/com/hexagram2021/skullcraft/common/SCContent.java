package com.hexagram2021.skullcraft.common;

import com.google.common.collect.ImmutableSet;
import com.hexagram2021.skullcraft.common.loot.SkullNBTOps;
import com.hexagram2021.skullcraft.common.register.*;
import com.hexagram2021.skullcraft.common.world.Villages;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Set;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@EventBusSubscriber(modid = MODID)
public class SCContent {
	public static void modConstruction(IEventBus bus) {
		SCBlocks.init(bus);
		SCItems.init(bus);
		SCDataComponents.init(bus);
		SkullNBTOps.init(bus);
		SCContainerTypes.init(bus);
		SCBlockEntities.init(bus);
		Villages.Registers.init(bus);
		SCCreativeModeTabs.init(bus);
	}

	public static void init() {
		Villages.init();
		Set<Block> skullValidBlocks = new ObjectOpenHashSet<>(BlockEntityType.SKULL.validBlocks);
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
				SCBlocks.CubeSkulls.WOLF_HEAD.get(), SCBlocks.CubeSkulls.WOLF_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.WOLF_ASHEN_HEAD.get(), SCBlocks.CubeSkulls.WOLF_ASHEN_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.WOLF_BLACK_HEAD.get(), SCBlocks.CubeSkulls.WOLF_BLACK_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.WOLF_CHESTNUT_HEAD.get(), SCBlocks.CubeSkulls.WOLF_CHESTNUT_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.WOLF_RUSTY_HEAD.get(), SCBlocks.CubeSkulls.WOLF_RUSTY_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.WOLF_SNOWY_HEAD.get(), SCBlocks.CubeSkulls.WOLF_SNOWY_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.WOLF_SPOTTED_HEAD.get(), SCBlocks.CubeSkulls.WOLF_SPOTTED_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.WOLF_STRIPED_HEAD.get(), SCBlocks.CubeSkulls.WOLF_STRIPED_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.WOLF_WOODS_HEAD.get(), SCBlocks.CubeSkulls.WOLF_WOODS_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ANGRY_WOLF_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ANGRY_WOLF_ASHEN_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_ASHEN_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ANGRY_WOLF_BLACK_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_BLACK_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ANGRY_WOLF_CHESTNUT_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_CHESTNUT_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ANGRY_WOLF_RUSTY_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_RUSTY_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ANGRY_WOLF_SNOWY_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_SNOWY_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ANGRY_WOLF_SPOTTED_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_SPOTTED_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ANGRY_WOLF_STRIPED_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_STRIPED_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ANGRY_WOLF_WOODS_HEAD.get(), SCBlocks.CubeSkulls.ANGRY_WOLF_WOODS_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.ENDERMAN_HEAD.get(), SCBlocks.CubeSkulls.ENDERMAN_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.SNOW_GOLEM_HEAD.get(), SCBlocks.CubeSkulls.SNOW_GOLEM_WALL_HEAD.get(),
				SCBlocks.CubeSkulls.BREEZE_HEAD.get(), SCBlocks.CubeSkulls.BREEZE_WALL_HEAD.get(),
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
				SCBlocks.WardenSkulls.WARDEN_HEAD.get(), SCBlocks.WardenSkulls.WARDEN_WALL_HEAD.get(),
				SCBlocks.HoglinSkulls.HOGLIN_HEAD.get(), SCBlocks.HoglinSkulls.HOGLIN_WALL_HEAD.get(),
				SCBlocks.HoglinSkulls.ZOGLIN_HEAD.get(), SCBlocks.HoglinSkulls.ZOGLIN_WALL_HEAD.get()
		));
		BlockEntityType.SKULL.validBlocks = skullValidBlocks;
	}

	@SubscribeEvent
	public static void onRegister(RegisterEvent event) {
		SCSounds.init(event);
	}

	@SubscribeEvent
	public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(
				Capabilities.ItemHandler.BLOCK,
				SCBlockEntities.SKULL_CHARGER.get(),
				(container, side) -> side == null ? new InvWrapper(container) : new SidedInvWrapper(container, side)
		);
	}
}
