package com.hexagram2021.skullcraft.common.loot;

import com.hexagram2021.skullcraft.common.block.IEnchantableBlockEntity;
import com.hexagram2021.skullcraft.common.block.IScalableBlockEntity;
import com.hexagram2021.skullcraft.common.components.SkullScale;
import com.hexagram2021.skullcraft.common.register.SCDataComponents;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 头颅 NBT 操作战利品修改器，在头颅方块被破坏掉落时，
 * 将缩放比例和附魔数据写入掉落物品的 NBT 中喵~
 *
 * @author liudongyu
 */
public final class SkullNBTOps {
	private static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> REGISTER = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);
	private static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SkullNBTOpsModifier>> SKULL_NBT_OPS = REGISTER.register(
			"skull_nbt_ops", () -> RecordCodecBuilder.mapCodec(inst -> OrConditionLootModifier.codecStart(inst).apply(inst, SkullNBTOpsModifier::new))
	);

	/**
	 * 将 NBT 操作战利品修改器注册到事件总线喵~
	 *
	 * @param bus 模组事件总线喵~
	 */
	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	private static class SkullNBTOpsModifier extends OrConditionLootModifier {
		protected SkullNBTOpsModifier(LootItemCondition[] conditionsIn) {
			super(conditionsIn);
		}

		@Override
		public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			BlockEntity blockEntity = context.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
			if(blockEntity instanceof SkullBlockEntity) {
				IScalableBlockEntity scalableBlockEntity = (IScalableBlockEntity)blockEntity;
				int scaleX = scalableBlockEntity.skullcraft$getScaleX();
				int scaleY = scalableBlockEntity.skullcraft$getScaleY();
				int scaleZ = scalableBlockEntity.skullcraft$getScaleZ();
				if(scaleX != 100 || scaleY != 100 || scaleZ != 100) {
					SkullScale skullScale = new SkullScale(scaleX, scaleY, scaleZ);
					for(ItemStack itemStack: generatedLoot) {
						Item item = itemStack.getItem();
						if(item instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock) {
							itemStack.set(SCDataComponents.SKULL_SCALE.get(), skullScale);
						}
					}
				}
				IEnchantableBlockEntity enchantableBlockEntity = (IEnchantableBlockEntity)blockEntity;
				for(ItemStack itemStack: generatedLoot) {
					Item item = itemStack.getItem();
					if(item instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock) {
						itemStack.set(DataComponents.ENCHANTMENTS, enchantableBlockEntity.skullcraft$getEnchantments());
						itemStack.set(DataComponents.REPAIR_COST, enchantableBlockEntity.skullcraft$getRepairCost());
					}
				}
			}
			return generatedLoot;
		}

		@Override
		public MapCodec<SkullNBTOpsModifier> codec() {
			return SkullNBTOps.SKULL_NBT_OPS.get();
		}
	}

	private SkullNBTOps() {
	}
}
