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
	public static enum AdjacentBlockType
	{
		CHEST_DOUBLE(new int[]
		{
			0, 0
		}, new int[]
		{
			54, 0
		}),
		CHEST_SINGLE(new int[]
		{
			64, 0
		}, new int[]
		{
			0, 0
		}),
		FURNACE_INACTIVE(new int[]
		{
			47, 13
		}, new int[]
		{
			1, 13
		}),
		FURNACE_ACTIVE(new int[]
		{
			47, 13
		}, new int[]
		{
			1, 13
		}),
		EMPTY(new int[]
		{
			0, 0
		}, new int[]
		{
			0, 0
		}),
		OFFSET(new int[]
		{
			8, 38
		}, new int[]
		{
			306, 38
		});

		private final int[] left, right;

		AdjacentBlockType(int[] left, int[] right)
		{
			this.left = left;
			this.right = right;
		}

		public static int[] getGUICoordinate(RelativeBenchSide side, AdjacentBlockType type)
		{
			if (side == RelativeBenchSide.LEFT)
			{
				int offsets[] = type.left;
				offsets[0] += AdjacentBlockType.OFFSET.left[0];
				offsets[1] += AdjacentBlockType.OFFSET.left[1];
				return offsets;
			} else
			{

				int offsets[] = type.right;
				offsets[0] += AdjacentBlockType.OFFSET.right[0];
				offsets[1] += AdjacentBlockType.OFFSET.right[1];
				return offsets;
			}
		}
	}

	public static enum RelativeBenchSide
	{
		LEFT, RIGHT, BACK, FRONT, TOP, BOTTOM
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
			public static final String background = "textures/gui/werkBenchGUIBackground.png";
			public static final String singleChest = "textures/gui/werkBenchGUIBackgroundChestSingle.png";
			public static final String doubleChest = "textures/gui/werkBenchGUIBackgroundChestDouble.png";
			public static final String furnace = "textures/gui/werkBenchGUIBackgroundFurnace.png";
		}

		public static final class Model
		{

			public static final String bench = "textures/blocks/werkBench.png";
		}
	}

	public static final class Resource
	{
		public static final class Icon
		{

		}

		public static final class GUI
		{

			public static final ResourceLocation background = new ResourceLocation(Compendium.Naming.id, Compendium.Texture.GUI.background);
			public static final ResourceLocation singleChest = new ResourceLocation(Compendium.Naming.id, Compendium.Texture.GUI.singleChest);
			public static final ResourceLocation doubleChest = new ResourceLocation(Compendium.Naming.id, Compendium.Texture.GUI.doubleChest);
			public static final ResourceLocation furnace = new ResourceLocation(Compendium.Naming.id, Compendium.Texture.GUI.furnace);
		}

		public static final class Model
		{
			public static final ResourceLocation bench = new ResourceLocation(Compendium.Naming.id, Compendium.Texture.Model.bench);
		}

		public static final class Tab
		{

		}
	}

	public static final class Config
	{
		public static final String configPrefix = "config/werkbench/";
		public static final String assetPrefix = "/assets/werkbench/";
		public static final String categoryPerformance = "performance";
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
