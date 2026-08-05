package dev.xkmc.l2weaponry.compat.dragons;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.item.ability.BuiltinAbilities;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafItems;
import com.iafenvoy.iceandfire.registry.IafTiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Supplier;

public class IAFProxyCE implements IAFProxy {

	@Override
	public void fireHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		try {
			BuiltinAbilities.DRAGONSTEEL_FIRE_TOOL.active(stack, target, user);
		} catch (Throwable ignore) {

		}
	}

	@Override
	public void fireDesc(ItemStack stack, List<Component> list) {
		try {
			BuiltinAbilities.DRAGONSTEEL_FIRE_TOOL.addDescription(list);
		} catch (Throwable ignore) {

		}
	}

	@Override
	public void iceHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		try {
			BuiltinAbilities.DRAGONSTEEL_ICE_TOOL.active(stack, target, user);
		} catch (Throwable ignore) {

		}
	}

	@Override
	public void iceDesc(ItemStack stack, List<Component> list) {
		try {
			BuiltinAbilities.DRAGONSTEEL_ICE_TOOL.addDescription(list);
		} catch (Throwable ignore) {

		}
	}

	private int rec = 0;

	@Override
	public void lightningHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		rec++;
		try {
			if (rec <= 1)
				BuiltinAbilities.DRAGONSTEEL_LIGHTNING_TOOL.active(stack, target, user);
		} catch (Throwable ignore) {

		}
		rec--;

	}

	@Override
	public void lightningDesc(ItemStack stack, List<Component> list) {
		try {
			BuiltinAbilities.DRAGONSTEEL_LIGHTNING_TOOL.addDescription(list);
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
		return IafTiers.DRAGONSTEEL_ICE;
	}

	@Override
	public Tier tierFire() {
		return IafTiers.DRAGONSTEEL_FIRE;
	}

	@Override
	public Tier tierLightning() {
		return IafTiers.DRAGONSTEEL_LIGHTNING;
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
