package dev.xkmc.l2weaponry.content.item.legendary;

import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;

public interface LegendaryWeapon {

	@Nullable
	static LivingEntity getTarget(@Nullable Entity entity) {
		if (entity == null) return null;
		if (entity instanceof LivingEntity le) return le;
		if (entity instanceof PartEntity<?> pe) {
			if (pe.getParent() == entity) return null;
			return getTarget(pe.getParent());
		}
		return null;
	}

	default boolean isImmuneTo(DamageSource source) {
		return false;
	}

	default void onHurt(AttackCache event, LivingEntity le, ItemStack stack) {

	}

	default boolean cancelFreeze() {
		return false;
	}

	default void onCrit(Player entity, Entity target) {

	}

	default void onDamageFinal(AttackCache cache, LivingEntity le) {

	}

	default void onKill(ItemStack stack, LivingEntity target, LivingEntity user) {

	}

	default void onHurtMaximized(AttackCache cache, LivingEntity le) {

	}
}
