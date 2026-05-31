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
 * 人形生物头颅模型，用于渲染村民、灾厄村民、女巫、铁傀儡和僵尸村民的头颅方块喵~
 *
 * @author liudongyu
 */
@OnlyIn(Dist.CLIENT)
public class HumanSkullModel extends SkullModelBase implements IWallShiftSkullModel {
	public static final ModelLayerLocation VILLAGER_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "villager_head"), "main");
	public static final ModelLayerLocation ILLAGER_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "illager_head"), "main");
	public static final ModelLayerLocation WITCH_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "witch_head"), "main");
	public static final ModelLayerLocation IRON_GOLEM_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "iron_golem_head"), "main");
	public static final ModelLayerLocation ZOMBIE_VILLAGER_HEAD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, "zombie_villager_head"), "main");

	protected final ModelPart head;
	protected final ModelPart nose;

	public HumanSkullModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.nose = this.head.getChild("nose");
	}

	private static MeshDefinition createHeadModel() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F),
				PartPose.ZERO);
		partdefinition.getChild("head").addOrReplaceChild("nose",
				CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F),
				PartPose.offset(0.0F, -2.0F, 0.0F));
		return meshdefinition;
	}

	/**
	 * 创建女巫头颅模型层定义喵~
	 *
	 * @return 女巫头颅的层定义喵~
	 */
	public static LayerDefinition createWitchHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");

		head.getChild("nose").addOrReplaceChild("mole",
				CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 3.0F, -6.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)),
				PartPose.offset(0.0F, -2.0F, 0.0F));
		PartDefinition hat = head.addOrReplaceChild("hat",
				CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, 0.0F, 0.0F, 10.0F, 2.0F, 10.0F),
				PartPose.offset(-5.0F, -10.03125F, -5.0F));
		PartDefinition hat2 = hat.addOrReplaceChild("hat2",
				CubeListBuilder.create().texOffs(0, 76).addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F),
				PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.05235988F, 0.0F, 0.02617994F));
		PartDefinition hat3 = hat2.addOrReplaceChild("hat3",
				CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F),
				PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.10471976F, 0.0F, 0.05235988F));
		hat3.addOrReplaceChild("hat4",
				CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(1.75F, -2.0F, 2.0F, -0.20943952F, 0.0F, 0.10471976F));
		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	/**
	 * 创建通用人形生物头颅模型层定义喵~
	 *
	 * @return 通用人形生物头颅的层定义喵~
	 */
	public static LayerDefinition createMobHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	/**
	 * 创建铁傀儡头颅模型层定义喵~
	 *
	 * @return 铁傀儡头颅的层定义喵~
	 */
	public static LayerDefinition createIronGolemHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(float tick, float yRot, float xRot) {
		this.head.yRot = yRot * ((float)Math.PI / 180F);
		this.head.xRot = xRot * ((float)Math.PI / 180F);
	}

	@Override
	public float getWallSkullZShift() {
		return 0.0F;
	}
}
