package dev.xkmc.l2weaponry.content.item.types;

import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2weaponry.content.entity.BaseThrownWeaponEntity;
import dev.xkmc.l2weaponry.content.entity.DaggerEntity;
import dev.xkmc.l2weaponry.content.item.base.BaseThrowableWeaponItem;
import dev.xkmc.l2weaponry.init.data.LWConfig;
import dev.xkmc.l2weaponry.init.data.LangData;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class DaggerItem extends BaseThrowableWeaponItem {

	public DaggerItem(Tier tier, Properties prop, ExtraToolConfig config) {
		super(tier, prop, config, BlockTags.MINEABLE_WITH_HOE);
	}

	@Override
	public BaseThrownWeaponEntity<?> getProjectile(Level level, LivingEntity player, ItemStack stack, int slot) {
		return new DaggerEntity(level, player, stack, slot);
	}

	@Override
	protected void shoot(AbstractArrow proj, ItemStack stack, Level level, Player player) {
		proj.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 3f, 0);
	}

	public float getMultiplier(DamageData.Offence event) {
		return event.getTarget() instanceof Mob le && le.getTarget() != event.getAttacker() ? (float) (double) LWConfig.SERVER.dagger_bonus.get() : 1;
	}

	protected int getInstantThrowCoolDown() {
		return LWConfig.SERVER.daggerInstantThrowCooldown.get();
	}

	@Override
	public boolean playerThrowable() {
		return LWConfig.RECIPE.daggerThrowable.get();
	}

	@Override
	public void appendHoverText(ItemStack pStack, TooltipContext pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
		list.add(LangData.TOOL_DAGGER.get());
		if (playerThrowable())
			list.add(LangData.TOOL_THROW_DAGGER.get());
		super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
	}

}
