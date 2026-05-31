package com.hexagram2021.skullcraft.client.model;

import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 监守者头颅模型，用于渲染监守者的头颅方块喵~
 *
 * @author liudongyu
 */
@OnlyIn(Dist.CLIENT)
public class WardenSkullModel extends SkullModelBase implements IWallShiftSkullModel {
	public static final ModelLayerLocation WARDEN_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "warden_head"), "main");

	public WardenSkullModel(ModelPart root) {
		super(root);
	}

	/**
	 * 创建监守者头颅模型层定义喵~
	 *
	 * @return 监守者头颅的层定义喵~
	 */
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
	public float getWallSkullZShift() {
		return -0.125F;
	}
}
