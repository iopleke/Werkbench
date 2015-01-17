package werkbench;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import werkbench.bench.BenchBlock;
import werkbench.bench.BenchTileEntity;
import werkbench.handler.GUIHandler;
import werkbench.helper.LogHelper;
import werkbench.reference.Compendium;

@Mod(modid = Compendium.Naming.id)
public class Werkbench
{

	// Instancing
	@Instance(value = Compendium.Naming.id)
	public static Werkbench INSTANCE;

	// Public metadata about the mod, used by Forge for display on the client's mod list
	@Mod.Metadata(Compendium.Naming.id)
	public static ModMetadata metadata;

	public static BenchBlock werkbench;

	public static GUIHandler guiHandler = new GUIHandler();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Register instance.
		INSTANCE = this;

		LogHelper.debug("Set Werkbench MetaData info...");
		metadata = Compendium.MetaData.init(metadata);

		LogHelper.debug("Registering Werkbench block...");
		werkbench = new BenchBlock();
		GameRegistry.registerBlock(werkbench, Compendium.Naming.block);
		GameRegistry.registerTileEntity(BenchTileEntity.class, Compendium.Naming.tileEntity);

		LogHelper.debug("Registering GUI handler for werkbench...");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);

		LogHelper.debug("Werkbench done loading");
	}
}
