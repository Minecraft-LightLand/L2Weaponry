package dev.xkmc.l2weaponry.content.item.types;

import dev.xkmc.l2complements.content.item.generic.ExtraToolConfig;
import dev.xkmc.l2weaponry.content.item.base.SlowWieldItem;
import dev.xkmc.l2weaponry.init.data.LangData;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BattleAxeItem extends SlowWieldItem {

	public BattleAxeItem(Tier tier, int damage, float speed, Properties prop, ExtraToolConfig config) {
		super(tier, damage, speed, prop, config, BlockTags.MINEABLE_WITH_AXE);
	}

	@Override
	protected boolean canSweep() {
		return true;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> list, TooltipFlag pIsAdvanced) {
		list.add(LangData.TOOL_BATTLE_AXE.get());
		super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
	}

}
