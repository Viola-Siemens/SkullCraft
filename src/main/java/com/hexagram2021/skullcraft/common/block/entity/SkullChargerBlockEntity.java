package com.hexagram2021.skullcraft.common.block.entity;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.common.SCSounds;
import com.hexagram2021.skullcraft.common.block.SkullChargerBlock;
import com.hexagram2021.skullcraft.common.crafting.SkullChargerMenu;
import com.hexagram2021.skullcraft.common.register.SCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class SkullChargerBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, StackedContentsCompatible {
	protected static final int SLOT_INPUT = 0;
	protected static final int SLOT_FUEL = 1;
	public static final int DATA_ENERGY = 0;
	public static final int DATA_ADDX = 1;
	public static final int DATA_ADDY = 2;
	public static final int DATA_ADDZ = 3;
	public static final int ENERGY_ADD = 2;
	public static final int MAX_ENERGY_LEVEL = 100;

	public static final long MIN_SOUND_GAP = 40;

	private static final int[] SLOTS_FOR_UP = new int[]{SLOT_INPUT};
	private static final int[] SLOTS_FOR_SIDES = new int[]{SLOT_FUEL, SLOT_INPUT};
	private static final int[] SLOTS_FOR_DOWN = new int[]{SLOT_INPUT};

	protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

	int energy;

	int addX, addY, addZ;

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

	@Override @NotNull
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
	public void load(@NotNull CompoundTag nbt) {
		super.load(nbt);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(nbt, this.items);
		this.energy = nbt.getInt("Energy");
	}

	@Override
	public void saveAdditional(@NotNull CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("Energy", this.energy);
		ContainerHelper.saveAllItems(nbt, this.items);
	}

	@Override
	public int[] getSlotsForFace(@NotNull Direction direction) {
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
	public void fillStackedContents(@NotNull StackedContents contents) {
		for(ItemStack itemstack : this.items) {
			contents.accountStack(itemstack);
		}
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		}
		return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
	}

	@Override @NotNull
	public ItemStack getItem(int index) {
		return this.items.get(index);
	}

	@Override @NotNull
	public ItemStack removeItem(int index, int count) {
		return ContainerHelper.removeItem(this.items, index, count);
	}

	@Override @NotNull
	public ItemStack removeItemNoUpdate(int index) {
		return ContainerHelper.takeItem(this.items, index);
	}

	@Override
	public void setItem(int index, @NotNull ItemStack itemStack) {
		this.items.set(index, itemStack);
		if (itemStack.getCount() > this.getMaxStackSize()) {
			itemStack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack itemStack) {
		return switch (index) {
			case SLOT_INPUT -> (itemStack.getItem() instanceof BlockItem blockItem) && blockItem.getBlock() instanceof AbstractSkullBlock;
			case SLOT_FUEL -> itemStack.is(Items.SOUL_SOIL);
			default -> false;
		};
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, @NotNull ItemStack itemStack, Direction direction) {
		return this.canPlaceItem(index, itemStack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, @NotNull ItemStack itemStack, @NotNull Direction direction) {
		return true;
	}

	LazyOptional<? extends IItemHandler>[] handlers =
			SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override @NotNull
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER) {
			if (facing == Direction.UP) {
				return handlers[0].cast();
			} else if (facing == Direction.DOWN) {
				return handlers[1].cast();
			} else {
				return handlers[2].cast();
			}
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		for (LazyOptional<? extends IItemHandler> handler : handlers) {
			handler.invalidate();
		}
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	}

	@Override @NotNull
	protected AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory) {
		return new SkullChargerMenu(id, inventory, this, this.dataAccess);
	}

	static long lastSoundTime = 0;

	public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, SkullChargerBlockEntity blockEntity) {
		ItemStack ingredient = blockEntity.items.get(SLOT_INPUT);
		ItemStack fuel = blockEntity.items.get(SLOT_FUEL);

		if(!fuel.isEmpty()) {
			if(fuel.is(Items.SOUL_SOIL)) {
				if(blockEntity.energy + ENERGY_ADD <= MAX_ENERGY_LEVEL) {
					fuel.shrink(1);
					blockEntity.energy += ENERGY_ADD;
				}
			}
		}

		if(!ingredient.isEmpty()) {
			if((ingredient.getItem() instanceof BlockItem blockItem) && blockItem.getBlock() instanceof AbstractSkullBlock) {
				CompoundTag tag = ingredient.getOrCreateTag();
				CompoundTag scaleTag;
				if(!tag.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
					scaleTag = new CompoundTag();
					scaleTag.putInt("x", 100);
					scaleTag.putInt("y", 100);
					scaleTag.putInt("z", 100);
					tag.put(SkullCraft.SCALE_TAG, scaleTag);
				} else {
					scaleTag = tag.getCompound(SkullCraft.SCALE_TAG);
				}

				if(blockEntity.addX != 0 || blockEntity.addY != 0 || blockEntity.addZ != 0) {
					int energyCost = Mth.abs(blockEntity.addX / 5) + Mth.abs(blockEntity.addY / 5) + Mth.abs(blockEntity.addZ / 5);

					if(blockEntity.energy >= energyCost) {
						CompoundTag newScaleTag = scaleTag.copy();
						newScaleTag.remove("x");
						newScaleTag.remove("y");
						newScaleTag.remove("z");
						newScaleTag.putInt("x", Mth.clamp(scaleTag.getInt("x") + blockEntity.addX, 50, 5000));
						newScaleTag.putInt("y", Mth.clamp(scaleTag.getInt("y") + blockEntity.addY, 50, 5000));
						newScaleTag.putInt("z", Mth.clamp(scaleTag.getInt("z") + blockEntity.addZ, 50, 5000));

						CompoundTag newTag = tag.copy();
						newTag.remove(SkullCraft.SCALE_TAG);
						newTag.put(SkullCraft.SCALE_TAG, newScaleTag);
						ingredient.setTag(newTag);

						blockEntity.energy -= energyCost;

						long time = level.getGameTime();
						if(time - lastSoundTime >= MIN_SOUND_GAP) {
							level.playSound(null, blockPos, SCSounds.SKULL_CHARGER, SoundSource.BLOCKS, 1.0F, 1.0F);
							lastSoundTime = time;
						}
					}
					blockEntity.addX = 0;
					blockEntity.addY = 0;
					blockEntity.addZ = 0;
				}
			}
		}
	}
}
