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
public class CowSkullModel extends SkullModelBase {
	public static final ModelLayerLocation COW_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "cow_head"), "main");
	public static final ModelLayerLocation RED_MOOSHROOM_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "red_mooshroom_head"), "main");
	public static final ModelLayerLocation BROWN_MOOSHROOM_HEAD = new ModelLayerLocation(new ResourceLocation(MODID, "brown_mooshroom_head"), "main");

	private final ModelPart root;

	public CowSkullModel(ModelPart root) {
		this.root = root;
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
	public void renderToBuffer(@NotNull PoseStack transform, @NotNull VertexConsumer consumer,
							   int x, int y, float r, float g, float b, float a) {
		this.root.render(transform, consumer, x, y, r, g, b, a);
	}
}
