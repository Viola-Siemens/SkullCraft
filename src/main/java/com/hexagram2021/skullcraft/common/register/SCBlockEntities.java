package com.hexagram2021.skullcraft.common.register;

import com.google.common.collect.ImmutableSet;
import com.hexagram2021.skullcraft.common.block.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SCBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

	@SuppressWarnings("DataFlowIssue")
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SkullChargerBlockEntity>> SKULL_CHARGER = REGISTER.register(
			"skull_charger", () -> new BlockEntityType<>(
					SkullChargerBlockEntity::new, ImmutableSet.of(SCBlocks.SKULL_CHARGER.get()), null
			)
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
