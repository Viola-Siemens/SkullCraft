package com.hexagram2021.skullcraft;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.common.SCContent;
import com.hexagram2021.skullcraft.common.world.Villages;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

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
		NeoForge.EVENT_BUS.addListener(this::tagsUpdated);

		modContainer.registerConfig(ModConfig.Type.CLIENT, SCClientConfig.getConfig());
		SCContent.modConstruction(modEventBus);
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(SCContent::init);
	}

	private void tagsUpdated(final TagsUpdatedEvent event) {
		if(event.getUpdateCause() != TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD) {
			return;
		}
		Villages.addAllStructuresToPool(event.getRegistryAccess());
	}
}
