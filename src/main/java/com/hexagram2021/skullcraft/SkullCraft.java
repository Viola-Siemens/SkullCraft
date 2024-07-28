package com.hexagram2021.skullcraft;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.common.SCContent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;

import java.util.function.Supplier;

@SuppressWarnings("unused")
@Mod(SkullCraft.MODID)
public class SkullCraft {
	public static final String MODID = "skullcraft";

	public static final String SCALE_TAG = "head_scale";

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

	public SkullCraft(IEventBus modEventBus, ModContainer modContainer) {

		modEventBus.addListener(this::setup);

		modContainer.registerConfig(ModConfig.Type.CLIENT, SCClientConfig.getConfig());
		SCContent.modConstruction(modEventBus);
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(SCContent::init);
	}
}
