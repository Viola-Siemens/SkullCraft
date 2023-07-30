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
public class SmallCubeSkullModel extends SkullModelBase {
	public static final ModelLayerLocation SHEEP_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "sheep_head"), "main");
	public static final ModelLayerLocation BAT_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "bat_head"), "main");
	public static final ModelLayerLocation SHULKER_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "shulker_head"), "main");
	public static final ModelLayerLocation ALLAY_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "allay_head"), "main");
	public static final ModelLayerLocation VEX_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "vex_head"), "main");

	private final ModelPart root;
	protected final ModelPart head;

	public SmallCubeSkullModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild("head");
	}

	@SuppressWarnings("SameParameterValue")
	private static MeshDefinition createHeadModel(int texOffX, int texOffY) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(texOffX, texOffY).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F),
				PartPose.ZERO);
		return meshdefinition;
	}

	public static LayerDefinition createSheepHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel(0, 32);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");
		head.addOrReplaceChild("face",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -12.0F, 0.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(-0.6F)),
				PartPose.offset(0.0F, 6.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createBatHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel(0, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");

		head.addOrReplaceChild("right_ear",
				CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("left_ear",
				CubeListBuilder.create().texOffs(24, 0).mirror().addBox(1.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createShulkerHeadLayer() {
		return LayerDefinition.create(createHeadModel(0, 52), 64, 64);
	}

	public static LayerDefinition createAllayHeadLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.5F)),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 32, 32);
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
