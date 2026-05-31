package com.hexagram2021.skullcraft;

import com.hexagram2021.skullcraft.client.config.SCClientConfig;
import com.hexagram2021.skullcraft.common.SCContent;
import com.hexagram2021.skullcraft.common.world.Villages;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

/**
 * SkullCraft 模组主类，负责模组的初始化、配置注册和事件监听喵~
 *
 * @author liudongyu
 */
@SuppressWarnings("unused")
@Mod(SkullCraft.MODID)
public class SkullCraft {
	/** 模组的唯一标识符喵~ */
	public static final String MODID = "skullcraft";

	/** 头颅缩放比例的 NBT 标签键喵~ */
	public static final String SCALE_TAG = "skullcraft:head_scale";
	/** 头颅附魔的 NBT 标签键喵~ */
	public static final String ENCHANTMENTS_TAG = "skullcraft:enchantments";
	/** 头颅修复惩罚的 NBT 标签键喵~ */
	public static final String REPAIR_COST_TAG = "skullcraft:repair_cost";

	/**
	 * 模组构造方法，注册事件监听器、客户端配置和模组内容喵~
	 *
	 * @param modEventBus 模组事件总线喵~
	 * @param modContainer 模组容器喵~
	 */
	public SkullCraft(IEventBus modEventBus, ModContainer modContainer) {
		modEventBus.addListener(this::setup);
		NeoForge.EVENT_BUS.addListener(this::tagsUpdated);

		modContainer.registerConfig(ModConfig.Type.CLIENT, SCClientConfig.getConfig());
		SCContent.modConstruction(modEventBus);
	}

	/**
	 * 通用设置事件处理，将初始化工作加入队列喵~
	 *
	 * @param event 通用设置事件喵~
	 */
	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(SCContent::init);
	}

	/**
	 * 标签更新事件处理，当服务端数据加载完成后向村庄建筑池添加结构喵~
	 *
	 * @param event 标签更新事件喵~
	 */
	private void tagsUpdated(final TagsUpdatedEvent event) {
		if(event.getUpdateCause() != TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD) {
			return;
		}
		Villages.addAllStructuresToPool(event.getLookupProvider());
	}
}
