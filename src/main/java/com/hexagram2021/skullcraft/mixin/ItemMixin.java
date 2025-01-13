package com.hexagram2021.skullcraft.mixin;

import net.minecraft.core.Holder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
	@Shadow @Final
	private Holder.Reference<Item> builtInRegistryHolder;

	@Inject(method = "getEnchantmentValue", at = @At(value = "HEAD"), cancellable = true)
	private void skullcraft$modifySkullEnchantmentValue(CallbackInfoReturnable<Integer> cir) {
		if(this.builtInRegistryHolder.is(ItemTags.SKULLS)) {
			cir.setReturnValue(5);
		}
	}

	@Inject(method = "isEnchantable", at = @At(value = "HEAD"), cancellable = true)
	private void skullcraft$modifySkullEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		if(this.builtInRegistryHolder.is(ItemTags.SKULLS)) {
			cir.setReturnValue(true);
		}
	}
}
