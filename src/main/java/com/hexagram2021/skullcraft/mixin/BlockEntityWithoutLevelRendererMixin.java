package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.client.renderer.CubeSkullBlockRenderer;
import com.hexagram2021.skullcraft.client.renderer.HumanSkullBlockRenderer;
import com.hexagram2021.skullcraft.common.block.CubeSkull.CubeSkullBlock;
import com.hexagram2021.skullcraft.common.block.CubeSkull.CubeWallSkullBlock;
import com.hexagram2021.skullcraft.common.block.HumanSkull.HumanSkullBlock;
import com.hexagram2021.skullcraft.common.block.HumanSkull.HumanWallSkullBlock;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin {
	@Shadow
	@Final
	private static ShulkerBoxBlockEntity[] SHULKER_BOXES;
	@Shadow
	@Final
	private static ShulkerBoxBlockEntity DEFAULT_SHULKER_BOX = new ShulkerBoxBlockEntity(BlockPos.ZERO, Blocks.SHULKER_BOX.defaultBlockState());
	@Shadow
	@Final
	private ChestBlockEntity chest;
	@Shadow
	@Final
	private ChestBlockEntity trappedChest;
	@Shadow
	@Final
	private EnderChestBlockEntity enderChest;
	@Shadow
	@Final
	private BannerBlockEntity banner;
	@Shadow
	@Final
	private BedBlockEntity bed;
	@Shadow
	@Final
	private ConduitBlockEntity conduit;

	@Shadow
	private ShieldModel shieldModel;
	@Shadow
	private TridentModel tridentModel;
	@Shadow
	@Final
	private BlockEntityRenderDispatcher blockEntityRenderDispatcher;

	@Shadow
	@Final
	private EntityModelSet entityModelSet;

	@Shadow
	private Map<SkullBlock.Type, SkullModelBase> skullModels;

	private Map<SkullBlock.Type, SkullModelBase> humanSkullModels;
	private Map<SkullBlock.Type, SkullModelBase> cubeSkullModels;

	@Inject(method = "onResourceManagerReload", at = @At(value = "TAIL"))
	public void createModSkullModels(ResourceManager manager, CallbackInfo ci) {
		this.humanSkullModels = HumanSkullBlockRenderer.createSkullRenderers(entityModelSet);
		this.cubeSkullModels = CubeSkullBlockRenderer.createSkullRenderers(entityModelSet);
	}

	/**
	 * @author Liu Dongyu
	 * @reason Render mod skulls.
	 */
	@Overwrite
	public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack transform, MultiBufferSource source, int uv2, int y) {
		Item item = itemStack.getItem();
		if (item instanceof BlockItem) {
			Block itemBlock = ((BlockItem)item).getBlock();
			if (itemBlock instanceof AbstractSkullBlock skullBlock) {
				GameProfile gameprofile = null;
				if (itemStack.hasTag()) {
					CompoundTag compoundtag = itemStack.getTag();
					assert compoundtag != null;
					if (compoundtag.contains("SkullOwner", 10)) {
						gameprofile = NbtUtils.readGameProfile(compoundtag.getCompound("SkullOwner"));
					} else if (compoundtag.contains("SkullOwner", 8) && !StringUtils.isBlank(compoundtag.getString("SkullOwner"))) {
						gameprofile = new GameProfile(null, compoundtag.getString("SkullOwner"));
						compoundtag.remove("SkullOwner");
						SkullBlockEntity.updateGameprofile(gameprofile, (newGameProfile) ->
								compoundtag.put("SkullOwner", NbtUtils.writeGameProfile(new CompoundTag(), newGameProfile)));
					}
				}

				SkullBlock.Type skullType = skullBlock.getType();
				if(itemBlock instanceof HumanSkullBlock || itemBlock instanceof HumanWallSkullBlock) {
					SkullModelBase skullmodelbase = this.humanSkullModels.get(skullType);
					RenderType rendertype = HumanSkullBlockRenderer.getRenderType(skullType);
					HumanSkullBlockRenderer.renderSkull(null, 180.0F, 0.0F, transform, source, uv2, skullmodelbase, rendertype);
				} else if(itemBlock instanceof CubeSkullBlock || itemBlock instanceof CubeWallSkullBlock) {
					SkullModelBase skullmodelbase = this.cubeSkullModels.get(skullType);
					RenderType rendertype = CubeSkullBlockRenderer.getRenderType(skullType);
					CubeSkullBlockRenderer.renderSkull(null, 180.0F, 0.0F, transform, source, uv2, skullmodelbase, rendertype);
				} else {
					SkullModelBase skullmodelbase = this.skullModels.get(skullType);
					RenderType rendertype = SkullBlockRenderer.getRenderType(skullType, gameprofile);
					SkullBlockRenderer.renderSkull(null, 180.0F, 0.0F, transform, source, uv2, skullmodelbase, rendertype);
				}
			} else {
				BlockState blockstate = itemBlock.defaultBlockState();
				BlockEntity blockentity;
				if (itemBlock instanceof AbstractBannerBlock bannerBlock) {
					this.banner.fromItem(itemStack, bannerBlock.getColor());
					blockentity = this.banner;
				} else if (itemBlock instanceof BedBlock bedBlock) {
					this.bed.setColor(bedBlock.getColor());
					blockentity = this.bed;
				} else if (blockstate.is(Blocks.CONDUIT)) {
					blockentity = this.conduit;
				} else if (blockstate.is(Blocks.CHEST)) {
					blockentity = this.chest;
				} else if (blockstate.is(Blocks.ENDER_CHEST)) {
					blockentity = this.enderChest;
				} else if (blockstate.is(Blocks.TRAPPED_CHEST)) {
					blockentity = this.trappedChest;
				} else {
					if (itemBlock instanceof ShulkerBoxBlock) {
						DyeColor dyecolor = ShulkerBoxBlock.getColorFromItem(item);
						if (dyecolor == null) {
							blockentity = DEFAULT_SHULKER_BOX;
						} else {
							blockentity = SHULKER_BOXES[dyecolor.getId()];
						}
					} else {
						return;
					}
				}

				this.blockEntityRenderDispatcher.renderItem(blockentity, transform, source, uv2, y);
			}
		} else {
			if (itemStack.is(Items.SHIELD)) {
				boolean flag = BlockItem.getBlockEntityData(itemStack) != null;
				transform.pushPose();
				transform.scale(1.0F, -1.0F, -1.0F);
				Material material = flag ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;
				VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBufferDirect(source, this.shieldModel.renderType(material.atlasLocation()), true, itemStack.hasFoil()));
				this.shieldModel.handle().render(transform, vertexconsumer, uv2, y, 1.0F, 1.0F, 1.0F, 1.0F);
				if (flag) {
					List<Pair<BannerPattern, DyeColor>> list = BannerBlockEntity.createPatterns(ShieldItem.getColor(itemStack), BannerBlockEntity.getItemPatterns(itemStack));
					BannerRenderer.renderPatterns(transform, source, uv2, y, this.shieldModel.plate(), material, false, list, itemStack.hasFoil());
				} else {
					this.shieldModel.plate().render(transform, vertexconsumer, uv2, y, 1.0F, 1.0F, 1.0F, 1.0F);
				}

				transform.popPose();
			} else if (itemStack.is(Items.TRIDENT)) {
				transform.pushPose();
				transform.scale(1.0F, -1.0F, -1.0F);
				VertexConsumer vertexconsumer1 = ItemRenderer.getFoilBufferDirect(source, this.tridentModel.renderType(TridentModel.TEXTURE), false, itemStack.hasFoil());
				this.tridentModel.renderToBuffer(transform, vertexconsumer1, uv2, y, 1.0F, 1.0F, 1.0F, 1.0F);
				transform.popPose();
			}
		}
	}
}
