package com.hexagram2021.skullcraft.client;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.client.model.CubeSkullModel;
import com.hexagram2021.skullcraft.client.model.HumanSkullModel;
import com.hexagram2021.skullcraft.client.renderer.CubeSkullBlockRenderer;
import com.hexagram2021.skullcraft.client.renderer.HumanSkullBlockRenderer;
import com.hexagram2021.skullcraft.client.screen.SkullChargerScreen;
import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import com.hexagram2021.skullcraft.common.register.SCContainerTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventSubscriber {
	@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
	public static class ClientForgeEventSubscriber {
		@SubscribeEvent
		public static void onToolTipShow(ItemTooltipEvent event) {
			ItemStack itemStack = event.getItemStack();
			Item item = itemStack.getItem();
			if(item instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock) {
				if(itemStack.hasTag() && itemStack.getTag().contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
					CompoundTag scaleNBT = itemStack.getTag().getCompound(SkullCraft.SCALE_TAG);
					final int scaleX = scaleNBT.contains("x") ? Mth.clamp(scaleNBT.getInt("x"), 50, 5000) : 100;
					final int scaleY = scaleNBT.contains("y") ? Mth.clamp(scaleNBT.getInt("y"), 50, 5000) : 100;
					final int scaleZ = scaleNBT.contains("z") ? Mth.clamp(scaleNBT.getInt("z"), 50, 5000) : 100;
					MutableComponent componentX = scaleX == 100 ?
							new TranslatableComponent("tooltip.skullcraft.nox").withStyle(ChatFormatting.GRAY) :
							scaleX > 100 ?
									new TranslatableComponent("tooltip.skullcraft.x", scaleX - 100).withStyle(ChatFormatting.GOLD) :
									new TranslatableComponent("tooltip.skullcraft.negx", 100 - scaleX).withStyle(ChatFormatting.RED);
					MutableComponent componentY = scaleY == 100 ?
							new TranslatableComponent("tooltip.skullcraft.noy").withStyle(ChatFormatting.GRAY) :
							scaleY > 100 ?
									new TranslatableComponent("tooltip.skullcraft.y", scaleY - 100).withStyle(ChatFormatting.GOLD) :
									new TranslatableComponent("tooltip.skullcraft.negy", 100 - scaleY).withStyle(ChatFormatting.RED);
					MutableComponent componentZ = scaleZ == 100 ?
							new TranslatableComponent("tooltip.skullcraft.noz").withStyle(ChatFormatting.GRAY) :
							scaleZ > 100 ?
									new TranslatableComponent("tooltip.skullcraft.z", scaleZ - 100).withStyle(ChatFormatting.GOLD) :
									new TranslatableComponent("tooltip.skullcraft.negz", 100 - scaleZ).withStyle(ChatFormatting.RED);
					event.getToolTip().add(componentX);
					event.getToolTip().add(componentY);
					event.getToolTip().add(componentZ);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(HumanSkullModel.VILLAGER_HEAD, HumanSkullModel::createMobHeadLayer);
		event.registerLayerDefinition(HumanSkullModel.ILLAGER_HEAD, HumanSkullModel::createMobHeadLayer);
		event.registerLayerDefinition(HumanSkullModel.WITCH_HEAD, HumanSkullModel::createWitchHeadLayer);
		event.registerLayerDefinition(HumanSkullModel.IRON_GOLEM_HEAD, HumanSkullModel::createIronGolemHeadLayer);
		event.registerLayerDefinition(HumanSkullModel.ZOMBIE_VILLAGER_HEAD, HumanSkullModel::createMobHeadLayer);

		event.registerLayerDefinition(CubeSkullModel.SLIME_HEAD, CubeSkullModel::createSlimeHeadLayer);
		event.registerLayerDefinition(CubeSkullModel.LAVASLIME_HEAD, CubeSkullModel::createLavaSlimeLayer);
		event.registerLayerDefinition(CubeSkullModel.BLAZE_HEAD, CubeSkullModel::createMobHeadLayer);
		event.registerLayerDefinition(CubeSkullModel.SPIDER_HEAD, CubeSkullModel::createSpiderHeadLayer);
		event.registerLayerDefinition(CubeSkullModel.CAVE_SPIDER_HEAD, CubeSkullModel::createSpiderHeadLayer);
		event.registerLayerDefinition(CubeSkullModel.PIG_HEAD, CubeSkullModel::createPigHeadLayer);
		event.registerLayerDefinition(CubeSkullModel.ENDERMAN_HEAD, CubeSkullModel::createEndermanHeadLayer);
		event.registerLayerDefinition(CubeSkullModel.SNOW_GOLEM_HEAD, CubeSkullModel::createSnowGolemHeadLayer);
		event.registerLayerDefinition(CubeSkullModel.TECHNOBLADE_HEAD, CubeSkullModel::createTechnobladeHeadLayer);
	}

	@SubscribeEvent
	public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(SCBlockEntities.HUMAN_SKULL.get(), HumanSkullBlockRenderer::new);
		event.registerBlockEntityRenderer(SCBlockEntities.CUBE_SKULL.get(), CubeSkullBlockRenderer::new);
	}

	public static void modConstruction() {

	}

	@SubscribeEvent
	public static void setup(final FMLClientSetupEvent event) {
		registerContainersAndScreens();
	}

	private static void registerContainersAndScreens() {
		MenuScreens.register(SCContainerTypes.SKULL_CHARGER_MENU.get(), SkullChargerScreen::new);
	}
}
