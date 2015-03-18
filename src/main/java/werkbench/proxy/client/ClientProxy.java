package werkbench.proxy.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import werkbench.Werkbench;
import werkbench.bench.BenchTileEntity;
import werkbench.bench.BenchTileEntityRenderer;
import werkbench.proxy.CommonProxy;

/**
 *
 * @author jakimfett
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        RENDER_ID = RenderingRegistry.getNextAvailableRenderId();

        BenchTileEntityRenderer benchTileEntityRenderer = new BenchTileEntityRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(BenchTileEntity.class, benchTileEntityRenderer);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Werkbench.werkbench), new BasicItemRenderer(benchTileEntityRenderer, new BenchTileEntity()));
    }
}
