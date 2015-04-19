package werkbench.helper;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import werkbench.bench.BenchTileEntity;
import werkbench.reference.Compendium.AdjacentBlockType;
import werkbench.reference.Compendium.RelativeBenchSide;

/**
 *
 * @author jakimfett
 */
public class SpatialHelper
{

    private static int[] getOffsetForDirection(BenchTileEntity bench, ForgeDirection direction)
    {
        return getOffsetForDirection(bench, direction, 1);
    }

    private static int[] getOffsetForDirection(BenchTileEntity bench, ForgeDirection direction, int multiplier)
    {
        int[] offsets = new int[3];

        offsets[0] = direction.offsetX * multiplier + bench.xCoord;
        offsets[1] = direction.offsetY * multiplier + bench.yCoord;
        offsets[2] = direction.offsetZ * multiplier + bench.zCoord;
        return offsets;
    }

    private static int[] getOffsetForRelativeSide(BenchTileEntity bench, RelativeBenchSide side)
    {
        return getOffsetForRelativeSide(bench, side, 1);
    }

    private static int[] getOffsetForRelativeSide(BenchTileEntity bench, RelativeBenchSide side, int multiplier)
    {
        int[] offsets = new int[3];

        offsets[0] = SpatialHelper.getDirectionFromRelativeSide(bench, side).offsetX * multiplier + bench.xCoord;
        offsets[1] = SpatialHelper.getDirectionFromRelativeSide(bench, side).offsetY * multiplier + bench.yCoord;
        offsets[2] = SpatialHelper.getDirectionFromRelativeSide(bench, side).offsetZ * multiplier + bench.zCoord;
        return offsets;
    }

    /**
     * Check if the bench has a chest on a specific side
     *
     * @param bench     instance of the werkbench tile entity
     * @param direction direction to check
     * @return Enum type for side
     */
    public static AdjacentBlockType getBlockForDirection(BenchTileEntity bench, ForgeDirection direction)
    {
        return bench.getBlockMemory().get(direction);
    }

    public static AdjacentBlockType getBlockForRelativeSide(BenchTileEntity bench, RelativeBenchSide side)
    {
        ForgeDirection direction = SpatialHelper.getDirectionFromRelativeSide(bench, side);
        return bench.getBlockMemory().get(direction);
    }

    /**
     * Get the ForgeDirection for a given side
     *
     * @param bench instance of the werkbench tile entity
     * @param side  relative side of the bench
     * @return ForgeDirection
     */
    public static ForgeDirection getDirectionFromRelativeSide(BenchTileEntity bench, RelativeBenchSide side)
    {
        ForgeDirection orientation = ForgeDirection.UNKNOWN;
        if (side == RelativeBenchSide.LEFT || side == RelativeBenchSide.RIGHT)
        {
            switch (bench.getBlockMetadata())
            {
                case 0:
                    orientation = ForgeDirection.EAST;
                    break;
                case 1:
                    orientation = ForgeDirection.SOUTH;
                    break;
                case 2:
                    orientation = ForgeDirection.WEST;
                    break;
                case 3:
                    orientation = ForgeDirection.NORTH;
                    break;
            }
            if (side == RelativeBenchSide.RIGHT)
            {
                orientation = orientation.getOpposite();
            }
        }

        return orientation;
    }

    public static TileEntity getTileEntityForDirection(BenchTileEntity bench, ForgeDirection direction)
    {
        return getTileEntityForDirection(bench, direction, 1);
    }

    public static TileEntity getTileEntityForDirection(BenchTileEntity bench, ForgeDirection direction, int multiplier)
    {
        int[] offsets = SpatialHelper.getOffsetForDirection(bench, direction, multiplier);
        return bench.getWorldObj().getTileEntity(offsets[0], offsets[1], offsets[2]);
    }

    public static TileEntity getTileEntityForRelativeSide(BenchTileEntity bench, RelativeBenchSide side, int multiplier)
    {
        int[] offsets = SpatialHelper.getOffsetForRelativeSide(bench, side, multiplier);
        return bench.getWorldObj().getTileEntity(offsets[0], offsets[1], offsets[2]);
    }

    public static TileEntity getTileEntityForRelativeSide(BenchTileEntity bench, RelativeBenchSide side)
    {
        return getTileEntityForRelativeSide(bench, side, 1);
    }
}
