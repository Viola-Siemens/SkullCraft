package com.hexagram2021.skullcraft.common.world;

import com.google.common.collect.ImmutableSet;
import com.hexagram2021.skullcraft.common.SCSounds;
import com.hexagram2021.skullcraft.common.register.SCBlocks;
import com.hexagram2021.skullcraft.common.register.SCItems;
import com.hexagram2021.skullcraft.mixin.HeroGiftsTaskAccess;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class Villages {
	public static final ResourceLocation ONMYOUJI = new ResourceLocation(MODID, "onmyouji");

	public static void init() {
		HeroGiftsTaskAccess.getGifts().put(Registers.PROF_ONMYOUJI.get(), new ResourceLocation(MODID, "gameplay/hero_of_the_village/onmyouji_gift"));
	}

	public static class Registers {
		public static final DeferredRegister<PoiType> POINTS_OF_INTEREST = DeferredRegister.create(ForgeRegistries.POI_TYPES, MODID);
		public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MODID);

		public static final RegistryObject<PoiType> POI_SKULL_CHARGER = POINTS_OF_INTEREST.register(
				"skull_charger", () -> createPOI(assembleStates(SCBlocks.SKULL_CHARGER.get()))
		);

		public static final RegistryObject<VillagerProfession> PROF_ONMYOUJI = PROFESSIONS.register(
				"onmyouji", () -> createProf(ONMYOUJI, POI_SKULL_CHARGER::getKey, SCSounds.SKULL_CHARGER)
		);

		private static Collection<BlockState> assembleStates(Block block) {
			return block.getStateDefinition().getPossibleStates();
		}

		private static PoiType createPOI(Collection<BlockState> block) {
			return new PoiType(ImmutableSet.copyOf(block), 1, 1);
		}

		@SuppressWarnings("SameParameterValue")
		private static VillagerProfession createProf(ResourceLocation name, Supplier<ResourceKey<PoiType>> poi, SoundEvent sound) {
			ResourceKey<PoiType> poiName = poi.get();
			return new VillagerProfession(
					name.toString(),
					(p) -> p.is(poiName), (p) -> p.is(poiName),
					ImmutableSet.of(), ImmutableSet.of(),
					sound
			);
		}

		public static void init(IEventBus bus) {
			POINTS_OF_INTEREST.register(bus);
			PROFESSIONS.register(bus);
		}
	}

	@SuppressWarnings("SameParameterValue")
	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
	public static class Events {
		@SubscribeEvent
		public static void registerTrades(VillagerTradesEvent event) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

			ResourceLocation currentVillagerProfession = ForgeRegistries.VILLAGER_PROFESSIONS.getKey(event.getType());
			if(ONMYOUJI.equals(currentVillagerProfession)) {
				trades.get(1).add(buy(SCItems.SmallCubeSkulls.SHEEP_HEAD, 12, 2));
				trades.get(1).add(buy(SCItems.CowSkulls.COW_HEAD, 12, 2));
				trades.get(1).add(buy(SCItems.CubeSkulls.PIG_HEAD, 12, 2));
				trades.get(2).add(sell(Items.PLAYER_HEAD, 24, 5));
				trades.get(2).add(random2Buy(SCItems.HumanSkulls.ILLAGER_HEAD, SCItems.HumanSkulls.WITCH_HEAD, 5, 10));
				trades.get(3).add(commonSell(Items.SOUL_SOIL, 2, 8, 10));
				trades.get(3).add(buy(SCItems.CubeSkulls.SLIME_HEAD, 5, 20));
				trades.get(3).add(buy(SCItems.CubeSkulls.LAVASLIME_HEAD, 7, 20));
				trades.get(3).add(random3Buy(Items.ZOMBIE_HEAD, Items.SKELETON_SKULL, Items.CREEPER_HEAD, 10, 20));
				trades.get(4).add(random3Buy(Items.PIGLIN_HEAD, SCItems.PiglinSkulls.ZOMBIFIED_PIGLIN_HEAD, SCItems.CubeSkulls.ENDERMAN_HEAD, 9, 30));
				trades.get(4).add(random2Buy(SCItems.CubeSkulls.BLAZE_HEAD, SCItems.SmallCubeSkulls.SHULKER_HEAD, 5, 30));
				trades.get(4).add(random2Buy(SCItems.CubeSkulls.SPIDER_HEAD, SCItems.SmallCubeSkulls.BAT_HEAD, 6, 30));
				trades.get(5).add(buy(SCItems.CubeSkulls.SPIDER_HEAD, 12, 30));
				trades.get(5).add(buy(SCItems.WardenSkulls.WARDEN_HEAD, 1, 30));
				trades.get(5).add(commonSell(Items.OBSIDIAN, 5, 1, 30));
			}
		}

		private static VillagerTrades.ItemListing buy(ItemLike item, int count, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemStack(item, count), new ItemStack(Items.EMERALD), 3, xp, 0.05F);
		}
		private static VillagerTrades.ItemListing random2Buy(ItemLike item1, ItemLike item2, int count, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemStack(random.nextBoolean() ? item1 : item2, count), new ItemStack(Items.EMERALD), 3, xp, 0.05F);
		}
		private static VillagerTrades.ItemListing random3Buy(ItemLike item1, ItemLike item2, ItemLike item3, int count, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemStack(switch (random.nextInt(3)) {
				case 0 -> item1;
				case 1 -> item2;
				default -> item3;
			}, count), new ItemStack(Items.EMERALD), 3, xp, 0.05F);
		}
		private static VillagerTrades.ItemListing sell(ItemLike item, int price, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, price), new ItemStack(item), 3, xp, 0.05F);
		}
		private static VillagerTrades.ItemListing commonSell(ItemLike item, int price, int count, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, price), new ItemStack(item, count), 16, xp, 0.05F);
		}
	}
}
