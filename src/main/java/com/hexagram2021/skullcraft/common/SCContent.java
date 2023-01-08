package com.hexagram2021.skullcraft.common;

import com.hexagram2021.skullcraft.common.loot.SkullNBTOps;
import com.hexagram2021.skullcraft.common.register.*;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Consumer;

public class SCContent {
	public static void modConstruction(IEventBus bus, Consumer<Runnable> runLater) {
		SCSounds.init(bus);
		SCBlocks.init(bus);
		SCItems.init(bus);
		SkullNBTOps.init(bus);
		SCContainerTypes.init(bus);
		SCBlockEntities.init(bus);
	}

	public static void init() {
	}
}
