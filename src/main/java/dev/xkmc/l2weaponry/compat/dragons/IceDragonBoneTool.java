package dev.xkmc.l2weaponry.compat.dragons;

import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2weaponry.content.entity.BaseThrownWeaponEntity;
import dev.xkmc.l2weaponry.init.materials.LWExtraConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class IceDragonBoneTool extends ExtraToolConfig implements LWExtraConfig {

	@Override
	public void onHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		super.onHit(stack, target, user);
		IAFProxy.get().iceHit(stack, target, user);
	}

	@Override
	public void onHitEntity(BaseThrownWeaponEntity<?> entity, ItemStack stack, LivingEntity target) {
		if (entity.getOwner() instanceof LivingEntity user)
			IAFProxy.get().lightningHit(stack, target, user);
	}

	@Override
	public void addTooltip(ItemStack stack, List<Component> list) {
		IAFProxy.get().iceDesc(stack, list);
	}

}
