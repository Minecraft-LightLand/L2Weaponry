package dev.xkmc.l2weaponry.content.item.base;

import dev.xkmc.l2complements.init.materials.LCMats;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2library.init.FlagMarker;
import dev.xkmc.l2weaponry.content.entity.BaseThrownWeaponEntity;
import dev.xkmc.l2weaponry.init.data.LWConfig;
import dev.xkmc.l2weaponry.init.materials.LWExtraConfig;
import dev.xkmc.l2weaponry.init.registrate.LWEnchantments;
import dev.xkmc.l2weaponry.init.registrate.LWItems;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

public abstract class BaseThrowableWeaponItem extends GenericWeaponItem implements IThrowableCallback {

	public BaseThrowableWeaponItem(Tier tier, Properties prop, ExtraToolConfig config, TagKey<Block> blocks) {
		super(tier, prop, config, blocks);
		LWItems.THROW_DECO.add(this);
	}

	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPEAR;
	}

	public int getUseDuration(ItemStack stack, LivingEntity le) {
		return 72000;
	}

	public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {
		if (user instanceof Player player) {
			int time = this.getUseDuration(stack, user) - timeLeft;
			if (time >= 10) {
				if (!level.isClientSide) {
					serverThrow(stack, level, player);
				}
			}
		}
	}

	protected void shoot(AbstractArrow proj, ItemStack stack, Level level, Player player) {
		proj.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
	}

	protected int getInstantThrowCoolDown() {
		return LWConfig.SERVER.instantThrowCooldown.get();
	}

	protected void serverThrow(ItemStack stack, Level level, Player player) {
		int slot = player.getUsedItemHand() == InteractionHand.OFF_HAND ? 40 : player.getInventory().selected;
		boolean projection = LWEnchantments.PROJECTION.getLv(stack) > 0;
		boolean no_pickup = projection || player.getAbilities().instabuild;
		stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
		AbstractArrow proj = getProjectile(level, player, stack, slot);
		proj.setBaseDamage(player.getAttributeValue(Attributes.ATTACK_DAMAGE));
		shoot(proj, stack, level, player);
		if (no_pickup) {
			proj.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
			proj.getPersistentData().putInt(FlagMarker.ARROW_DESPAWN, 20);
		}
		level.addFreshEntity(proj);
		proj.playSound(SoundEvents.TRIDENT_THROW.value(), 1.0F, 1.0F);
		if (!no_pickup) {
			player.getInventory().removeItem(stack);
		}
		player.awardStat(Stats.ITEM_USED.get(this));
	}

	public boolean playerThrowable() {
		return true;
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand pHand) {
		ItemStack stack = player.getItemInHand(pHand);
		if (!playerThrowable()) return InteractionResultHolder.pass(stack);
		if (stack.getDamageValue() >= stack.getMaxDamage() - 1) {
			return InteractionResultHolder.fail(stack);
		} else {
			boolean instant = LWEnchantments.INSTANT_THROWING.getLv(stack) > 0 &&
					!player.isShiftKeyDown();
			if (instant) {
				if (!level.isClientSide) {
					serverThrow(stack, level, player);
					player.getCooldowns().addCooldown(this, getInstantThrowCoolDown());
				}
			} else {
				player.startUsingItem(pHand);
			}
			return InteractionResultHolder.consume(stack);
		}
	}

	public abstract BaseThrownWeaponEntity<?> getProjectile(Level level, LivingEntity player, ItemStack stack, int slot);

	@MustBeInvokedByOverriders
	@Override
	public void onHitBlock(BaseThrownWeaponEntity<?> entity, ItemStack item) {
		IThrowableCallback.super.onHitBlock(entity, item);
		if (getExtraConfig() instanceof LWExtraConfig w)
			w.onHitBlock(entity, item);
	}

	@MustBeInvokedByOverriders
	@Override
	public void onHitEntity(BaseThrownWeaponEntity<?> entity, ItemStack item, LivingEntity le) {
		IThrowableCallback.super.onHitEntity(entity, item, le);
		if (getExtraConfig() instanceof LWExtraConfig w)
			w.onHitEntity(entity, item, le);
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return enchantment == Enchantments.LOYALTY || super.isPrimaryItemFor(stack, enchantment);
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		if (getTier() == LCMats.POSEIDITE.getTier()) {
			if (enchantment.is(Enchantments.CHANNELING)) {
				return true;
			}
		}
		return enchantment == Enchantments.LOYALTY || super.supportsEnchantment(stack, enchantment);
	}

}
