package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.client.model.HattedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
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

/**
 * 人形生物模型 Mixin，实现 {@link HattedModel} 接口并提供穿戴头颅时隐藏原版头部/帽子的功能喵~
 */
@Mixin(HumanoidModel.class)
public class HumanoidModelMixin implements HattedModel {
	/** 帽子 */
	@Shadow @Final
	public ModelPart hat;

	@Override
	public ModelPart skullcraft$getHat() {
		return this.hat;
	}

	@Override @Nullable
	public ModelPart skullcraft$getHatRim() {
		return null;
	}

	/**
	 * 尝试隐藏原版头部/帽子
	 * @param renderState 渲染状态
	 * @param ci 回调信息
	 */
	@Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V", at = @At(value = "HEAD"))
	public void skullcraft$trySkipRenderHead(HumanoidRenderState renderState, CallbackInfo ci) {
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
