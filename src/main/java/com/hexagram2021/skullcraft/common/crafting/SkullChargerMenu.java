package com.hexagram2021.skullcraft.common.crafting;

import com.hexagram2021.skullcraft.common.block.entity.SkullChargerBlockEntity;
import com.hexagram2021.skullcraft.common.register.SCContainerTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.AbstractSkullBlock;

public class SkullChargerMenu extends AbstractContainerMenu {
	public static final int INPUT_SLOT = 0;
	public static final int FUEL_SLOT = 1;
	private static final int INV_SLOT_START = 2;
	private static final int INV_SLOT_END = 29;
	private static final int USE_ROW_SLOT_START = 29;
	private static final int USE_ROW_SLOT_END = 38;
	public static final int SLOT_COUNT = 2;
	public static final int DATA_COUNT = 4;

	private final Container skullCharger;
	private final ContainerData skullChargerData;

	final Slot inputSlot;

	Runnable slotUpdateListener = () -> {};

	public SkullChargerMenu(int id, Inventory inventory) {
		this(id, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
	}

	public SkullChargerMenu(int id, Inventory inventory, Container container, ContainerData data) {
		super(SCContainerTypes.SKULL_CHARGER_MENU.get(), id);
		checkContainerSize(container, SLOT_COUNT);
		checkContainerDataCount(data, DATA_COUNT);
		this.skullCharger = container;
		this.skullChargerData = data;


		this.inputSlot = this.addSlot(new Slot(container, INPUT_SLOT, 143, 33) {
			@Override
			public boolean mayPlace(ItemStack itemStack) {
				return (itemStack.getItem() instanceof BlockItem blockItem) && blockItem.getBlock() instanceof AbstractSkullBlock;
			}

			@Override
			public void setChanged() {
				super.setChanged();
				SkullChargerMenu.this.slotUpdateListener.run();
			}

			@Override
			public int getMaxStackSize() {
				return 1;
			}
		});
		this.addSlot(new Slot(container, FUEL_SLOT, 20, 51) {
			@Override
			public boolean mayPlace(ItemStack itemStack) {
				return itemStack.is(Items.SOUL_SOIL);
			}

			@Override
			public int getMaxStackSize() {
				return 64;
			}
		});
		this.addDataSlots(data);

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
		}
	}

	@Override
	public boolean stillValid(Player player) {
		return this.skullCharger.stillValid(player);
	}

	@Override
	public MenuType<?> getType() {
		return SCContainerTypes.SKULL_CHARGER_MENU.get();
	}

	public boolean hasInputItem() {
		return this.inputSlot.hasItem() && this.canCharge(this.inputSlot.getItem());
	}

	private boolean canCharge(ItemStack itemStack) {
		return itemStack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock;
	}

	protected boolean isFuel(ItemStack itemStack) {
		return itemStack.is(Items.SOUL_SOIL);
	}

	@Override
	public boolean clickMenuButton(Player player, int index) {
		if(this.hasInputItem() && index >= 0 && index <= 2) {
			this.setAddXYZ(index);
			this.broadcastChanges();
			return true;
		}
		return false;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index != FUEL_SLOT && index != INPUT_SLOT) {
				if (this.canCharge(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, INPUT_SLOT, INPUT_SLOT + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isFuel(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, FUEL_SLOT, FUEL_SLOT + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= INV_SLOT_START && index < INV_SLOT_END) {
					if (!this.moveItemStackTo(itemstack1, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= USE_ROW_SLOT_START && index < USE_ROW_SLOT_END && !this.moveItemStackTo(itemstack1, INV_SLOT_START, INV_SLOT_END, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}

	public void registerUpdateListener(Runnable func) {
		this.slotUpdateListener = func;
	}

	public int getEnergy() {
		return this.skullChargerData.get(SkullChargerBlockEntity.DATA_ENERGY);
	}

	public boolean hasAddXYZ(int index) {
		return this.skullChargerData.get(index + 1) != 0;
	}

	public void setAddXYZ(int index) {
		this.skullChargerData.set(index + 1, this.skullChargerData.get(index + 1) + 5);
	}
}
