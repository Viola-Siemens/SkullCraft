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
 * 牛形生物头颅模型，用于渲染牛、哞菇等生物的头颅方块喵~
 *
 * @author liudongyu
 */
@OnlyIn(Dist.CLIENT)
public class CowSkullModel extends SkullModelBase implements IWallShiftSkullModel {
	public static final ModelLayerLocation COW_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "cow_head"), "main");
	public static final ModelLayerLocation RED_MOOSHROOM_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "red_mooshroom_head"), "main");
	public static final ModelLayerLocation BROWN_MOOSHROOM_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "brown_mooshroom_head"), "main");

	public CowSkullModel(ModelPart root) {
		super(root);
	}

	private static MeshDefinition createHeadModel() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 6.0F)
						.texOffs(22, 0).addBox("right_horn", -5.0F, -9.0F, -1.0F, 1.0F, 3.0F, 1.0F)
						.texOffs(22, 0).addBox("left_horn", 4.0F, -9.0F, -1.0F, 1.0F, 3.0F, 1.0F),
				PartPose.ZERO);
		return meshdefinition;
	}

	/**
	 * 创建牛形生物头颅模型
	 * @return 层片定义
	 */
	public static LayerDefinition createCowHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(float tick, float yRot, float xRot) {
		this.root.yRot = yRot * ((float)Math.PI / 180F);
		this.root.xRot = xRot * ((float)Math.PI / 180F);
	}

	@Override
	public float getWallSkullZShift() {
		return 0.125F;
	}
}
