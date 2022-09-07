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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

@OnlyIn(Dist.CLIENT)
public class WardenSkullModel extends SkullModelBase {
	public static final ModelLayerLocation WARDEN_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "warden_head"), "main");

	private final ModelPart root;

	public WardenSkullModel(ModelPart root) {
		this.root = root;
	}

	public static LayerDefinition createWardenHeadLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create()
						.texOffs(0, 32).addBox(-8.0F, -16.0F, -5.0F, 16.0F, 16.0F, 10.0F),
				PartPose.ZERO);
		head.addOrReplaceChild("left_tendril",
				CubeListBuilder.create()
						.texOffs(58, 0).addBox(0.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F),
				PartPose.offset(8.0F, -9.0F, 0.0F));
		head.addOrReplaceChild("right_tendril",
				CubeListBuilder.create()
						.texOffs(52, 32).addBox(-16.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F),
				PartPose.offset(-8.0F, -9.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}


	@Override
	public void setupAnim(float tick, float yRot, float xRot) {
		this.root.yRot = yRot * ((float)Math.PI / 180F);
		this.root.xRot = xRot * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack transform, @NotNull VertexConsumer consumer,
							   int x, int y, float r, float g, float b, float a) {
		this.root.render(transform, consumer, x, y, r, g, b, a);
	}
}
