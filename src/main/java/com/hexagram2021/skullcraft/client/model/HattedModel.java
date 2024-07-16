package com.hexagram2021.skullcraft.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public interface HattedModel {
	ModelPart skullcraft$getHat();

	@Nullable
	ModelPart skullcraft$getHatRim();
}
