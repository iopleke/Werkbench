package werkbench.bench;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import werkbench.reference.Compendium;

public class BenchGUI extends GuiContainer
{
    BenchTileEntity bench;

    public BenchGUI(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {
        super(new BenchContainer(inventoryPlayer, bench, world));
        this.bench = bench;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mousex, int mousey)
    {
        this.mc.renderEngine.bindTexture(Compendium.Texture.GUI.background);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2 + 30;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (bench.getHasChestLeft())
        {
            renderSingleChestLeft();
        }
        if (bench.getHasChestRight())
        {
            renderSingleChestRight();
        }
    }

    private void renderDoubleChestLeft()
    {
        this.mc.renderEngine.bindTexture(Compendium.Texture.GUI.doubleChest);
        int x = (width - xSize) / 2 - 122;
        int y = (height - ySize) / 2 + 20;
        drawTexturedModalRect(x, y, 0, 0, 122, 176);
    }

    private void renderSingleChestLeft()
    {
        this.mc.renderEngine.bindTexture(Compendium.Texture.GUI.singleChest);
        int x = (width - xSize) / 2 - 68;
        int y = (height - ySize) / 2 + 20;
        drawTexturedModalRect(x, y, 0, 0, 68, 176);
    }

    private void renderDoubleChestRight()
    {
        this.mc.renderEngine.bindTexture(Compendium.Texture.GUI.doubleChest);
        int x = (width - xSize) / 2 + 176;
        int y = (height - ySize) / 2 + 20;
        drawTexturedModalRect(x, y, 0, 0, 122, 176);
    }

    private void renderSingleChestRight()
    {
        this.mc.renderEngine.bindTexture(Compendium.Texture.GUI.singleChest);
        int x = (width - xSize) / 2 + 176;
        int y = (height - ySize) / 2 + 20;
        drawTexturedModalRect(x, y, 0, 0, 68, 176);
    }

}
