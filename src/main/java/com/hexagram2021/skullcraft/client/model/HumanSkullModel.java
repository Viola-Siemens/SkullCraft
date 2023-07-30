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
public class HumanSkullModel extends SkullModelBase {
	public static final ModelLayerLocation VILLAGER_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "villager_head"), "main");
	public static final ModelLayerLocation ILLAGER_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "illager_head"), "main");
	public static final ModelLayerLocation WITCH_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "witch_head"), "main");
	public static final ModelLayerLocation IRON_GOLEM_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "iron_golem_head"), "main");
	public static final ModelLayerLocation ZOMBIE_VILLAGER_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "zombie_villager_head"), "main");

	private final ModelPart root;
	protected final ModelPart head;
	protected final ModelPart nose;

	public HumanSkullModel(ModelPart root) {
		this.root = root;
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

	public static LayerDefinition createMobHeadLayer() {
		MeshDefinition meshdefinition = createHeadModel();
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

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
	public void renderToBuffer(PoseStack transform, VertexConsumer consumer,
							   int x, int y, float r, float g, float b, float a) {
		this.root.render(transform, consumer, x, y, r, g, b, a);
	}
}
