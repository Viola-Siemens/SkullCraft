package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.SkullCraft;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCItems {
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public static final Supplier<Item.Properties> DEFAULT_ITEM_PROPERTIES = () -> new Item.Properties().tab(SkullCraft.ITEM_GROUP);
	public static final Supplier<Item.Properties> UNCOMMON_ITEM_PROPERTIES = () -> new Item.Properties().tab(SkullCraft.ITEM_GROUP).rarity(Rarity.UNCOMMON);

	public static final ItemEntry<BlockItem> SKULL_CHARGER = new ItemEntry<>(
			"skull_charger", DEFAULT_ITEM_PROPERTIES, (props) -> new BlockItem(SCBlocks.SKULL_CHARGER.get(), props) {
				@Override
				public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag flag) {
					components.add(new TranslatableComponent("desc.skullcraft.skull_charger").withStyle(ChatFormatting.GRAY));
				}
			}
	);

	public static class HumanSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> VILLAGER_HEAD = new ItemEntry<>(
				"villager_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.VILLAGER_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ILLAGER_HEAD = new ItemEntry<>(
				"illager_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.ILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ILLAGER_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> WITCH_HEAD = new ItemEntry<>(
				"witch_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.WITCH_HEAD.get(), SCBlocks.HumanSkulls.WITCH_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> IRON_GOLEM_HEAD = new ItemEntry<>(
				"iron_golem_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.IRON_GOLEM_HEAD.get(), SCBlocks.HumanSkulls.IRON_GOLEM_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ZOMBIE_VILLAGER_HEAD = new ItemEntry<>(
				"zombie_villager_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_HEAD.get(), SCBlocks.HumanSkulls.ZOMBIE_VILLAGER_WALL_HEAD.get(), props
				)
		);

		private HumanSkulls() {}

		private static void init() {
		}
	}

	public static class CubeSkulls {
		public static final ItemEntry<StandingAndWallBlockItem> SLIME_HEAD = new ItemEntry<>(
				"slime_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.SLIME_HEAD.get(), SCBlocks.CubeSkulls.SLIME_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> LAVASLIME_HEAD = new ItemEntry<>(
				"lavaslime_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.LAVASLIME_HEAD.get(), SCBlocks.CubeSkulls.LAVASLIME_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> BLAZE_HEAD = new ItemEntry<>(
				"blaze_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.BLAZE_HEAD.get(), SCBlocks.CubeSkulls.BLAZE_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> SPIDER_HEAD = new ItemEntry<>(
				"spider_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.SPIDER_HEAD.get(), SCBlocks.CubeSkulls.SPIDER_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> CAVE_SPIDER_HEAD = new ItemEntry<>(
				"cave_spider_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.CAVE_SPIDER_HEAD.get(), SCBlocks.CubeSkulls.CAVE_SPIDER_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> PIG_HEAD = new ItemEntry<>(
				"pig_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.PIG_HEAD.get(), SCBlocks.CubeSkulls.PIG_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> ENDERMAN_HEAD = new ItemEntry<>(
				"enderman_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.ENDERMAN_HEAD.get(), SCBlocks.CubeSkulls.ENDERMAN_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> SNOW_GOLEM_HEAD = new ItemEntry<>(
				"snow_golem_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.SNOW_GOLEM_HEAD.get(), SCBlocks.CubeSkulls.SNOW_GOLEM_WALL_HEAD.get(), props
				)
		);
		public static final ItemEntry<StandingAndWallBlockItem> TECHNOBLADE_HEAD = new ItemEntry<>(
				"technoblade_head", UNCOMMON_ITEM_PROPERTIES, (props) -> new StandingAndWallBlockItem(
						SCBlocks.CubeSkulls.TECHNOBLADE_HEAD.get(), SCBlocks.CubeSkulls.TECHNOBLADE_WALL_HEAD.get(), props
				) {
					@Override
					public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag flag) {
						components.add(new TranslatableComponent("desc.skullcraft.technoblade_head").withStyle(ChatFormatting.GRAY));
					}
				}
		);

		private CubeSkulls() {}

		private static void init() {
		}
	}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
		HumanSkulls.init();
		CubeSkulls.init();
	}

	public static final class ItemEntry<T extends Item> implements Supplier<T>, ItemLike {
		private final RegistryObject<T> item;
		private final Supplier<Item.Properties> properties;

		public ItemEntry(String name, Supplier<Item.Properties> properties, Function<Item.Properties, T> make) {
			this.properties = properties;
			this.item = REGISTER.register(name, () -> make.apply(properties.get()));
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

		@Nonnull
		@Override
		public Item asItem() {
			return this.item.get();
		}
	}
}
