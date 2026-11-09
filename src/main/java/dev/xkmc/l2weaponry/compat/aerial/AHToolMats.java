package dev.xkmc.l2weaponry.compat.aerial;

import com.google.common.collect.Lists;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.l2damagetracker.contents.materials.api.IMatToolType;
import dev.xkmc.l2damagetracker.contents.materials.generic.ExtraToolConfig;
import dev.xkmc.l2library.serial.recipe.ConditionalRecipeWrapper;
import dev.xkmc.l2weaponry.compat.ModMats;
import dev.xkmc.l2weaponry.init.materials.ILWToolMats;
import dev.xkmc.l2weaponry.init.materials.LWToolTypes;
import dev.xkmc.l2weaponry.init.registrate.LWItems;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Item.Material.ToolMaterials;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum AHToolMats implements ILWToolMats {
	ARSONIST(new ModMats(() -> tier("ARSONIST", () -> ToolMaterials.arsonist), new ArsonistTool()), true, AerialHellBlocksAndItems.ARSONIST_INGOT, AerialHellBlocksAndItems.ARSONIST_BLOCK),
	LUNAR(new ModMats(() -> tier("LUNATIC", () -> ToolMaterials.lunatic), new ExtraToolConfig()), false, AerialHellBlocksAndItems.LUNATIC_CRYSTAL, AerialHellBlocksAndItems.LUNATIC_CRYSTAL_BLOCK),
	RUBY(new ModMats(() -> tier("RUBY", () -> ToolMaterials.ruby), new ExtraToolConfig()), false, AerialHellBlocksAndItems.RUBY, AerialHellBlocksAndItems.RUBY_BLOCK),
	VOLUCITE(new ModMats(() -> tier("VOLUCITE", () -> ToolMaterials.volucite), new VoluciteTool()), false, AerialHellBlocksAndItems.VOLUCITE_VIBRANT, AerialHellBlocksAndItems.VOLUCITE_BLOCK);

	/**
	 * Aerial Hell 0.7.7.9 replaced ToolMaterials by AerialHellToolMaterials (tier given by vanillaMaterial()).
	 * Uses the new class when present, and falls back to the old one for older Aerial Hell versions.
	 */
	private static Tier tier(String name, Supplier<Tier> legacy) {
		try {
			Object material = Class.forName("fr.factionbedrock.aerialhell.Item.Material.AerialHellToolMaterials").getField(name).get(null);
			return (Tier) material.getClass().getMethod("vanillaMaterial").invoke(material);
		} catch (ReflectiveOperationException e) {
			return legacy.get();
		}
	}

	private final IMatToolType type;
	private final boolean fireRes;
	private final Supplier<Item> ingot;
	private final Supplier<Block> block;

	AHToolMats(IMatToolType type, boolean fireRes, Supplier<Item> ingot, Supplier<Block> block) {
		this.type = type;
		this.fireRes = fireRes;
		this.ingot = ingot;
		this.block = block;
	}

	@Override
	public IMatToolType type() {
		return type;
	}

	@Override
	public boolean fireRes() {
		return fireRes;
	}

	@Override
	public Item getTool(LWToolTypes type) {
		return AHCompat.ITEMS[ordinal()][type.ordinal()].get();
	}

	@Override
	public Item getIngot() {
		return ingot.get();
	}

	@Override
	public Item getBlock() {
		return block.get().asItem();
	}

	@Override
	public Item getStick() {
		return LWItems.HANDLE.get();
	}

	@Override
	public Consumer<FinishedRecipe> getProvider(RegistrateRecipeProvider pvd, ICondition... cond) {
		List<ICondition> list = Lists.asList(new ModLoadedCondition(AerialHell.MODID), cond);
		return ConditionalRecipeWrapper.of(pvd, list.toArray(ICondition[]::new));
	}

	@Override
	public boolean hasTool(LWToolTypes type) {
		if (type == LWToolTypes.NUNCHAKU) {
			return false;
		}
		if (type == LWToolTypes.PLATE_SHIELD || type == LWToolTypes.ROUND_SHIELD) {
			return this != RUBY && this != LUNAR;
		}
		return ILWToolMats.super.hasTool(type);
	}

	@Override
	public String prefix() {
		if (this == ARSONIST) {
			return "§c";
		} else if (this == LUNAR) {
			return "§6";
		} else if (this == VOLUCITE) {
			return "§2";
		}
		return "";
	}

	@Override
	public boolean isOptional() {
		return true;
	}


}
