package dev.xkmc.l2weaponry.compat.cataclysm;

import dev.xkmc.cataclysm_mux.LWCataProxy;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2weaponry.content.item.base.IExplosionSource;
import dev.xkmc.l2weaponry.content.item.base.IStackableWeapon;
import dev.xkmc.l2weaponry.content.item.legendary.LegendaryWeapon;
import dev.xkmc.l2weaponry.content.item.types.MacheteItem;
import dev.xkmc.l2weaponry.init.data.LWConfig;
import dev.xkmc.l2weaponry.init.data.LangData;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class AncientTraveller extends MacheteItem implements LegendaryWeapon, IExplosionSource {

	public AncientTraveller(Tier tier, Properties prop, ExtraToolConfig config) {
		super(tier, prop, config);
	}

	@Override
	public void onDamageFinal(DamageData.DefenceMax data, LivingEntity le) {
		if (data.getStrength() < 0.95) return;
		var attacker = data.getAttacker();
		if (attacker == null) return;
		LWCataProxy.inflictStun(attacker, data.getTarget(), 20);
	}

	@Override
	public void onAffecting(LivingEntity attacker, Entity entity, ItemStack stack) {
		if (!(entity instanceof LivingEntity le)) return;
		int time = 20;
		int count = IStackableWeapon.getHitCount(stack);
		if (count > 0 && stack.getItem() instanceof IStackableWeapon item) {
			int max = item.getMaxStack(stack, attacker);
			var bonus = 1 + LWConfig.SERVER.claw_bonus.get() * Mth.clamp(count, 0, max);
			time = (int) (time * bonus);
		}
		LWCataProxy.inflictStun(attacker, le, time);
	}

	@Override
	public void appendHoverText(ItemStack pStack, TooltipContext pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
		list.add(LangData.MATS_ANCIENT_TRAVELLER.get());
	}

}
