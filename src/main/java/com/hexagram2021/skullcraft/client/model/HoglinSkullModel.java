package com.hexagram2021.skullcraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

public class HoglinSkullModel extends SkullModelBase implements IWallShiftSkullModel {
	public static final ModelLayerLocation HOGLIN_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "hoglin_head"), "main");
	public static final ModelLayerLocation ZOGLIN_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "zoglin_head"), "main");

	private final ModelPart root;

	public HoglinSkullModel(ModelPart root) {
		this.root = root;
	}

	public static LayerDefinition createHeadLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(61, 1).addBox(-7.0F, -3.0F, -19.0F, 14.0F, 6.0F, 19.0F),
				PartPose.offsetAndRotation(0.0F, -14.0F, 5.0F, Mth.DEG_TO_RAD * 50.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("right_ear",
				CubeListBuilder.create().texOffs(1, 1).addBox(-6.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F),
				PartPose.offsetAndRotation(-6.0F, -2.0F, -3.0F, 0.0F, 0.0F, -Mth.DEG_TO_RAD * 50.0F));
		head.addOrReplaceChild("left_ear",
				CubeListBuilder.create().texOffs(1, 6).addBox(0.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F),
				PartPose.offsetAndRotation(6.0F, -2.0F, -3.0F, 0.0F, 0.0F, Mth.DEG_TO_RAD * 50.0F));
		head.addOrReplaceChild("right_horn",
				CubeListBuilder.create().texOffs(10, 13).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 11.0F, 2.0F),
				PartPose.offset(-7.0F, 2.0F, -12.0F));
		head.addOrReplaceChild("left_horn",
				CubeListBuilder.create().texOffs(1, 13).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 11.0F, 2.0F),
				PartPose.offset(7.0F, 2.0F, -12.0F));
		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(float tick, float yRot, float xRot) {
		this.root.yRot = yRot * ((float)Math.PI / 180F);
		this.root.xRot = xRot * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack transform, VertexConsumer consumer, int x, int y, int rgba) {
		this.root.render(transform, consumer, x, y, rgba);
	}

	@Override
	public float getWallSkullZShift() {
		return 0.0F;
	}
}
