package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.common.register.SCEnchantments;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public class PlayerMixin {
	@WrapOperation(method = "causeFoodExhaustion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;addExhaustion(F)V"))
	public void skullcraft$modifyFoodExhaustion(FoodData instance, float exhaustion, Operation<Void> original) {
		Player player = (Player)(Object)this;
		Holder<Enchantment> staminaBoost = player.registryAccess().holder(SCEnchantments.STAMINA_BOOST).orElse(null);
		if(staminaBoost != null) {
			float finalExhaustion = exhaustion;
			exhaustion = player.getInventory().armor.stream()
					.filter(itemStack -> itemStack.getEnchantmentLevel(staminaBoost) > 0)
					.findFirst().map(itemStack -> finalExhaustion / (itemStack.getEnchantmentLevel(staminaBoost) + 1)).orElse(exhaustion);
		}
		original.call(instance, exhaustion);
	}
}
