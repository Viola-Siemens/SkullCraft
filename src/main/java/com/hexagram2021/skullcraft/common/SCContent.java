package com.hexagram2021.skullcraft.common;

import com.hexagram2021.skullcraft.common.register.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCContent {
	public static void modConstruction(IEventBus bus, Consumer<Runnable> runLater) {
		SCBlocks.init(bus);
		SCItems.init(bus);
		SCContainerTypes.init(bus);
		SCBlockEntities.init(bus);
	}

	public static void init() {
	}
}
