package com.hexagram2021.skullcraft.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * 带帽子模型接口，提供获取帽子模型部件的访问方法喵~
 *
 * @author liudongyu
 */
@OnlyIn(Dist.CLIENT)
public interface HattedModel {
	/**
	 * 获取帽子模型部件喵~
	 *
	 * @return 帽子模型部件喵~
	 */
	ModelPart skullcraft$getHat();

	/**
	 * 获取帽子边缘模型部件，可能为 {@code null} 喵~
	 *
	 * @return 帽子边缘模型部件，可能为 {@code null} 喵~
	 */
	@Nullable
	ModelPart skullcraft$getHatRim();
}
