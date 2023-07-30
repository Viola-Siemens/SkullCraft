package com.hexagram2021.skullcraft.common.register;

import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@SuppressWarnings("unused")
public class SCItems {
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public static final Supplier<Item.Properties> DEFAULT_ITEM_PROPERTIES = Item.Properties::new;
	public static final Supplier<Item.Properties> UNCOMMON_ITEM_PROPERTIES = () -> new Item.Properties().rarity(Rarity.UNCOMMON);
	public static final Supplier<Item.Properties> RARE_ITEM_PROPERTIES = () -> new Item.Properties().rarity(Rarity.RARE);

	public static final ItemEntry<BlockItem> SKULL_CHARGER = new ItemEntry<>(
			"skull_charger", DEFAULT_ITEM_PROPERTIES, (props) -> new BlockItem(SCBlocks.SKULL_CHARGER.get(), props) {
				@Override
				public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
					components.add(Component.translatable("desc.skullcraft.skull_charger").withStyle(ChatFormatting.GRAY));
				}
			}
	);

	public static final Tier KOPIS_TIER = new Tier() {
		@Override
		public int getUses() { return 1145; }

		@Override
		public float getSpeed() { return 0.0F; }

		@Override
		public float getAttackDamageBonus() { return 6.0F; }

		@Override
		public int getLevel() { return 2; }

		@Override
		public int getEnchantmentValue() { return 10; }

		@Override
		public Ingredient getRepairIngredient() { return Ingredient.of(Items.OBSIDIAN); }
	};

	public static final ItemEntry<SwordItem> KOPIS = new ItemEntry<>(
			"kopis", DEFAULT_ITEM_PROPERTIES, (props) -> new SwordItem(KOPIS_TIER, 3, -3.6F, props) {
		@Override
		public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
			components.add(Component.translatable("desc.skullcraft.kopis").withStyle(ChatFormatting.GRAY));
		}
	}
	);
	public static final ItemEntry<Item> KOPIS_BLADE = new ItemEntry<>(
			"kopis_blade", DEFAULT_ITEM_PROPERTIES, Item::new
	);
	public static final ItemEntry<Item> KOPIS_HILT = new ItemEntry<>(
			"kopis_hilt", DEFAULT_ITEM_PROPERTIES, Item::new
	);

	public static class HumanSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> VILLAGER_HEAD = new ItemEntry<>(
				"villager_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.VILLAGER_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ILLAGER_HEAD = new ItemEntry<>(
				"illager_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.ILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ILLAGER_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WITCH_HEAD = new ItemEntry<>(
				"witch_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.WITCH_HEAD.get(), SCBlocks.HumanSkulls.WITCH_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> IRON_GOLEM_HEAD = new ItemEntry<>(
				"iron_golem_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.IRON_GOLEM_HEAD.get(), SCBlocks.HumanSkulls.IRON_GOLEM_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ZOMBIE_VILLAGER_HEAD = new ItemEntry<>(
				"zombie_villager_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_WALL_HEAD.get(), props, Direction.DOWN
				)
		);

		private HumanSkulls() {}

		private static void init() {
		}
	}

	public static class CubeSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> SLIME_HEAD = new ItemEntry<>(
				"slime_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.SLIME_HEAD.get(), SCBlocks.CubeSkulls.SLIME_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> LAVASLIME_HEAD = new ItemEntry<>(
				"lavaslime_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.LAVASLIME_HEAD.get(), SCBlocks.CubeSkulls.LAVASLIME_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> BLAZE_HEAD = new ItemEntry<>(
				"blaze_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.BLAZE_HEAD.get(), SCBlocks.CubeSkulls.BLAZE_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> SPIDER_HEAD = new ItemEntry<>(
				"spider_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.SPIDER_HEAD.get(), SCBlocks.CubeSkulls.SPIDER_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> CAVE_SPIDER_HEAD = new ItemEntry<>(
				"cave_spider_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.CAVE_SPIDER_HEAD.get(), SCBlocks.CubeSkulls.CAVE_SPIDER_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> PIG_HEAD = new ItemEntry<>(
				"pig_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.PIG_HEAD.get(), SCBlocks.CubeSkulls.PIG_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ENDERMAN_HEAD = new ItemEntry<>(
				"enderman_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ENDERMAN_HEAD.get(), SCBlocks.CubeSkulls.ENDERMAN_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> SNOW_GOLEM_HEAD = new ItemEntry<>(
				"snow_golem_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.SNOW_GOLEM_HEAD.get(), SCBlocks.CubeSkulls.SNOW_GOLEM_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> TECHNOBLADE_HEAD = new ItemEntry<>(
				"technoblade_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.TECHNOBLADE_HEAD.get(), SCBlocks.CubeSkulls.TECHNOBLADE_WALL_HEAD.get(), props, Direction.DOWN
				) {
					@Override
					public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
						components.add(Component.translatable("desc.skullcraft.technoblade_head").withStyle(ChatFormatting.GRAY));
					}
				}
		);

		private CubeSkulls() {}

		private static void init() {
		}
	}

	public static class SmallCubeSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> SHEEP_HEAD = new ItemEntry<>(
				"sheep_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.SHEEP_HEAD.get(), SCBlocks.SmallCubeSkulls.SHEEP_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> BAT_HEAD = new ItemEntry<>(
				"bat_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.BAT_HEAD.get(), SCBlocks.SmallCubeSkulls.BAT_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> SHULKER_HEAD = new ItemEntry<>(
				"shulker_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.SHULKER_HEAD.get(), SCBlocks.SmallCubeSkulls.SHULKER_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ALLAY_HEAD = new ItemEntry<>(
				"allay_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.ALLAY_HEAD.get(), SCBlocks.SmallCubeSkulls.ALLAY_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> VEX_HEAD = new ItemEntry<>(
				"vex_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.SmallCubeSkulls.VEX_HEAD.get(), SCBlocks.SmallCubeSkulls.VEX_WALL_HEAD.get(), props, Direction.DOWN
				)
		);


		private SmallCubeSkulls() {}

		private static void init() {
		}
	}

	public static class CowSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> COW_HEAD = new ItemEntry<>(
				"cow_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CowSkulls.COW_HEAD.get(), SCBlocks.CowSkulls.COW_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> RED_MOOSHROOM_HEAD = new ItemEntry<>(
				"red_mooshroom_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CowSkulls.RED_MOOSHROOM_HEAD.get(), SCBlocks.CowSkulls.RED_MOOSHROOM_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> BROWN_MOOSHROOM_HEAD = new ItemEntry<>(
				"brown_mooshroom_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CowSkulls.BROWN_MOOSHROOM_HEAD.get(), SCBlocks.CowSkulls.BROWN_MOOSHROOM_WALL_HEAD.get(), props, Direction.DOWN
				)
		);


		private CowSkulls() {}

		private static void init() {
		}
	}

	public static class PiglinSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> PIGLIN_BRUTE_HEAD = new ItemEntry<>(
				"piglin_brute_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.PiglinSkulls.PIGLIN_BRUTE_HEAD.get(), SCBlocks.PiglinSkulls.PIGLIN_BRUTE_WALL_HEAD.get(), props, Direction.DOWN
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ZOMBIFIED_PIGLIN_HEAD = new ItemEntry<>(
				"zombified_piglin_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.PiglinSkulls.ZOMBIFIED_PIGLIN_HEAD.get(), SCBlocks.PiglinSkulls.ZOMBIFIED_PIGLIN_WALL_HEAD.get(), props, Direction.DOWN
				)
		);

		private PiglinSkulls() {}

		private static void init() {
		}
	}

	public static class WardenSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> WARDEN_HEAD = new ItemEntry<>(
				"warden_head", RARE_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.WardenSkulls.WARDEN_HEAD.get(), SCBlocks.WardenSkulls.WARDEN_WALL_HEAD.get(), props, Direction.DOWN
				) {
					@Override
					public void onArmorTick(ItemStack stack, Level level, Player player) {
						if(!player.hasEffect(MobEffects.DARKNESS) && level.random.nextInt(4) == 0) {
							player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 0, false, false, true));
						}
						player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 3, false, false, true));
					}
				}
		);

		private WardenSkulls() {}

		private static void init() {
		}
	}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
		HumanSkulls.init();
		CubeSkulls.init();
		SmallCubeSkulls.init();
		CowSkulls.init();
		PiglinSkulls.init();
		WardenSkulls.init();
	}

	public static final class ItemEntry<T extends Item> implements Supplier<T>, ItemLike {
		public static final List<ItemEntry<? extends Item>> REGISTERED_ITEMS = Lists.newArrayList();

		private final RegistryObject<T> item;
		private final Supplier<Item.Properties> properties;

		public ItemEntry(String name, Supplier<Item.Properties> properties, Function<Item.Properties, T> make) {
			this.properties = properties;
			this.item = REGISTER.register(name, () -> make.apply(properties.get()));

			REGISTERED_ITEMS.add(this);
		}

		@Override
		public T get() {
			return this.item.get();
		}

		public ResourceLocation getId() {
			return this.item.getId();
		}

		public Item.Properties getProperties() {
			return this.properties.get();
		}

		@Override
		public Item asItem() {
			return this.item.get();
		}
	}
}
