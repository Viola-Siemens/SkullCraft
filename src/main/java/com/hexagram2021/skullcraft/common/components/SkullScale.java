package com.hexagram2021.skullcraft.common.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;

public record SkullScale(int x, int y, int z) implements Comparable<SkullScale> {
	public static final Codec<SkullScale> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.INT.optionalFieldOf("x", 100).forGetter(SkullScale::x),
			Codec.INT.optionalFieldOf("y", 100).forGetter(SkullScale::y),
			Codec.INT.optionalFieldOf("z", 100).forGetter(SkullScale::z)
	).apply(instance, SkullScale::new));
	public static final StreamCodec<ByteBuf, SkullScale> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.INT, SkullScale::x,
			ByteBufCodecs.INT, SkullScale::y,
			ByteBufCodecs.INT, SkullScale::z,
			SkullScale::new
	);
	public static final SkullScale DEFAULT = new SkullScale(100, 100, 100);

	public SkullScale(int x, int y, int z) {
		this.x = Mth.clamp(x, 50, 5000);
		this.y = Mth.clamp(y, 50, 5000);
		this.z = Mth.clamp(z, 50, 5000);
	}

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
