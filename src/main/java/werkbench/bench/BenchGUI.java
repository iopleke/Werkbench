package werkbench.bench;

import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import werkbench.reference.Compendium;

public class BenchGUI extends GuiContainer
{
    private int buttonLeftX;
    private int buttonRightX;
    private int buttonY;
    BenchTileEntity bench;

    public BenchGUI(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {
        super(new BenchContainer(inventoryPlayer, bench));
        this.bench = bench;

        this.xSize = 420;

        this.ySize = 206;

    }

    public int getLeft()
    {
        return this.guiLeft;
    }

    public int getTop()
    {
        return this.guiTop;
    }

    public void drawHoverString(List list, int w, int h)
    {
        this.drawHoveringText(list, w, h, fontRendererObj);
    }

    @Override
    public void mouseClicked(int x, int y, int button)
    {
        super.mouseClicked(x, y, button);

        if (inLeftButton(x, y))
        {
            ((BenchContainer) inventorySlots).saveCraftGridToTileEntity();
            bench.decrementSelectedWorkspace();
            ((BenchContainer) inventorySlots).loadCraftGridFromTileEntity();
            ((BenchContainer) inventorySlots).saveCraftGridToTileEntity();
        } else if (inRightButton(x, y))
        {
            ((BenchContainer) inventorySlots).saveCraftGridToTileEntity();
            bench.incrementSelectedWorkspace();
            ((BenchContainer) inventorySlots).loadCraftGridFromTileEntity();
            ((BenchContainer) inventorySlots).saveCraftGridToTileEntity();
        }
    }

    public boolean inLeftButton(int mouseX, int mouseY)
    {
        int buttonWidth = 15;
        int buttonHeight = 30;
        return mouseX >= buttonLeftX && mouseX <= buttonLeftX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
    }

    public boolean inRightButton(int mouseX, int mouseY)
    {
        int buttonWidth = 15;
        int buttonHeight = 30;
        return mouseX >= buttonRightX && mouseX <= buttonRightX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mousex, int mousey)
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.background);
        int x = (width - xSize) / 2 + 122;
        int y = (height - ySize) / 2 + 40;
        drawTexturedModalRect(x, y, 0, 0, 176, 166);

        buttonLeftX = x + 6;
        buttonY = y + 24;

        drawTexturedModalRect(buttonLeftX, buttonY, 0, 166, 15, 30);
        buttonRightX = buttonLeftX + 20;
        drawTexturedModalRect(buttonRightX, buttonY, 15, 166, 15, 30);

        if (bench.getHasLeftChest())
        {
            if (bench.chestIsDouble(bench.getLeftChestDirection()))
            {
                renderDoubleChestLeft();
            } else
            {
                renderSingleChestLeft();
            }
        }
        if (bench.getHasRightChest())
        {
            if (bench.chestIsDouble(bench.getRightChestDirection()))
            {
                renderDoubleChestRight();
            } else
            {
                renderSingleChestRight();
            }
        }
    }

    private void renderDoubleChestLeft()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.doubleChest);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2 + 30;
        drawTexturedModalRect(x, y, 0, 0, 122, 176);
    }

    private void renderSingleChestLeft()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.singleChest);
        int x = (width - xSize) / 2 + 54;
        int y = (height - ySize) / 2 + 30;
        drawTexturedModalRect(x, y, 0, 0, 68, 176);
    }

    private void renderDoubleChestRight()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.doubleChest);
        int x = (width - xSize) / 2 + 298;
        int y = (height - ySize) / 2 + 30;
        drawTexturedModalRect(x, y, 0, 0, 122, 176);
    }

    private void renderSingleChestRight()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.singleChest);
        int x = (width - xSize) / 2 + 298;
        int y = (height - ySize) / 2 + 30;
        drawTexturedModalRect(x, y, 0, 0, 68, 176);
    }

}
