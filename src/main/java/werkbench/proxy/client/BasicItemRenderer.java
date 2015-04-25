package werkbench.proxy.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class BasicItemRenderer implements IItemRenderer
{
    private final TileEntity tileEntity;
    TileEntitySpecialRenderer tesr;

    public BasicItemRenderer(TileEntitySpecialRenderer tesr, TileEntity tileEntity)
    {
        this.tesr = tesr;
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType type)
    {
        switch (type)
        {
            case ENTITY:
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        tileEntity.blockMetadata = 1;
        GL11.glScaled(1, 1, 1);
        switch (type)
        {
            case ENTITY:
                // Translate models sideways so objects in the world spin in place
                GL11.glTranslatef(-0.5F, -0.0F, -0.5F);
                break;
            case EQUIPPED_FIRST_PERSON:
            case EQUIPPED:
                // Move model up and over slightly when in the player's hand
                GL11.glTranslatef(0.0F, 0.4F, -0.2F);
                // Rotate model 90 degrees
                tileEntity.blockMetadata = 2;
                break;
            case INVENTORY:
                // Scale block down slightly in the inventory
                double inventoryVerticalScale = 0.75;
                double inventoryHorizontalScale = 0.85;
                GL11.glScaled(inventoryHorizontalScale, inventoryVerticalScale, inventoryHorizontalScale);
                break;
            default:
                // do nothing special
                break;
        }

        tesr.renderTileEntityAt(this.tileEntity, 0.0D, -0.2D, 0.0D, 0.0625F);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack itemStack, ItemRendererHelper helper)
    {
        switch (helper)
        {
            case ENTITY_BOBBING:
            case ENTITY_ROTATION:
            case EQUIPPED_BLOCK:
            case INVENTORY_BLOCK:
                return true;
            default:
                return false;
        }
    }
}
