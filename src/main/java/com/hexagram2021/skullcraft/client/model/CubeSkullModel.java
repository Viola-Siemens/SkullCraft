package com.hexagram2021.skullcraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@OnlyIn(Dist.CLIENT)
public class CubeSkullModel extends SkullModelBase {
	public static final ModelLayerLocation SLIME_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "slime_head"), "main");
	public static final ModelLayerLocation LAVASLIME_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "lavaslime_head"), "main");
	public static final ModelLayerLocation BLAZE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "blaze_head"), "main");
	public static final ModelLayerLocation SPIDER_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "spider_head"), "main");
	public static final ModelLayerLocation CAVE_SPIDER_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "cave_spider_head"), "main");
	public static final ModelLayerLocation PIG_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "pig_head"), "main");
	public static final ModelLayerLocation ENDERMAN_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "enderman_head"), "main");
	public static final ModelLayerLocation SNOW_GOLEM_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "snow_golem_head"), "main");
	public static final ModelLayerLocation TECHNOBLADE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "technoblade_head"), "main");

	private final ModelPart root;

	public CubeSkullModel(ModelPart root) {
		this.root = root;
	}

	private static MeshDefinition createHeadModel() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
				PartPose.ZERO);
		return meshdefinition;
	}

	public static LayerDefinition createSlimeHeadLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("cubeOut", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("cubeIn", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(32, 0).addBox(-3.25F, -6.0F, -3.5F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(32, 4).addBox(1.25F, -6.0F, -3.5F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(32, 8).addBox(0.0F, -3.0F, -3.5F, 1.0F, 1.0F, 1.0F), PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public static LayerDefinition createLavaSlimeLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		for(int i = 0; i < 8; ++i) {
			int j = 0;
			int k = i;
			if (i == 2) {
				j = 24;
				k = 10;
			} else if (i == 3) {
				j = 24;
				k = 19;
			}

			partdefinition.addOrReplaceChild("cube" + i, CubeListBuilder.create().texOffs(j, k).addBox(-4.0F, (float)(i - 8), -4.0F, 8.0F, 1.0F, 8.0F), PartPose.ZERO);
		}

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public static LayerDefinition createSpiderHeadLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(32, 4).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public static LayerDefinition createPigHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");

		head.addOrReplaceChild("nose",
				CubeListBuilder.create().texOffs(16, 16).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 3.0F, 1.0F, CubeDeformation.NONE),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public static LayerDefinition createEndermanHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");

		head.addOrReplaceChild("mouse",
				CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public static LayerDefinition createSnowGolemHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createTechnobladeHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");
		head.addOrReplaceChild("hat",
				CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createMobHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(float tick, float yRot, float xRot) {
		this.root.yRot = yRot * ((float)Math.PI / 180F);
		this.root.xRot = xRot * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack transform, VertexConsumer consumer,
							   int x, int y, float r, float g, float b, float a) {
		this.root.render(transform, consumer, x, y, r, g, b, a);
	}
}
