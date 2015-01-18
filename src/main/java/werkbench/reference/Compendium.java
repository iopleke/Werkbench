package werkbench.reference;

import cpw.mods.fml.common.ModMetadata;
import java.util.Arrays;
import net.minecraft.util.ResourceLocation;

/*
 * A compendium of all constants for the mod, such as the modID and
 * ResourceLocations, as well as more general things like color codes
 */
public class Compendium
{
    private static final String modPrefix = Compendium.Naming.id + ":";

    public static final class Naming
    {
        public static final String id = "werkbench";
        public static final String name = "Werkbench";
        public static final String block = "Werkbench";
        public static final String tileEntity = "werkbenchTileEntity";
        public static final String inventoryName = "container.bench.name";
    }

    public static final class Version
    {
        public static final String major = "@MAJOR@";
        public static final String minor = "@MINOR@";
        public static final String build = "@BUILD@";
        public static final String full = major + "." + minor + "." + build;
    }

    public static final class MetaData
    {
        /**
         * Setup mod metadata
         *
         * @param metadata
         * @return edited metadata object
         */
        public static ModMetadata init(ModMetadata metadata)
        {
            metadata.modId = Compendium.Naming.id;
            metadata.name = Compendium.Naming.name;
            metadata.description = Compendium.Naming.name + " adds a useful extended workbench";
            metadata.url = "http://patreon.com/jakimfett";
            metadata.logoFile = "assets/" + Compendium.Naming.id + "/logo.png";
            metadata.version = Compendium.Version.major + "." + Compendium.Version.minor + "." + Compendium.Version.build;
            metadata.authorList = Arrays.asList("jakimfett");
            metadata.autogenerated = false;
            return metadata;
        }
    }

    public static final class Texture
    {

        public static final class IIcon
        {

        }

        public static final class Icon
        {

        }

        public static final class GUI
        {
            public static ResourceLocation background = new ResourceLocation(Compendium.Naming.id, "textures/gui/werkBenchGUIBackground.png");
        }

        public static final class Model
        {

        }
    }

    public static final class Resource
    {
        public static final class Icon
        {

        }

        public static final class GUI
        {

        }

        public static final class Model
        {

        }

        public static final class Tab
        {

        }
    }

    public static final class Config
    {
        public static final String configPrefix = "config/werkbench/";
        public static final String assetPrefix = "/assets/werkbench/";
    }

    public static final class Color
    {

        public static final String black = "\u00A70";
        public static final String darkBlue = "\u00A71";
        public static final String darkCyan = "\u00A73";
        public static final String darkGreen = "\u00A72";
        public static final String darkGrey = "\u00A78";
        public static final String darkRed = "\u00A74";
        public static final String lightBlue = "\u00A79";
        public static final String lightCyan = "\u00A7b";
        public static final String lightGreen = "\u00A7a";
        public static final String lightGrey = "\u00A77";
        public static final String lightRed = "\u00A7c";
        public static final String orange = "\u00A76";
        public static final String pink = "\u00A7d";
        public static final String purple = "\u00A75";
        public static final String white = "\u00A7f";
        public static final String yellow = "\u00A7e";

    }
}
