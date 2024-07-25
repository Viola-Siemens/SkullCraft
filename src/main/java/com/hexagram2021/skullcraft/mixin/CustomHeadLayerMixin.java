package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.client.model.HattedModel;
import com.hexagram2021.skullcraft.common.components.SkullScale;
import com.hexagram2021.skullcraft.common.register.SCDataComponents;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
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
		SkullScale skullScale = itemStack.get(SCDataComponents.SKULL_SCALE.get());

		HeadedModel model = ((CustomHeadLayer<T, ? extends HeadedModel>)(Object)this).getParentModel();
		if(SCClientConfig.HIDE_ORIGINAL_HEAD.get()) {
			model.getHead().skipDraw = true;	//TODO buggy: other mobs with same model will skip.
		}
		if(model instanceof HattedModel hattedModel && SCClientConfig.HIDE_ORIGINAL_HAT.get()) {
			hattedModel.skullcraft$getHat().skipDraw = true;
			ModelPart hat = hattedModel.skullcraft$getHatRim();
			if(hat != null) {
				hat.skipDraw = true;
			}
		}
		if (SCClientConfig.ENABLE_CUSTOM_SKULL_SIZE.get() && skullScale != null) {
			final double dx = (double)skullScale.x() / 100.0D;
			final double dy = (double)skullScale.y() / 100.0D;
			final double dz = (double)skullScale.z() / 100.0D;
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
