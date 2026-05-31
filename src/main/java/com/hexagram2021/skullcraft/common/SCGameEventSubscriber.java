package com.hexagram2021.skullcraft.common;

import com.hexagram2021.skullcraft.common.register.SCEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 游戏事件订阅器，处理暴击、死亡掉落等游戏事件的模组自定义逻辑喵~
 *
 * @author liudongyu
 */
@EventBusSubscriber(modid = MODID)
public final class SCGameEventSubscriber {
	/**
	 * 处理暴击事件，当玩家装备了"地面打击"附魔的护甲时，
	 * 在非暴击攻击中触发额外的暴击效果喵~
	 *
	 * @param event 暴击事件喵~
	 */
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
				.filter(itemStack -> itemStack.getEnchantmentLevel(groundStrike) > 0 && !player.getCooldowns().isOnCooldown(itemStack))
				.findFirst().ifPresent(itemStack -> {
					event.setCriticalHit(true);
					event.setDamageMultiplier(1.5F);
					player.getCooldowns().addCooldown(itemStack, 120 / (itemStack.getEnchantmentLevel(groundStrike) + 1));
				});
	}

	private SCGameEventSubscriber() {
	}
}
