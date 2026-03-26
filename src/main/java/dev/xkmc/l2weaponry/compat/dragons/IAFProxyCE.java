package dev.xkmc.l2weaponry.compat.dragons;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.data.component.IafEntityData;
import com.iafenvoy.iceandfire.entity.EntityFireDragon;
import com.iafenvoy.iceandfire.entity.EntityIceDragon;
import com.iafenvoy.iceandfire.item.tool.DragonSteelToolMaterial;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafItems;
import dev.xkmc.l2weaponry.events.LWGeneralEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

public class IAFProxyCE implements IAFProxy {

	@Override
	public void fireHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		try {
			if (IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue()) {
				if (target instanceof EntityIceDragon) {
					target.hurt(user.level().damageSources().inFire(), 13.5F);
				}

				target.setSecondsOnFire(5);
				target.knockback(1.0, user.getX() - target.getX(), user.getZ() - target.getZ());
			}
		} catch (Throwable ignore) {

		}

	}

	@Override
	public void fireDesc(ItemStack stack, List<Component> list) {
		try {
			list.add(Component.translatable("dragon_sword_fire.hurt1").withStyle(ChatFormatting.GREEN));
			if (IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue()) {
				list.add(Component.translatable("dragon_sword_fire.hurt2").withStyle(ChatFormatting.DARK_RED));
			}
		} catch (Throwable ignore) {

		}
	}

	@Override
	public void iceHit(ItemStack stack, LivingEntity target, LivingEntity user) {

		try {
			if (IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue()) {
				if (target instanceof EntityFireDragon) {
					target.hurt(user.level().damageSources().drown(), 13.5F);
				}

				IafEntityData data = IafEntityData.get(target);
				data.frozenData.setFrozen(target, 200);
				target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
				target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 2));
				target.knockback(1.0, user.getX() - target.getX(), user.getZ() - target.getZ());
			}
		} catch (Throwable ignore) {

		}
	}

	@Override
	public void iceDesc(ItemStack stack, List<Component> list) {
		try {
			list.add(Component.translatable("dragon_sword_ice.hurt1").withStyle(ChatFormatting.GREEN));
			if (IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue()) {
				list.add(Component.translatable("dragon_sword_ice.hurt2").withStyle(ChatFormatting.AQUA));
			}
		} catch (Throwable ignore) {

		}
	}

	@Override
	public void lightningHit(ItemStack stack, LivingEntity target, LivingEntity user) {
		try {
			if (IafCommonConfig.INSTANCE.armors.dragonLightningAbility.getValue()) {
				boolean flag = !(user instanceof Player) || !((double) user.attackAnim > 0.2);

				if (!user.level().isClientSide && flag) {
					LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(target.level());

					assert lightningboltentity != null;

					lightningboltentity.getTags().add("iceandfire.bolt_skip_loot");
					lightningboltentity.getTags().add(user.getStringUUID());
					lightningboltentity.addTag(LWGeneralEvents.LIGHTNING);
					lightningboltentity.moveTo(target.position());
					if (!target.level().isClientSide) {
						target.level().addFreshEntity(lightningboltentity);
					}
				}

				if (target instanceof EntityFireDragon || target instanceof EntityIceDragon) {
					target.hurt(user.level().damageSources().lightningBolt(), 9.5F);
				}

				target.knockback(1.0, user.getX() - target.getX(), user.getZ() - target.getZ());
			}
		} catch (Throwable ignore) {

		}

	}

	@Override
	public void lightningDesc(ItemStack stack, List<Component> list) {
		try {
			list.add(Component.translatable("dragon_sword_lightning.hurt1").withStyle(ChatFormatting.GREEN));
			if (IafCommonConfig.INSTANCE.armors.dragonLightningAbility.getValue()) {
				list.add(Component.translatable("dragon_sword_lightning.hurt2").withStyle(ChatFormatting.DARK_PURPLE));
			}
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
		return DragonSteelToolMaterial.createMaterialWithRepairItem(IafItems.DRAGONSTEEL_ICE_INGOT::get, "dragonsteel_tier_ice");
	}

	@Override
	public Tier tierFire() {
		return DragonSteelToolMaterial.createMaterialWithRepairItem(IafItems.DRAGONSTEEL_FIRE_INGOT::get, "dragonsteel_tier_fire");
	}

	@Override
	public Tier tierLightning() {
		return DragonSteelToolMaterial.createMaterialWithRepairItem(IafItems.DRAGONSTEEL_LIGHTNING_INGOT::get, "dragonsteel_tier_lightning");
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
