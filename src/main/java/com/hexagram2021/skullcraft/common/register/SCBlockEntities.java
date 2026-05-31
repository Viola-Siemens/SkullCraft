package com.hexagram2021.skullcraft.common.register;

import com.hexagram2021.skullcraft.common.block.entity.SkullChargerBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 方块实体类型注册类，负责注册模组中所有自定义方块实体类型喵~
 *
 * @author liudongyu
 */
public final class SCBlockEntities {
	private static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

	/** 头颅充能器方块实体类型喵~ */
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SkullChargerBlockEntity>> SKULL_CHARGER = REGISTER.register(
			"skull_charger", () -> new BlockEntityType<>(
					SkullChargerBlockEntity::new, SCBlocks.SKULL_CHARGER.get()
			)
	);

	/**
	 * 将方块实体类型注册到事件总线喵~
	 *
	 * @param bus 模组事件总线喵~
	 */
	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	private SCBlockEntities() {
	}
}
