package com.hexagram2021.skullcraft.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.behavior.GiveGiftToHero;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(GiveGiftToHero.class)
public interface HeroGiftsTaskAccess {
	@Accessor("GIFTS")
	static Map<VillagerProfession, ResourceKey<LootTable>> skullcraft$getGifts() {
		throw new UnsupportedOperationException("Replaced by Mixin");
	}
}
