package dev.xkmc.l2weaponry.compat.cataclysm;

import dev.xkmc.cataclysm_mux.LWCataProxy;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2weaponry.content.entity.BaseThrownWeaponEntity;
import dev.xkmc.l2weaponry.content.item.base.IStackableWeapon;
import dev.xkmc.l2weaponry.content.item.legendary.LegendaryWeapon;
import dev.xkmc.l2weaponry.content.item.types.ScytheItem;
import dev.xkmc.l2weaponry.init.data.LangData;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class SoulHarvester extends ScytheItem implements LegendaryWeapon {

	public SoulHarvester(Tier tier, Properties prop, ExtraToolConfig config) {
		super(tier, prop, config);
	}

	@Override
	public void onDamageFinal(DamageData.DefenceMax data, LivingEntity le) {
		if (data.getStrength() < 0.95) return;
		var attacker = data.getAttacker();
		if (attacker == null) return;
		float factor = 1;

		if (data.getSource().getDirectEntity() instanceof BaseThrownWeaponEntity<?>) {
			factor = 2;
		} else if (attacker instanceof Player player) {
			float speed = (float) player.getAttributeValue(Attributes.ATTACK_SPEED);
			factor = 1f / Math.clamp(speed, 0.5f, 2);
		}
		ItemStack stack = data.getWeapon();
		int count = IStackableWeapon.getHitCount(stack);
		if (count > 0 && stack.getItem() instanceof IStackableWeapon item) {
			int max = item.getMaxStack(stack, attacker);
			factor *= 1 + 0.2f * Mth.clamp(count, 0, max);
		}
		LWCataProxy.stackBlazingBrand(attacker, data.getTarget(), factor);
	}

	@Override
	protected int getMaxStackIntrinsic(ItemStack stack) {
		return 64 - 5;
	}

	@Override
	public void appendHoverText(ItemStack pStack, TooltipContext pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
		list.add(LangData.MATS_IGNITIUM.get());
	}

}
