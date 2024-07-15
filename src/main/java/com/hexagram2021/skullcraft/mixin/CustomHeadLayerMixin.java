package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.client.model.HattedModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomHeadLayer.class)
public class CustomHeadLayerMixin<T extends LivingEntity> {
	@SuppressWarnings("unchecked")
	@Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", shift = At.Shift.BEFORE))
	public void handleSkullCraftScale(PoseStack transform, MultiBufferSource source, int uv2, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.HEAD);
		CompoundTag blockItemTag = null;
		if(itemStack.hasTag()) {
			blockItemTag = itemStack.getTag();
		}

		HeadedModel model = ((CustomHeadLayer<T, ? extends HeadedModel>)(Object)this).getParentModel();
		if(SCClientConfig.HIDE_ORIGINAL_HEAD.get()) {
			model.getHead().skipDraw = true;
		}
		if(model instanceof HattedModel hattedModel && SCClientConfig.HIDE_ORIGINAL_HAT.get()) {
			hattedModel.skullcraft$getHat().skipDraw = true;
			ModelPart hat = hattedModel.skullcraft$getHatRim();
			if(hat != null) {
				hat.skipDraw = true;
			}
		}
		if (SCClientConfig.ENABLE_CUSTOM_SKULL_SIZE.get() && blockItemTag != null && blockItemTag.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
			final CompoundTag scaleNBT = blockItemTag.getCompound(SkullCraft.SCALE_TAG);
			final int scaleX = scaleNBT.contains("x") ? Mth.clamp(scaleNBT.getInt("x"), 50, 5000) : 100;
			final int scaleY = scaleNBT.contains("y") ? Mth.clamp(scaleNBT.getInt("y"), 50, 5000) : 100;
			final int scaleZ = scaleNBT.contains("z") ? Mth.clamp(scaleNBT.getInt("z"), 50, 5000) : 100;
			final double dx = (double)scaleX / 100.0D;
			final double dy = (double)scaleY / 100.0D;
			final double dz = (double)scaleZ / 100.0D;
			transform.scale((float)dx, (float)dy, (float)dz);
		}
	}

	@SuppressWarnings("unchecked")
	@Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "HEAD"))
	public void resetSkipDraw(PoseStack transform, MultiBufferSource source, int uv2, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		HeadedModel model = ((CustomHeadLayer<T, ? extends HeadedModel>)(Object)this).getParentModel();
		if(SCClientConfig.HIDE_ORIGINAL_HEAD.get()) {
			model.getHead().skipDraw = false;
		}
		if(model instanceof HattedModel hattedModel && SCClientConfig.HIDE_ORIGINAL_HAT.get()) {
			hattedModel.skullcraft$getHat().skipDraw = false;
			ModelPart hat = hattedModel.skullcraft$getHatRim();
			if(hat != null) {
				hat.skipDraw = false;
			}
		}
	}
}
