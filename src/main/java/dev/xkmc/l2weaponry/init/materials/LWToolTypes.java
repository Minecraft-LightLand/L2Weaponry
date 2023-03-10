package dev.xkmc.l2weaponry.init.materials;

import dev.xkmc.l2complements.content.item.generic.ExtraToolConfig;
import dev.xkmc.l2complements.init.materials.api.ITool;
import dev.xkmc.l2complements.init.materials.vanilla.RawToolFactory;
import dev.xkmc.l2weaponry.content.item.types.*;
import dev.xkmc.l2weaponry.init.data.TagGen;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public enum LWToolTypes implements ITool {
	CLAW(TagGen.CLAW, ClawItem::new, 0.7f, 2, false),
	DAGGER(TagGen.DAGGER, DaggerItem::new, 0.7f, 4, false),
	HAMMER(TagGen.HAMMER, HammerItem::new, 2f, 0.7f, false),
	BATTLE_AXE(TagGen.BATTLE_AXE, BattleAxeItem::new, 2f, 0.7f, false),
	SPEAR(TagGen.SPEAR, SpearItem::new, 1f, 1.2f, true),
	MACHETE(TagGen.MACHETE, MacheteItem::new, 1.4f, 0.7f, false),

	ROUND_SHIELD(TagGen.ROUND_SHIELD, RoundShieldItem::new, 5f, 1f / 2, false),
	PLATE_SHIELD(TagGen.PLATE_SHIELD, PlateShieldItem::new, 20f, 1f / 8, false),

	THROWING_AXE(TagGen.THROWIG_AXE, ThrowingAxeItem::new, 1.4f, 1f, false),
	JAVELIN(TagGen.JAVELIN, JavelinItem::new, 1f, 1.2f, true);

	public final TagKey<Item> tag;
	private final RawToolFactory fac;
	private final float damage, speed;
	private final boolean longItem;

	LWToolTypes(TagKey<Item> tag, RawToolFactory fac, float damage, float speed, boolean longItem) {
		this.tag = tag;
		this.fac = fac;
		this.damage = damage;
		this.speed = speed;
		this.longItem = longItem;
	}

	@Override
	public int getDamage(int base_damage) {
		return Math.round(base_damage * damage);
	}

	@Override
	public float getSpeed(float base_speed) {
		return Math.round(base_speed * speed * 10) * 0.1f;
	}

	@Override
	public Item create(Tier tier, int i, float v, Item.Properties properties, ExtraToolConfig extraToolConfig) {
		return fac.get(tier, i, v, properties, extraToolConfig);
	}

	public boolean longItem() {
		return longItem;
	}

	public <T extends Item> LegendaryTool<T> legendary(LegendaryToolFactory<T> fac) {
		return new LegendaryTool<>(this, fac);
	}

}
