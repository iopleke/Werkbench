package werkbench.reference;

import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config
{
    protected static Configuration config;

    // turns on extra logging printouts
    public static boolean debugMode;

    public static int maxUpdateTickCount = 100;

    public static int maxGUIUpdatePacketTickCount = 10;

    public static void init()
    {

        if (config == null)
        {
            config = new Configuration(new File(Compendium.Config.configPrefix + "Werkbench.cfg"));
            debugMode = true;
            loadConfig();
        }
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Compendium.Naming.id))
        {
            loadConfig();
        }
    }

    private static void loadConfig()
    {
        Property prop;
        List<String> configList = new ArrayList<String>();

        Config.config.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, StatCollector.translateToLocal("config.general.description"));
        Config.config.addCustomCategoryComment(Compendium.Config.categoryPerformance, StatCollector.translateToLocal("config.performance.description"));

        prop = Config.config.get(Configuration.CATEGORY_GENERAL, "debugMode", Config.debugMode);
        prop.comment = StatCollector.translateToLocal("config.debugMode.description");
        prop.setLanguageKey("config.debugMode.tooltip");
        Config.debugMode = prop.getBoolean();
        configList.add(prop.getName());

        prop = Config.config.get(Compendium.Config.categoryPerformance, "maxUpdateTickCount", Config.maxUpdateTickCount);
        prop.comment = StatCollector.translateToLocal("config.maxUpdateTickCount.description");
        prop.setLanguageKey("config.maxUpdateTickCount.tooltip");
        Config.maxUpdateTickCount = prop.getInt();
        configList.add(prop.getName());

        if (Config.config.hasChanged())
        {
            Config.config.save();
        }
    }

    public static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.addAll(new ConfigElement(Config.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements());
        return list;
    }
}
