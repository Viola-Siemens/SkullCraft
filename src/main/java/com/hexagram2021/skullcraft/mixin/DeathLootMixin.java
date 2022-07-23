package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.common.register.SCItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class DeathLootMixin {
	@Inject(method = "dropCustomDeathLoot", at = @At(value = "TAIL"))
	public void dropCustomHead(@NotNull DamageSource damageSource, int x, boolean flag, CallbackInfo ci) {
		Entity entity = damageSource.getEntity();
		if (entity instanceof Creeper creeper) {
			if (creeper.canDropMobsSkull()) {
				Item skullItem = null;
				if((Mob)(Object)this instanceof AbstractVillager) {
					skullItem = SCItems.HumanSkulls.VILLAGER_HEAD.get();
				} else if((Mob)(Object)this instanceof AbstractIllager) {
					skullItem = SCItems.HumanSkulls.ILLAGER_HEAD.get();
				} else if((Mob)(Object)this instanceof Witch) {
					skullItem = SCItems.HumanSkulls.WITCH_HEAD.get();
				} else if((Mob)(Object)this instanceof IronGolem) {
					skullItem = SCItems.HumanSkulls.IRON_GOLEM_HEAD.get();
				} else if((Mob)(Object)this instanceof ZombieVillager) {
					skullItem = SCItems.HumanSkulls.ZOMBIE_VILLAGER_HEAD.get();
				} else if((Mob)(Object)this instanceof MagmaCube) {
					skullItem = SCItems.CubeSkulls.LAVASLIME_HEAD.get();
				} else if((Mob)(Object)this instanceof Slime) {
					skullItem = SCItems.CubeSkulls.SLIME_HEAD.get();
				} else if((Mob)(Object)this instanceof Blaze) {
					skullItem = SCItems.CubeSkulls.BLAZE_HEAD.get();
				} else if((Mob)(Object)this instanceof CaveSpider) {
					skullItem = SCItems.CubeSkulls.CAVE_SPIDER_HEAD.get();
				} else if((Mob)(Object)this instanceof Spider) {
					skullItem = SCItems.CubeSkulls.SPIDER_HEAD.get();
				} else if((Mob)(Object)this instanceof Pig) {
					skullItem = SCItems.CubeSkulls.PIG_HEAD.get();
				} else if((Mob)(Object)this instanceof EnderMan) {
					skullItem = SCItems.CubeSkulls.ENDERMAN_HEAD.get();
				} else if((Mob)(Object)this instanceof SnowGolem) {
					skullItem = SCItems.CubeSkulls.SNOW_GOLEM_HEAD.get();
				}

				if(skullItem != null) {
					ItemStack itemstack = new ItemStack(skullItem);
					if (!itemstack.isEmpty()) {
						creeper.increaseDroppedSkulls();
						((Entity) (Object) this).spawnAtLocation(itemstack);
					}
				}
			}
		}
	}
}
