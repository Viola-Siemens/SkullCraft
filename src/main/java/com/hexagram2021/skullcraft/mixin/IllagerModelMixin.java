package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.model.HattedModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(IllagerModel.class)
public class IllagerModelMixin implements HattedModel {
	@Shadow @Final
	private ModelPart hat;

	@Override
	public ModelPart getHat() {
		return this.hat;
	}
}
