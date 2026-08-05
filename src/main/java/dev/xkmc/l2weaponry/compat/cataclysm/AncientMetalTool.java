package dev.xkmc.l2weaponry.compat.cataclysm;

import dev.xkmc.cataclysm_mux.LWCataProxy;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2weaponry.content.entity.BaseThrownWeaponEntity;
import dev.xkmc.l2weaponry.init.data.LangData;
import dev.xkmc.l2weaponry.init.materials.LWExtraConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AncientMetalTool extends ExtraToolConfig implements LWExtraConfig {

	@Override
	public void onDamageFinal(AttackCache data, LivingEntity le, ItemStack stack) {
		if (data.getStrength() < 0.95) return;
		var attacker = data.getAttacker();
		if (attacker == null) return;
		int time = 20;
		var event = data.getLivingDamageEvent();
		assert event != null;
		if (event.getSource().getDirectEntity() instanceof BaseThrownWeaponEntity<?>) {
			time = 40;
		} else if (attacker instanceof Player player) {
			float speed = (float) player.getAttributeValue(Attributes.ATTACK_SPEED);
			time = (int) (20 / Mth.clamp(speed, 0.5f, 2));
		}
		LWCataProxy.inflictStun(attacker, data.getAttackTarget(), time);
	}

	@Override
	public void addTooltip(ItemStack stack, List<Component> list) {
		list.add(LangData.MATS_ANCIENT.get());
	}

}
