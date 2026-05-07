package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.client.model.HattedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(IllagerModel.class)
public class IllagerModelMixin implements HattedModel {
	@Shadow @Final
	private ModelPart hat;

	@Override
	public ModelPart skullcraft$getHat() {
		return this.hat;
	}

	@Override @Nullable
	public ModelPart skullcraft$getHatRim() {
		return null;
	}

	@Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/IllagerRenderState;)V", at = @At(value = "HEAD"))
	public void skullcraft$trySkipRenderHead(IllagerRenderState renderState, CallbackInfo ci) {
		ItemStack itemStack = renderState.headItem;
		HeadedModel model = (HeadedModel) this;
		if (itemStack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock) {
			if (SCClientConfig.HIDE_ORIGINAL_HEAD.get()) {
				model.getHead().skipDraw = true;
			}
			if (SCClientConfig.HIDE_ORIGINAL_HAT.get()) {
				this.skullcraft$getHat().skipDraw = true;
				ModelPart hat = this.skullcraft$getHatRim();
				if (hat != null) {
					hat.skipDraw = true;
				}
			}
		} else {
			if(SCClientConfig.HIDE_ORIGINAL_HEAD.get()) {
				model.getHead().skipDraw = false;
			}
			if(SCClientConfig.HIDE_ORIGINAL_HAT.get()) {
				this.skullcraft$getHat().skipDraw = false;
				ModelPart hat = this.skullcraft$getHatRim();
				if(hat != null) {
					hat.skipDraw = false;
				}
			}
		}
	}
}
