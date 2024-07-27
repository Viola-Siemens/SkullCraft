package com.hexagram2021.skullcraft.mixin;

import com.google.common.collect.ImmutableList;
import com.hexagram2021.skullcraft.common.register.SCItems;
import net.minecraft.server.level.ServerLevel;
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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class DeathLootMixin {
	@Inject(method = "dropCustomDeathLoot", at = @At(value = "TAIL"))
	public void skullcraft$dropCustomHead(ServerLevel level, DamageSource damageSource, boolean recentHit, CallbackInfo ci) {
		Entity entity = damageSource.getEntity();
		if (entity instanceof Creeper creeper) {
			if (creeper.canDropMobsSkull()) {
				if (skullcraft$dropSkullItem((Mob)(Object)this, false)) {
					creeper.increaseDroppedSkulls();
				}
			}
		} else if(entity instanceof LivingEntity livingEntity &&
				livingEntity.getMainHandItem().getItem() == SCItems.KOPIS.get() &&
				(entity.level().random.nextInt(5) == 0 ||
						(ImmutableList.copyOf(livingEntity.getArmorSlots()).stream().anyMatch(
								itemStack -> itemStack.getItem() == SCItems.CubeSkulls.TECHNOBLADE_HEAD.get()
						) && entity.level().random.nextBoolean()))
		) {
			skullcraft$dropSkullItem((Mob)(Object)this, true);
		}
	}

	@Unique
	private static boolean skullcraft$dropSkullItem(Mob current, boolean includeVanillaSkulls) {
		Item skullItem = switch (current) {
			case AbstractVillager ignored -> SCItems.HumanSkulls.VILLAGER_HEAD.get();
			case AbstractIllager ignored -> SCItems.HumanSkulls.ILLAGER_HEAD.get();
			case Witch ignored -> SCItems.HumanSkulls.WITCH_HEAD.get();
			case IronGolem ignored -> SCItems.HumanSkulls.IRON_GOLEM_HEAD.get();
			case ZombieVillager ignored -> SCItems.HumanSkulls.ZOMBIE_VILLAGER_HEAD.get();
			case MagmaCube ignored -> SCItems.CubeSkulls.LAVASLIME_HEAD.get();
			case Slime ignored -> SCItems.CubeSkulls.SLIME_HEAD.get();
			case Blaze ignored -> SCItems.CubeSkulls.BLAZE_HEAD.get();
			case CaveSpider ignored -> SCItems.CubeSkulls.CAVE_SPIDER_HEAD.get();
			case Spider ignored -> SCItems.CubeSkulls.SPIDER_HEAD.get();
			case Pig ignored -> SCItems.CubeSkulls.PIG_HEAD.get();
			case EnderMan ignored -> SCItems.CubeSkulls.ENDERMAN_HEAD.get();
			case SnowGolem ignored -> SCItems.CubeSkulls.SNOW_GOLEM_HEAD.get();
			case Sheep ignored -> SCItems.SmallCubeSkulls.SHEEP_HEAD.get();
			case Bat ignored -> SCItems.SmallCubeSkulls.BAT_HEAD.get();
			case Shulker ignored -> SCItems.SmallCubeSkulls.SHULKER_HEAD.get();
			case Allay ignored -> SCItems.SmallCubeSkulls.ALLAY_HEAD.get();
			case Vex ignored -> SCItems.SmallCubeSkulls.VEX_HEAD.get();
			case Wolf wolf -> wolf.isAngry() ?
					SCItems.SmallCubeSkulls.ANGRY_WOLF_HEAD.get() : SCItems.SmallCubeSkulls.WOLF_HEAD.get();
			case MushroomCow mushroomCow -> switch (mushroomCow.getVariant()) {
				case RED -> SCItems.CowSkulls.RED_MOOSHROOM_HEAD.get();
				case BROWN -> SCItems.CowSkulls.BROWN_MOOSHROOM_HEAD.get();
			};
			case Cow ignored -> SCItems.CowSkulls.COW_HEAD.get();
			case PiglinBrute ignored -> SCItems.PiglinSkulls.PIGLIN_BRUTE_HEAD.get();
			case ZombifiedPiglin ignored -> SCItems.PiglinSkulls.ZOMBIFIED_PIGLIN_HEAD.get();
			case Horse horse -> switch (horse.getVariant()) {
				case WHITE -> SCItems.HorseSkulls.WHITE_HORSE_HEAD.get();
				case CREAMY -> SCItems.HorseSkulls.CREAMY_HORSE_HEAD.get();
				case CHESTNUT -> SCItems.HorseSkulls.CHESTNUT_HORSE_HEAD.get();
				case BROWN -> SCItems.HorseSkulls.BROWN_HORSE_HEAD.get();
				case BLACK -> SCItems.HorseSkulls.BLACK_HORSE_HEAD.get();
				case GRAY -> SCItems.HorseSkulls.GRAY_HORSE_HEAD.get();
				case DARK_BROWN -> SCItems.HorseSkulls.DARKBROWN_HORSE_HEAD.get();
			};
			case Donkey ignored -> SCItems.HorseSkulls.DONKEY_HEAD.get();
			case Mule ignored -> SCItems.HorseSkulls.MULE_HEAD.get();
			case SkeletonHorse ignored -> SCItems.HorseSkulls.SKELETON_HORSE_HEAD.get();
			case ZombieHorse ignored -> SCItems.HorseSkulls.ZOMBIE_HORSE_HEAD.get();
			case Warden ignored -> SCItems.WardenSkulls.WARDEN_HEAD.get();
			default -> includeVanillaSkulls ? switch (current) {
				case Zombie ignored -> Items.ZOMBIE_HEAD;
				case AbstractSkeleton ignored -> Items.SKELETON_SKULL;
				case Creeper ignored -> Items.CREEPER_HEAD;
				case EnderDragon ignored -> Items.DRAGON_HEAD;
				case WitherBoss ignored -> Items.WITHER_SKELETON_SKULL;
				case AbstractPiglin ignored -> Items.PIGLIN_HEAD;
				default -> null;
			} : null;
		};

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
