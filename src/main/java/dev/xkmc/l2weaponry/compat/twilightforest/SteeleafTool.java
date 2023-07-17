package dev.xkmc.l2weaponry.compat.twilightforest;

import dev.xkmc.l2complements.init.registrate.LCEffects;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2library.base.effects.EffectUtil;
import dev.xkmc.l2weaponry.content.item.base.BaseShieldItem;
import dev.xkmc.l2weaponry.init.data.LWConfig;
import dev.xkmc.l2weaponry.init.data.LangData;
import dev.xkmc.l2weaponry.init.materials.LWExtraConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SteeleafTool extends ExtraToolConfig implements LWExtraConfig {

	private boolean canTrigger(LivingEntity target) {
		return target.getAttribute(Attributes.ARMOR) == null || target.getAttributeValue(Attributes.ARMOR) == 0;
	}

	private void addEffect(LivingEntity user, LivingEntity target) {
		if (canTrigger(target)) {
			double chance = LWConfig.COMMON.steeleafChance.get();
			if (user.getRandom().nextDouble() < chance) {
				LCEffects.BLEED.get().addTo(target, 100, 4, EffectUtil.AddReason.NONE, user);
			}
		}
	}

	@Override
	public void onShieldBlock(ItemStack stack, LivingEntity user, LivingEntity entity) {
		addEffect(user, entity);
	}

	@Override
	public double onShieldReflect(ItemStack stack, LivingEntity user, LivingEntity entity, double original, double reflect) {
		return reflect + original * LWConfig.COMMON.steeleafReflect.get();
	}

	@Override
	public void onDamage(AttackCache cache, ItemStack stack) {
		LivingEntity target = cache.getAttackTarget();
		if (canTrigger(target)) {
			double bonus = LWConfig.COMMON.steeleafBonus.get();
			cache.addHurtModifier(DamageModifier.multBase((float) bonus));
			if (cache.getAttacker() != null) {
				addEffect(cache.getAttacker(), target);
			}
		}
	}

	@Override
	public void addTooltip(ItemStack stack, List<Component> list) {
		double bonus = LWConfig.COMMON.steeleafBonus.get();
		double chance = LWConfig.COMMON.steeleafChance.get();
		list.add(LangData.MATS_STEELEAF.get((int) Math.round(bonus * 100), (int) Math.round(chance * 100)));
		if (stack.getItem() instanceof BaseShieldItem) {
			double reflect = LWConfig.COMMON.steeleafReflect.get();
			list.add(LangData.MATS_REFLECT.get((int) Math.round(reflect * 100)));
		}
	}

}