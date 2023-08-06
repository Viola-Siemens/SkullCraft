package com.hexagram2021.skullcraft.client;

import com.google.common.collect.ImmutableMap;
import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.client.model.*;
import com.hexagram2021.skullcraft.client.screen.SkullChargerScreen;
import com.hexagram2021.skullcraft.common.block.CowSkull.CowSkullBlock;
import com.hexagram2021.skullcraft.common.block.CubeSkull.CubeSkullBlock;
import com.hexagram2021.skullcraft.common.block.HorseSkull.HorseSkullBlock;
import com.hexagram2021.skullcraft.common.block.HumanSkull.HumanSkullBlock;
import com.hexagram2021.skullcraft.common.block.PiglinSkull.PiglinSkullBlock;
import com.hexagram2021.skullcraft.common.block.SmallCubeSkull.SmallCubeSkullBlock;
import com.hexagram2021.skullcraft.common.block.WardenSkull.WardenSkullBlock;
import com.hexagram2021.skullcraft.common.register.SCContainerTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
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
							Component.translatable("tooltip.skullcraft.nox").withStyle(ChatFormatting.GRAY) :
							scaleX > 100 ?
									Component.translatable("tooltip.skullcraft.x", scaleX - 100).withStyle(ChatFormatting.GOLD) :
									Component.translatable("tooltip.skullcraft.negx", 100 - scaleX).withStyle(ChatFormatting.RED);
					MutableComponent componentY = scaleY == 100 ?
							Component.translatable("tooltip.skullcraft.noy").withStyle(ChatFormatting.GRAY) :
							scaleY > 100 ?
									Component.translatable("tooltip.skullcraft.y", scaleY - 100).withStyle(ChatFormatting.GOLD) :
									Component.translatable("tooltip.skullcraft.negy", 100 - scaleY).withStyle(ChatFormatting.RED);
					MutableComponent componentZ = scaleZ == 100 ?
							Component.translatable("tooltip.skullcraft.noz").withStyle(ChatFormatting.GRAY) :
							scaleZ > 100 ?
									Component.translatable("tooltip.skullcraft.z", scaleZ - 100).withStyle(ChatFormatting.GOLD) :
									Component.translatable("tooltip.skullcraft.negz", 100 - scaleZ).withStyle(ChatFormatting.RED);
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

	public static void modConstruction() {

	}

	@SubscribeEvent
	public static void setup(final FMLClientSetupEvent event) {
		registerContainersAndScreens();
		event.enqueueWork(() -> {
			ImmutableMap.Builder<SkullBlock.Type, ResourceLocation> builder = ImmutableMap.builder();
			builder.put(HumanSkullBlock.Types.VILLAGER, new ResourceLocation("textures/entity/villager/villager.png"));
			builder.put(HumanSkullBlock.Types.ILLAGER, new ResourceLocation("textures/entity/illager/pillager.png"));
			builder.put(HumanSkullBlock.Types.WITCH, new ResourceLocation("textures/entity/witch.png"));
			builder.put(HumanSkullBlock.Types.IRON_GOLEM, new ResourceLocation("textures/entity/iron_golem/iron_golem.png"));
			builder.put(HumanSkullBlock.Types.ZOMBIE_VILLAGER, new ResourceLocation("textures/entity/zombie_villager/zombie_villager.png"));
			builder.put(CubeSkullBlock.Types.SLIME, new ResourceLocation("textures/entity/slime/slime.png"));
			builder.put(CubeSkullBlock.Types.LAVASLIME, new ResourceLocation("textures/entity/slime/magmacube.png"));
			builder.put(CubeSkullBlock.Types.BLAZE, new ResourceLocation("textures/entity/blaze.png"));
			builder.put(CubeSkullBlock.Types.SPIDER, new ResourceLocation("textures/entity/spider/spider.png"));
			builder.put(CubeSkullBlock.Types.CAVE_SPIDER, new ResourceLocation("textures/entity/spider/cave_spider.png"));
			builder.put(CubeSkullBlock.Types.PIG, new ResourceLocation("textures/entity/pig/pig.png"));
			builder.put(CubeSkullBlock.Types.ENDERMAN, new ResourceLocation("textures/entity/enderman/enderman.png"));
			builder.put(CubeSkullBlock.Types.SNOW_GOLEM, new ResourceLocation("textures/entity/snow_golem.png"));
			builder.put(CubeSkullBlock.Types.TECHNOBLADE, new ResourceLocation(MODID, "textures/entity/technoblade.png"));
			builder.put(SmallCubeSkullBlock.Types.SHEEP, new ResourceLocation(MODID, "textures/entity/sheep.png"));
			builder.put(SmallCubeSkullBlock.Types.BAT, new ResourceLocation("textures/entity/bat.png"));
			builder.put(SmallCubeSkullBlock.Types.SHULKER, new ResourceLocation("textures/entity/shulker/shulker.png"));
			builder.put(SmallCubeSkullBlock.Types.ALLAY, new ResourceLocation("textures/entity/allay/allay.png"));
			builder.put(SmallCubeSkullBlock.Types.VEX, new ResourceLocation("textures/entity/illager/vex.png"));
			builder.put(CowSkullBlock.Types.COW, new ResourceLocation("textures/entity/cow/cow.png"));
			builder.put(CowSkullBlock.Types.RED_MOOSHROOM, new ResourceLocation("textures/entity/cow/red_mooshroom.png"));
			builder.put(CowSkullBlock.Types.BROWN_MOOSHROOM, new ResourceLocation("textures/entity/cow/brown_mooshroom.png"));
			builder.put(PiglinSkullBlock.Types.PIGLIN_BRUTE, new ResourceLocation("textures/entity/piglin/piglin_brute.png"));
			builder.put(PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN, new ResourceLocation("textures/entity/piglin/zombified_piglin.png"));
			builder.put(WardenSkullBlock.Types.WARDEN, new ResourceLocation("textures/entity/warden/warden.png"));
			builder.put(HorseSkullBlock.Types.BLACK_HORSE, new ResourceLocation("textures/entity/horse/horse_black.png"));
			builder.put(HorseSkullBlock.Types.BROWN_HORSE, new ResourceLocation("textures/entity/horse/horse_brown.png"));
			builder.put(HorseSkullBlock.Types.CHESTNUT_HORSE, new ResourceLocation("textures/entity/horse/horse_chestnut.png"));
			builder.put(HorseSkullBlock.Types.CREAMY_HORSE, new ResourceLocation("textures/entity/horse/horse_creamy.png"));
			builder.put(HorseSkullBlock.Types.DARKBROWN_HORSE, new ResourceLocation("textures/entity/horse/horse_darkbrown.png"));
			builder.put(HorseSkullBlock.Types.GRAY_HORSE, new ResourceLocation("textures/entity/horse/horse_gray.png"));
			builder.put(HorseSkullBlock.Types.WHITE_HORSE, new ResourceLocation("textures/entity/horse/horse_white.png"));
			builder.put(HorseSkullBlock.Types.DONKEY, new ResourceLocation("textures/entity/horse/donkey.png"));
			builder.put(HorseSkullBlock.Types.MULE, new ResourceLocation("textures/entity/horse/mule.png"));
			builder.put(HorseSkullBlock.Types.SKELETON_HORSE, new ResourceLocation("textures/entity/horse/horse_skeleton.png"));
			builder.put(HorseSkullBlock.Types.ZOMBIE_HORSE, new ResourceLocation("textures/entity/horse/horse_zombie.png"));
			SkullBlockRenderer.SKIN_BY_TYPE.putAll(builder.build());
		});
	}

	private static void registerContainersAndScreens() {
		MenuScreens.register(SCContainerTypes.SKULL_CHARGER_MENU.get(), SkullChargerScreen::new);
	}
}
