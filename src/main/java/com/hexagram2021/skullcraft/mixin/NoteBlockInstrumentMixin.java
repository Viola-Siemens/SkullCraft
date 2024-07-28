package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.common.SCSounds;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

import static com.hexagram2021.skullcraft.common.register.SCNoteBlockInstruments.*;

@SuppressWarnings("unused")
@Mixin(NoteBlockInstrument.class)
public class NoteBlockInstrumentMixin {
	NoteBlockInstrumentMixin(String enumName, int ord, String name, Holder<SoundEvent> sound, NoteBlockInstrument.Type type) {
		throw new UnsupportedOperationException("Replaced by Mixin");
	}

	@Shadow @Mutable @Final
	private static NoteBlockInstrument[] $VALUES;

	@Unique
	@SuppressWarnings("SameParameterValue")
	private static NoteBlockInstrument skullcraft$createSkullInstrument(String enumName, int ord, String name, SoundEvent soundEvent) {
		return (NoteBlockInstrument)(Object)new NoteBlockInstrumentMixin(
				"SKULLCRAFT$" + enumName, ord, "skullcraft_" + name, Holder.direct(soundEvent), NoteBlockInstrument.Type.MOB_HEAD
		);
	}

	@Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;$VALUES:[Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;", shift = At.Shift.AFTER))
	private static void skullcraft$injectEnum(CallbackInfo ci) {
		int ordinal = $VALUES.length;
		$VALUES = Arrays.copyOf($VALUES, ordinal + 31);


		NOTE_BLOCK_IMITATE_VILLAGER = $VALUES[ordinal] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_VILLAGER", ordinal, "villager", SCSounds.NOTE_BLOCK_IMITATE_VILLAGER);
		NOTE_BLOCK_IMITATE_ILLAGER = $VALUES[ordinal + 1] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_ILLAGER", ordinal + 1, "illager", SCSounds.NOTE_BLOCK_IMITATE_ILLAGER);
		NOTE_BLOCK_IMITATE_WITCH = $VALUES[ordinal + 2] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_WITCH", ordinal + 2, "witch", SCSounds.NOTE_BLOCK_IMITATE_WITCH);
		NOTE_BLOCK_IMITATE_IRON_GOLEM = $VALUES[ordinal + 3] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_IRON_GOLEM", ordinal + 3, "iron_golem", SCSounds.NOTE_BLOCK_IMITATE_IRON_GOLEM);
		NOTE_BLOCK_IMITATE_ZOMBIE_VILLAGER = $VALUES[ordinal + 4] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_ZOMBIE_VILLAGER", ordinal + 4, "zombie_villager", SCSounds.NOTE_BLOCK_IMITATE_ZOMBIE_VILLAGER);
		NOTE_BLOCK_IMITATE_SLIME = $VALUES[ordinal + 5] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_SLIME", ordinal + 5, "slime", SCSounds.NOTE_BLOCK_IMITATE_SLIME);
		NOTE_BLOCK_IMITATE_LAVASLIME = $VALUES[ordinal + 6] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_LAVASLIME", ordinal + 6, "lavaslime", SCSounds.NOTE_BLOCK_IMITATE_LAVASLIME);
		NOTE_BLOCK_IMITATE_BLAZE = $VALUES[ordinal + 7] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_BLAZE", ordinal + 7, "blaze", SCSounds.NOTE_BLOCK_IMITATE_BLAZE);
		NOTE_BLOCK_IMITATE_SPIDER = $VALUES[ordinal + 8] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_SPIDER", ordinal + 8, "spider", SCSounds.NOTE_BLOCK_IMITATE_SPIDER);
		NOTE_BLOCK_IMITATE_PIG = $VALUES[ordinal + 9] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_PIG", ordinal + 9, "pig", SCSounds.NOTE_BLOCK_IMITATE_PIG);
		NOTE_BLOCK_IMITATE_WOLF = $VALUES[ordinal + 10] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_WOLF", ordinal + 10, "wolf", SCSounds.NOTE_BLOCK_IMITATE_WOLF);
		NOTE_BLOCK_IMITATE_ANGRY_WOLF = $VALUES[ordinal + 11] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_ANGRY_WOLF", ordinal + 11, "angry_wolf", SCSounds.NOTE_BLOCK_IMITATE_ANGRY_WOLF);
		NOTE_BLOCK_IMITATE_ENDERMAN = $VALUES[ordinal + 12] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_ENDERMAN", ordinal + 12, "enderman", SCSounds.NOTE_BLOCK_IMITATE_ENDERMAN);
		NOTE_BLOCK_IMITATE_SNOW_GOLEM = $VALUES[ordinal + 13] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_SNOW_GOLEM", ordinal + 13, "snow_golem", SCSounds.NOTE_BLOCK_IMITATE_SNOW_GOLEM);
		NOTE_BLOCK_IMITATE_TECHNOBLADE = $VALUES[ordinal + 14] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_TECHNOBLADE", ordinal + 14, "technoblade", SCSounds.NOTE_BLOCK_IMITATE_TECHNOBLADE);
		NOTE_BLOCK_IMITATE_SHEEP = $VALUES[ordinal + 15] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_SHEEP", ordinal + 15, "sheep", SCSounds.NOTE_BLOCK_IMITATE_SHEEP);
		NOTE_BLOCK_IMITATE_BAT = $VALUES[ordinal + 16] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_BAT", ordinal + 16, "bat", SCSounds.NOTE_BLOCK_IMITATE_BAT);
		NOTE_BLOCK_IMITATE_SHULKER = $VALUES[ordinal + 17] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_SHULKER", ordinal + 17, "shulker", SCSounds.NOTE_BLOCK_IMITATE_SHULKER);
		NOTE_BLOCK_IMITATE_ALLAY = $VALUES[ordinal + 18] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_ALLAY", ordinal + 18, "allay", SCSounds.NOTE_BLOCK_IMITATE_ALLAY);
		NOTE_BLOCK_IMITATE_VEX = $VALUES[ordinal + 19] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_VEX", ordinal + 19, "vex", SCSounds.NOTE_BLOCK_IMITATE_VEX);
		NOTE_BLOCK_IMITATE_COW = $VALUES[ordinal + 20] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_COW", ordinal + 20, "cow", SCSounds.NOTE_BLOCK_IMITATE_COW);
		NOTE_BLOCK_IMITATE_PIGLIN_BRUTE = $VALUES[ordinal + 21] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_PIGLIN_BRUTE", ordinal + 21, "piglin_brute", SCSounds.NOTE_BLOCK_IMITATE_PIGLIN_BRUTE);
		NOTE_BLOCK_IMITATE_ZOMBIFIED_PIGLIN = $VALUES[ordinal + 22] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_ZOMBIFIED_PIGLIN", ordinal + 22, "zombified_piglin", SCSounds.NOTE_BLOCK_IMITATE_ZOMBIFIED_PIGLIN);
		NOTE_BLOCK_IMITATE_HORSE = $VALUES[ordinal + 23] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_HORSE", ordinal + 23, "horse", SCSounds.NOTE_BLOCK_IMITATE_HORSE);
		NOTE_BLOCK_IMITATE_DONKEY = $VALUES[ordinal + 24] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_DONKEY", ordinal + 24, "donkey", SCSounds.NOTE_BLOCK_IMITATE_DONKEY);
		NOTE_BLOCK_IMITATE_MULE = $VALUES[ordinal + 25] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_MULE", ordinal + 25, "mule", SCSounds.NOTE_BLOCK_IMITATE_MULE);
		NOTE_BLOCK_IMITATE_SKELETON_HORSE = $VALUES[ordinal + 26] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_SKELETON_HORSE", ordinal + 26, "skeleton_horse", SCSounds.NOTE_BLOCK_IMITATE_SKELETON_HORSE);
		NOTE_BLOCK_IMITATE_ZOMBIE_HORSE = $VALUES[ordinal + 27] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_ZOMBIE_HORSE", ordinal + 27, "zombie_horse", SCSounds.NOTE_BLOCK_IMITATE_ZOMBIE_HORSE);
		NOTE_BLOCK_IMITATE_WARDEN = $VALUES[ordinal + 28] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_WARDEN", ordinal + 28, "warden", SCSounds.NOTE_BLOCK_IMITATE_WARDEN);
		NOTE_BLOCK_IMITATE_HOGLIN = $VALUES[ordinal + 29] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_HOGLIN", ordinal + 29, "hoglin", SCSounds.NOTE_BLOCK_IMITATE_HOGLIN);
		NOTE_BLOCK_IMITATE_ZOGLIN = $VALUES[ordinal + 30] =
				skullcraft$createSkullInstrument("NOTE_BLOCK_IMITATE_ZOGLIN", ordinal + 30, "zoglin", SCSounds.NOTE_BLOCK_IMITATE_ZOGLIN);
	}
}
