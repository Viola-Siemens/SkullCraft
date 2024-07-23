package com.hexagram2021.skullcraft.common.loot;

import com.hexagram2021.skullcraft.common.block.Scalable;
import com.hexagram2021.skullcraft.common.components.SkullScale;
import com.hexagram2021.skullcraft.common.register.SCDataComponents;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class SkullNBTOps {
	private static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> REGISTER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);
	private static final RegistryObject<MapCodec<SkullNBTOpsModifier>> SKULL_NBT_OPS = REGISTER.register(
			"skull_nbt_ops", () -> RecordCodecBuilder.mapCodec(inst -> OrConditionLootModifier.codecStart(inst).apply(inst, SkullNBTOpsModifier::new))
	);

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}

	private static class SkullNBTOpsModifier extends OrConditionLootModifier {
		protected SkullNBTOpsModifier(LootItemCondition[] conditionsIn) {
			super(conditionsIn);
		}

		@Override
		public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			if(context.hasParam(LootContextParams.BLOCK_ENTITY)) {
				BlockEntity blockEntity = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
				if(blockEntity instanceof SkullBlockEntity) {
					Scalable skullBlockEntity = (Scalable)blockEntity;
					int scaleX = skullBlockEntity.skullcraft$getScaleX();
					int scaleY = skullBlockEntity.skullcraft$getScaleY();
					int scaleZ = skullBlockEntity.skullcraft$getScaleZ();
					if(scaleX != 100 || scaleY != 100 || scaleZ != 100) {
						SkullScale skullScale = new SkullScale(scaleX, scaleY, scaleZ);
						for(ItemStack itemStack: generatedLoot) {
							Item item = itemStack.getItem();
							if(item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof AbstractSkullBlock) {
								itemStack.set(SCDataComponents.SKULL_SCALE.get(), skullScale);
							}
						}
					}
				}
			}
			return generatedLoot;
		}

		@Override
		public MapCodec<? extends IGlobalLootModifier> codec() {
			return SkullNBTOps.SKULL_NBT_OPS.get();
		}
	}
}
