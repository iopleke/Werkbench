package werkbench.bench;

import jakimbox.prefab.block.BasicBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import werkbench.Werkbench;
import werkbench.proxy.CommonProxy;
import werkbench.reference.Compendium;

public class BenchBlock extends BasicBlockContainer
{

    public BenchBlock()
    {
        super(Compendium.Naming.id, Compendium.Naming.name, Material.wood, CreativeTabs.tabDecorations);
        setStepSound(Block.soundTypeWood);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof IInventory)
        {
            IInventory inventory = (IInventory) te;
            for (int i = 0; i < inventory.getSizeInventory(); i++)
            {
                ItemStack stack = inventory.getStackInSlotOnClosing(i);
                if (stack != null)
                {
                    float spawnX = x + world.rand.nextFloat();
                    float spawnY = y + world.rand.nextFloat();
                    float spawnZ = z + world.rand.nextFloat();
                    EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);
                    float scale = 0.05F;
                    droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * scale;
                    droppedItem.motionY = (4 + world.rand.nextFloat()) * scale;
                    droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * scale;
                    world.spawnEntityInWorld(droppedItem);
                }
            }
        }
    }

    /**
     * Create the tileEntity
     *
     * @param world game world object
     * @param meta  block metadata
     * @return TileEntity
     */
    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new BenchTileEntity();
    }

    @Override
    public int getRenderType()
    {
        return CommonProxy.RENDER_ID;
    }

    /**
     * Open the GUI on block activation
     *
     * @param world  the game world object
     * @param x      the x coordinate of the block being activated
     * @param y      the y coordinate of the block being activated
     * @param z      the z coordinate of the block being activated
     * @param player the entityplayer object
     * @param side   which side was hit
     * @param hitX   on the side that was hit, the x coordinate
     * @param hitY   on the side that was hit, the y coordinate
     * @param hitZ   on the side that was hit, the z coordinate
     * @return boolean does the block get activated
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity != null && !player.isSneaking())
        {
            if (tileEntity instanceof BenchTileEntity)
            {
                if (!world.isRemote)
                {
                    player.openGui(Werkbench.INSTANCE, 0, world, x, y, z);
                }
                return true;
            }
        }

        return false;
    }

    /**
     * Check if changedBlock is a chest, update GUI to reflect this
     *
     * @param world        the world object
     * @param x            the changed block's x coordinate
     * @param y            the changed block's y coordinate
     * @param z            the changed block's z coordinate
     * @param changedBlock the block object of whatever changed
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block changedBlock)
    {
        TileEntity potentialBench = world.getTileEntity(x, y, z);
        if (potentialBench instanceof BenchTileEntity)
        {
            ((BenchTileEntity) potentialBench).updateSideChecks();
        }
    }
}
