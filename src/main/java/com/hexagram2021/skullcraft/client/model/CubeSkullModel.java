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
 * 立方体形生物头颅模型，用于渲染史莱姆、烈焰人、蜘蛛、狼、末影人、雪傀儡、
 * 旋风人、Technoblade 等立方体形态生物的头颅方块喵~
 *
 * @author liudongyu
 */
@OnlyIn(Dist.CLIENT)
public class CubeSkullModel extends SkullModelBase implements IWallShiftSkullModel {
	public static final ModelLayerLocation SLIME_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "slime_head"), "main");
	public static final ModelLayerLocation LAVASLIME_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "lavaslime_head"), "main");
	public static final ModelLayerLocation BLAZE_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "blaze_head"), "main");
	public static final ModelLayerLocation SPIDER_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "spider_head"), "main");
	public static final ModelLayerLocation CAVE_SPIDER_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "cave_spider_head"), "main");
	public static final ModelLayerLocation PIG_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "pig_head"), "main");
	public static final ModelLayerLocation WOLF_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_head"), "main");
	public static final ModelLayerLocation WOLF_ASHEN_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_ashen_head"), "main");
	public static final ModelLayerLocation WOLF_BLACK_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_black_head"), "main");
	public static final ModelLayerLocation WOLF_CHESTNUT_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_chestnut_head"), "main");
	public static final ModelLayerLocation WOLF_RUSTY_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_rusty_head"), "main");
	public static final ModelLayerLocation WOLF_SNOWY_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_snowy_head"), "main");
	public static final ModelLayerLocation WOLF_SPOTTED_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_spotted_head"), "main");
	public static final ModelLayerLocation WOLF_STRIPED_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_striped_head"), "main");
	public static final ModelLayerLocation WOLF_WOODS_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_woods_head"), "main");
	public static final ModelLayerLocation ANGRY_WOLF_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "angry_wolf_head"), "main");
	public static final ModelLayerLocation ANGRY_WOLF_ASHEN_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "angry_wolf_ashen_head"), "main");
	public static final ModelLayerLocation ANGRY_WOLF_BLACK_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "angry_wolf_black_head"), "main");
	public static final ModelLayerLocation ANGRY_WOLF_CHESTNUT_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "angry_wolf_chestnut_head"), "main");
	public static final ModelLayerLocation ANGRY_WOLF_RUSTY_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "angry_wolf_rusty_head"), "main");
	public static final ModelLayerLocation ANGRY_WOLF_SNOWY_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "angry_wolf_snowy_head"), "main");
	public static final ModelLayerLocation ANGRY_WOLF_SPOTTED_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "angry_wolf_spotted_head"), "main");
	public static final ModelLayerLocation ANGRY_WOLF_STRIPED_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "angry_wolf_striped_head"), "main");
	public static final ModelLayerLocation ANGRY_WOLF_WOODS_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "angry_wolf_woods_head"), "main");
	public static final ModelLayerLocation ENDERMAN_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "enderman_head"), "main");
	public static final ModelLayerLocation SNOW_GOLEM_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "snow_golem_head"), "main");
	public static final ModelLayerLocation BREEZE_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "breeze_head"), "main");
	public static final ModelLayerLocation TECHNOBLADE_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "technoblade_head"), "main");

	public CubeSkullModel(ModelPart root) {
		super(root);
	}

	private static MeshDefinition createHeadModel() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
				PartPose.ZERO);
		return meshdefinition;
	}

	/**
	 * 创建史莱姆头颅模型层定义喵~
	 *
	 * @return 史莱姆头颅的层定义喵~
	 */
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

	/**
	 * 创建岩浆怪头颅模型层定义喵~
	 *
	 * @return 岩浆怪头颅的层定义喵~
	 */
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

			partdefinition.addOrReplaceChild("cube" + i, CubeListBuilder.create().texOffs(j, k).addBox(-4.0F, i - 8.0F, -4.0F, 8.0F, 1.0F, 8.0F), PartPose.ZERO);
		}

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	/**
	 * 创建蜘蛛类头颅模型层定义喵~
	 *
	 * @return 蜘蛛头颅的层定义喵~
	 */
	public static LayerDefinition createSpiderHeadLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(32, 4).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	/**
	 * 创建猪头颅模型层定义喵~
	 *
	 * @return 猪头颅的层定义喵~
	 */
	public static LayerDefinition createPigHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");

		head.addOrReplaceChild("nose",
				CubeListBuilder.create().texOffs(16, 16).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 3.0F, 1.0F, CubeDeformation.NONE),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	/**
	 * 创建狼头颅模型层定义喵~
	 *
	 * @return 狼头颅的层定义喵~
	 */
	public static LayerDefinition createWolfHeadLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-3.0F, -7.0F, 0.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(1.0F))
						.texOffs(16, 14).addBox(-3.5F, -10.5F, 3.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.5F))
						.texOffs(16, 14).addBox(1.5F, -10.5F, 3.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.5F))
						.texOffs(0, 10).addBox(-1.5F, -3.752F, -5.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.5F)),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	/**
	 * 创建末影人头颅模型层定义喵~
	 *
	 * @return 末影人头颅的层定义喵~
	 */
	public static LayerDefinition createEndermanHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");

		head.addOrReplaceChild("mouse",
				CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	/**
	 * 创建雪傀儡头颅模型层定义喵~
	 *
	 * @return 雪傀儡头颅的层定义喵~
	 */
	public static LayerDefinition createSnowGolemHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	/**
	 * 创建旋风人头颅模型层定义喵~
	 *
	 * @return 旋风人头颅的层定义喵~
	 */
	public static LayerDefinition createBreezeHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	/**
	 * 创建 Technoblade 头颅模型层定义喵~
	 *
	 * @return Technoblade 头颅的层定义喵~
	 */
	public static LayerDefinition createTechnobladeHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");
		head.addOrReplaceChild("hat",
				CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
				PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	/**
	 * 创建通用立方体生物头颅模型层定义喵~
	 *
	 * @return 通用立方体生物头颅的层定义喵~
	 */
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
	public float getWallSkullZShift() {
		return 0.0F;
	}
}
