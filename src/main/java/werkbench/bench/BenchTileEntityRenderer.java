package werkbench.bench;

import jakimbox.prefab.render.BasicTileEntityRenderer;
import jakimbox.prefab.tileEntity.BasicTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import werkbench.reference.Compendium;

/**
 *
 * @author jakimfett
 */
public class BenchTileEntityRenderer extends BasicTileEntityRenderer
{

    /**
     * Rendering object with scale and rotation
     */
    public BenchTileEntityRenderer()
    {
        super(0.03125F);
        this.model = new BenchModel();
        this.texture = Compendium.TextureResource.Model.bench;
        setOffset(0.5D, 0.74D, 0.5D);

        setRotation(0F, 0F, 180F);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick)
    {
        if (tileEntity instanceof BasicTileEntity)
        {

            GL11.glPushMatrix();
            GL11.glTranslated(x + xOffset, y + yOffset, z + zOffset);

            doModelRotations();

            GL11.glRotatef((tileEntity.getBlockMetadata() * 90.0F), 0.0F, 1.0F, 0.0F);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            bindTexture(texture);
            model.render(0.03125F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        }
    }
}
