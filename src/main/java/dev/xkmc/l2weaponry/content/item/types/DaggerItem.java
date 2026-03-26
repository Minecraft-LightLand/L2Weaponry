package dev.xkmc.l2weaponry.content.item.types;

import com.google.common.collect.ImmutableMultimap;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2library.util.math.MathHelper;
import dev.xkmc.l2weaponry.content.entity.BaseThrownWeaponEntity;
import dev.xkmc.l2weaponry.content.entity.DaggerEntity;
import dev.xkmc.l2weaponry.content.item.base.BaseThrowableWeaponItem;
import dev.xkmc.l2weaponry.init.data.LWConfig;
import dev.xkmc.l2weaponry.init.data.LangData;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DaggerItem extends BaseThrowableWeaponItem {

	public static final AttributeModifier RANGE = new AttributeModifier(MathHelper.getUUIDFromString("dagger_reach"), "dagger_reach", -1, AttributeModifier.Operation.ADDITION);

	public DaggerItem(Tier tier, int damage, float speed, Properties prop, ExtraToolConfig config) {
		super(tier, damage, speed, prop, config, BlockTags.MINEABLE_WITH_HOE);
	}

	@Override
	protected void addModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
		super.addModifiers(builder);
		builder.put(ForgeMod.ENTITY_REACH.get(), RANGE);
	}

	@Override
	public BaseThrownWeaponEntity<?> getProjectile(Level level, LivingEntity player, ItemStack stack, int slot) {
		return new DaggerEntity(level, player, stack, slot);
	}

	@Override
	protected void shoot(AbstractArrow proj, ItemStack stack, Level level, Player player) {
		proj.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 3f, 0);
	}

	public float getMultiplier(AttackCache event) {
		return event.getAttackTarget() instanceof Mob le && le.getTarget() != event.getAttacker() ? (float) (double) LWConfig.COMMON.dagger_bonus.get() : 1;
	}

	protected int getInstantThrowCoolDown() {
		return LWConfig.COMMON.daggerInstantThrowCooldown.get();
	}

	@Override
	public boolean playerThrowable() {
		return LWConfig.COMMON.daggerThrowable.get();
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
		list.add(LangData.TOOL_DAGGER.get());
		if (playerThrowable())
			list.add(LangData.TOOL_THROW_DAGGER.get());
		super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
	}

}
