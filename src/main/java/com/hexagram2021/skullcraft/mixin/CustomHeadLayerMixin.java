package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.common.components.SkullScale;
import com.hexagram2021.skullcraft.common.register.SCDataComponents;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 自定义头部渲染层 Mixin，根据物品中的缩放数据调整头颅在生物头上的渲染大小喵~
 * @param <S> 渲染状态
 * @author liudongyu
 */
@Mixin(CustomHeadLayer.class)
public class CustomHeadLayerMixin<S extends LivingEntityRenderState> {
	/**
	 * 自定义头部渲染层注入点，用于处理物品中的缩放数据
	 * @param transform 渲染矩阵
	 * @param source 缓冲源
	 * @param uv2 UV 坐标
	 * @param renderState 渲染状态
	 * @param netHeadYaw 头部偏航角
	 * @param headPitch 头部俯仰角
	 * @param ci 回调信息
	 */
	@Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", shift = At.Shift.BEFORE))
	public void skullcraft$handleSkullCraftScale(PoseStack transform, MultiBufferSource source, int uv2, S renderState, float netHeadYaw, float headPitch, CallbackInfo ci) {
		ItemStack itemStack = renderState.headItem;
		SkullScale skullScale = itemStack.get(SCDataComponents.SKULL_SCALE.get());

		if (SCClientConfig.ENABLE_CUSTOM_SKULL_SIZE.get() && skullScale != null) {
			final double dx = skullScale.x() / 100.0D;
			final double dy = skullScale.y() / 100.0D;
			final double dz = skullScale.z() / 100.0D;
			transform.scale((float)dx, (float)dy, (float)dz);
		}
	}
}
