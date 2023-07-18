package com.hexagram2021.skullcraft.common;

import com.hexagram2021.skullcraft.common.loot.SkullNBTOps;
import com.hexagram2021.skullcraft.common.register.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

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
		SCCreativeModeTabs.init(bus);
	}

	public static void init() {
	}

	@SubscribeEvent
	public static void onRegister(RegisterEvent event) {
		SCSounds.init(event);
	}
}
