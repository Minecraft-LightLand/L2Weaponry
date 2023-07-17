package dev.xkmc.l2weaponry.compat.twilightforest;

import dev.xkmc.l2damagetracker.contents.materials.api.*;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2damagetracker.contents.materials.vanilla.GenItemVanillaType;
import net.minecraft.world.item.Tier;

public record TFMats(Tier tier, ExtraToolConfig config) implements IMatToolType, IToolStats {

	@Override
	public Tier getTier() {
		return tier;
	}

	@Override
	public IToolStats getToolStats() {
		return this;
	}

	@Override
	public ToolConfig getToolConfig() {
		return GenItemVanillaType.TOOL_GEN;
	}

	@Override
	public ExtraToolConfig getExtraToolConfig() {
		return config;
	}


	public int durability() {
		return this.tier.getUses();
	}

	public int speed() {
		return Math.round(this.tier.getSpeed());
	}

	public int enchant() {
		return this.tier.getEnchantmentValue();
	}

	public int getDamage(ITool tool) {
		return tool.getDamage(Math.round(this.tier.getAttackDamageBonus()) + 4);
	}

	public float getSpeed(ITool tool) {
		return tool.getSpeed(1.0F);
	}

}
