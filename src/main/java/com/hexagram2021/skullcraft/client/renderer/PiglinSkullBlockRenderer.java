package com.hexagram2021.skullcraft.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.hexagram2021.skullcraft.client.model.PiglinSkullModel;
import com.hexagram2021.skullcraft.common.block.PiglinSkull.PiglinSkullBlock;
import com.hexagram2021.skullcraft.common.block.PiglinSkull.PiglinWallSkullBlock;
import com.hexagram2021.skullcraft.common.block.entity.PiglinSkullBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class PiglinSkullBlockRenderer implements BlockEntityRenderer<PiglinSkullBlockEntity> {
	private final Map<SkullBlock.Type, SkullModelBase> modelByType;
	public static final Map<SkullBlock.Type, ResourceLocation> SKIN_BY_TYPE = Util.make(Maps.newHashMap(), (map) -> {
		map.put(PiglinSkullBlock.Types.PIGLIN_BRUTE, new ResourceLocation("textures/entity/piglin/piglin_brute.png"));
		map.put(PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN, new ResourceLocation("textures/entity/piglin/zombified_piglin.png"));
	});

	public static Map<SkullBlock.Type, SkullModelBase> createSkullRenderers(EntityModelSet set) {
		ImmutableMap.Builder<SkullBlock.Type, SkullModelBase> builder = ImmutableMap.builder();
		builder.put(PiglinSkullBlock.Types.PIGLIN_BRUTE, new PiglinSkullModel(set.bakeLayer(PiglinSkullModel.PIGLIN_BRUTE_HEAD)));
		builder.put(PiglinSkullBlock.Types.ZOMBIFIED_PIGLIN, new PiglinSkullModel(set.bakeLayer(PiglinSkullModel.ZOMBIFIED_PIGLIN_HEAD)));
		return builder.build();
	}

	public PiglinSkullBlockRenderer(BlockEntityRendererProvider.Context context) {
		this.modelByType = createSkullRenderers(context.getModelSet());
	}

	@Override
	public void render(PiglinSkullBlockEntity entity, float tick, @NotNull PoseStack transform, @NotNull MultiBufferSource source, int uv2, int y) {
		BlockState blockstate = entity.getBlockState();
		boolean flag = blockstate.getBlock() instanceof PiglinWallSkullBlock;
		Direction direction = flag ? blockstate.getValue(PiglinWallSkullBlock.FACING) : null;
		float f1 = 22.5F * (float)(flag ? (2 + direction.get2DDataValue()) * 4 : blockstate.getValue(PiglinSkullBlock.ROTATION));
		SkullBlock.Type skullType = ((AbstractSkullBlock)blockstate.getBlock()).getType();
		SkullModelBase skullmodelbase = this.modelByType.get(skullType);
		RenderType rendertype = getRenderType(skullType);
		renderSkull(direction, f1, 0.0F, transform, source, uv2, skullmodelbase, rendertype);
	}

	public static void renderSkull(@Nullable Direction direction, float yRot, float tick, PoseStack transform, MultiBufferSource source,
								   int uv2, SkullModelBase model, RenderType renderType) {
		transform.pushPose();
		if (direction == null) {
			transform.translate(0.5D, 0.0D, 0.5D);
		} else {
			transform.translate(0.5F - (float)direction.getStepX() * 0.25F, 0.25D, 0.5F - (float)direction.getStepZ() * 0.25F);
		}

		transform.scale(-1.0F, -1.0F, 1.0F);
		VertexConsumer vertexconsumer = source.getBuffer(renderType);
		model.setupAnim(tick, yRot, 0.0F);
		model.renderToBuffer(transform, vertexconsumer, uv2, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		transform.popPose();
	}

	public static RenderType getRenderType(SkullBlock.Type skullType) {
		ResourceLocation skin = SKIN_BY_TYPE.get(skullType);
		return RenderType.entityCutoutNoCullZOffset(skin);
	}

	static {
		SkullBlockRenderer.SKIN_BY_TYPE.putAll(SKIN_BY_TYPE);
	}
}
