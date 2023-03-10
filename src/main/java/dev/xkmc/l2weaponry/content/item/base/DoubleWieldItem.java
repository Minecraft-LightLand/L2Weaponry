package dev.xkmc.l2weaponry.content.item.base;

import dev.xkmc.l2complements.content.item.generic.ExtraToolConfig;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

public class DoubleWieldItem extends GenericWeaponItem {

	public DoubleWieldItem(Tier tier, int damage, float speed, Properties prop, ExtraToolConfig config, TagKey<Block> blocks) {
		super(tier, damage, speed, prop, config, blocks);
	}

	@Override
	protected final boolean canSweep() {
		return true;
	}

	@Override
	public @NotNull AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
		if (player.getOffhandItem().getItem() == this) {
			double r = player.getAttributeValue(ForgeMod.ATTACK_RANGE.get());
			return player.getBoundingBox().inflate(r, r, r);
		} else {
			return player.getBoundingBox().inflate(1, 0.25, 1);
		}
	}

}
