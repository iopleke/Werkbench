package werkbench.gui;

import net.minecraft.util.ResourceLocation;
import werkbench.bench.BenchGUI;
import werkbench.reference.Compendium;
import werkbench.reference.Compendium.AdjacentBlockType;
import werkbench.reference.Compendium.RelativeBenchSide;

/**
 *
 * @author jakimfett
 */
public class Tab
{
    public static ResourceLocation tabBackground;
    private AdjacentBlockType blockType;
    private boolean closedTab;
    private final int closedTabTextureXOffset = 3;
    private final int closedTabXSize = 15;
    private final int closedTabYSize = 18;
    private boolean closingTab;
    private BenchGUI gui;
    private boolean openTab;
    private final int openTabTextureYOffset = 3;
    private int openTabXSize;
    private int openTabYSize;
    private boolean openingTab;
    private TabSide tabSide;
    private int textureX, textureY;
    private int xMax;
    private int xMin;
    private int xOffset;
    private int xSize;
    private int yMax;
    private int yMin;
    private int yOffset;
    private int ySize;

    public Tab(BenchGUI gui, AdjacentBlockType blockType, TabSide tabSide)
    {
        this.gui = gui;
        this.blockType = blockType;
        this.tabSide = tabSide;
        resetTab();
    }

    private void animateTab()
    {
        if (openingTab)
        {
            incrementTabValues();
        } else if (closingTab)
        {
            decrementTabValues();
        }
    }

    private void decrementTabValues()
    {
        if (xSize > xMin)
        {
            xSize--;
        }
        if (ySize > yMin)
        {
            ySize--;
        }
        if (xSize <= xMin && ySize <= yMin)
        {
            resetTab();
        }
    }

    private ResourceLocation getResourceForType(AdjacentBlockType type)
    {
        switch (type)
        {
            case CHEST_SINGLE:
                return new ResourceLocation(Compendium.Naming.id, Compendium.Texture.GUI.singleChestTabBackground);
        }
        return null;
    }

    private int getTextureOffsetXForSide(TabSide side)
    {
        if (closedTab)
        {
            if (side == TabSide.RIGHT)
            {
                return closedTabTextureXOffset;
            }
        }
        return 0;
    }

    private int getTextureOffsetYForSide(TabSide side)
    {
        if (!closedTab)
        {
            return openTabTextureYOffset;
        }
        return 0;

    }

    private void incrementTabValues()
    {
        if (xSize < xMax)
        {
            xSize++;
        }
        if (ySize < yMax)
        {
            ySize++;
        }
        if (xSize >= xMax && ySize >= yMax)
        {
            xSize = xMax;
            ySize = yMax;
        }
    }

    private void initializeTabAnimation()
    {
        if (closedTab)
        {
            incrementTabValues();
            setTabState(false, false, true, false);
        } else
        {
            decrementTabValues();
            setTabState(false, false, false, true);
        }

    }

    private void resetTab()
    {
        setTabState(true, false, false, false);

        tabBackground = getResourceForType(blockType);
        textureX = getTextureOffsetXForSide(tabSide);
        textureY = getTextureOffsetYForSide(tabSide);
        xMin = closedTabXSize;
        yMin = closedTabYSize;
        xMax = 68;
        yMax = 176;
        xSize = xMin;
        ySize = yMin;
        openTabXSize = 68;
        openTabYSize = 176;
    }

    public void setTabGUIOffsets(int xOffset, int yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    private void setTabState(boolean closed, boolean open, boolean opening, boolean closing)
    {
        closedTab = closed;
        openTab = open;
        openingTab = opening;
        closingTab = closing;
    }

    public int getTabHeight()
    {
        return this.closedTabYSize;
    }

    public boolean intersectsWithTab(int xCoordinate, int yCoordinate)
    {
        if (xCoordinate >= xOffset && xCoordinate <= xOffset + xSize)
        {
            if (yCoordinate >= yOffset && yCoordinate <= yOffset + ySize)
            {
                return true;
            }
        }
        return false;
    }

    public void renderTab()
    {
        gui.mc.renderEngine.bindTexture(tabBackground);
        if (closingTab || openingTab)
        {
            animateTab();
        }
        int xCoord = xOffset;
        if (closedTab && tabSide == TabSide.LEFT)
        {
            xCoord = xOffset + xMax - xMin;
        }
        gui.drawTexturedModalRect(xCoord, yOffset, textureX, textureY, xSize, ySize);
    }

    public static enum TabSide
    {
        LEFT, RIGHT;

        public static TabSide getTabSideFromRelativeSide(RelativeBenchSide side)
        {
            switch (side)
            {
                case LEFT:
                    return TabSide.LEFT;
                default:
                    return TabSide.RIGHT;
            }
        }
    }
}
