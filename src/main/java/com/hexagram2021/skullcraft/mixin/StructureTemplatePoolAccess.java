package com.hexagram2021.skullcraft.mixin;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * 结构模板池访问器 Mixin，提供对 {@link StructureTemplatePool} 中私有字段的读写访问，
 * 用于向原版村庄建筑池中注入模组自定义结构喵~
 *
 * @author liudongyu
 */
@Mixin(StructureTemplatePool.class)
public interface StructureTemplatePoolAccess {
	/**
	 * 获取结构模板池中的原始模板列表
	 * @return 原始模板列表
	 */
	@Accessor("rawTemplates")
	List<Pair<StructurePoolElement, Integer>> skullcraft$getRawTemplates();
	/**
	 * 设置结构模板池中的原始模板列表
	 * @param value 原始模板列表
	 */
	@Accessor("rawTemplates") @Final @Mutable
	void skullcraft$setRawTemplates(List<Pair<StructurePoolElement, Integer>> value);

	/**
	 * 获取结构模板池中的模板列表
	 * @return 模板列表
	 */
	@Accessor("templates")
	ObjectArrayList<StructurePoolElement> skullcraft$getTemplates();
}
