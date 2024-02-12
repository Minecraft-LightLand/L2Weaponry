package dev.xkmc.l2weaponry.compat.dragons;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.item.DragonSteelTier;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.l2damagetracker.contents.materials.api.IMatToolType;
import dev.xkmc.l2library.serial.recipe.ConditionalRecipeWrapper;
import dev.xkmc.l2weaponry.compat.ModMats;
import dev.xkmc.l2weaponry.init.materials.ILWToolMats;
import dev.xkmc.l2weaponry.init.materials.LWToolTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;
import java.util.function.Supplier;

public enum DragonToolMats implements ILWToolMats {
	ICE_DRAGONSTEEL(new ModMats(DragonSteelTier.DRAGONSTEEL_TIER_ICE, new IceDragonBoneTool()), true, IafItemRegistry.DRAGONSTEEL_ICE_INGOT, IafBlockRegistry.DRAGONSTEEL_ICE_BLOCK),
	FIRE_DRAGONSTEEL(new ModMats(DragonSteelTier.DRAGONSTEEL_TIER_FIRE, new FireDragonBoneTool()), true, IafItemRegistry.DRAGONSTEEL_FIRE_INGOT, IafBlockRegistry.DRAGONSTEEL_FIRE_BLOCK),
	LIGHTNING_DRAGONSTEEL(new ModMats(DragonSteelTier.DRAGONSTEEL_TIER_LIGHTNING, new LightningDragonBoneTool()), true, IafItemRegistry.DRAGONSTEEL_LIGHTNING_INGOT, IafBlockRegistry.DRAGONSTEEL_LIGHTNING_BLOCK),
	;

	private final IMatToolType type;
	private final boolean fireRes;
	private final Supplier<Item> ingot;
	private final Supplier<Block> block;

	DragonToolMats(IMatToolType type, boolean fireRes, Supplier<Item> ingot, Supplier<Block> block) {
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
		return DragonCompat.ITEMS[ordinal()][type.ordinal()].get();
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
		return IafItemRegistry.WITHERBONE.get();
	}

	@Override
	public Consumer<FinishedRecipe> getProvider(RegistrateRecipeProvider pvd) {
		return ConditionalRecipeWrapper.mod(pvd, IceAndFire.MODID);
	}

	@Override
	public boolean hasTool(LWToolTypes type) {
		return true;
	}

	@Override
	public boolean isOptional() {
		return true;
	}

}
