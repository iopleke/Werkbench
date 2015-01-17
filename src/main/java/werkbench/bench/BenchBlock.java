package werkbench.bench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import werkbench.Werkbench;
import werkbench.reference.Compendium;

public class BenchBlock extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon;
	@SideOnly(Side.CLIENT)
	private IIcon bottomIcon;

	public BenchBlock()
	{
		super(Material.wood);
		setCreativeTab(CreativeTabs.tabDecorations);
		setStepSound(Block.soundTypeWood);
		textureName = Compendium.Naming.id + ":werkBenchIcon";
	}

	/**
	 * Do stuff on block activation
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
			if (!world.isRemote)
			{
				player.openGui(Werkbench.INSTANCE, 0, world, x, y, z);
			}
			return true;
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
		switch (side)
		{
			case 0:
				return bottomIcon;
			case 1:
				return topIcon;
			case 3:
				return frontIcon;
			default:
				return blockIcon;
		}
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
		this.topIcon = iconRegister.registerIcon(this.getTextureName());
		this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "Side");
		this.frontIcon = iconRegister.registerIcon(this.getTextureName() + "Front");
		this.bottomIcon = iconRegister.registerIcon(this.getTextureName() + "Bottom");
	}

}
