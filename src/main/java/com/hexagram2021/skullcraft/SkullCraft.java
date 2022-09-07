package com.hexagram2021.skullcraft;

import com.hexagram2021.skullcraft.client.ClientEventSubscriber;
import com.hexagram2021.skullcraft.common.SCContent;
import com.hexagram2021.skullcraft.common.register.SCBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingStage;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        DeferredWorkQueue queue = DeferredWorkQueue.lookup(Optional.of(ModLoadingStage.CONSTRUCT)).orElseThrow();
        Consumer<Runnable> runLater = job -> queue.enqueueWork(
                ModLoadingContext.get().getActiveContainer(), job
        );
        SCContent.modConstruction(bus, runLater);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, bootstrapErrorToXCPInDev(() -> ClientEventSubscriber::modConstruction));

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(SCContent::init);
    }

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(MODID) {
        @Override
        @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(SCBlocks.SKULL_CHARGER.asItem());
        }
    };
}
