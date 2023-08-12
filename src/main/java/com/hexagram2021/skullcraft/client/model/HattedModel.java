package com.hexagram2021.skullcraft.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public interface HattedModel {
	ModelPart getHat();

	@Nullable
	ModelPart getHatRim();
}
