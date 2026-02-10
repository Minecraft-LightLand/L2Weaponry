package dev.xkmc.l2weaponry.compat.dragons;

import com.github.alexthe666.iceandfire.IafConfig;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.entity.props.EntityDataProvider;
import com.github.alexthe666.iceandfire.item.DragonSteelTier;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import dev.xkmc.l2weaponry.events.LWGeneralEvents;
import dev.xkmc.l2weaponry.init.L2Weaponry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Supplier;

public class IAFProxyAlex implements IAFProxy {

	/**
	 * Constructs a new object.
	 */
	public IAFProxyAlex() {
		if (IafConfig.dragonWeaponIceAbility) {
			L2Weaponry.LOGGER.info("Alex's Ice and Fire loaded");
		}
	}

	@Override
	public void fireHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		if (IafConfig.dragonWeaponFireAbility) {
			if (target instanceof EntityIceDragon) {
				target.hurt(user.level().damageSources().inFire(), 13.5F);
			}

			target.setSecondsOnFire(5);
			target.knockback(1.0D, user.getX() - target.getX(), user.getZ() - target.getZ());
		}
	}

	@Override
	public void fireDesc(ItemStack stack, List<Component> list) {
		list.add(Component.translatable("dragon_sword_fire.hurt1").withStyle(ChatFormatting.GREEN));
		if (IafConfig.dragonWeaponFireAbility) {
			list.add(Component.translatable("dragon_sword_fire.hurt2").withStyle(ChatFormatting.DARK_RED));
		}
	}

	@Override
	public void iceHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		if (IafConfig.dragonWeaponIceAbility) {
			if (target instanceof EntityFireDragon) {
				target.hurt(user.level().damageSources().drown(), 13.5F);
			}
			EntityDataProvider.getCapability(target).ifPresent((data) -> data.frozenData.setFrozen(target, 200));
			target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
			target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 2));
			target.knockback(1.0D, user.getX() - target.getX(), user.getZ() - target.getZ());
		}
	}

	@Override
	public void iceDesc(ItemStack stack, List<Component> list) {
		list.add(Component.translatable("dragon_sword_ice.hurt1").withStyle(ChatFormatting.GREEN));
		if (IafConfig.dragonWeaponIceAbility) {
			list.add(Component.translatable("dragon_sword_ice.hurt2").withStyle(ChatFormatting.AQUA));
		}
	}

	@Override
	public void lightningHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		if (IafConfig.dragonWeaponLightningAbility) {
			boolean flag = !(user instanceof Player) || !((double) user.attackAnim > 0.2D);

			if (!user.level().isClientSide && flag) {
				LightningBolt entity = EntityType.LIGHTNING_BOLT.create(target.level());
				assert entity != null;
				entity.addTag(LWGeneralEvents.LIGHTNING);
				entity.moveTo(target.position());
				if (user instanceof ServerPlayer sp) entity.setCause(sp);
				if (!target.level().isClientSide) {
					target.level().addFreshEntity(entity);
				}
			}

			if (target instanceof EntityFireDragon || target instanceof EntityIceDragon) {
				target.hurt(user.level().damageSources().lightningBolt(), 9.5F);
			}

			target.knockback(1.0D, user.getX() - target.getX(), user.getZ() - target.getZ());
		}
	}

	@Override
	public void lightningDesc(ItemStack stack, List<Component> list) {
		list.add(Component.translatable("dragon_sword_lightning.hurt1").withStyle(ChatFormatting.GREEN));
		if (IafConfig.dragonWeaponLightningAbility) {
			list.add(Component.translatable("dragon_sword_lightning.hurt2").withStyle(ChatFormatting.DARK_PURPLE));
		}
	}

	@Override
	public String modid() {
		return IceAndFire.MODID;
	}

	@Override
	public Item witherBone() {
		return IafItemRegistry.WITHERBONE.get();
	}

	@Override
	public Tier tierIce() {
		return DragonSteelTier.DRAGONSTEEL_TIER_ICE;
	}

	@Override
	public Tier tierFire() {
		return DragonSteelTier.DRAGONSTEEL_TIER_FIRE;
	}

	@Override
	public Tier tierLightning() {
		return DragonSteelTier.DRAGONSTEEL_TIER_LIGHTNING;
	}

	@Override
	public Supplier<Item> ingotIceSteel() {
		return IafItemRegistry.DRAGONSTEEL_ICE_INGOT;
	}

	@Override
	public Supplier<Item> ingotFireSteel() {
		return IafItemRegistry.DRAGONSTEEL_FIRE_INGOT;
	}

	@Override
	public Supplier<Item> ingotLightningSteel() {
		return IafItemRegistry.DRAGONSTEEL_LIGHTNING_INGOT;
	}

	@Override
	public Supplier<Block> blockIceSteel() {
		return IafBlockRegistry.DRAGONSTEEL_ICE_BLOCK;
	}

	@Override
	public Supplier<Block> blockFireSteel() {
		return IafBlockRegistry.DRAGONSTEEL_FIRE_BLOCK;
	}

	@Override
	public Supplier<Block> blockLightningSteel() {
		return IafBlockRegistry.DRAGONSTEEL_LIGHTNING_BLOCK;
	}

}
