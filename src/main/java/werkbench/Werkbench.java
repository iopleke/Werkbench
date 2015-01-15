package werkbench;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
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

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Register instance.
        INSTANCE = this;

        LogHelper.debug("Set Werkbench MetaData info");
        metadata = Compendium.MetaData.init(metadata);
    }
}
