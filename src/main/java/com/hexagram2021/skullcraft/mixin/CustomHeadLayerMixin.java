package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.SkullCraft;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(CustomHeadLayer.class)
public class CustomHeadLayerMixin<T extends LivingEntity> {
	@Nullable
	private CompoundTag blockItemTag = null;

	@Redirect(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getTag()Lnet/minecraft/nbt/CompoundTag;"))
	@Nullable
	public CompoundTag getBlockItemTag(ItemStack instance) {
		return this.blockItemTag = instance.getTag();
	}

	@SuppressWarnings("unchecked")
	@Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", shift = At.Shift.BEFORE))
	public void handleSkullCraftScale(PoseStack transform, MultiBufferSource source, int uv2, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		((CustomHeadLayer<T, ? extends HeadedModel>)(Object)this).getParentModel().getHead().skipDraw = true;
		if (this.blockItemTag != null && this.blockItemTag.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
			final CompoundTag scaleNBT = this.blockItemTag.getCompound(SkullCraft.SCALE_TAG);
			final int scaleX = scaleNBT.contains("x") ? Mth.clamp(scaleNBT.getInt("x"), 50, 5000) : 100;
			final int scaleY = scaleNBT.contains("y") ? Mth.clamp(scaleNBT.getInt("y"), 50, 5000) : 100;
			final int scaleZ = scaleNBT.contains("z") ? Mth.clamp(scaleNBT.getInt("z"), 50, 5000) : 100;
			final double dx = (double)scaleX / 100.0D;
			final double dy = (double)scaleY / 100.0D;
			final double dz = (double)scaleZ / 100.0D;
			transform.scale((float)dx, (float)dy, (float)dz);
		}
		this.blockItemTag = null;
	}

	@SuppressWarnings("unchecked")
	@Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "HEAD"))
	public void resetSkipDraw(PoseStack transform, MultiBufferSource source, int uv2, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		((CustomHeadLayer<T, ? extends HeadedModel>)(Object)this).getParentModel().getHead().skipDraw = false;
	}
}
