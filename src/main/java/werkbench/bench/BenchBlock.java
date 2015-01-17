package werkbench.bench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
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

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new BenchTileEntity();
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
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

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.topIcon = iconRegister.registerIcon(this.getTextureName());
		this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "Side");
		this.frontIcon = iconRegister.registerIcon(this.getTextureName() + "Front");
		this.bottomIcon = iconRegister.registerIcon(this.getTextureName() + "Bottom");
	}

}
