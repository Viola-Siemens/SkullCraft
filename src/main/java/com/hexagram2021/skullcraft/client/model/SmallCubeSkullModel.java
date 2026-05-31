package com.hexagram2021.skullcraft.client.model;

import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import static com.hexagram2021.skullcraft.SkullCraft.MODID;

/**
 * 小型立方体形生物头颅模型，用于渲染绵羊、蝙蝠、潜影贝、悦灵和恼鬼的头颅方块喵~
 *
 * @author liudongyu
 */
@OnlyIn(Dist.CLIENT)
public class SmallCubeSkullModel extends SkullModelBase implements IWallShiftSkullModel {
	public static final ModelLayerLocation SHEEP_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "sheep_head"), "main");
	public static final ModelLayerLocation BAT_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "bat_head"), "main");
	public static final ModelLayerLocation SHULKER_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "shulker_head"), "main");
	public static final ModelLayerLocation ALLAY_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "allay_head"), "main");
	public static final ModelLayerLocation VEX_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "vex_head"), "main");

	protected final ModelPart head;

	public SmallCubeSkullModel(ModelPart root) {
		super(root);
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

	/**
	 * 创建绵羊头颅模型层定义喵~
	 *
	 * @return 绵羊头颅的层定义喵~
	 */
	public static LayerDefinition createSheepHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel(0, 32);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");
		head.addOrReplaceChild("face",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -12.0F, 0.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(-0.6F)),
				PartPose.offset(0.0F, 6.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	/**
	 * 创建蝙蝠头颅模型层定义喵~
	 *
	 * @return 蝙蝠头颅的层定义喵~
	 */
	public static LayerDefinition createBatHeadLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 3.0F, 2.0F),
				PartPose.ZERO);

		head.addOrReplaceChild("right_ear",
				CubeListBuilder.create().texOffs(1, 15).addBox(-2.5F, -4.0F, 0.0F, 3.0F, 5.0F, 0.0F),
				PartPose.offset(-1.5F, -2.0F, 0.0F));
		head.addOrReplaceChild("left_ear",
				CubeListBuilder.create().texOffs(8, 15).addBox(-0.1F, -3.0F, 0.0F, 3.0F, 5.0F, 0.0F),
				PartPose.offset(1.1F, -3.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	/**
	 * 创建潜影贝头颅模型层定义喵~
	 *
	 * @return 潜影贝头颅的层定义喵~
	 */
	public static LayerDefinition createShulkerHeadLayer() {
		return LayerDefinition.create(createHeadModel(0, 52), 64, 64);
	}

	/**
	 * 创建悦灵类头颅模型层定义喵~
	 *
	 * @return 悦灵类头颅的层定义喵~
	 */
	public static LayerDefinition createAllayHeadLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.5F)),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 32, 32);
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
