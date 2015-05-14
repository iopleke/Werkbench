package werkbench;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import jakimbox.helper.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import werkbench.bench.BenchBlock;
import werkbench.bench.BenchTileEntity;
import werkbench.handler.GUIHandler;
import werkbench.network.MessageHandler;
import werkbench.proxy.CommonProxy;
import werkbench.reference.Compendium;
import werkbench.reference.Config;

@Mod(modid = Compendium.Naming.id, name = Compendium.Naming.name, version = Compendium.Version.full, useMetadata = false, acceptedMinecraftVersions = "[1.7.10,)", dependencies = "required-after:Forge@[10.13.2.1291,);required-after:jakimbox")
public class Werkbench
{

    // Instancing
    @Instance(value = Compendium.Naming.id)
    public static Werkbench INSTANCE;

    // Public metadata about the mod, used by Forge for display on the client's mod list
    @Mod.Metadata(Compendium.Naming.id)
    protected static ModMetadata metadata;

    @SidedProxy(clientSide = "werkbench.proxy.client.ClientProxy", serverSide = "werkbench.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static BenchBlock werkbench;

    protected static final GUIHandler guiHandler = new GUIHandler();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Register instance.
        INSTANCE = this;

        // Load configuration.
        LogHelper.debug("Loading configuration...");
        Config.init();
        FMLCommonHandler.instance().bus().register(new Config());

        LogHelper.debug("Set Werkbench MetaData info...");
        metadata = Compendium.MetaData.init(metadata);

        LogHelper.debug("Registering Werkbench block...");
        werkbench = new BenchBlock();
        GameRegistry.registerBlock(werkbench, Compendium.Naming.block);
        GameRegistry.registerTileEntity(BenchTileEntity.class, Compendium.Naming.tileEntity);

        LogHelper.debug("Registering GUI handler for werkbench...");
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);

        // Register packet handler
        MessageHandler.init();

        LogHelper.debug("Werkbench done loading");
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.addRecipe(new ItemStack(Werkbench.werkbench), new Object[]
        {
            "ppp", "pbp", "pcp", 'p', Blocks.planks, 'b', Blocks.crafting_table, 'c', Blocks.chest
        });
        proxy.registerRenderers();
    }
}
