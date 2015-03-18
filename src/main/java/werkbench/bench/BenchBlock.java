package werkbench.bench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import werkbench.Werkbench;
import werkbench.proxy.CommonProxy;
import werkbench.reference.Compendium;

public class BenchBlock extends BlockContainer
{
    public BenchBlock()
    {
        super(Material.wood);
        setCreativeTab(CreativeTabs.tabDecorations);
        setStepSound(Block.soundTypeWood);
        textureName = Compendium.Naming.id + ":werkBenchIcon";
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

    /**
     * Gets the texture, given a side
     *
     * @param side the block side
     * @param meta the block metadata
     * @return IIcon
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return blockIcon;
    }

    /**
     * Register the block icons for top, sides, front, and bottom
     *
     * @param iconRegister object for icon registration
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName());
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

    /**
     * Set the direction the block is facing
     *
     * @param world        the world object
     * @param x            the changed block's x coordinate
     * @param y            the changed block's y coordinate
     * @param z            the changed block's z coordinate
     * @param livingEntity entity that placed the block
     * @param itemStack    the itemstack used to place the block
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingEntity, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, livingEntity, itemStack);

        int facing = MathHelper.floor_double(livingEntity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, facing, 2);
    }

    @Override
    public int getRenderType()
    {
        return CommonProxy.RENDER_ID;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
