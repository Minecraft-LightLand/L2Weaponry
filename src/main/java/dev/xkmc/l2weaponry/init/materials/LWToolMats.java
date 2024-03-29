package dev.xkmc.l2weaponry.init.materials;

import dev.xkmc.l2complements.init.materials.LCMats;
import dev.xkmc.l2complements.init.materials.api.IMatToolType;
import dev.xkmc.l2weaponry.init.registrate.LWItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;

import java.util.function.Supplier;

public enum LWToolMats {
	IRON(new VanillaMats(Tiers.IRON), Items.IRON_NUGGET, Items.IRON_INGOT, Items.IRON_BLOCK, false),
	GOLD(new VanillaMats(Tiers.GOLD), Items.GOLD_NUGGET, Items.GOLD_INGOT, Items.GOLD_BLOCK, false),
	DIAMOND(new VanillaMats(Tiers.DIAMOND), Items.AIR, Items.DIAMOND, Items.DIAMOND_BLOCK, false),
	NETHERITE(new VanillaMats(Tiers.NETHERITE), Items.AIR, Items.NETHERITE_INGOT, Items.NETHERITE_BLOCK, true),
	TOTEMIC_GOLD(LCMats.TOTEMIC_GOLD, false),
	POSEIDITE(LCMats.POSEIDITE, false),
	SHULKERATE(LCMats.SHULKERATE, false),
	SCULKIUM(LCMats.SCULKIUM, false),
	ETERNIUM(LCMats.ETERNIUM, false);

	public final IMatToolType type;
	private final Supplier<Item> nugget, ingot, block, toolIngot, handle;
	final boolean fireRes;

	LWToolMats(IMatToolType type, Item nugget, Item ingot, Item block, boolean fireRes) {
		this.type = type;
		this.nugget = () -> nugget;
		this.ingot = () -> ingot;
		this.block = () -> block;
		this.fireRes = fireRes;
		this.toolIngot = this.ingot;
		this.handle = LWItems.HANDLE;
	}

	LWToolMats(LCMats type, boolean fireRes) {
		this.type = type;
		nugget = type::getNugget;
		ingot = type::getIngot;
		block = () -> type.getBlock().asItem();
		toolIngot = type::getToolIngot;
		handle = type::getToolStick;
		this.fireRes = fireRes;
	}

	public Item getIngot() {
		return ingot.get();
	}

	public Item getToolIngot() {
		return toolIngot.get();
	}

	public Item getStick() {
		return handle.get();
	}

	public Item getBlock() {
		return block.get();
	}

	public Item getTool(LWToolTypes type) {
		return LWItems.GEN_ITEM[ordinal()][type.ordinal()].get();
	}

	public Item getNugget() {
		return nugget.get();
	}
}
