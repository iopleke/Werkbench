package werkbench.reference;

import cpw.mods.fml.common.ModMetadata;
import java.util.Arrays;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;

/**
 * A compendium of constants for the mod, such as the ID and ResourceLocations
 *
 * @author jakimfett
 */
public class Compendium
{
    public static enum AdjacentBlockType
    {
        CHEST_DOUBLE, CHEST_SINGLE, EMPTY, FURNACE_ACTIVE, FURNACE_INACTIVE, OFFSET;

        /**
         * Array of all valid block types
         */
        public static final AdjacentBlockType[] VALID_TYPES =
        {
            CHEST_DOUBLE, CHEST_SINGLE, EMPTY, FURNACE_ACTIVE, FURNACE_INACTIVE, OFFSET
        };

        /**
         * Check if the TileEntity type is supported
         *
         * @param tileEntity
         * @return boolean
         */
        public static boolean isTileEntitySupported(TileEntity tileEntity)
        {
            if (tileEntity instanceof TileEntityBrewingStand
                || tileEntity instanceof TileEntityChest
                || tileEntity instanceof TileEntityEnchantmentTable
                || tileEntity instanceof TileEntityEnderChest)
            {
                return true;
            }
            return false;
        }
    }

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

    public static final class TextureString
    {

        public static final class TAB
        {
            public static final String chestBackground = "textures/gui/chestTabBackground.png";
            public static final String furnaceBackground = "textures/gui/furnaceTabBackground.png";
        }

        public static final class GUI
        {
            public static final String background = "textures/gui/werkBenchGUIBackground.png";
        }

        public static final class Model
        {

            public static final String bench = "textures/blocks/werkBench.png";
        }
    }

    public static final class TextureResource
    {

        public static final class GUI
        {
            public static final ResourceLocation background = new ResourceLocation(Compendium.Naming.id, Compendium.TextureString.GUI.background);
            public static final ResourceLocation chest = new ResourceLocation(Compendium.Naming.id, Compendium.TextureString.TAB.chestBackground);
            public static final ResourceLocation furnace = new ResourceLocation(Compendium.Naming.id, Compendium.TextureString.TAB.furnaceBackground);
        }

        public static final class Model
        {
            public static final ResourceLocation bench = new ResourceLocation(Compendium.Naming.id, Compendium.TextureString.Model.bench);
        }
    }

    public static final class Config
    {
        public static final String categoryPerformance = "performance";
    }
}
