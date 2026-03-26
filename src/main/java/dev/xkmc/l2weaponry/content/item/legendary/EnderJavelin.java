package dev.xkmc.l2weaponry.content.item.legendary;

import dev.xkmc.l2core.base.effects.EffectUtil;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2weaponry.content.entity.BaseThrownWeaponEntity;
import dev.xkmc.l2weaponry.content.entity.JavelinEntity;
import dev.xkmc.l2weaponry.content.item.types.JavelinItem;
import dev.xkmc.l2weaponry.init.data.LangData;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class EnderJavelin extends JavelinItem implements LegendaryWeapon {

	public EnderJavelin(Tier tier, Properties prop, ExtraToolConfig config) {
		super(tier, prop, config);
	}

	@Override
	public JavelinEntity getProjectile(Level level, LivingEntity player, ItemStack stack, int slot) {
		JavelinEntity entity = super.getProjectile(level, player, stack, slot);
		entity.setNoGravity(true);
		return entity;
	}

	@Override
	public void onHitBlock(BaseThrownWeaponEntity<?> entity, ItemStack item) {
		super.onHitBlock(entity, item);
		if (entity.level().isClientSide) return;
		if (!(entity.getOwner() instanceof Player player)) return;
		if (player.level() != entity.level()) return;
		if (!player.isAlive()) return;
		player.teleportTo(entity.getX(), entity.getY(), entity.getZ());
		player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
		super.inventoryTick(stack, level, entity, slot, selected);
		if (selected && !level.isClientSide && entity instanceof LivingEntity le && le.position().y() < level.getMinBuildHeight()) {
			EffectUtil.refreshEffect(le, new MobEffectInstance(MobEffects.SLOW_FALLING, 219), le);
			EffectUtil.refreshEffect(le, new MobEffectInstance(MobEffects.LEVITATION, 119), le);
		}
	}

	@Override
	public void appendHoverText(ItemStack pStack, TooltipContext pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
		list.add(LangData.ENDER_JAVELIN.get());
	}

	@Override
	public boolean playerThrowable() {
		return true;
	}

}
