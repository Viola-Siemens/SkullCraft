package com.hexagram2021.skullcraft.common.block.entity;

import com.hexagram2021.skullcraft.common.SCSounds;
import com.hexagram2021.skullcraft.common.block.SkullChargerBlock;
import com.hexagram2021.skullcraft.common.components.SkullScale;
import com.hexagram2021.skullcraft.common.crafting.SkullChargerMenu;
import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import com.hexagram2021.skullcraft.common.register.SCDataComponents;
import com.hexagram2021.skullcraft.common.register.SCEnchantmentTags;
import com.hexagram2021.skullcraft.common.register.SCItems;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 头颅充能器方块实体，负责处理头颅的缩放调整和附魔操作喵~
 * 包含三个槽位：输入槽（头颅）、燃料槽（灵魂土）和附魔珠槽喵~
 *
 * @author liudongyu
 */
@SuppressWarnings("unused")
public class SkullChargerBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, StackedContentsCompatible {
	protected static final int SLOT_INPUT = 0;
	protected static final int SLOT_FUEL = 1;
	protected static final int SLOT_ENCHANTING_BEAD = 2;
	/** 能量数据索引喵~ */
	public static final int DATA_ENERGY = 0;
	/** X 轴增量数据索引喵~ */
	public static final int DATA_ADDX = 1;
	/** Y 轴增量数据索引喵~ */
	public static final int DATA_ADDY = 2;
	/** Z 轴增量数据索引喵~ */
	public static final int DATA_ADDZ = 3;
	/** 每次添加的能量值喵~ */
	public static final int ENERGY_ADD = 2;
	/** 最大能量等级喵~ */
	public static final int MAX_ENERGY_LEVEL = 100;

	/** 两次音效之间的最小间隔（tick）喵~ */
	public static final long MIN_SOUND_GAP = 40;

	private static final int[] SLOTS_FOR_UP = new int[]{SLOT_INPUT};
	private static final int[] SLOTS_FOR_SIDES = new int[]{SLOT_ENCHANTING_BEAD, SLOT_FUEL, SLOT_INPUT};
	private static final int[] SLOTS_FOR_DOWN = new int[]{SLOT_INPUT};

	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

	int energy;

	int addX;
	int addY;
	int addZ;

	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int index) {
			return switch(index) {
				case DATA_ENERGY -> SkullChargerBlockEntity.this.energy;
				case DATA_ADDX -> SkullChargerBlockEntity.this.addX;
				case DATA_ADDY -> SkullChargerBlockEntity.this.addY;
				case DATA_ADDZ -> SkullChargerBlockEntity.this.addZ;
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case DATA_ENERGY -> SkullChargerBlockEntity.this.energy = value;
				case DATA_ADDX -> SkullChargerBlockEntity.this.addX = value;
				case DATA_ADDY -> SkullChargerBlockEntity.this.addY = value;
				case DATA_ADDZ -> SkullChargerBlockEntity.this.addZ = value;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	@Override
	protected Component getDefaultName() {
		return SkullChargerBlock.CONTAINER_TITLE;
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 创建头颅充能器方块实体
	 * @param pos 方块位置
	 * @param state 方块状态
	 */
	public SkullChargerBlockEntity(BlockPos pos, BlockState state) {
		super(SCBlockEntities.SKULL_CHARGER.get(), pos, state);
	}

	public int getEnergy() {
		return this.energy;
	}

	public void setEnergy(int newEnergy) {
		this.energy = newEnergy;
	}

	@Override
	public void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
		super.loadAdditional(nbt, provider);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(nbt, this.items, provider);
		this.energy = nbt.getInt("Energy");
	}

	@Override
	public void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
		super.saveAdditional(nbt, provider);
		nbt.putInt("Energy", this.energy);
		ContainerHelper.saveAllItems(nbt, this.items, provider);
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		if(direction == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		}
		if(direction == Direction.UP) {
			return SLOTS_FOR_UP;
		}
		return SLOTS_FOR_SIDES;
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	public void fillStackedContents(StackedItemContents contents) {
		for(ItemStack itemstack : this.items) {
			contents.accountStack(itemstack);
		}
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		}
		return player.distanceToSqr(this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 0.5D, this.worldPosition.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public ItemStack getItem(int index) {
		return this.items.get(index);
	}

	@Override
	public ItemStack removeItem(int index, int count) {
		return ContainerHelper.removeItem(this.items, index, count);
	}

	@Override
	public ItemStack removeItemNoUpdate(int index) {
		return ContainerHelper.takeItem(this.items, index);
	}

	@Override
	public void setItem(int index, ItemStack itemStack) {
		this.items.set(index, itemStack);
		if (itemStack.getCount() > this.getMaxStackSize()) {
			itemStack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack itemStack) {
		return switch (index) {
			case SLOT_INPUT -> (itemStack.getItem() instanceof BlockItem blockItem) && blockItem.getBlock() instanceof AbstractSkullBlock;
			case SLOT_FUEL -> itemStack.is(Items.SOUL_SOIL);
			case SLOT_ENCHANTING_BEAD -> itemStack.is(SCItems.SKULL_ENCHANTING_BEAD.get());
			default -> false;
		};
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
		return this.canPlaceItem(index, itemStack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack itemStack, Direction direction) {
		return true;
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new SkullChargerMenu(id, inventory, this, this.dataAccess);
	}

	private long lastSoundTime = 0;

	/**
	 * 服务端 tick 方法，处理燃料消耗、能量充能和头颅缩放喵~
	 *
	 * @param level       所在世界喵~
	 * @param blockPos    方块位置喵~
	 * @param blockState  方块状态喵~
	 * @param blockEntity 方块实体喵~
	 */
	public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, SkullChargerBlockEntity blockEntity) {
		ItemStack ingredient = blockEntity.items.get(SLOT_INPUT);
		ItemStack fuel = blockEntity.items.get(SLOT_FUEL);

		if(!fuel.isEmpty() && fuel.is(Items.SOUL_SOIL) && blockEntity.energy + ENERGY_ADD <= MAX_ENERGY_LEVEL) {
			fuel.shrink(1);
			blockEntity.energy += ENERGY_ADD;
		}

		if(!ingredient.isEmpty()) {
			if((ingredient.getItem() instanceof BlockItem blockItem) && blockItem.getBlock() instanceof AbstractSkullBlock) {
				SkullScale skullScale = ingredient.get(SCDataComponents.SKULL_SCALE.get());
				if(skullScale == null) {
					skullScale = SkullScale.DEFAULT;
					ingredient.set(SCDataComponents.SKULL_SCALE.get(), skullScale);
				}

				if(blockEntity.addX != 0 || blockEntity.addY != 0 || blockEntity.addZ != 0) {
					int energyCost = Mth.abs(blockEntity.addX / 5) + Mth.abs(blockEntity.addY / 5) + Mth.abs(blockEntity.addZ / 5);

					if(energyCost > 0 && blockEntity.energy >= energyCost) {
						SkullScale newSkullScale = skullScale.add(blockEntity.addX, blockEntity.addY, blockEntity.addZ);
						ingredient.set(SCDataComponents.SKULL_SCALE.get(), newSkullScale);

						blockEntity.energy -= energyCost;

						long time = level.getGameTime();
						if(time - blockEntity.lastSoundTime >= MIN_SOUND_GAP) {
							level.playSound(null, blockPos, SCSounds.SKULL_CHARGER, SoundSource.BLOCKS, 1.0F, 1.0F);
							blockEntity.lastSoundTime = time;
						}
					}
					blockEntity.addX = 0;
					blockEntity.addY = 0;
					blockEntity.addZ = 0;
				}
			}
		}
	}

	/**
	 * 执行附魔操作，消耗附魔珠并从可用附魔列表中随机选取一个附魔应用到输入的头颅上喵~
	 *
	 * @param level          所在世界喵~
	 * @param registryAccess 注册表访问接口喵~
	 * @param randomSource   随机源喵~
	 */
	public void performEnchant(Level level, RegistryAccess registryAccess, RandomSource randomSource) {
		ItemStack ingredient = this.items.get(SLOT_INPUT);
		ItemStack bead = this.items.get(SLOT_ENCHANTING_BEAD);
		if(bead.isEmpty()) {
			return;
		}
		Stream<Holder<Enchantment>> holderSet = StreamSupport.stream(
				registryAccess.lookupOrThrow(Registries.ENCHANTMENT)
						.getTagOrEmpty(SCEnchantmentTags.IN_SKULL_CHARGER)
						.spliterator(),
				false
		);
		List<Holder<Enchantment>> availableEnchantments = holderSet.filter(enchantmentHolder -> enchantmentHolder.value().getMaxLevel() > ingredient.getEnchantmentLevel(enchantmentHolder)).toList();
		if(availableEnchantments.isEmpty()) {
			return;
		}
		bead.shrink(1);
		Holder<Enchantment> enchantment = availableEnchantments.get(randomSource.nextInt(availableEnchantments.size()));
		ingredient.enchant(enchantment, ingredient.getEnchantmentLevel(enchantment) + 1);
		long time = level.getGameTime();
		if(time - this.lastSoundTime >= MIN_SOUND_GAP) {
			level.playSound(null, this.getBlockPos(), SCSounds.SKULL_CHARGER, SoundSource.BLOCKS, 1.0F, 1.0F);
			this.lastSoundTime = time;
		}
	}
}
