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

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {
	@SubscribeEvent
	public static void onToolTipShow(ItemTooltipEvent event) {
		ItemStack itemStack = event.getItemStack();
		Item item = itemStack.getItem();
		if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock) {
			SkullScale skullScale = itemStack.get(SCDataComponents.SKULL_SCALE.get());
			if (skullScale != null) {
				MutableComponent componentX = skullScale.x() == 100 ?
						Component.translatable("tooltip.skullcraft.nox").withStyle(ChatFormatting.GRAY) :
						skullScale.x() > 100 ?
								Component.translatable("tooltip.skullcraft.x", skullScale.x() - 100).withStyle(ChatFormatting.GOLD) :
								Component.translatable("tooltip.skullcraft.negx", 100 - skullScale.x()).withStyle(ChatFormatting.RED);
				MutableComponent componentY = skullScale.y() == 100 ?
						Component.translatable("tooltip.skullcraft.noy").withStyle(ChatFormatting.GRAY) :
						skullScale.y() > 100 ?
								Component.translatable("tooltip.skullcraft.y", skullScale.y() - 100).withStyle(ChatFormatting.GOLD) :
								Component.translatable("tooltip.skullcraft.negy", 100 - skullScale.y()).withStyle(ChatFormatting.RED);
				MutableComponent componentZ = skullScale.z() == 100 ?
						Component.translatable("tooltip.skullcraft.noz").withStyle(ChatFormatting.GRAY) :
						skullScale.z() > 100 ?
								Component.translatable("tooltip.skullcraft.z", skullScale.z() - 100).withStyle(ChatFormatting.GOLD) :
								Component.translatable("tooltip.skullcraft.negz", 100 - skullScale.z()).withStyle(ChatFormatting.RED);
				event.getToolTip().add(componentX);
				event.getToolTip().add(componentY);
				event.getToolTip().add(componentZ);
			}
		}
	}
}
