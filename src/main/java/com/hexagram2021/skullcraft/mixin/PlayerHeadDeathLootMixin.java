package com.hexagram2021.skullcraft.mixin;

import com.google.common.collect.Lists;
import com.hexagram2021.skullcraft.common.register.SCItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ResolvableProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class PlayerHeadDeathLootMixin {
	@Inject(method = "dropCustomDeathLoot", at = @At(value = "TAIL"))
	public void skullcraft$dropCustomHead(ServerLevel level, DamageSource damageSource, boolean recentHit, CallbackInfo ci) {
		LivingEntity entity = (LivingEntity)(Object)this;
		if(entity instanceof ServerPlayer player) {
			Entity killer = damageSource.getEntity();
			if(killer instanceof Creeper creeper) {
				if(creeper.canDropMobsSkull() && skullcraft$dropPlayerHead(player)) {
					creeper.increaseDroppedSkulls();
				}
			} else if(killer instanceof LivingEntity livingEntity && livingEntity.getMainHandItem().getItem() == SCItems.KOPIS.get() &&
					(killer.level().random.nextInt(5) == 0 ||
							(Lists.newArrayList(livingEntity.getArmorSlots()).stream().anyMatch(
									itemStack -> itemStack.getItem() == SCItems.CubeSkulls.TECHNOBLADE_HEAD.get()
							) && entity.level().random.nextBoolean()))
			) {
				skullcraft$dropPlayerHead(player);
			}
		}
	}

	@Unique
	private static boolean skullcraft$dropPlayerHead(ServerPlayer player) {
		ItemStack itemstack = new ItemStack(Items.PLAYER_HEAD);
		itemstack.set(DataComponents.PROFILE, new ResolvableProfile(player.getGameProfile()));
		if (!itemstack.isEmpty()) {
			player.spawnAtLocation(player.serverLevel(), itemstack);
			return true;
		}
		return false;
	}
}
