package dev.xkmc.l2weaponry.content.enchantments;

import dev.xkmc.l2complements.content.enchantment.core.SingleLevelEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class ProjectionEnchantment extends SingleLevelEnchantment {

	public ProjectionEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(rarity, category, slots);
	}

	@Override
	protected boolean checkCompatibility(Enchantment other) {
		if (other == Enchantments.LOYALTY) {
			return false;
		}
		return super.checkCompatibility(other);
	}
}