package werkbench.proxy.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class BasicItemRenderer implements IItemRenderer
{
    TileEntitySpecialRenderer tesr;
    private final TileEntity tileEntity;

    public BasicItemRenderer(TileEntitySpecialRenderer tesr, TileEntity tileEntity)
    {
        this.tesr = tesr;
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        this.tileEntity.blockMetadata = 1;
        tesr.renderTileEntityAt(this.tileEntity, 0.0D, -0.2D, 0.0D, 0.0625F);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }
}
