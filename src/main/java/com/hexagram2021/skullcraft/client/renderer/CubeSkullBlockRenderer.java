package com.hexagram2021.skullcraft.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.hexagram2021.skullcraft.client.model.CubeSkullModel;
import com.hexagram2021.skullcraft.common.block.CubeSkull.CubeSkullBlock;
import com.hexagram2021.skullcraft.common.block.CubeSkull.CubeWallSkullBlock;
import com.hexagram2021.skullcraft.common.block.entity.CubeSkullBlockEntity;
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

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@OnlyIn(Dist.CLIENT)
public class CubeSkullBlockRenderer implements BlockEntityRenderer<CubeSkullBlockEntity> {
	private final Map<SkullBlock.Type, SkullModelBase> modelByType;
	public static final Map<SkullBlock.Type, ResourceLocation> SKIN_BY_TYPE = Util.make(Maps.newHashMap(), (map) -> {
		map.put(CubeSkullBlock.Types.SLIME, new ResourceLocation("textures/entity/slime/slime.png"));
		map.put(CubeSkullBlock.Types.LAVASLIME, new ResourceLocation("textures/entity/slime/magmacube.png"));
		map.put(CubeSkullBlock.Types.BLAZE, new ResourceLocation("textures/entity/blaze.png"));
		map.put(CubeSkullBlock.Types.SPIDER, new ResourceLocation("textures/entity/spider/spider.png"));
		map.put(CubeSkullBlock.Types.CAVE_SPIDER, new ResourceLocation("textures/entity/spider/cave_spider.png"));
		map.put(CubeSkullBlock.Types.PIG, new ResourceLocation("textures/entity/pig/pig.png"));
		map.put(CubeSkullBlock.Types.ENDERMAN, new ResourceLocation("textures/entity/enderman/enderman.png"));
		map.put(CubeSkullBlock.Types.SNOW_GOLEM, new ResourceLocation("textures/entity/snow_golem.png"));
		map.put(CubeSkullBlock.Types.TECHNOBLADE, new ResourceLocation(MODID, "textures/entity/technoblade.png"));
	});

	public static Map<SkullBlock.Type, SkullModelBase> createSkullRenderers(EntityModelSet set) {
		ImmutableMap.Builder<SkullBlock.Type, SkullModelBase> builder = ImmutableMap.builder();
		builder.put(CubeSkullBlock.Types.SLIME, new CubeSkullModel(set.bakeLayer(CubeSkullModel.SLIME_HEAD)));
		builder.put(CubeSkullBlock.Types.LAVASLIME, new CubeSkullModel(set.bakeLayer(CubeSkullModel.LAVASLIME_HEAD)));
		builder.put(CubeSkullBlock.Types.BLAZE, new CubeSkullModel(set.bakeLayer(CubeSkullModel.BLAZE_HEAD)));
		builder.put(CubeSkullBlock.Types.SPIDER, new CubeSkullModel(set.bakeLayer(CubeSkullModel.SPIDER_HEAD)));
		builder.put(CubeSkullBlock.Types.CAVE_SPIDER, new CubeSkullModel(set.bakeLayer(CubeSkullModel.CAVE_SPIDER_HEAD)));
		builder.put(CubeSkullBlock.Types.PIG, new CubeSkullModel(set.bakeLayer(CubeSkullModel.PIG_HEAD)));
		builder.put(CubeSkullBlock.Types.ENDERMAN, new CubeSkullModel(set.bakeLayer(CubeSkullModel.ENDERMAN_HEAD)));
		builder.put(CubeSkullBlock.Types.SNOW_GOLEM, new CubeSkullModel(set.bakeLayer(CubeSkullModel.SNOW_GOLEM_HEAD)));
		builder.put(CubeSkullBlock.Types.TECHNOBLADE, new CubeSkullModel(set.bakeLayer(CubeSkullModel.TECHNOBLADE_HEAD)));
		return builder.build();
	}

	public CubeSkullBlockRenderer(BlockEntityRendererProvider.Context context) {
		this.modelByType = createSkullRenderers(context.getModelSet());
	}

	@Override
	public void render(CubeSkullBlockEntity entity, float tick, @NotNull PoseStack transform, @NotNull MultiBufferSource source, int uv2, int y) {
		BlockState blockstate = entity.getBlockState();
		boolean flag = blockstate.getBlock() instanceof CubeWallSkullBlock;
		Direction direction = flag ? blockstate.getValue(CubeWallSkullBlock.FACING) : null;
		float f1 = 22.5F * (float)(flag ? (2 + direction.get2DDataValue()) * 4 : blockstate.getValue(CubeSkullBlock.ROTATION));
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
