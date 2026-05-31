package com.hexagram2021.skullcraft.common.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;

/**
 * 头颅缩放比例数据组件，记录头颅在 X、Y、Z 三个轴向上的缩放百分比喵~
 * 默认值为 100（即 100%，不缩放），范围限制在 50 到 5000 之间喵~
 *
 * @param x X 轴缩放比例喵~
 * @param y Y 轴缩放比例喵~
 * @param z Z 轴缩放比例喵~
 * @author liudongyu
 */
public record SkullScale(int x, int y, int z) implements Comparable<SkullScale> {
	/** 缩放比例的编解码器喵~ */
	public static final Codec<SkullScale> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.INT.optionalFieldOf("x", 100).forGetter(SkullScale::x),
			Codec.INT.optionalFieldOf("y", 100).forGetter(SkullScale::y),
			Codec.INT.optionalFieldOf("z", 100).forGetter(SkullScale::z)
	).apply(instance, SkullScale::new));
	/** 缩放比例的网络流编解码器喵~ */
	public static final StreamCodec<ByteBuf, SkullScale> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.INT, SkullScale::x,
			ByteBufCodecs.INT, SkullScale::y,
			ByteBufCodecs.INT, SkullScale::z,
			SkullScale::new
	);
	/** 默认缩放比例（100%，不缩放）喵~ */
	public static final SkullScale DEFAULT = new SkullScale(100, 100, 100);

	/**
	 * 缩放比例构造方法，将缩放比例限制在 50 到 5000 之间喵~
	 * @param x 左右宽度
	 * @param y 上下高度
	 * @param z 前后长度
	 */
	public SkullScale(int x, int y, int z) {
		this.x = Mth.clamp(x, 50, 5000);
		this.y = Mth.clamp(y, 50, 5000);
		this.z = Mth.clamp(z, 50, 5000);
	}

	/**
	 * 在当前缩放比例基础上增加指定值，返回新的缩放比例喵~
	 *
	 * @param x X 轴增量喵~
	 * @param y Y 轴增量喵~
	 * @param z Z 轴增量喵~
	 * @return 新的缩放比例喵~
	 */
	public SkullScale add(int x, int y, int z) {
		return new SkullScale(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public int compareTo(SkullScale o) {
		if(this.z == o.z) {
			if(this.y == o.y) {
				return this.x - o.x;
			}
			return this.y - o.y;
		}
		return this.z - o.z;
	}
}
