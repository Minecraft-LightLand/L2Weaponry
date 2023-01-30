package dev.xkmc.l2weaponry.content.item.types;

import dev.xkmc.l2complements.content.item.generic.ExtraToolConfig;
import dev.xkmc.l2weaponry.content.item.base.GenericShieldItem;
import net.minecraft.world.item.Tier;

public class HeavyShieldItem extends GenericShieldItem {

	public HeavyShieldItem(Tier tier, int maxDefense, float recover, Properties prop, ExtraToolConfig config) {
		super(tier, prop, config, maxDefense, recover, false);
	}

}
