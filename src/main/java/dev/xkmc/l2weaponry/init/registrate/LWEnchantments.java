package dev.xkmc.l2weaponry.init.registrate;

import com.tterrag.registrate.builders.EnchantmentBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.l2complements.content.enchantment.core.SingleLevelEnchantment;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2weaponry.content.enchantments.*;
import dev.xkmc.l2weaponry.content.item.base.BaseShieldItem;
import dev.xkmc.l2weaponry.content.item.base.BaseThrowableWeaponItem;
import dev.xkmc.l2weaponry.content.item.base.DoubleWieldItem;
import dev.xkmc.l2weaponry.content.item.base.LWTieredItem;
import dev.xkmc.l2weaponry.content.item.types.ClawItem;
import dev.xkmc.l2weaponry.content.item.types.DaggerItem;
import dev.xkmc.l2weaponry.content.item.types.MacheteItem;
import dev.xkmc.l2weaponry.content.item.types.ScytheItem;
import dev.xkmc.l2weaponry.init.L2Weaponry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LWEnchantments {

	public static final EnchantmentCategory THROWABLE = EnchantmentCategory.create("throwable", (e) ->
			e instanceof BaseThrowableWeaponItem);

	public static final EnchantmentCategory DAGGER = EnchantmentCategory.create("dagger", (e) ->
			e instanceof DaggerItem);

	public static final EnchantmentCategory HEAVY_WEAPON = EnchantmentCategory.create("heavy_weapon", (e) ->
			e instanceof LWTieredItem t && t.isHeavy() || e instanceof AxeItem);

	public static final EnchantmentCategory SHIELDS = EnchantmentCategory.create("shields", e ->
			e instanceof BaseShieldItem);

	public static final EnchantmentCategory MACHETES = EnchantmentCategory.create("machetes", e ->
			e instanceof MacheteItem);

	public static final EnchantmentCategory DOUBLE_WIELD = EnchantmentCategory.create("double_wield", e ->
			e instanceof DoubleWieldItem);

	public static final EnchantmentCategory CLAW = EnchantmentCategory.create("claws", e ->
			e instanceof ClawItem);

	public static final EnchantmentCategory SCYTHE = EnchantmentCategory.create("scythes", e ->
			e instanceof ScytheItem);

	public static final RegistryEntry<EnderHandEnchantment> ENDER_HAND;
	public static final RegistryEntry<ProjectionEnchantment> PROJECTION;
	public static final RegistryEntry<SingleLevelEnchantment> INSTANT_THROWING;
	public static final RegistryEntry<StealthEnchantment> NO_AGGRO;
	public static final RegistryEntry<HeavyEnchantment> HEAVY;
	public static final RegistryEntry<HardShieldEnchantment> HARD_SHIELD;
	public static final RegistryEntry<HeavyShieldEnchantment> HEAVY_SHIELD;
	public static final RegistryEntry<EnergizedWillEnchantment> ENERGIZED_WILL;
	public static final RegistryEntry<RaisedSpiritEnchantment> RAISED_SPIRIT;
	public static final RegistryEntry<SingleLevelEnchantment> GHOST_SLASH;
	public static final RegistryEntry<ClawBlockEnchantment> CLAW_BLOCK;
	public static final RegistryEntry<ThinBladeEnchantment> THIN_BLADE;


	static {
		ENDER_HAND = reg("ender_hand", THROWABLE, EnderHandEnchantment::new,
				"Thrown attacks will appear as direct hit.")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();

		PROJECTION = reg("projection", THROWABLE, ProjectionEnchantment::new,
				"Thrown attacks will not consume the used weapon")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();

		INSTANT_THROWING = reg("instant_shot", THROWABLE, SingleLevelEnchantment::new,
				"Throw the weapon out immediately on right click when not sneaking")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();

		NO_AGGRO = reg("stealth", DAGGER, StealthEnchantment::new,
				"Dagger damage has a chance to not aggravate enemy, but reduce damage.")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();

		HEAVY = reg("heavy", HEAVY_WEAPON, HeavyEnchantment::new,
				"Reduce attack speed, increase critical hit and projectile damage. Works on Axe and heavy weapons.")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();

		HARD_SHIELD = reg("hard_shield", SHIELDS, HardShieldEnchantment::new,
				"Increase shield defense. Works for both hands.")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)
				.defaultLang().register();

		HEAVY_SHIELD = reg("heavy_shield", SHIELDS, HeavyShieldEnchantment::new,
				"Reduce movement speed, increase shield defense by a lot in main hand.")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();

		ENERGIZED_WILL = reg("energized_will", MACHETES, EnergizedWillEnchantment::new,
				"Gradually increase machete attack range when stacking consecutive attacks. Conflicts with Raised Spirit.")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();

		RAISED_SPIRIT = reg("raised_spirit", MACHETES, RaisedSpiritEnchantment::new,
				"Gradually increase machete attack speed when stacking consecutive attacks. Conflicts with Energized Will.")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();

		GHOST_SLASH = reg("ghost_slash", DOUBLE_WIELD, SingleLevelEnchantment::new,
				"Empty hits will stack hit count and consume durability as well.")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();

		CLAW_BLOCK = reg("claw_shielding", CLAW, ClawBlockEnchantment::new,
				"Increase damage blocking time for claws. Works on either hand")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)
				.defaultLang().register();

		THIN_BLADE = reg("thin_blade", SCYTHE, ThinBladeEnchantment::new,
				"Reduce attack damage, increase attack speed. Works on scythe.")
				.rarity(Enchantment.Rarity.RARE).addSlots(EquipmentSlot.MAINHAND)
				.defaultLang().register();
	}

	private static <T extends Enchantment> EnchantmentBuilder<T, L2Registrate> reg(String id, EnchantmentCategory category, EnchantmentBuilder.EnchantmentFactory<T> fac, String desc) {
		return L2Weaponry.REGISTRATE.enchantment(id, category, fac, desc);
	}

	public static void register() {

	}

}
