package com.hexagram2021.skullcraft;

import com.hexagram2021.skullcraft.client.ClientEventSubscriber;
import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.common.SCContent;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingStage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Consumer;
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

	public SkullCraft() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::setup);

		DeferredWorkQueue queue = DeferredWorkQueue.lookup(Optional.of(ModLoadingStage.CONSTRUCT)).orElseThrow();
		Consumer<Runnable> runLater = job -> queue.enqueueWork(
				ModLoadingContext.get().getActiveContainer(), job
		);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SCClientConfig.getConfig());
		SCContent.modConstruction(bus, runLater);

		DistExecutor.safeRunWhenOn(Dist.CLIENT, bootstrapErrorToXCPInDev(() -> ClientEventSubscriber::modConstruction));

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(SCContent::init);
	}
}
