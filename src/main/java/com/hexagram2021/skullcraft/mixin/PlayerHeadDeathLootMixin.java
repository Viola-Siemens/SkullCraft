package com.hexagram2021.skullcraft.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class PlayerHeadDeathLootMixin {
	@Inject(method = "dropCustomDeathLoot", at = @At(value = "TAIL"))
	public void dropCustomHead(@NotNull DamageSource damageSource, int x, boolean flag, CallbackInfo ci) {
		LivingEntity entity = (LivingEntity)(Object)this;
		if(damageSource.getEntity() instanceof Player && entity instanceof Player && entity.level.random.nextBoolean()) {
			ItemStack itemstack = new ItemStack(Items.PLAYER_HEAD);
			if (!itemstack.isEmpty()) {
				((Entity) (Object) this).spawnAtLocation(itemstack);
			}
		}
	}
}
