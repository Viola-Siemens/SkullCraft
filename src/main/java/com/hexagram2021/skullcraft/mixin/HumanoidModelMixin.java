package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.model.HattedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin implements HattedModel {
	@Shadow @Final
	public ModelPart hat;

	@Override
	public ModelPart getHat() {
		return this.hat;
	}
}
