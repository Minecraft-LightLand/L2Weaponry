package dev.xkmc.l2weaponry.compat.dragons;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.tool.DragonSteelOverrides;
import com.iafenvoy.iceandfire.item.tool.DragonSteelToolMaterial;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafItems;
import dev.xkmc.l2weaponry.content.item.base.WeaponItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Supplier;

public class IAFProxyCE implements IAFProxy {

	private static final DragonSteelOverrides<WeaponItem> DUMMY = () -> null;

	@Override
	public void fireHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		try {
			DUMMY.hurtEnemy((WeaponItem) stack.getItem(), stack, target, user);
		} catch (Throwable ignore) {

		}

	}

	@Override
	public void fireDesc(ItemStack stack, List<Component> list) {
		try {
			list.add(Component.translatable("dragon_sword_fire.hurt1").withStyle(ChatFormatting.GREEN));
			if (IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue()) {
				list.add(Component.translatable("dragon_sword_fire.hurt2").withStyle(ChatFormatting.DARK_RED));
			}
		} catch (Throwable ignore) {

		}
	}

	@Override
	public void iceHit(ItemStack stack, LivingEntity target, LivingEntity user) {

		try {
			DUMMY.hurtEnemy((WeaponItem) stack.getItem(), stack, target, user);
		} catch (Throwable ignore) {

		}
	}

	@Override
	public void iceDesc(ItemStack stack, List<Component> list) {
		try {
			list.add(Component.translatable("dragon_sword_ice.hurt1").withStyle(ChatFormatting.GREEN));
			if (IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue()) {
				list.add(Component.translatable("dragon_sword_ice.hurt2").withStyle(ChatFormatting.AQUA));
			}
		} catch (Throwable ignore) {

		}
	}

	private int rec = 0;

	@Override
	public void lightningHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		rec++;
		try {
			if (rec <= 1)
				DUMMY.hurtEnemy((WeaponItem) stack.getItem(), stack, target, user);
		} catch (Throwable ignore) {

		}
		rec--;
	}

	@Override
	public void lightningDesc(ItemStack stack, List<Component> list) {
		try {
			list.add(Component.translatable("dragon_sword_lightning.hurt1").withStyle(ChatFormatting.GREEN));
			if (IafCommonConfig.INSTANCE.armors.dragonLightningAbility.getValue()) {
				list.add(Component.translatable("dragon_sword_lightning.hurt2").withStyle(ChatFormatting.DARK_PURPLE));
			}
		} catch (Throwable ignore) {

		}
	}

	@Override
	public String modid() {
		return IceAndFire.MOD_ID;
	}

	@Override
	public Item witherBone() {
		return IafItems.WITHERBONE.get();
	}

	@Override
	public Tier tierIce() {
		return DragonSteelToolMaterial.createMaterialWithRepairItem(IafItems.DRAGONSTEEL_ICE_INGOT::get);
	}

	@Override
	public Tier tierFire() {
		return DragonSteelToolMaterial.createMaterialWithRepairItem(IafItems.DRAGONSTEEL_FIRE_INGOT::get);
	}

	@Override
	public Tier tierLightning() {
		return DragonSteelToolMaterial.createMaterialWithRepairItem(IafItems.DRAGONSTEEL_LIGHTNING_INGOT::get);
	}

	@Override
	public Supplier<Item> ingotIceSteel() {
		return IafItems.DRAGONSTEEL_ICE_INGOT;
	}

	@Override
	public Supplier<Item> ingotFireSteel() {
		return IafItems.DRAGONSTEEL_FIRE_INGOT;
	}

	@Override
	public Supplier<Item> ingotLightningSteel() {
		return IafItems.DRAGONSTEEL_LIGHTNING_INGOT;
	}

	@Override
	public Supplier<Block> blockIceSteel() {
		return IafBlocks.DRAGONSTEEL_ICE_BLOCK;
	}

	@Override
	public Supplier<Block> blockFireSteel() {
		return IafBlocks.DRAGONSTEEL_FIRE_BLOCK;
	}

	@Override
	public Supplier<Block> blockLightningSteel() {
		return IafBlocks.DRAGONSTEEL_LIGHTNING_BLOCK;
	}

}
