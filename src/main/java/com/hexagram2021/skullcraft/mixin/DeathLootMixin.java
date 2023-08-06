package com.hexagram2021.skullcraft.mixin;

import com.google.common.collect.ImmutableList;
import com.hexagram2021.skullcraft.common.register.SCItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class DeathLootMixin {
	@Inject(method = "dropCustomDeathLoot", at = @At(value = "TAIL"))
	public void dropCustomHead(DamageSource damageSource, int x, boolean flag, CallbackInfo ci) {
		Entity entity = damageSource.getEntity();
		if (entity instanceof Creeper creeper) {
			if (creeper.canDropMobsSkull()) {
				if (dropSkullItem((Mob)(Object)this, false)) {
					creeper.increaseDroppedSkulls();
				}
			}
		} else if((entity instanceof LivingEntity) &&
				((LivingEntity) entity).getMainHandItem().getItem() == SCItems.KOPIS.get() &&
				(entity.level().random.nextInt(5) == 0 ||
						(ImmutableList.copyOf(entity.getArmorSlots()).stream().anyMatch(
								itemStack -> itemStack.getItem() == SCItems.CubeSkulls.TECHNOBLADE_HEAD.get()
						) && entity.level().random.nextBoolean()))
		) {
			dropSkullItem((Mob)(Object)this, true);
		}
	}

	private static boolean dropSkullItem(Mob current, boolean includeVanillaSkulls) {
		Item skullItem = null;
		if(current instanceof AbstractVillager) {
			skullItem = SCItems.HumanSkulls.VILLAGER_HEAD.get();
		} else if(current instanceof AbstractIllager) {
			skullItem = SCItems.HumanSkulls.ILLAGER_HEAD.get();
		} else if(current instanceof Witch) {
			skullItem = SCItems.HumanSkulls.WITCH_HEAD.get();
		} else if(current instanceof IronGolem) {
			skullItem = SCItems.HumanSkulls.IRON_GOLEM_HEAD.get();
		} else if(current instanceof ZombieVillager) {
			skullItem = SCItems.HumanSkulls.ZOMBIE_VILLAGER_HEAD.get();
		} else if(current instanceof MagmaCube) {
			skullItem = SCItems.CubeSkulls.LAVASLIME_HEAD.get();
		} else if(current instanceof Slime) {
			skullItem = SCItems.CubeSkulls.SLIME_HEAD.get();
		} else if(current instanceof Blaze) {
			skullItem = SCItems.CubeSkulls.BLAZE_HEAD.get();
		} else if(current instanceof CaveSpider) {
			skullItem = SCItems.CubeSkulls.CAVE_SPIDER_HEAD.get();
		} else if(current instanceof Spider) {
			skullItem = SCItems.CubeSkulls.SPIDER_HEAD.get();
		} else if(current instanceof Pig) {
			skullItem = SCItems.CubeSkulls.PIG_HEAD.get();
		} else if(current instanceof EnderMan) {
			skullItem = SCItems.CubeSkulls.ENDERMAN_HEAD.get();
		} else if(current instanceof SnowGolem) {
			skullItem = SCItems.CubeSkulls.SNOW_GOLEM_HEAD.get();
		} else if(current instanceof Sheep) {
			skullItem = SCItems.SmallCubeSkulls.SHEEP_HEAD.get();
		} else if(current instanceof Bat) {
			skullItem = SCItems.SmallCubeSkulls.BAT_HEAD.get();
		} else if(current instanceof Shulker) {
			skullItem = SCItems.SmallCubeSkulls.SHULKER_HEAD.get();
		} else if(current instanceof Allay) {
			skullItem = SCItems.SmallCubeSkulls.ALLAY_HEAD.get();
		} else if(current instanceof Vex) {
			skullItem = SCItems.SmallCubeSkulls.VEX_HEAD.get();
		} else if(current instanceof MushroomCow mushroomCow) {
			skullItem = switch (mushroomCow.getVariant()) {
				case RED -> SCItems.CowSkulls.RED_MOOSHROOM_HEAD.get();
				case BROWN -> SCItems.CowSkulls.BROWN_MOOSHROOM_HEAD.get();
			};
		} else if(current instanceof Cow) {
			skullItem = SCItems.CowSkulls.COW_HEAD.get();
		} else if(current instanceof PiglinBrute) {
			skullItem = SCItems.PiglinSkulls.PIGLIN_BRUTE_HEAD.get();
		} else if(current instanceof ZombifiedPiglin) {
			skullItem = SCItems.PiglinSkulls.ZOMBIFIED_PIGLIN_HEAD.get();
		} else if(current instanceof Horse horse) {
			skullItem = switch(horse.getVariant()) {
				case WHITE -> SCItems.HorseSkulls.WHITE_HORSE_HEAD.get();
				case CREAMY -> SCItems.HorseSkulls.CREAMY_HORSE_HEAD.get();
				case CHESTNUT -> SCItems.HorseSkulls.CHESTNUT_HORSE_HEAD.get();
				case BROWN -> SCItems.HorseSkulls.BROWN_HORSE_HEAD.get();
				case BLACK -> SCItems.HorseSkulls.BLACK_HORSE_HEAD.get();
				case GRAY -> SCItems.HorseSkulls.GRAY_HORSE_HEAD.get();
				case DARK_BROWN -> SCItems.HorseSkulls.DARKBROWN_HORSE_HEAD.get();
			};
		} else if(current instanceof Donkey) {
			skullItem = SCItems.HorseSkulls.DONKEY_HEAD.get();
		} else if(current instanceof Mule) {
			skullItem = SCItems.HorseSkulls.MULE_HEAD.get();
		} else if(current instanceof SkeletonHorse) {
			skullItem = SCItems.HorseSkulls.SKELETON_HORSE_HEAD.get();
		} else if(current instanceof ZombieHorse) {
			skullItem = SCItems.HorseSkulls.ZOMBIE_HORSE_HEAD.get();
		} else if(current instanceof Warden) {
			skullItem = SCItems.WardenSkulls.WARDEN_HEAD.get();
		} else if(includeVanillaSkulls) {
			if(current instanceof Zombie) {
				skullItem = Items.ZOMBIE_HEAD;
			} else if(current instanceof AbstractSkeleton) {
				skullItem = Items.SKELETON_SKULL;
			} else if(current instanceof Creeper) {
				skullItem = Items.CREEPER_HEAD;
			} else if(current instanceof EnderDragon) {
				skullItem = Items.DRAGON_HEAD;
			} else if(current instanceof WitherBoss) {
				skullItem = Items.WITHER_SKELETON_SKULL;
			} else if(current instanceof AbstractPiglin) {
				skullItem = Items.PIGLIN_HEAD;
			}
		}

		if(skullItem != null) {
			ItemStack itemstack = new ItemStack(skullItem);
			if (!itemstack.isEmpty()) {
				current.spawnAtLocation(itemstack);
				return true;
			}
		}
		return false;
	}
}
