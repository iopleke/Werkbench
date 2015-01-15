package werkbench.helper;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import werkbench.reference.Compendium;
import werkbench.reference.Config;

/**
 * Helper class for logging
 *
 * @author way2muchnoise
 */
public class LogHelper
{
    /**
     * General logging method
     *
     * @param level Level of the log
     * @param obj object to log
     */
    public static void log(Level level, Object obj)
    {
        FMLLog.log(Compendium.Naming.id, level, String.valueOf(obj));
    }

    /**
     * Used for logging when debug is turned on in the config
     *
     * @param obj object to log
     */
    public static void debug(Object obj)
    {
        if (Config.debugMode)
        {
            log(Level.INFO, obj);
        }
    }

    /**
     * Used for logging in any case
     *
     * @param obj object to log
     */
    public static void info(Object obj)
    {
        log(Level.INFO, obj);
    }
}
