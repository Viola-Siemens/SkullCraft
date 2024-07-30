package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.common.block.Scalable;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkullBlockRenderer.class)
public class SkullBlockRendererMixin {
	@Inject(method = "render(Lnet/minecraft/world/level/block/entity/SkullBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/SkullBlockRenderer;renderSkull(Lnet/minecraft/core/Direction;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/SkullModelBase;Lnet/minecraft/client/renderer/RenderType;)V", shift = At.Shift.BEFORE))
	public void skullcraft$renderScale(SkullBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, CallbackInfo ci) {
		if(SCClientConfig.ENABLE_CUSTOM_SKULL_SIZE.get() && blockEntity instanceof Scalable scalable) {
			final float dx = (float)scalable.skullcraft$getScaleX() / 100.0F;
			final float dy = (float)scalable.skullcraft$getScaleY() / 100.0F;
			final float dz = (float)scalable.skullcraft$getScaleZ() / 100.0F;
			poseStack.scale(dx, dy, dz);
			BlockState blockState = blockEntity.getBlockState();
			boolean flag = blockState.getBlock() instanceof WallSkullBlock;
			if(flag) {
				Direction direction = blockState.getValue(WallSkullBlock.FACING);
				poseStack.translate(
						(0.25F - dx * 0.25F) * (1.0F - direction.getStepX()),
						0.25F - dy * 0.25F,
						(0.25F - dz * 0.25F) * (1.0F - direction.getStepZ())
				);
			} else {
				poseStack.translate(0.25F - dx * 0.25F, 0.0F, 0.25F - dz * 0.25F);
			}
		}
	}
}
