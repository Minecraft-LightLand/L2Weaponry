package dev.xkmc.l2weaponry.init.data;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class LWConfig {

	public static class Client {

		Client(ForgeConfigSpec.Builder builder) {
		}

	}

	public static class Common {

		public final ForgeConfigSpec.DoubleValue dagger_bonus;
		public final ForgeConfigSpec.DoubleValue claw_bonus;
		public final ForgeConfigSpec.IntValue claw_max;
		public final ForgeConfigSpec.IntValue claw_timeout;
		public final ForgeConfigSpec.IntValue claw_block_time;
		public final ForgeConfigSpec.DoubleValue reflectCost;
		public final ForgeConfigSpec.BooleanValue defaultEnchantmentOnWeapons;
		public final ForgeConfigSpec.BooleanValue diggerEnchantmentOnWeapon;
		public final ForgeConfigSpec.ConfigValue<List<String>> extraCompatibleEnchantmentCategories;
		public final ForgeConfigSpec.BooleanValue daggerThrowable;
		public final ForgeConfigSpec.BooleanValue axeThrowable;
		public final ForgeConfigSpec.BooleanValue javelinThrowable;

		public final ForgeConfigSpec.IntValue shadowHunterDistance;
		public final ForgeConfigSpec.IntValue hauntingDemonDistance;
		public final ForgeConfigSpec.IntValue hammerOfIncarcerationRadius;
		public final ForgeConfigSpec.IntValue hammerOfIncarcerationDuration;
		public final ForgeConfigSpec.DoubleValue dogmaticStandoffGain;
		public final ForgeConfigSpec.DoubleValue dogmaticStandoffMax;
		public final ForgeConfigSpec.DoubleValue determinationRate;
		public final ForgeConfigSpec.DoubleValue illusionRate;
		public final ForgeConfigSpec.DoubleValue deathScytheMax;

		public final ForgeConfigSpec.DoubleValue heavySpeedReduction;
		public final ForgeConfigSpec.DoubleValue heavyCritBonus;
		public final ForgeConfigSpec.DoubleValue stealthChance;
		public final ForgeConfigSpec.DoubleValue stealthDamageReduction;
		public final ForgeConfigSpec.DoubleValue heavyShieldSpeedReduction;
		public final ForgeConfigSpec.DoubleValue heavyShieldDefenseBonus;
		public final ForgeConfigSpec.DoubleValue hardShieldDefenseBonus;
		public final ForgeConfigSpec.DoubleValue raisedSpiritSpeedBonus;
		public final ForgeConfigSpec.DoubleValue energizedWillReachBonus;
		public final ForgeConfigSpec.DoubleValue thinBladeAttackSpeedBonus;
		public final ForgeConfigSpec.DoubleValue thinBladeAttackReduction;
		public final ForgeConfigSpec.IntValue instantThrowCooldown;
		public final ForgeConfigSpec.IntValue daggerInstantThrowCooldown;


		public final ForgeConfigSpec.DoubleValue knightmetalBonus;
		public final ForgeConfigSpec.DoubleValue knightmetalReflect;
		public final ForgeConfigSpec.DoubleValue fieryBonus;
		public final ForgeConfigSpec.IntValue fieryDuration;
		public final ForgeConfigSpec.DoubleValue steeleafBonus;
		public final ForgeConfigSpec.DoubleValue steeleafReflect;
		public final ForgeConfigSpec.DoubleValue steeleafChance;
		public final ForgeConfigSpec.IntValue ironwoodRegenDuration;
		public final ForgeConfigSpec.IntValue ironwoodEffectDuration;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("Weapon Type Features");
			dagger_bonus = builder.comment("Dagger damage multiplier when hitting targets not targeting user")
					.defineInRange("dagger_bonus", 2d, 1, 1000);
			claw_bonus = builder.comment("Claw damage bonus for each consecutive hit")
					.defineInRange("claw_bonus", 0.1d, 0, 10);
			claw_max = builder.comment("Claw damage bonus maximum hit for one claw (two claw has double maximum)")
					.defineInRange("claw_max", 5, 1, 1000);
			claw_timeout = builder.comment("Claw damage bonus timeout")
					.defineInRange("claw_timeout", 60, 1, 1000);
			claw_block_time = builder.comment("Claw block damage time")
					.defineInRange("claw_block_time", 3, 0, 1000);
			reflectCost = builder.comment("Shield reflect cost")
					.defineInRange("reflectCost", 0.2, 0, 1);
			daggerThrowable = builder.comment("Allow player to throw dagger")
					.define("daggerThrowable", true);
			axeThrowable = builder.comment("Allow player to throw throwing axe")
					.define("axeThrowable", true);
			javelinThrowable = builder.comment("Allow player to throw javelin")
					.define("javelinThrowable", true);
			diggerEnchantmentOnWeapon = builder.comment("Allow digger enchantments on weapon")
					.define("diggerEnchantmentOnWeapon", true);
			defaultEnchantmentOnWeapons = builder.comment("Default enchantments on crafted weapons")
					.define("defaultEnchantmentOnWeapons", true);
			extraCompatibleEnchantmentCategories = builder
					.comment("List of enchantment categories for weapons. Use upper case enum name.")
					.comment("For modded enchantment categories, find them in their code through GitHub")
					.comment("Example: 'WEAPON', 'DIGGER'")
					.define("extraCompatibleEnchantmentCategories", new ArrayList<>(List.of()));

			builder.pop();
			builder.push("Legendary Weapon Effects");
			shadowHunterDistance = builder.comment("Shadow Hunter teleport distance")
					.defineInRange("shadowHunterDistance", 8, 1, 128);
			hauntingDemonDistance = builder.comment("Haunting Demon of the End teleport distance")
					.defineInRange("hauntingDemonDistance", 64, 1, 128);
			hammerOfIncarcerationRadius = builder.comment("Hammer of Incarceration effect radius")
					.defineInRange("hammerOfIncarcerationRadius", 8, 1, 64);
			hammerOfIncarcerationDuration = builder.comment("Hammer of Incarceration effect duration")
					.defineInRange("hammerOfIncarcerationDuration", 60, 1, 60000);
			dogmaticStandoffGain = builder.comment("Dogmatic Standoff absorption gain percentage")
					.defineInRange("dogmaticStandoffGain", 0.02, 0.0001, 1);
			dogmaticStandoffMax = builder.comment("Dogmatic Standoff absorption max percentage")
					.defineInRange("dogmaticStandoffMax", 0.1, 0.0001, 100);
			determinationRate = builder.comment("Claw of Determination increase rate")
					.defineInRange("determinationRate", 2d, 0, 100);
			illusionRate = builder.comment("Blade of illusion increase rate")
					.defineInRange("illusionRate", 1d, 0, 100);
			deathScytheMax = builder.comment("Increase damage by this factor of percentage of target health lost")
					.defineInRange("deathScytheMax", 1d, 0, 100);
			builder.pop();
			builder.push("Enchantments");
			heavySpeedReduction = builder.comment("Heavy enchantment reduction on attack speed")
					.defineInRange("heavySpeedReduction", 0.2, 0.0001, 100);
			heavyCritBonus = builder.comment("Heavy enchantment crit damage bonus")
					.defineInRange("heavyCritBonus", 0.3, 0.0001, 100);
			stealthChance = builder.comment("Stealth enchantment no aggro chance")
					.defineInRange("stealthChance", 0.2, 0.0001, 100);
			stealthDamageReduction = builder.comment("Stealth enchantment damage reduction")
					.defineInRange("stealthDamageReduction", 0.1, 0.0001, 100);
			heavyShieldSpeedReduction = builder.comment("HeavyShield enchantment reduction on attack speed")
					.defineInRange("heavyShieldSpeedReduction", 0.1, 0.0001, 100);
			heavyShieldDefenseBonus = builder.comment("HeavyShield enchantment defense bonus")
					.defineInRange("heavyShieldDefenseBonus", 0.1, 0.0001, 100);
			hardShieldDefenseBonus = builder.comment("HardShield enchantment defense bonus")
					.defineInRange("hardShieldDefenseBonus", 0.05, 0.0001, 100);
			raisedSpiritSpeedBonus = builder.comment("Raised Spirit enchantment bonus on attack speed per stacking level per enchantment level")
					.defineInRange("raisedSpiritSpeedBonus", 0.01, 0.0001, 100);
			energizedWillReachBonus = builder.comment("Energized Will enchantment bonus on attack range per stacking level per enchantment level")
					.defineInRange("energizedWillReachBonus", 0.02, 0.0001, 100);
			instantThrowCooldown = builder.comment("Cooldown for Instant Throwing")
					.defineInRange("instantThrowCooldown", 60, 1, 6000);
			daggerInstantThrowCooldown = builder.comment("Cooldown for Dagger Instant Throwing")
					.defineInRange("daggerInstantThrowCooldown", 15, 1, 6000);
			thinBladeAttackSpeedBonus = builder.comment("Thin Blade enchantment attack speed bonus")
					.defineInRange("thinBladeAttackSpeedBonus", 0.2, 0.0001, 100);
			thinBladeAttackReduction = builder.comment("Thin Bladeeavy enchantment attack damage reduction")
					.defineInRange("thinBladeAttackReduction", 0.1, 0.0001, 100);
			builder.pop();

			builder.push("Twilight Forest Compat");
			knightmetalBonus = builder.comment("Damage bonus to enemies with armor")
					.defineInRange("knightmetalBonus", 0.2, 0, 1000);
			knightmetalReflect = builder.comment("Extra damage reflection")
					.defineInRange("knightmetalReflect", 0.5, 0, 1000);
			fieryBonus = builder.comment("Damage bonus to enemies not immune to fire")
					.defineInRange("knightmetalBonus", 0.5, 0, 1000);
			fieryDuration = builder.comment("Ignite enemy by seconds")
					.defineInRange("fieryDuration", 15, 0, 1000);
			steeleafBonus = builder.comment("Damage bonus to enemies without armor")
					.defineInRange("steeleafBonus", 0.5, 0, 1000);
			steeleafReflect = builder.comment("Extra damage reflection")
					.defineInRange("steeleafReflect", 0.5, 0, 1000);
			steeleafChance = builder.comment("Effect Application Chance")
					.defineInRange("steeleafChance", 0.5, 0, 1000);
			ironwoodRegenDuration = builder.comment("Regeneration interval (in ticks)")
					.defineInRange("ironwoodRegenDuration", 100, 1, 10000);
			ironwoodEffectDuration = builder.comment("Resistance duration (in ticks)")
					.defineInRange("ironwoodEffectDuration", 100, 1, 10000);
			builder.pop();

		}

	}

	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;
	public static String COMMON_PATH;

	static {
		final Pair<Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = client.getRight();
		CLIENT = client.getLeft();

		final Pair<Common, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = common.getRight();
		COMMON = common.getLeft();
	}

	public static void init() {
		register(ModConfig.Type.CLIENT, CLIENT_SPEC);
		COMMON_PATH = register(ModConfig.Type.COMMON, COMMON_SPEC);
	}

	private static String register(ModConfig.Type type, IConfigSpec<?> spec) {
		var mod = ModLoadingContext.get().getActiveContainer();
		String path = "l2_configs/" + mod.getModId() + "-" + type.extension() + ".toml";
		ModLoadingContext.get().registerConfig(type, spec, path);
		return path;
	}

}
