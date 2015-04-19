package werkbench.bench;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import werkbench.reference.Compendium;
import werkbench.reference.Compendium.AdjacentBlockType;
import werkbench.reference.Compendium.RelativeBenchSide;

public class BenchGUI extends GuiContainer
{
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

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mousex, int mousey)
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.background);
        int x = (width - xSize) / 2 + 122;
        int y = (height - ySize) / 2 + 40;
        drawTexturedModalRect(x, y, 0, 0, 176, 166);

        if (bench.getBlockForDirection(bench.getLeftDirection()) == AdjacentBlockType.CHEST)
        {
            if (bench.isChestDouble(bench.getDirectionFromRelativeSide(RelativeBenchSide.LEFT)))
            {
                renderDoubleChestLeft();
            } else
            {
                renderSingleChestLeft();
            }
        }

        if (bench.getBlockForDirection(bench.getLeftDirection()) == AdjacentBlockType.FURNACE || bench.getBlockForDirection(bench.getLeftDirection()) == AdjacentBlockType.FURNACE_ACTIVE)
        {
            renderFurnaceLeft();
        }
        if (bench.getBlockForDirection(bench.getRightDirection()) == AdjacentBlockType.CHEST)
        {
            if (bench.isChestDouble(bench.getDirectionFromRelativeSide(RelativeBenchSide.RIGHT)))
            {
                renderDoubleChestRight();
            } else
            {
                renderSingleChestRight();
            }
        }
        if (bench.getBlockForDirection(bench.getRightDirection()) == AdjacentBlockType.FURNACE || bench.getBlockForDirection(bench.getRightDirection()) == AdjacentBlockType.FURNACE_ACTIVE)
        {
            renderFurnaceRight();
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

    private void renderFurnaceRight()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.furnace);
        /*
         * @TODO - get burn times from the tileEntity
         * TileEntityFurnace furnace = bench.getRightFurnaceTileEntity();
         * if (furnace != null)
         * {
         * if (furnace.isBurning())
         * {
         * drawTexturedModalRect(x, y + 38, 0, 76, 76, furnace.getBurnTimeRemainingScaled(38));
         * } else
         * {
         * drawTexturedModalRect(x, y + 38, 0, 115, 76, 38);
         * }
         *
         * }
         */

        // @TODO - make these number self explanitory
        int x = (width - xSize) / 2 + 298;
        int y = (height - ySize) / 2 + 40;

        if (bench.getBlockForDirection(bench.getRightDirection()) == AdjacentBlockType.FURNACE_ACTIVE)
        {
            drawTexturedModalRect(x, y + 38, 0, 76, 76, 38);
        } else
        {
            drawTexturedModalRect(x, y + 38, 0, 115, 76, 38);
        }

        drawTexturedModalRect(x, y, 0, 0, 76, 76);

    }

    private void renderFurnaceLeft()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.furnace);

        // @TODO - make these number self explanitory
        int x = (width - xSize) / 2 + 46;
        int y = (height - ySize) / 2 + 40;

        if (bench.getBlockForDirection(bench.getLeftDirection()) == AdjacentBlockType.FURNACE_ACTIVE)
        {
            drawTexturedModalRect(x, y + 38, 0, 76, 76, 38);
        } else
        {
            drawTexturedModalRect(x, y + 38, 0, 115, 76, 38);
        }

        drawTexturedModalRect(x, y, 0, 0, 76, 76);

    }

}
