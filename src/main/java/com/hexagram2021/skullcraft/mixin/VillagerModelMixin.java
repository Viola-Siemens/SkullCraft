package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.model.HattedModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VillagerModel.class)
public class VillagerModelMixin implements HattedModel {
	@Shadow @Final
	private ModelPart hat;

	@Shadow @Final private ModelPart hatRim;

	@Override
	public ModelPart getHat() {
		return this.hat;
	}

	@Override
	public ModelPart getHatRim() {
		return this.hatRim;
	}
}
