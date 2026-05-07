package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.client.model.IWallShiftSkullModel;
import com.hexagram2021.skullcraft.common.block.IScalableBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkullBlockRenderer.class)
public class SkullBlockRendererMixin {
	@Inject(method = "render(Lnet/minecraft/world/level/block/entity/SkullBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/SkullBlockRenderer;renderSkull(Lnet/minecraft/core/Direction;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/SkullModelBase;Lnet/minecraft/client/renderer/RenderType;)V", shift = At.Shift.BEFORE))
	public void skullcraft$renderScale(SkullBlockEntity blockEntity, float partialTick, PoseStack poseStack,
									   MultiBufferSource bufferSource, int packedLight, int packedOverlay,
									   CallbackInfo ci) {
		if(SCClientConfig.ENABLE_CUSTOM_SKULL_SIZE.get() && blockEntity instanceof IScalableBlockEntity scalable) {
			float dx = scalable.skullcraft$getScaleX() / 100.0F;
			float dy = scalable.skullcraft$getScaleY() / 100.0F;
			float dz = scalable.skullcraft$getScaleZ() / 100.0F;
			BlockState blockState = blockEntity.getBlockState();
			if(blockState.getBlock() instanceof WallSkullBlock) {
				Direction direction = blockState.getValue(WallSkullBlock.FACING);
				if(direction.getAxis().equals(Direction.Axis.X)) {
					float d = dx;
					dx = dz;
					dz = d;
				}
				poseStack.translate(
						(0.5F - dx * 0.5F) * (1.0F - direction.getStepX()),
						0.5F - dy * 0.5F,
						(0.5F - dz * 0.5F) * (1.0F - direction.getStepZ())
				);
			} else {
				float theta = RotationSegment.convertToDegrees(blockState.getValue(SkullBlock.ROTATION)) * Mth.DEG_TO_RAD;
				float cos = Mth.cos(theta);
				float sin = Mth.sin(theta);
				float d = dx;
				dx = d * cos * cos + dz * sin * sin;
				dz = d * sin * sin + dz * cos * cos;
				poseStack.translate(0.5F - dx * 0.5F, 0.0F, 0.5F - dz * 0.5F);
			}
			poseStack.scale(dx, dy, dz);
		}
	}

	@Inject(method = "renderSkull", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V", ordinal = 1, shift = At.Shift.AFTER))
	private static void skullcraft$wallSkullZShift(Direction direction, float yRot, float mouthAnimation, PoseStack poseStack,
												   MultiBufferSource bufferSource, int packedLight, SkullModelBase model, RenderType renderType,
												   CallbackInfo ci) {
		if(model instanceof IWallShiftSkullModel shiftSkullModel) {
			poseStack.translate(
					-shiftSkullModel.getWallSkullZShift() * direction.getStepX() * 0.5F,
					0.0F,
					-shiftSkullModel.getWallSkullZShift() * direction.getStepZ() * 0.5F
			);
		}
	}
}
