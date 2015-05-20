package werkbench.bench;

import jakimbox.helper.SpatialHelper;
import jakimbox.prefab.tileEntity.BasicInventoryTileEntity;
import jakimbox.reference.RelativeDirection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import werkbench.reference.Compendium;
import werkbench.reference.Compendium.AdjacentBlockType;
import werkbench.reference.Config;

public class BenchTileEntity extends BasicInventoryTileEntity
{
    /**
     * Cached memory for the surrounding blocks
     */
    private final Map<RelativeDirection, Block> blockCache = new EnumMap<RelativeDirection, Block>(RelativeDirection.class);
    private int processingTicks;

    public BenchTileEntity()
    {
        super(Compendium.Naming.block, 9);
        resetBlockCache();
        resetProcessingTicks();
    }

    private void incrementProcessingTicks()
    {
        processingTicks++;
    }

    /**
     * Wipe the blockmemory, then set all valid directions to null
     */
    private void resetBlockCache()
    {
        blockCache.clear();
        for (RelativeDirection direction : RelativeDirection.VALID_DIRECTIONS)
        {
            blockCache.put(direction, null);
        }
    }

    private void resetProcessingTicks()
    {
        processingTicks = new Random().nextInt(Config.maxUpdateTickCount);
    }

    public Map<RelativeDirection, Block> getBlockCache()
    {
        return blockCache;
    }

    /**
     * Checks for chests on each side on update
     */
    @Override
    public void updateEntity()
    {
        if (processingTicks >= Config.maxUpdateTickCount)
        {
            updateSideChecks();
            resetProcessingTicks();
        } else
        {
            incrementProcessingTicks();
        }
    }

    public void updateSideChecks()
    {
        for (RelativeDirection direction : RelativeDirection.VALID_DIRECTIONS)
        {
            int[] directionOffset = SpatialHelper.getOffsetFromRelative(direction, blockMetadata);
            TileEntity tileEntity = this.worldObj.getTileEntity(xCoord + directionOffset[0], yCoord, zCoord + directionOffset[1]);
            if (tileEntity != null)
            {
                if (AdjacentBlockType.isTileEntitySupported(tileEntity))
                {
                    blockCache.put(direction, tileEntity.getBlockType());
                }
            }
        }
    }
}
