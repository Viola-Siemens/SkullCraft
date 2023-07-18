package com.hexagram2021.skullcraft.client.screen;

import com.hexagram2021.skullcraft.common.crafting.SkullChargerMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@OnlyIn(Dist.CLIENT)
public class SkullChargerScreen extends AbstractContainerScreen<SkullChargerMenu> {
	private static final ResourceLocation BG_LOCATION = new ResourceLocation(MODID, "textures/gui/container/skull_charger.png");

	private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
	private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
	private static final int RECIPES_X = 52;
	private static final int RECIPES_Y = 14;

	private boolean displayRecipes;

	public SkullChargerScreen(SkullChargerMenu menu, Inventory inventory, Component component) {
		super(menu, inventory, component);
		menu.registerUpdateListener(this::containerChanged);
		--this.titleLabelY;
	}

	@Override
	public void render(@NotNull GuiGraphics transform, int x, int y, float partialTicks) {
		super.render(transform, x, y, partialTicks);
		this.renderTooltip(transform, x, y);
	}

	@Override
	protected void renderBg(@NotNull GuiGraphics transform, float partialTicks, int x, int y) {
		this.renderBackground(transform);
		int i = this.leftPos;
		int j = this.topPos;
		transform.blit(BG_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
		int recipeX = this.leftPos + RECIPES_X;
		int recipeY = this.topPos + RECIPES_Y;
		this.renderButtons(transform, x, y, recipeX, recipeY);
		int energyLevel = this.menu.getEnergy();
		if(energyLevel > 0) {
			int k = Mth.clamp((energyLevel + 1) / 2, 1, 50);
			transform.blit(BG_LOCATION, i + 58, j + 62, 176, 15, k, 8);
		}
	}

	@Override
	protected void renderTooltip(@NotNull GuiGraphics transform, int x, int y) {
		super.renderTooltip(transform, x, y);
		if (this.displayRecipes) {
			int recipeX = this.leftPos + RECIPES_X;
			int recipeY = this.topPos + RECIPES_Y;

			for(int i = 0; i < 3; ++i) {
				int curX = recipeX + i * RECIPES_IMAGE_SIZE_WIDTH;
				int curY = recipeY + 2;
				if (x >= curX && x < curX + RECIPES_IMAGE_SIZE_WIDTH && y >= curY && y < curY + RECIPES_IMAGE_SIZE_WIDTH) {
					transform.renderTooltip(this.font, Component.translatable("tooltip.skullcraft.skull_charger" + i), x, y);
				}
			}
		}
	}

	private void renderButtons(GuiGraphics transform, int x, int y, int recipeX, int recipeY) {
		if(this.menu.hasInputItem()) {
			for(int i = 0; i < 3; ++i) {
				int curX = recipeX + i * RECIPES_IMAGE_SIZE_WIDTH;
				int curY = recipeY + 2;
				int h = this.imageHeight;
				if(this.menu.hasAddXYZ(i)) {
					h += RECIPES_IMAGE_SIZE_HEIGHT;
				} else if (x >= curX && y >= curY && x < curX + RECIPES_IMAGE_SIZE_WIDTH && y < curY + RECIPES_IMAGE_SIZE_HEIGHT) {
					h += RECIPES_IMAGE_SIZE_HEIGHT * 2;
				}

				transform.blit(BG_LOCATION, curX, curY - 1, i * RECIPES_IMAGE_SIZE_WIDTH, h, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT);
			}
		}
	}

	@Override
	public boolean mouseClicked(double x, double y, int key) {
		final SimpleSoundInstance uiSound = SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F);
		if (this.displayRecipes) {
			int recipeX = this.leftPos + RECIPES_X;
			int recipeY = this.topPos + RECIPES_Y;

			for(int i = 0; i < 3; ++i) {
				double d0 = x - (double)(recipeX + i * RECIPES_IMAGE_SIZE_WIDTH);
				double d1 = y - (double)(recipeY);
				if (d0 >= 0.0D && d1 >= 0.0D && d0 < RECIPES_IMAGE_SIZE_WIDTH && d1 < RECIPES_IMAGE_SIZE_HEIGHT && this.menu.clickMenuButton(this.minecraft.player, i)) {
					Minecraft.getInstance().getSoundManager().play(uiSound);
					this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, i);
					return true;
				}
			}
		}

		return super.mouseClicked(x, y, key);
	}

	private void containerChanged() {
		this.displayRecipes = this.menu.hasInputItem();
	}
}
