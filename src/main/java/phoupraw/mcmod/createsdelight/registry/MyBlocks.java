package phoupraw.mcmod.createsdelight.registry;

import com.nhoryzon.mc.farmersdelight.registry.BlocksRegistry;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.relays.elementary.ShaftBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.util.registry.Registry;
import phoupraw.mcmod.createsdelight.block.GrillBlock;
import phoupraw.mcmod.createsdelight.block.PanBlock;
import phoupraw.mcmod.createsdelight.block.SprinklerBlock;
public final class MyBlocks {
	public static final PanBlock PAN = new PanBlock(FabricBlockSettings.copyOf(BlocksRegistry.SKILLET.get()));
	public static final GrillBlock GRILL = new GrillBlock(FabricBlockSettings.copyOf(PAN).nonOpaque());
	public static final SprinklerBlock SPRINKLER = new SprinklerBlock(FabricBlockSettings.copyOf(AllBlocks.MECHANICAL_PRESS.get()));
	static {
		Registry.register(Registry.BLOCK, MyIdentifiers.PAN, PAN);
		Registry.register(Registry.BLOCK, MyIdentifiers.GRILL, GRILL);
		Registry.register(Registry.BLOCK, MyIdentifiers.SPRINKLER, SPRINKLER);
	}
	private MyBlocks() {}
}