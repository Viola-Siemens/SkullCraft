package com.hexagram2021.skullcraft.client;

import com.google.common.collect.ImmutableMap;
import com.hexagram2021.skullcraft.client.model.*;
import com.hexagram2021.skullcraft.client.screen.SkullChargerScreen;
import com.hexagram2021.skullcraft.common.block.cow.CowSkullBlock;
import com.hexagram2021.skullcraft.common.block.cube.CubeSkullBlock;
import com.hexagram2021.skullcraft.common.block.horse.HorseSkullBlock;
import com.hexagram2021.skullcraft.common.block.human.HumanSkullBlock;
import com.hexagram2021.skullcraft.common.block.piglin.PiglinSkullBlock;
import com.hexagram2021.skullcraft.common.block.small_cube.SmallCubeSkullBlock;
import com.hexagram2021.skullcraft.common.block.warden.WardenSkullBlock;
import com.hexagram2021.skullcraft.common.components.SkullScale;
import com.hexagram2021.skullcraft.common.register.SCContainerTypes;
import com.hexagram2021.skullcraft.common.register.SCDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientEventSubscriber {
	@EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
	public static class ClientForgeEventSubscriber {
		@SubscribeEvent
		public static void onToolTipShow(ItemTooltipEvent event) {
			ItemStack itemStack = event.getItemStack();
			Item item = itemStack.getItem();
			if(item instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock) {
				SkullScale skullScale = itemStack.get(SCDataComponents.SKULL_SCALE.get());
				if(skullScale != null) {
					MutableComponent componentX = skullScale.x() == 100 ?
							Component.translatable("tooltip.skullcraft.nox").withStyle(ChatFormatting.GRAY) :
							skullScale.x() > 100 ?
									Component.translatable("tooltip.skullcraft.x", skullScale.x() - 100).withStyle(ChatFormatting.GOLD) :
									Component.translatable("tooltip.skullcraft.negx", 100 - skullScale.x()).withStyle(ChatFormatting.RED);
					MutableComponent componentY = skullScale.y() == 100 ?
							Component.translatable("tooltip.skullcraft.noy").withStyle(ChatFormatting.GRAY) :
							skullScale.y() > 100 ?
									Component.translatable("tooltip.skullcraft.y", skullScale.y() - 100).withStyle(ChatFormatting.GOLD) :
									Component.translatable("tooltip.skullcraft.negy", 100 - skullScale.y()).withStyle(ChatFormatting.RED);
					MutableComponent componentZ = skullScale.z() == 100 ?
							Component.translatable("tooltip.skullcraft.noz").withStyle(ChatFormatting.GRAY) :
							skullScale.z() > 100 ?
									Component.translatable("tooltip.skullcraft.z", skullScale.z() - 100).withStyle(ChatFormatting.GOLD) :
									Component.translatable("tooltip.skullcraft.negz", 100 - skullScale.z()).withStyle(ChatFormatting.RED);
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

		event.registerLayerDefinition(SmallCubeSkullModel.SHEEP_HEAD, SmallCubeSkullModel::createSheepHeadLayer);
		event.registerLayerDefinition(SmallCubeSkullModel.BAT_HEAD, SmallCubeSkullModel::createBatHeadLayer);
		event.registerLayerDefinition(SmallCubeSkullModel.SHULKER_HEAD, SmallCubeSkullModel::createShulkerHeadLayer);
		event.registerLayerDefinition(SmallCubeSkullModel.ALLAY_HEAD, SmallCubeSkullModel::createAllayHeadLayer);
		event.registerLayerDefinition(SmallCubeSkullModel.VEX_HEAD, SmallCubeSkullModel::createAllayHeadLayer);
		event.registerLayerDefinition(SmallCubeSkullModel.WOLF_HEAD, SmallCubeSkullModel::createWolfHeadLayer);
		event.registerLayerDefinition(SmallCubeSkullModel.ANGRY_WOLF_HEAD, SmallCubeSkullModel::createWolfHeadLayer);

		event.registerLayerDefinition(CowSkullModel.COW_HEAD, CowSkullModel::createCowHeadLayer);
		event.registerLayerDefinition(CowSkullModel.RED_MOOSHROOM_HEAD, CowSkullModel::createCowHeadLayer);
		event.registerLayerDefinition(CowSkullModel.BROWN_MOOSHROOM_HEAD, CowSkullModel::createCowHeadLayer);

		event.registerLayerDefinition(PiglinSkullModel.PIGLIN_BRUTE_HEAD, PiglinSkullModel::createPiglinHeadLayer);
		event.registerLayerDefinition(PiglinSkullModel.ZOMBIFIED_PIGLIN_HEAD, PiglinSkullModel::createPiglinHeadLayer);

		event.registerLayerDefinition(HorseSkullModel.BLACK_HORSE_HEAD, HorseSkullModel::createHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.BROWN_HORSE_HEAD, HorseSkullModel::createHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.CHESTNUT_HORSE_HEAD, HorseSkullModel::createHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.CREAMY_HORSE_HEAD, HorseSkullModel::createHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.DARKBROWN_HORSE_HEAD, HorseSkullModel::createHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.GRAY_HORSE_HEAD, HorseSkullModel::createHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.WHITE_HORSE_HEAD, HorseSkullModel::createHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.DONKEY_HEAD, HorseSkullModel::createChestedHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.MULE_HEAD, HorseSkullModel::createChestedHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.SKELETON_HORSE_HEAD, HorseSkullModel::createHorseHeadLayer);
		event.registerLayerDefinition(HorseSkullModel.ZOMBIE_HORSE_HEAD, HorseSkullModel::createHorseHeadLayer);

		event.registerLayerDefinition(WardenSkullModel.WARDEN_HEAD, WardenSkullModel::createWardenHeadLayer);
	}

	@SubscribeEvent
	public static void onCreateSkullModel(EntityRenderersEvent.CreateSkullModels event) {
		event.registerSkullModel(HumanSkullBlock.Types.VILLAGER, new HumanSkullModel(event.getEntityModelSet().bakeLayer(HumanSkullModel.VILLAGER_HEAD)));
		event.registerSkullModel(HumanSkullBlock.Types.ILLAGER, new HumanSkullModel(event.getEntityModelSet().bakeLayer(HumanSkullModel.ILLAGER_HEAD)));
		event.registerSkullModel(HumanSkullBlock.Types.WITCH, new HumanSkullModel(event.getEntityModelSet().bakeLayer(HumanSkullModel.WITCH_HEAD)));
		event.registerSkullModel(HumanSkullBlock.Types.IRON_GOLEM, new HumanSkullModel(event.getEntityModelSet().bakeLayer(HumanSkullModel.IRON_GOLEM_HEAD)));
		event.registerSkullModel(HumanSkullBlock.Types.ZOMBIE_VILLAGER, new HumanSkullModel(event.getEntityModelSet().bakeLayer(HumanSkullModel.ZOMBIE_VILLAGER_HEAD)));
		event.registerSkullModel(CubeSkullBlock.Types.SLIME, new CubeSkullModel(event.getEntityModelSet().bakeLayer(CubeSkullModel.SLIME_HEAD)));
		event.registerSkullModel(CubeSkullBlock.Types.LAVASLIME, new CubeSkullModel(event.getEntityModelSet().bakeLayer(CubeSkullModel.LAVASLIME_HEAD)));
		event.registerSkullModel(CubeSkullBlock.Types.BLAZE, new CubeSkullModel(event.getEntityModelSet().bakeLayer(CubeSkullModel.BLAZE_HEAD)));
		event.registerSkullModel(CubeSkullBlock.Types.SPIDER, new CubeSkullModel(event.getEntityModelSet().bakeLayer(CubeSkullModel.SPIDER_HEAD)));
		event.registerSkullModel(CubeSkullBlock.Types.CAVE_SPIDER, new CubeSkullModel(event.getEntityModelSet().bakeLayer(CubeSkullModel.CAVE_SPIDER_HEAD)));
		event.registerSkullModel(CubeSkullBlock.Types.PIG, new CubeSkullModel(event.getEntityModelSet().bakeLayer(CubeSkullModel.PIG_HEAD)));
		event.registerSkullModel(CubeSkullBlock.Types.ENDERMAN, new CubeSkullModel(event.getEntityModelSet().bakeLayer(CubeSkullModel.ENDERMAN_HEAD)));
		event.registerSkullModel(CubeSkullBlock.Types.SNOW_GOLEM, new CubeSkullModel(event.getEntityModelSet().bakeLayer(CubeSkullModel.SNOW_GOLEM_HEAD)));
		event.registerSkullModel(CubeSkullBlock.Types.TECHNOBLADE, new CubeSkullModel(event.getEntityModelSet().bakeLayer(CubeSkullModel.TECHNOBLADE_HEAD)));
		event.registerSkullModel(SmallCubeSkullBlock.Types.SHEEP, new SmallCubeSkullModel(event.getEntityModelSet().bakeLayer(SmallCubeSkullModel.SHEEP_HEAD)));
		event.registerSkullModel(SmallCubeSkullBlock.Types.BAT, new SmallCubeSkullModel(event.getEntityModelSet().bakeLayer(SmallCubeSkullModel.BAT_HEAD)));
		event.registerSkullModel(SmallCubeSkullBlock.Types.SHULKER, new SmallCubeSkullModel(event.getEntityModelSet().bakeLayer(SmallCubeSkullModel.SHULKER_HEAD)));
		event.registerSkullModel(SmallCubeSkullBlock.Types.ALLAY, new SmallCubeSkullModel(event.getEntityModelSet().bakeLayer(SmallCubeSkullModel.ALLAY_HEAD)));
		event.registerSkullModel(SmallCubeSkullBlock.Types.VEX, new SmallCubeSkullModel(event.getEntityModelSet().bakeLayer(SmallCubeSkullModel.VEX_HEAD)));
		event.registerSkullModel(SmallCubeSkullBlock.Types.WOLF, new SmallCubeSkullModel(event.getEntityModelSet().bakeLayer(SmallCubeSkullModel.WOLF_HEAD)));
		event.registerSkullModel(SmallCubeSkullBlock.Types.ANGRY_WOLF, new SmallCubeSkullModel(event.getEntityModelSet().bakeLayer(SmallCubeSkullModel.ANGRY_WOLF_HEAD)));
		event.registerSkullModel(CowSkullBlock.Types.COW, new CowSkullModel(event.getEntityModelSet().bakeLayer(CowSkullModel.COW_HEAD)));
		event.registerSkullModel(CowSkullBlock.Types.RED_MOOSHROOM, new CowSkullModel(event.getEntityModelSet().bakeLayer(CowSkullModel.RED_MOOSHROOM_HEAD)));
		event.registerSkullModel(CowSkullBlock.Types.BROWN_MOOSHROOM, new CowSkullModel(event.getEntityModelSet().bakeLayer(CowSkullModel.BROWN_MOOSHROOM_HEAD)));
		event.registerSkullModel(PiglinSkullBlock.Types.PIGLIN_BRUTE, new PiglinSkullModel(event.getEntityModelSet().bakeLayer(PiglinSkullModel.PIGLIN_BRUTE_HEAD)));
		event.registerSkullModel(PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN, new PiglinSkullModel(event.getEntityModelSet().bakeLayer(PiglinSkullModel.ZOMBIFIED_PIGLIN_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.BLACK_HORSE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.BLACK_HORSE_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.BROWN_HORSE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.BROWN_HORSE_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.CHESTNUT_HORSE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.CHESTNUT_HORSE_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.CREAMY_HORSE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.CREAMY_HORSE_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.DARKBROWN_HORSE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.DARKBROWN_HORSE_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.GRAY_HORSE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.GRAY_HORSE_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.WHITE_HORSE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.WHITE_HORSE_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.DONKEY, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.DONKEY_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.MULE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.MULE_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.SKELETON_HORSE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.SKELETON_HORSE_HEAD)));
		event.registerSkullModel(HorseSkullBlock.Types.ZOMBIE_HORSE, new HorseSkullModel(event.getEntityModelSet().bakeLayer(HorseSkullModel.ZOMBIE_HORSE_HEAD)));
		event.registerSkullModel(WardenSkullBlock.Types.WARDEN, new WardenSkullModel(event.getEntityModelSet().bakeLayer(WardenSkullModel.WARDEN_HEAD)));
	}

	@SubscribeEvent
	public static void setup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ImmutableMap.Builder<SkullBlock.Type, ResourceLocation> builder = ImmutableMap.builder();
			builder.put(HumanSkullBlock.Types.VILLAGER, ResourceLocation.withDefaultNamespace("textures/entity/villager/villager.png"));
			builder.put(HumanSkullBlock.Types.ILLAGER, ResourceLocation.withDefaultNamespace("textures/entity/illager/pillager.png"));
			builder.put(HumanSkullBlock.Types.WITCH, ResourceLocation.withDefaultNamespace("textures/entity/witch.png"));
			builder.put(HumanSkullBlock.Types.IRON_GOLEM, ResourceLocation.withDefaultNamespace("textures/entity/iron_golem/iron_golem.png"));
			builder.put(HumanSkullBlock.Types.ZOMBIE_VILLAGER, ResourceLocation.withDefaultNamespace("textures/entity/zombie_villager/zombie_villager.png"));
			builder.put(CubeSkullBlock.Types.SLIME, ResourceLocation.withDefaultNamespace("textures/entity/slime/slime.png"));
			builder.put(CubeSkullBlock.Types.LAVASLIME, ResourceLocation.withDefaultNamespace("textures/entity/slime/magmacube.png"));
			builder.put(CubeSkullBlock.Types.BLAZE, ResourceLocation.withDefaultNamespace("textures/entity/blaze.png"));
			builder.put(CubeSkullBlock.Types.SPIDER, ResourceLocation.withDefaultNamespace("textures/entity/spider/spider.png"));
			builder.put(CubeSkullBlock.Types.CAVE_SPIDER, ResourceLocation.withDefaultNamespace("textures/entity/spider/cave_spider.png"));
			builder.put(CubeSkullBlock.Types.PIG, ResourceLocation.withDefaultNamespace("textures/entity/pig/pig.png"));
			builder.put(CubeSkullBlock.Types.ENDERMAN, ResourceLocation.withDefaultNamespace("textures/entity/enderman/enderman.png"));
			builder.put(CubeSkullBlock.Types.SNOW_GOLEM, ResourceLocation.withDefaultNamespace("textures/entity/snow_golem.png"));
			builder.put(CubeSkullBlock.Types.TECHNOBLADE, ResourceLocation.fromNamespaceAndPath(MODID, "textures/entity/technoblade.png"));
			builder.put(SmallCubeSkullBlock.Types.SHEEP, ResourceLocation.fromNamespaceAndPath(MODID, "textures/entity/sheep.png"));
			builder.put(SmallCubeSkullBlock.Types.BAT, ResourceLocation.withDefaultNamespace("textures/entity/bat.png"));
			builder.put(SmallCubeSkullBlock.Types.SHULKER, ResourceLocation.withDefaultNamespace("textures/entity/shulker/shulker.png"));
			builder.put(SmallCubeSkullBlock.Types.ALLAY, ResourceLocation.withDefaultNamespace("textures/entity/allay/allay.png"));
			builder.put(SmallCubeSkullBlock.Types.VEX, ResourceLocation.withDefaultNamespace("textures/entity/illager/vex.png"));
			builder.put(SmallCubeSkullBlock.Types.WOLF, ResourceLocation.withDefaultNamespace("textures/entity/wolf/wolf.png"));
			builder.put(SmallCubeSkullBlock.Types.ANGRY_WOLF, ResourceLocation.withDefaultNamespace("textures/entity/wolf/wolf_angry.png"));
			builder.put(CowSkullBlock.Types.COW, ResourceLocation.withDefaultNamespace("textures/entity/cow/cow.png"));
			builder.put(CowSkullBlock.Types.RED_MOOSHROOM, ResourceLocation.withDefaultNamespace("textures/entity/cow/red_mooshroom.png"));
			builder.put(CowSkullBlock.Types.BROWN_MOOSHROOM, ResourceLocation.withDefaultNamespace("textures/entity/cow/brown_mooshroom.png"));
			builder.put(PiglinSkullBlock.Types.PIGLIN_BRUTE, ResourceLocation.withDefaultNamespace("textures/entity/piglin/piglin_brute.png"));
			builder.put(PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN, ResourceLocation.withDefaultNamespace("textures/entity/piglin/zombified_piglin.png"));
			builder.put(WardenSkullBlock.Types.WARDEN, ResourceLocation.withDefaultNamespace("textures/entity/warden/warden.png"));
			builder.put(HorseSkullBlock.Types.BLACK_HORSE, ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_black.png"));
			builder.put(HorseSkullBlock.Types.BROWN_HORSE, ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_brown.png"));
			builder.put(HorseSkullBlock.Types.CHESTNUT_HORSE, ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_chestnut.png"));
			builder.put(HorseSkullBlock.Types.CREAMY_HORSE, ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_creamy.png"));
			builder.put(HorseSkullBlock.Types.DARKBROWN_HORSE, ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_darkbrown.png"));
			builder.put(HorseSkullBlock.Types.GRAY_HORSE, ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_gray.png"));
			builder.put(HorseSkullBlock.Types.WHITE_HORSE, ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_white.png"));
			builder.put(HorseSkullBlock.Types.DONKEY, ResourceLocation.withDefaultNamespace("textures/entity/horse/donkey.png"));
			builder.put(HorseSkullBlock.Types.MULE, ResourceLocation.withDefaultNamespace("textures/entity/horse/mule.png"));
			builder.put(HorseSkullBlock.Types.SKELETON_HORSE, ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_skeleton.png"));
			builder.put(HorseSkullBlock.Types.ZOMBIE_HORSE, ResourceLocation.withDefaultNamespace("textures/entity/horse/horse_zombie.png"));
			SkullBlockRenderer.SKIN_BY_TYPE.putAll(builder.build());
		});
	}

	@SubscribeEvent
	public static void registerMenus(RegisterMenuScreensEvent event) {
		event.register(SCContainerTypes.SKULL_CHARGER_MENU.get(), SkullChargerScreen::new);
	}
}
