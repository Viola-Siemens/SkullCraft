package com.hexagram2021.skullcraft.common;

import com.hexagram2021.skullcraft.common.register.SCEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
public class SCGameEventSubscriber {
	@SubscribeEvent
	public static void tryCriticalHit(CriticalHitEvent event) {
		if(event.isCriticalHit()) {
			return;
		}
		Player player = event.getEntity();
		Holder<Enchantment> groundStrike = player.registryAccess().holder(SCEnchantments.GROUND_STRIKE).orElse(null);
		if(groundStrike == null) {
			return;
		}
		player.getInventory().armor.stream()
				.filter(itemStack -> itemStack.getEnchantmentLevel(groundStrike) > 0 && !player.getCooldowns().isOnCooldown(itemStack.getItem()))
				.findFirst().ifPresent(itemStack -> {
					event.setCriticalHit(true);
					event.setDamageMultiplier(1.5F);
					player.getCooldowns().addCooldown(itemStack.getItem(), 120 / (itemStack.getEnchantmentLevel(groundStrike) + 1));
				});
	}
}
