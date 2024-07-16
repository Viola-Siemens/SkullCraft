package com.hexagram2021.skullcraft;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.common.SCContent;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.DeferredWorkQueue;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.ModLoadingStage;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@Mod(SkullCraft.MODID)
public class SkullCraft {
	public static final String MODID = "skullcraft";
	public static final String MODNAME = "Skull Craft";
	public static final String VERSION = "${version}";

	public static final Logger LOGGER = LogUtils.getLogger();

	public static final String SCALE_TAG = "HeadScale";

	public static <T> Supplier<T> bootstrapErrorToXCPInDev(Supplier<T> in) {
		if(FMLLoader.isProduction()) {
			return in;
		}
		return () -> {
			try {
				return in.get();
			} catch(BootstrapMethodError e) {
				throw new RuntimeException(e);
			}
		};
	}

	public SkullCraft(IEventBus modEventBus) {

		modEventBus.addListener(this::setup);

		DeferredWorkQueue queue = DeferredWorkQueue.lookup(Optional.of(ModLoadingStage.CONSTRUCT)).orElseThrow();
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SCClientConfig.getConfig());
		SCContent.modConstruction(modEventBus);
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(SCContent::init);
	}
}
