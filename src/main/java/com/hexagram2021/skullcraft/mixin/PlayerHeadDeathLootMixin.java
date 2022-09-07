package com.hexagram2021.skullcraft.mixin;

import com.google.common.collect.Lists;
import com.hexagram2021.skullcraft.common.register.SCItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
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
		if(entity instanceof Player) {
			Entity killer = damageSource.getEntity();
			if(killer instanceof Creeper creeper) {
				if(creeper.canDropMobsSkull()) {
					if(dropPlayerHead((Entity) (Object) this)) {
						creeper.increaseDroppedSkulls();
					}
				}
			} else if(killer instanceof LivingEntity && ((LivingEntity) killer).getMainHandItem().getItem() == SCItems.KOPIS.get() &&
					(killer.level.random.nextInt(5) == 0 ||
							(Lists.newArrayList(killer.getArmorSlots()).stream().anyMatch(
									itemStack -> itemStack.getItem() == SCItems.CubeSkulls.TECHNOBLADE_HEAD.get()
							) && entity.level.random.nextBoolean()))
			) {
				dropPlayerHead((Entity) (Object) this);
			}
		}
	}

	private static boolean dropPlayerHead(Entity player) {
		ItemStack itemstack = new ItemStack(Items.PLAYER_HEAD);
		CompoundTag nbt = itemstack.getOrCreateTag();
		nbt.putString("SkullOwner", player.getDisplayName().getString());
		itemstack.setTag(nbt);
		if (!itemstack.isEmpty()) {
			player.spawnAtLocation(itemstack);
			return true;
		}
		return false;
	}
}
