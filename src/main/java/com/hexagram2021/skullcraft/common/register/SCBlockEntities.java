package com.hexagram2021.skullcraft.common.register;

import com.google.common.collect.ImmutableSet;
import com.hexagram2021.skullcraft.common.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

	public static final RegistryObject<BlockEntityType<SkullChargerBlockEntity>> SKULL_CHARGER = REGISTER.register(
			"skull_charger", () -> new BlockEntityType<>(
					SkullChargerBlockEntity::new, ImmutableSet.of(SCBlocks.SKULL_CHARGER.get()), null
			)
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
