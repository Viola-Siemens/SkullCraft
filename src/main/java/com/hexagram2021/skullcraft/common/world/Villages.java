package com.hexagram2021.skullcraft.common.world;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.hexagram2021.skullcraft.common.SCSounds;
import com.hexagram2021.skullcraft.common.register.SCBlocks;
import com.hexagram2021.skullcraft.common.register.SCItems;
import com.hexagram2021.skullcraft.common.util.SCLogger;
import com.hexagram2021.skullcraft.mixin.StructureTemplatePoolAccess;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public final class Villages {
	public static final ResourceLocation ONMYOUJI = ResourceLocation.fromNamespaceAndPath(MODID, "onmyouji");

	private Villages() {
	}

	public static void init() {
	}

	public static void addAllStructuresToPool(HolderLookup.Provider provider) {
		SCLogger.info("Adding skullcraft structures to template pools.");
		addToPool(
				ResourceLocation.withDefaultNamespace("village/plains/houses"),
				provider,
				builder -> builder.add(ResourceLocation.fromNamespaceAndPath(MODID, "village/plains/houses/plains_onmyouji_1"), 3)
		);
		addToPool(
				ResourceLocation.withDefaultNamespace("village/desert/houses"),
				provider,
				builder -> builder.add(ResourceLocation.fromNamespaceAndPath(MODID, "village/desert/houses/desert_onmyouji_1"), 3)
		);
	}
	private static void addToPool(ResourceLocation poolName, HolderLookup.Provider provider, Consumer<PoolBuilder> consumer) {
		HolderLookup.RegistryLookup<StructureTemplatePool> registry = provider.lookupOrThrow(Registries.TEMPLATE_POOL);
		Holder.Reference<StructureTemplatePool> structureTemplatePool = registry.get(ResourceKey.create(Registries.TEMPLATE_POOL, poolName)).orElse(null);
		if(structureTemplatePool == null) {
			SCLogger.error("Ignored empty structure template pool: " + poolName);
			return;
		}
		StructureTemplatePoolAccess pool = (StructureTemplatePoolAccess)structureTemplatePool.value();
		List<Pair<StructurePoolElement, Integer>> rawTemplates = pool.skullcraft$getRawTemplates() instanceof ArrayList ?
				pool.skullcraft$getRawTemplates() : Lists.newArrayList(pool.skullcraft$getRawTemplates());

		PoolBuilder poolBuilder = new PoolBuilder(pool, rawTemplates);
		consumer.accept(poolBuilder);

		pool.skullcraft$setRawTemplates(rawTemplates);
	}
	private static final class PoolBuilder {
		StructureTemplatePoolAccess pool;
		List<Pair<StructurePoolElement, Integer>> rawTemplates;

		public PoolBuilder(StructureTemplatePoolAccess pool, List<Pair<StructurePoolElement, Integer>> rawTemplates) {
			this.pool = pool;
			this.rawTemplates = rawTemplates;
		}

		public void add(ResourceLocation toAdd, int weight) {
			SinglePoolElement addedElement = StructurePoolElement.single(toAdd.toString()).apply(StructureTemplatePool.Projection.RIGID);
			this.rawTemplates.add(Pair.of(addedElement, weight));
			this.pool.skullcraft$getTemplates().add(addedElement);
		}
	}

	public static class Registers {
		public static final DeferredRegister<PoiType> POINTS_OF_INTEREST = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, MODID);
		public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, MODID);

		public static final DeferredHolder<PoiType, PoiType> POI_SKULL_CHARGER = POINTS_OF_INTEREST.register(
				"skull_charger", () -> createPOI(assembleStates(SCBlocks.SKULL_CHARGER.get()))
		);

		public static final DeferredHolder<VillagerProfession, VillagerProfession> PROF_ONMYOUJI = PROFESSIONS.register(
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
	@EventBusSubscriber(modid = MODID)
	public static final class Events {
		@SubscribeEvent
		public static void registerTrades(VillagerTradesEvent event) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

			String currentVillagerProfession = event.getType().name();
			if(ONMYOUJI.toString().equals(currentVillagerProfession)) {
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
				trades.get(4).add(random7Buy(
						SCItems.HorseSkulls.BLACK_HORSE_HEAD, SCItems.HorseSkulls.BROWN_HORSE_HEAD, SCItems.HorseSkulls.CHESTNUT_HORSE_HEAD, SCItems.HorseSkulls.CREAMY_HORSE_HEAD,
						SCItems.HorseSkulls.DARKBROWN_HORSE_HEAD, SCItems.HorseSkulls.GRAY_HORSE_HEAD, SCItems.HorseSkulls.WHITE_HORSE_HEAD, 4, 30
				));
				trades.get(5).add(buy(SCItems.CubeSkulls.CAVE_SPIDER_HEAD, 5, 30));
				trades.get(5).add(buy(SCItems.WardenSkulls.WARDEN_HEAD, 1, 30));
				trades.get(5).add(random3Buy(SCItems.SmallCubeSkulls.VEX_HEAD, SCItems.SmallCubeSkulls.SHULKER_HEAD, SCItems.HorseSkulls.DONKEY_HEAD, 6, 30));
				trades.get(5).add(commonSell(Items.OBSIDIAN, 5, 1, 30));
			}
		}

		private static VillagerTrades.ItemListing buy(ItemLike item, int count, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemCost(item, count), new ItemStack(Items.EMERALD), 3, xp, 0.05F);
		}
		private static VillagerTrades.ItemListing random2Buy(ItemLike item1, ItemLike item2, int count, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemCost(random.nextBoolean() ? item1 : item2, count), new ItemStack(Items.EMERALD), 3, xp, 0.05F);
		}
		private static VillagerTrades.ItemListing random3Buy(ItemLike item1, ItemLike item2, ItemLike item3, int count, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemCost(switch (random.nextInt(3)) {
				case 0 -> item1;
				case 1 -> item2;
				default -> item3;
			}, count), new ItemStack(Items.EMERALD), 3, xp, 0.05F);
		}
		private static VillagerTrades.ItemListing random7Buy(ItemLike item1, ItemLike item2, ItemLike item3, ItemLike item4,
															 ItemLike item5, ItemLike item6, ItemLike item7, int count, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemCost(switch (random.nextInt(7)) {
				case 0 -> item1;
				case 1 -> item2;
				case 2 -> item3;
				case 3 -> item4;
				case 4 -> item5;
				case 5 -> item6;
				default -> item7;
			}, count), new ItemStack(Items.EMERALD), 3, xp, 0.05F);
		}
		private static VillagerTrades.ItemListing sell(ItemLike item, int price, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, price), new ItemStack(item), 3, xp, 0.05F);
		}
		private static VillagerTrades.ItemListing commonSell(ItemLike item, int price, int count, int xp) {
			return (trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, price), new ItemStack(item, count), 16, xp, 0.05F);
		}

		private Events() {
		}
	}
}
