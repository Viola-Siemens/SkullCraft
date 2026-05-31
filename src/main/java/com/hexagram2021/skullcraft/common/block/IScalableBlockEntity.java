package com.hexagram2021.skullcraft.common.block;

/**
 * 可缩放方块实体接口，为头颅方块实体提供缩放比例的存取能力喵~
 *
 * @author liudongyu
 */
public interface IScalableBlockEntity {
	/**
	 * 设置头颅在 X、Y、Z 三个轴向上的缩放比例喵~
	 *
	 * @param scaleX X 轴缩放比例喵~
	 * @param scaleY Y 轴缩放比例喵~
	 * @param scaleZ Z 轴缩放比例喵~
	 */
	void skullcraft$setScaleXYZ(int scaleX, int scaleY, int scaleZ);

	/**
	 * 获取头颅在 X 轴上的缩放比例喵~
	 *
	 * @return X 轴缩放比例喵~
	 */
	int skullcraft$getScaleX();
	/**
	 * 获取头颅在 Y 轴上的缩放比例喵~
	 *
	 * @return Y 轴缩放比例喵~
	 */
	int skullcraft$getScaleY();
	/**
	 * 获取头颅在 Z 轴上的缩放比例喵~
	 *
	 * @return Z 轴缩放比例喵~
	 */
	int skullcraft$getScaleZ();
}
