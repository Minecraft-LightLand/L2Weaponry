package dev.xkmc.l2weaponry.init.data;

import dev.xkmc.l2core.util.ConfigInit;
import dev.xkmc.l2weaponry.init.L2Weaponry;
import net.neoforged.neoforge.common.ModConfigSpec;

public class LWConfig {

	public static class Recipe extends ConfigInit {

		public final ModConfigSpec.BooleanValue defaultEnchantmentOnWeapons;
		public final ModConfigSpec.BooleanValue daggerThrowable;
		public final ModConfigSpec.BooleanValue axeThrowable;
		public final ModConfigSpec.BooleanValue javelinThrowable;

		public Recipe(Builder builder) {
			markL2();
			defaultEnchantmentOnWeapons = builder.text("Default enchantments on crafted weapons")
					.define("defaultEnchantmentOnWeapons", true);
			daggerThrowable = builder.text("Allow player to throw dagger")
					.define("daggerThrowable", true);
			axeThrowable = builder.text("Allow player to throw throwing axe")
					.define("axeThrowable", true);
			javelinThrowable = builder.text("Allow player to throw javelin")
					.define("javelinThrowable", true);

		}

	}

	public static class Server extends ConfigInit {

		public final ModConfigSpec.DoubleValue dagger_bonus;
		public final ModConfigSpec.DoubleValue claw_bonus;
		public final ModConfigSpec.IntValue claw_max;
		public final ModConfigSpec.IntValue claw_timeout;
		public final ModConfigSpec.IntValue claw_block_time;
		public final ModConfigSpec.DoubleValue reflectCost;

		public final ModConfigSpec.IntValue shadowHunterDistance;
		public final ModConfigSpec.IntValue hauntingDemonDistance;
		public final ModConfigSpec.IntValue hammerOfIncarcerationRadius;
		public final ModConfigSpec.IntValue hammerOfIncarcerationDuration;
		public final ModConfigSpec.DoubleValue dogmaticStandoffGain;
		public final ModConfigSpec.DoubleValue dogmaticStandoffMax;
		public final ModConfigSpec.DoubleValue determinationRate;
		public final ModConfigSpec.DoubleValue illusionRate;
		public final ModConfigSpec.DoubleValue deathScytheMax;

		public final ModConfigSpec.DoubleValue heavySpeedReduction;
		public final ModConfigSpec.DoubleValue heavyCritBonus;
		public final ModConfigSpec.DoubleValue stealthChance;
		public final ModConfigSpec.DoubleValue heavyShieldSpeedReduction;
		public final ModConfigSpec.DoubleValue heavyShieldDefenseBonus;
		public final ModConfigSpec.DoubleValue hardShieldDefenseBonus;
		public final ModConfigSpec.DoubleValue raisedSpiritSpeedBonus;
		public final ModConfigSpec.DoubleValue energizedWillReachBonus;
		public final ModConfigSpec.DoubleValue thinBladeAttackSpeedBonus;
		public final ModConfigSpec.DoubleValue thinBladeAttackReduction;
		public final ModConfigSpec.IntValue instantThrowCooldown;
		public final ModConfigSpec.IntValue daggerInstantThrowCooldown;

		public final ModConfigSpec.DoubleValue knightmetalBonus;
		public final ModConfigSpec.DoubleValue knightmetalReflect;
		public final ModConfigSpec.DoubleValue fieryBonus;
		public final ModConfigSpec.IntValue fieryDuration;
		public final ModConfigSpec.DoubleValue steeleafBonus;
		public final ModConfigSpec.DoubleValue steeleafReflect;
		public final ModConfigSpec.DoubleValue steeleafChance;
		public final ModConfigSpec.IntValue ironwoodRegenDuration;
		public final ModConfigSpec.IntValue ironwoodEffectDuration;

		Server(Builder builder) {
			markL2();
			builder.push("weapon_type", "Weapon Type Features");
			dagger_bonus = builder.text("Dagger damage multiplier when hitting targets not targeting user")
					.defineInRange("dagger_bonus", 2d, 1, 1000);
			claw_bonus = builder.text("Claw damage bonus for each consecutive hit")
					.defineInRange("claw_bonus", 0.1d, 0, 10);
			claw_max = builder.text("Claw damage bonus maximum hit for one claw (two claw has double maximum)")
					.defineInRange("claw_max", 5, 1, 1000);
			claw_timeout = builder.text("Claw damage bonus timeout")
					.defineInRange("claw_timeout", 60, 1, 1000);
			claw_block_time = builder.text("Claw block damage time")
					.defineInRange("claw_block_time", 3, 0, 1000);
			reflectCost = builder.text("Shield reflect cost")
					.defineInRange("reflectCost", 0.2, 0, 1);

			builder.pop();
			builder.push("legendary", "Legendary Weapon Effects");
			shadowHunterDistance = builder.text("Shadow Hunter teleport distance")
					.defineInRange("shadowHunterDistance", 8, 1, 128);
			hauntingDemonDistance = builder.text("Haunting Demon of the End teleport distance")
					.defineInRange("hauntingDemonDistance", 64, 1, 128);
			hammerOfIncarcerationRadius = builder.text("Hammer of Incarceration effect radius")
					.defineInRange("hammerOfIncarcerationRadius", 8, 1, 64);
			hammerOfIncarcerationDuration = builder.text("Hammer of Incarceration effect duration")
					.defineInRange("hammerOfIncarcerationDuration", 60, 1, 60000);
			dogmaticStandoffGain = builder.text("Dogmatic Standoff absorption gain percentage")
					.defineInRange("dogmaticStandoffGain", 0.02, 0.0001, 1);
			dogmaticStandoffMax = builder.text("Dogmatic Standoff absorption max percentage")
					.defineInRange("dogmaticStandoffMax", 0.1, 0.0001, 100);
			determinationRate = builder.text("Claw of Determination increase rate")
					.defineInRange("determinationRate", 2d, 0, 100);
			illusionRate = builder.text("Blade of illusion increase rate")
					.defineInRange("illusionRate", 1d, 0, 100);
			deathScytheMax = builder.text("Increase damage by this factor of percentage of target health lost")
					.defineInRange("deathScytheMax", 1d, 0, 100);

			builder.pop();
			builder.push("enchantments", "Enchantments");
			heavySpeedReduction = builder.text("Heavy enchantment reduction on attack speed")
					.defineInRange("heavySpeedReduction", 0.2, 0.0001, 100);
			heavyCritBonus = builder.text("Heavy enchantment crit damage bonus")
					.defineInRange("heavyCritBonus", 0.3, 0.0001, 100);
			stealthChance = builder.text("Stealth enchantment no aggro chance")
					.defineInRange("stealthChance", 0.2, 0.0001, 100);
			heavyShieldSpeedReduction = builder.text("HeavyShield enchantment reduction on attack speed")
					.defineInRange("heavyShieldSpeedReduction", 0.1, 0.0001, 100);
			heavyShieldDefenseBonus = builder.text("HeavyShield enchantment defense bonus")
					.defineInRange("heavyShieldDefenseBonus", 0.1, 0.0001, 100);
			hardShieldDefenseBonus = builder.text("HardShield enchantment defense bonus")
					.defineInRange("hardShieldDefenseBonus", 0.05, 0.0001, 100);
			raisedSpiritSpeedBonus = builder.text("Raised Spirit enchantment bonus on attack speed per stacking level per enchantment level")
					.defineInRange("raisedSpiritSpeedBonus", 0.01, 0.0001, 100);
			energizedWillReachBonus = builder.text("Energized Will enchantment bonus on attack range per stacking level per enchantment level")
					.defineInRange("energizedWillReachBonus", 0.02, 0.0001, 100);
			instantThrowCooldown = builder.text("Cooldown for Instant Throwing")
					.defineInRange("instantThrowCooldown", 60, 1, 6000);
			daggerInstantThrowCooldown = builder.text("Cooldown for Dagger Instant Throwing")
					.defineInRange("daggerInstantThrowCooldown", 15, 1, 6000);
			thinBladeAttackSpeedBonus = builder.text("Thin Blade enchantment attack speed bonus")
					.defineInRange("thinBladeAttackSpeedBonus", 0.2, 0.0001, 100);
			thinBladeAttackReduction = builder.text("Thin Bladeeavy enchantment attack damage reduction")
					.defineInRange("thinBladeAttackReduction", 0.1, 0.0001, 100);
			builder.pop();

			builder.push("twilight", "Twilight Forest Compat");
			knightmetalBonus = builder.text("Damage bonus to enemies with armor")
					.defineInRange("knightmetalBonus", 0.5, 0, 1000);
			knightmetalReflect = builder.text("Extra damage reflection")
					.defineInRange("knightmetalReflect", 0.5, 0, 1000);
			fieryBonus = builder.text("Damage bonus to enemies not immune to fire")
					.defineInRange("fieryBonus", 0.5, 0, 1000);
			fieryDuration = builder.text("Ignite enemy by seconds")
					.defineInRange("fieryDuration", 15, 0, 1000);
			steeleafBonus = builder.text("Damage bonus to enemies without armor")
					.defineInRange("steeleafBonus", 0.5, 0, 1000);
			steeleafReflect = builder.text("Extra damage reflection")
					.defineInRange("steeleafReflect", 0.5, 0, 1000);
			steeleafChance = builder.text("Effect Application Chance")
					.defineInRange("steeleafChance", 0.5, 0, 1000);
			ironwoodRegenDuration = builder.text("Regeneration interval (in ticks)")
					.defineInRange("ironwoodRegenDuration", 100, 1, 10000);
			ironwoodEffectDuration = builder.text("Resistance duration (in ticks)")
					.defineInRange("ironwoodEffectDuration", 100, 1, 10000);
			builder.pop();

		}

	}

	public static final Recipe RECIPE = L2Weaponry.REGISTRATE.registerUnsynced(Recipe::new);
	public static final Server SERVER = L2Weaponry.REGISTRATE.registerSynced(Server::new);

	public static void init() {
	}

}
