package dev.xkmc.l2weaponry.content.enchantments;

import dev.xkmc.l2complements.content.enchantment.core.AttributeEnchantment;
import dev.xkmc.l2complements.content.enchantment.core.UnobtainableEnchantment;
import dev.xkmc.l2library.util.math.MathHelper;
import dev.xkmc.l2weaponry.init.data.LWConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.ItemAttributeModifierEvent;

import java.util.UUID;

public class ThinBladeEnchantment extends UnobtainableEnchantment implements AttributeEnchantment {

	private static final String NAME_DMG = "thin_blade_enchantment_dmg";
	private static final String NAME_SPEED = "thin_blade_enchantment_speed";

	private static final UUID ID_DMG = MathHelper.getUUIDFromString(NAME_DMG);
	private static final UUID ID_SPEED = MathHelper.getUUIDFromString(NAME_SPEED);

	public ThinBladeEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
		super(pRarity, pCategory, pApplicableSlots);
	}

	@Override
	public void addAttributes(int level, ItemAttributeModifierEvent event) {
		if (event.getSlotType() == EquipmentSlot.MAINHAND) {
			event.addModifier(Attributes.ATTACK_SPEED, new AttributeModifier(ID_SPEED, NAME_SPEED, LWConfig.COMMON.thinBladeAttackSpeedBonus.get() * level, AttributeModifier.Operation.MULTIPLY_TOTAL));
			event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(ID_DMG, NAME_DMG, -LWConfig.COMMON.thinBladeAttackReduction.get() * level, AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
	}

	public ChatFormatting getColor() {
		return ChatFormatting.LIGHT_PURPLE;
	}

	public int getMinLevel() {
		return 1;
	}

	public int getMaxLevel() {
		return 5;
	}

}
