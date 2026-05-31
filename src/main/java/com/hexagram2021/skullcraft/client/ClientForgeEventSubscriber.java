package com.hexagram2021.skullcraft.client;

import com.hexagram2021.skullcraft.common.components.SkullScale;
import com.hexagram2021.skullcraft.common.register.SCDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 客户端 Forge 事件订阅器，负责在物品提示框中显示头颅的缩放比例信息喵~
 *
 * @author liudongyu
 */
@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public final class ClientForgeEventSubscriber {
	/**
	 * 在物品提示框中显示头颅缩放比例信息，
	 * 正值显示为金色（放大），负值显示为红色（缩小），零值显示为灰色喵~
	 *
	 * @param event 物品提示框事件喵~
	 */
	@SubscribeEvent
	public static void onToolTipShow(ItemTooltipEvent event) {
		ItemStack itemStack = event.getItemStack();
		Item item = itemStack.getItem();
		if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock) {
			SkullScale skullScale = itemStack.get(SCDataComponents.SKULL_SCALE.get());
			if (skullScale != null) {
				MutableComponent componentX = buildScaleComponent("tooltip.skullcraft.nox", "tooltip.skullcraft.x", "tooltip.skullcraft.negx", skullScale.x());
				MutableComponent componentY = buildScaleComponent("tooltip.skullcraft.noy", "tooltip.skullcraft.y", "tooltip.skullcraft.negy", skullScale.y());
				MutableComponent componentZ = buildScaleComponent("tooltip.skullcraft.noz", "tooltip.skullcraft.z", "tooltip.skullcraft.negz", skullScale.z());
				event.getToolTip().add(componentX);
				event.getToolTip().add(componentY);
				event.getToolTip().add(componentZ);
			}
		}
	}

	private static MutableComponent buildScaleComponent(String noScaleTranslationKey, String positiveScaleTranslationKey,
														String negativeScaleTranslationKey, int scale) {
		if (scale == 100) {
			return Component.translatable(noScaleTranslationKey).withStyle(ChatFormatting.GRAY);
		}
		if (scale > 100) {
			return Component.translatable(positiveScaleTranslationKey, scale - 100).withStyle(ChatFormatting.GOLD);
		}
		return Component.translatable(negativeScaleTranslationKey, 100 - scale).withStyle(ChatFormatting.RED);
	}

	private ClientForgeEventSubscriber() {
	}
}
