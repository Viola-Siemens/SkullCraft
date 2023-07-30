package com.hexagram2021.skullcraft.common.loot;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.common.block.Scaleable;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
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
	private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);
	private static final RegistryObject<Codec<SkullNBTOpsModifier>> SKULL_NBT_OPS = REGISTER.register(
			"skull_nbt_ops", () -> RecordCodecBuilder.create(inst -> OrConditionLootModifier.codecStart(inst).apply(inst, SkullNBTOpsModifier::new))
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
					Scaleable skullBlockEntity = (Scaleable)blockEntity;
					int scaleX = skullBlockEntity.getScaleX();
					int scaleY = skullBlockEntity.getScaleY();
					int scaleZ = skullBlockEntity.getScaleZ();
					if(scaleX != 100 || scaleY != 100 || scaleZ != 100) {
						CompoundTag scaleTag = new CompoundTag();
						scaleTag.putInt("x", scaleX);
						scaleTag.putInt("y", scaleY);
						scaleTag.putInt("z", scaleZ);
						for(ItemStack itemStack: generatedLoot) {
							Item item = itemStack.getItem();
							if(item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof AbstractSkullBlock) {
								CompoundTag tag = itemStack.getOrCreateTag();
								tag.put(SkullCraft.SCALE_TAG, scaleTag.copy());
								itemStack.setTag(tag);
							}
						}
					}
				}
			}
			return generatedLoot;
		}

		@Override
		public Codec<? extends IGlobalLootModifier> codec() {
			return SkullNBTOps.SKULL_NBT_OPS.get();
		}
	}
}
