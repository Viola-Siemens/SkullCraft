package com.hexagram2021.skullcraft.mixin;

import com.hexagram2021.skullcraft.SkullCraft;
import com.hexagram2021.skullcraft.client.renderer.CubeSkullBlockRenderer;
import com.hexagram2021.skullcraft.client.renderer.HumanSkullBlockRenderer;
import com.hexagram2021.skullcraft.common.block.CubeSkull.*;
import com.hexagram2021.skullcraft.common.block.HumanSkull.*;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(CustomHeadLayer.class)
public class CustomHeadLayerMixin<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> {
	@Final
	@Shadow
	private float scaleX;
	@Final
	@Shadow
	private float scaleY;
	@Final
	@Shadow
	private float scaleZ;

	@Final
	@Shadow
	private Map<SkullBlock.Type, SkullModelBase> skullModels;

	private Map<SkullBlock.Type, SkullModelBase> humanSkullModels;
	private Map<SkullBlock.Type, SkullModelBase> cubeSkullModels;

	@Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/RenderLayerParent;Lnet/minecraft/client/model/geom/EntityModelSet;FFF)V",
			at = @At(value = "TAIL"))
	public void createModSkullModels(RenderLayerParent<T, M> parent, EntityModelSet set, float scaleX, float scaleY, float scaleZ, CallbackInfo ci) {
		this.humanSkullModels = HumanSkullBlockRenderer.createSkullRenderers(set);
		this.cubeSkullModels = CubeSkullBlockRenderer.createSkullRenderers(set);
	}

	/**
	 * @author Liu Dongyu
	 * @reason Render mod skulls.
	 */
	@Overwrite
	public void render(PoseStack transform, MultiBufferSource source, int uv2, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack headSlot = entity.getItemBySlot(EquipmentSlot.HEAD);
		if (!headSlot.isEmpty()) {
			Item item = headSlot.getItem();
			transform.pushPose();
			transform.scale(this.scaleX, this.scaleY, this.scaleZ);
			boolean flag = entity instanceof Villager || entity instanceof ZombieVillager;
			if (entity.isBaby() && !(entity instanceof Villager)) {
				transform.translate(0.0D, 0.03125D, 0.0D);
				transform.scale(0.7F, 0.7F, 0.7F);
				transform.translate(0.0D, 1.0D, 0.0D);
			}

			((CustomHeadLayer<T, M>)(Object)this).getParentModel().getHead().translateAndRotate(transform);
			if (item instanceof BlockItem) {
				Block itemBlock = ((BlockItem)item).getBlock();
				if(itemBlock instanceof AbstractSkullBlock skullBlock) {
					BasicScale(transform, flag);

					GameProfile gameprofile = null;
					if (headSlot.hasTag()) {
						CompoundTag compoundtag = headSlot.getTag();
						assert compoundtag != null;
						if (compoundtag.contains("SkullOwner", Tag.TAG_COMPOUND)) {
							gameprofile = NbtUtils.readGameProfile(compoundtag.getCompound("SkullOwner"));
						}
						HandleSkullCraftScale(transform, compoundtag);
					}

					transform.translate(-0.5D, 0.0D, -0.5D);
					SkullBlock.Type skullType = skullBlock.getType();
					if(itemBlock instanceof HumanSkullBlock || itemBlock instanceof HumanWallSkullBlock) {
						SkullModelBase skullmodelbase = this.humanSkullModels.get(skullType);
						RenderType rendertype = HumanSkullBlockRenderer.getRenderType(skullType);
						HumanSkullBlockRenderer.renderSkull(null, 180.0F, limbSwing, transform, source, uv2, skullmodelbase, rendertype);
					} else if(itemBlock instanceof CubeSkullBlock || itemBlock instanceof CubeWallSkullBlock) {
						SkullModelBase skullmodelbase = this.cubeSkullModels.get(skullType);
						RenderType rendertype = CubeSkullBlockRenderer.getRenderType(skullType);
						CubeSkullBlockRenderer.renderSkull(null, 180.0F, limbSwing, transform, source, uv2, skullmodelbase, rendertype);
					} else {
						SkullModelBase skullmodelbase = this.skullModels.get(skullType);
						RenderType rendertype = SkullBlockRenderer.getRenderType(skullType, gameprofile);
						SkullBlockRenderer.renderSkull(null, 180.0F, limbSwing, transform, source, uv2, skullmodelbase, rendertype);
					}
				} else {
					this.FallBackRenderItem(transform, source, uv2, entity, item, headSlot, flag);
				}
			} else {
				this.FallBackRenderItem(transform, source, uv2, entity, item, headSlot, flag);
			}

			transform.popPose();
		}
	}

	private void FallBackRenderItem(PoseStack transform, MultiBufferSource source, int uv2, T entity, Item item, ItemStack headSlot, Boolean flag) {
		if (!(item instanceof ArmorItem) || ((ArmorItem)item).getSlot() != EquipmentSlot.HEAD) {
			CustomHeadLayer.translateToHead(transform, flag);
			Minecraft.getInstance().getItemInHandRenderer().renderItem(entity, headSlot, ItemTransforms.TransformType.HEAD, false, transform, source, uv2);
		}
	}

	private static void BasicScale(PoseStack transform, Boolean flag) {
		float scale = 1.1875F;
		transform.scale(scale, -scale, -scale);
		if (flag) {
			transform.translate(0.0D, 0.0625D, 0.0D);
		}
	}

	private static void HandleSkullCraftScale(PoseStack transform, CompoundTag compoundtag) {
		if (compoundtag.contains(SkullCraft.SCALE_TAG, Tag.TAG_COMPOUND)) {
			final CompoundTag scaleNBT = compoundtag.getCompound(SkullCraft.SCALE_TAG);
			final int scaleX = scaleNBT.contains("x") ? Mth.clamp(scaleNBT.getInt("x"), 50, 5000) : 100;
			final int scaleY = scaleNBT.contains("y") ? Mth.clamp(scaleNBT.getInt("y"), 50, 5000) : 100;
			final int scaleZ = scaleNBT.contains("z") ? Mth.clamp(scaleNBT.getInt("z"), 50, 5000) : 100;
			final double dx = (double)scaleX / 100.0D;
			final double dy = (double)scaleY / 100.0D;
			final double dz = (double)scaleZ / 100.0D;
			transform.scale((float)dx, (float)dy, (float)dz);
		}
	}
}
