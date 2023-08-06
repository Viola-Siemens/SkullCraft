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
public class HorseSkullModel extends SkullModelBase {
	public static final ModelLayerLocation BLACK_HORSE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "black_horse_head"), "main");
	public static final ModelLayerLocation BROWN_HORSE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "brown_horse_head"), "main");
	public static final ModelLayerLocation CHESTNUT_HORSE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "chestnut_horse_head"), "main");
	public static final ModelLayerLocation CREAMY_HORSE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "creamy_horse_head"), "main");
	public static final ModelLayerLocation DARKBROWN_HORSE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "darkbrown_horse_head"), "main");
	public static final ModelLayerLocation GRAY_HORSE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "gray_horse_head"), "main");
	public static final ModelLayerLocation WHITE_HORSE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "white_horse_head"), "main");
	public static final ModelLayerLocation DONKEY_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "donkey_head"), "main");
	public static final ModelLayerLocation MULE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "mule_head"), "main");
	public static final ModelLayerLocation SKELETON_HORSE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "skeleton_horse_head"), "main");
	public static final ModelLayerLocation ZOMBIE_HORSE_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "zombie_horse_head"), "main");

	private final ModelPart root;
	protected final ModelPart head;

	public HorseSkullModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head_parts");
	}

	private static MeshDefinition createHeadModel() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition headParts = partdefinition.addOrReplaceChild(
				"head_parts",
				CubeListBuilder.create().texOffs(0, 35).addBox(-2.05F, -6.0F, -4.0F, 4.0F, 4.0F, 7.0F),
				PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, ((float)Math.PI / 6F), 0.0F, 0.0F)
		);
		headParts.addOrReplaceChild(
				"head",
				CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -11.0F, -4.0F, 6.0F, 5.0F, 7.0F),
				PartPose.ZERO
		);
		headParts.addOrReplaceChild(
				"mane",
				CubeListBuilder.create().texOffs(56, 36).addBox(-1.0F, -11.0F, 3.01F, 2.0F, 8.0F, 2.0F),
				PartPose.ZERO
		);
		headParts.addOrReplaceChild(
				"upper_mouth",
				CubeListBuilder.create().texOffs(0, 25).addBox(-2.0F, -11.0F, -9.0F, 4.0F, 5.0F, 5.0F),
				PartPose.ZERO
		);
		return meshdefinition;
	}

	public static LayerDefinition createHorseHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		PartDefinition head = meshdefinition.getRoot().getChild("head_parts").getChild("head");

		head.addOrReplaceChild(
				"left_ear",
				CubeListBuilder.create().texOffs(19, 16).addBox(0.55F, -13.0F, 2.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)),
				PartPose.ZERO
		);
		head.addOrReplaceChild(
				"right_ear",
				CubeListBuilder.create().texOffs(19, 16).addBox(-2.55F, -13.0F, 2.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.001F)),
				PartPose.ZERO
		);
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createChestedHorseHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		PartDefinition head = meshdefinition.getRoot().getChild("head_parts").getChild("head");

		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 7.0F, 1.0F);
		head.addOrReplaceChild("left_ear", cubelistbuilder, PartPose.offsetAndRotation(1.25F, -10.0F, 4.0F, (float)Math.PI / 12F, 0.0F, (float)Math.PI / 12F));
		head.addOrReplaceChild("right_ear", cubelistbuilder, PartPose.offsetAndRotation(-1.25F, -10.0F, 4.0F, (float)Math.PI / 12F, 0.0F, -(float)Math.PI / 12F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(float tick, float yRot, float xRot) {
		this.head.yRot = yRot * ((float)Math.PI / 180F);
		this.head.xRot = xRot * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack transform, VertexConsumer consumer,
							   int x, int y, float r, float g, float b, float a) {
		this.root.render(transform, consumer, x, y, r, g, b, a);
	}
}
