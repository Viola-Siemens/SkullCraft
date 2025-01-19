package com.hexagram2021.skullcraft.common.crafting;

import com.hexagram2021.skullcraft.common.block.entity.SkullChargerBlockEntity;
import com.hexagram2021.skullcraft.common.register.SCContainerTypes;
import com.hexagram2021.skullcraft.common.register.SCItems;
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
	public static final int ENCHANT_SLOT = 2;
	private static final int INV_SLOT_START = 3;
	private static final int INV_SLOT_END = 30;
	private static final int USE_ROW_SLOT_START = 30;
	private static final int USE_ROW_SLOT_END = 39;
	public static final int SLOT_COUNT = 3;
	public static final int DATA_COUNT = 4;

	private final Container skullCharger;
	private final ContainerData skullChargerData;

	final Slot inputSlot;
	final Slot enchantSlot;

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
			}

			@Override
			public int getMaxStackSize() {
				return 1;
			}
		});
		this.addSlot(new Slot(container, FUEL_SLOT, 20, 51) {
			@Override
			public boolean mayPlace(ItemStack itemStack) {
				return SkullChargerMenu.this.isFuel(itemStack);
			}

			@Override
			public int getMaxStackSize() {
				return 64;
			}
		});
		this.enchantSlot = this.addSlot(new Slot(container, ENCHANT_SLOT, 92, 40) {
			@Override
			public boolean mayPlace(ItemStack itemStack) {
				return SkullChargerMenu.this.isEnchantingBead(itemStack);
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

	public boolean hasEnchantingBead() {
		return this.enchantSlot.hasItem() && this.isEnchantingBead(this.enchantSlot.getItem());
	}

	private boolean canCharge(ItemStack itemStack) {
		return itemStack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock;
	}

	protected boolean isFuel(ItemStack itemStack) {
		return itemStack.is(Items.SOUL_SOIL);
	}

	protected boolean isEnchantingBead(ItemStack itemStack) {
		return itemStack.is(SCItems.SKULL_ENCHANTING_BEAD.get());
	}

	@Override
	public boolean clickMenuButton(Player player, int index) {
		if(this.hasInputItem()) {
			if(index >= 0 && index <= 2) {
				this.setAddXYZ(index);
				this.broadcastChanges();
				return true;
			}
			if(index == 3 && this.hasEnchantingBead()) {
				if(this.skullCharger instanceof SkullChargerBlockEntity skullChargerBlockEntity) {
					skullChargerBlockEntity.performEnchant(player.level(), player.registryAccess(), player.getRandom());
				}
				this.broadcastChanges();
				return true;
			}
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
			if (index != FUEL_SLOT && index != ENCHANT_SLOT && index != INPUT_SLOT) {
				if (this.canCharge(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, INPUT_SLOT, INPUT_SLOT + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isFuel(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, FUEL_SLOT, FUEL_SLOT + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if(this.isEnchantingBead(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, ENCHANT_SLOT, ENCHANT_SLOT + 1, false)) {
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
