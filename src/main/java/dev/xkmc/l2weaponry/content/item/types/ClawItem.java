package dev.xkmc.l2weaponry.content.item.types;

import com.google.common.collect.ImmutableMultimap;
import dev.xkmc.l2complements.content.item.generic.ExtraToolConfig;
import dev.xkmc.l2library.util.math.MathHelper;
import dev.xkmc.l2weaponry.content.item.base.BaseClawItem;
import dev.xkmc.l2weaponry.init.data.LangData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClawItem extends BaseClawItem {

	public static final AttributeModifier RANGE = new AttributeModifier(MathHelper.getUUIDFromString("claw_reach"), "claw_reach", -1, AttributeModifier.Operation.ADDITION);

	public ClawItem(Tier tier, int damage, float speed, Properties prop, ExtraToolConfig config) {
		super(tier, damage, speed, prop, config);
	}

	@Override
	protected void addModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
		super.addModifiers(builder);
		builder.put(ForgeMod.ATTACK_RANGE.get(), RANGE);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
		list.add(LangData.TOOL_CLAW.get());
		list.add(LangData.TOOL_CLAW_EXTRA.get());
		super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
	}

}
