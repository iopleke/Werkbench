package werkbench.bench;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import werkbench.reference.Compendium;

/**
 *
 * @author jakimfett
 */
public class BenchTileEntityRenderer extends TileEntitySpecialRenderer
{

    private BenchModel benchModel = new BenchModel();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double xCoord, double yCoord, double zCoord, float partialTick)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(xCoord + 0.5, yCoord + 0.74, zCoord + 0.5);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        GL11.glRotatef((tileEntity.getBlockMetadata() * 90.0F), 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glScaled(1, 1, 1);
        bindTexture(Compendium.Resource.Model.bench);
        this.benchModel.render(0.03125F);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

}
