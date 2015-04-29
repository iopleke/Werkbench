package werkbench.bench;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import werkbench.gui.Tab;
import werkbench.helper.LogHelper;
import werkbench.helper.SpatialHelper;
import werkbench.network.MessageHandler;
import werkbench.network.message.FurnaceUpdateRequestMessage;
import werkbench.reference.Compendium;
import werkbench.reference.Compendium.AdjacentBlockType;
import werkbench.reference.Compendium.RelativeBenchSide;
import werkbench.reference.Config;

public class BenchGUI extends GuiContainer
{
    private BenchTileEntity bench;
    private boolean doFurnaceUpdate;
    private int tickCount, xOffset, yOffset;
    private Tab[] leftTabs;
    private Tab[] rightTabs;

    public BenchGUI(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {
        super(new BenchContainer(inventoryPlayer, bench));
        this.bench = bench;

        this.xSize = 420;

        this.ySize = 206;

        updateOffsetCoordinates();
        resetTickCount();
        leftTabs = new Tab[4];
        rightTabs = new Tab[4];

    }

    private void bindGUITexture()
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.background);
    }

    private void drawBackgroundForSide(RelativeBenchSide side)
    {
        AdjacentBlockType sideBlock = SpatialHelper.getBlockForRelativeSide(bench, side);
        switch (sideBlock)
        {
            case CHEST_SINGLE:
                renderSingleChestForSide(side);
                break;
            case CHEST_DOUBLE:
                renderDoubleChestForSide(side);
                break;
            case FURNACE_ACTIVE:
            case FURNACE_INACTIVE:
                renderFurnaceForSide(side);
                break;
            default:
                // do nothing
                break;
        }
    }

    private void drawBenchBackground()
    {
        drawTexturedModalRect(xOffset + 98, yOffset - 50, 0, 0, 222, 256);
    }

    private void incrementTickCount()
    {
        tickCount++;
        if (tickCount >= Config.furnaceGUIUpdatePacketFrequency)
        {
            resetTickCount();
        }
    }

    private void renderDoubleChestForSide(RelativeBenchSide side)
    {
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.doubleChest);
        int[] guiOffsets = AdjacentBlockType.getGUIBackgroundCoordinates(side, AdjacentBlockType.CHEST_DOUBLE);
        drawTexturedModalRect(xOffset + guiOffsets[0], yOffset + guiOffsets[1], 0, 0, 122, 176);

    }

    private void renderFurnaceForSide(RelativeBenchSide side)
    {
        if (doFurnaceUpdate)
        {
            sendFurnaceGUIUpdateRequest(side);
        }
        this.mc.renderEngine.bindTexture(Compendium.Resource.GUI.furnace);
        int[] guiOffsets = AdjacentBlockType.getGUIBackgroundCoordinates(side, AdjacentBlockType.FURNACE_ACTIVE);

        renderFurnaceProgressBars(side, xOffset + guiOffsets[0], yOffset + guiOffsets[1]);
        renderFurnaceForeground(xOffset + guiOffsets[0], yOffset + guiOffsets[1]);

    }

    private void renderFurnaceForeground(int x, int y)
    {
        drawTexturedModalRect(x, y, 0, 0, 76, 76);
    }

    private void renderFurnaceProgressBars(RelativeBenchSide side, int x, int y)
    {
        int[] furnaceSideValues = bench.getFurnaceValuesForSide(side);
        int cookProgress = 0;
        int burnLevel = 0;
        if (furnaceSideValues != null)
        {
            if (furnaceSideValues[0] > 0 && furnaceSideValues[2] > 0)
            {
                burnLevel = (furnaceSideValues[0] * 29 / furnaceSideValues[2]);
            }
            if (furnaceSideValues[1] > 0)
            {
                cookProgress = (furnaceSideValues[1] * 22 / 200);
            }
        }
        drawTexturedModalRect(x, y, 0, 105, 76, 76);
        if (SpatialHelper.getBlockForRelativeSide(bench, side) == AdjacentBlockType.FURNACE_ACTIVE)
        {
            if (burnLevel != 0)
            {
                drawTexturedModalRect(x, y + 76 - burnLevel, 0, 76 + 14 - burnLevel / 2, 76, burnLevel);
            }
            if (cookProgress != 0)
            {
                drawTexturedModalRect(x + 26, y + 12, 76, 0, cookProgress, 15);
            }
        }

        drawTexturedModalRect(x, y, 0, 0, 76, 76);
    }

    private void renderSingleChestForSide(RelativeBenchSide side)
    {
        int[] guiOffsets = AdjacentBlockType.getGUIBackgroundCoordinates(side, AdjacentBlockType.CHEST_SINGLE);
        if (side == RelativeBenchSide.LEFT)
        {
            leftTabs[0] = new Tab(this, AdjacentBlockType.CHEST_SINGLE, side);
            leftTabs[0].setTabGUIOffsets(xOffset + guiOffsets[0], yOffset + guiOffsets[1]);
        } else
        {
            rightTabs[0] = new Tab(this, AdjacentBlockType.CHEST_SINGLE, side);
            rightTabs[0].setTabGUIOffsets(xOffset + guiOffsets[0], yOffset + guiOffsets[1]);
        }
    }

    private void resetTickCount()
    {
        tickCount = 0;
        doFurnaceUpdate = true;
    }

    private void sendFurnaceGUIUpdateRequest(RelativeBenchSide side)
    {
        TileEntity tileEntity = SpatialHelper.getTileEntityForRelativeSide(bench, side);
        if (tileEntity instanceof TileEntityFurnace)
        {
            MessageHandler.INSTANCE.sendToServer(new FurnaceUpdateRequestMessage(((TileEntityFurnace) tileEntity), bench, side));
        }
    }

    private void updateOffsetCoordinates()
    {
        xOffset = (width - xSize) / 2;
        yOffset = (height - ySize) / 2;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mouseX, int mouseY)
    {
        incrementTickCount();
        updateOffsetCoordinates();
        bindGUITexture();
        drawBenchBackground();

        drawBackgroundForSide(RelativeBenchSide.LEFT);
        drawBackgroundForSide(RelativeBenchSide.RIGHT);
        renderSideBackgrounds();

        doTabToolTips(mouseX, mouseY);

        if (doFurnaceUpdate)
        {
            doFurnaceUpdate = false;
        }
    }

    private void renderSideBackgrounds()
    {

        for (Tab tab : leftTabs)
        {
            if (tab != null)
            {
                tab.renderTab();
            }
        }

        for (Tab tab : rightTabs)
        {
            if (tab != null)
            {
                tab.renderTab();
            }
        }
    }

    private void doTabClicks(int clickX, int clickY)
    {
        LogHelper.debug("Clicked at x:" + clickX + " y:" + clickY);

        for (Tab tab : leftTabs)
        {
            if (tab != null)
            {
                if (tab.intersectsWithTab(clickX, clickY))
                {
                    LogHelper.debug("Tab on " + tab.getTabSide().toString() + " was clicked!");
                }
            }
        }

        for (Tab tab : rightTabs)
        {
            if (tab != null)
            {
                if (tab.intersectsWithTab(clickX, clickY))
                {
                    LogHelper.debug("Tab on " + tab.getTabSide().toString() + " was clicked!");
                }
            }
        }
    }

    private void doTabToolTips(int mouseX, int mouseY)
    {
        for (Tab tab : leftTabs)
        {
            if (tab != null)
            {
                if (tab.intersectsWithTab(mouseX, mouseY))
                {
                    LogHelper.debug("Tab on " + tab.getTabSide().toString() + " is hovered!");
                    tab.drawTooltipForTab(mouseX, mouseY);
                }
            }
        }

        for (Tab tab : rightTabs)
        {
            if (tab != null)
            {
                if (tab.intersectsWithTab(mouseX, mouseY))
                {
                    LogHelper.debug("Tab on " + tab.getTabSide().toString() + " is hovered!");
                    List<String> toolTipText = new ArrayList<String>();
                    toolTipText.add("type: " + tab.blockType.toString());
                    toolTipText.add("side: " + tab.benchSide.toString());
                    drawHoveringText(toolTipText, mouseX, mouseY, mc.fontRenderer);
                    //tab.drawTooltipForTab(mouseX, mouseY);
                }
            }
        }
    }

    @Override
    protected void mouseClicked(int clickX, int clickY, int button)
    {
        super.mouseClicked(clickX, clickY, button);
        doTabClicks(clickX, clickY);
    }
}
