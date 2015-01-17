package werkbench.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import werkbench.bench.BenchContainer;
import werkbench.bench.BenchGUI;
import werkbench.bench.BenchTileEntity;

public class GUIHandler implements IGuiHandler
{

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity != null)
		{
			if (tileEntity instanceof BenchTileEntity)
			{
				return new BenchGUI(player.inventory, (BenchTileEntity) tileEntity);

			}
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity != null)
		{
			if (tileEntity instanceof BenchTileEntity)
			{
				return new BenchContainer(player.inventory, (BenchTileEntity) tileEntity);

			}
		}
		return null;
	}

}
