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
    public AdjacentBlockType blockType;
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
    public RelativeBenchSide benchSide;
    private int textureX, textureY;
    private int xMax;
    private int xMin;
    private int xOffset;
    private int xSize;
    private int yMax;
    private int yMin;
    private int yOffset;
    private int ySize;

    public Tab(BenchGUI gui, AdjacentBlockType blockType, RelativeBenchSide side)
    {
        this.gui = gui;
        this.blockType = blockType;
        this.benchSide = side;
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
                return new ResourceLocation(Compendium.Naming.id, Compendium.Texture.GUI.chestTabBackground);
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
        textureX = getTextureOffsetXForSide(TabSide.getTabSideFromRelativeSide(benchSide));
        textureY = getTextureOffsetYForSide(TabSide.getTabSideFromRelativeSide(benchSide));
        xMin = closedTabXSize;
        yMin = closedTabYSize;
        xMax = 68;
        yMax = 176;
        xSize = xMin;
        ySize = yMin;
        openTabXSize = 68;
        openTabYSize = 176;
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

    public Object getTabSide()
    {
        return TabSide.getTabSideFromRelativeSide(benchSide);
    }

    public boolean intersectsWithTab(int clickX, int clickY)
    {

        if (clickX >= xOffset && clickX <= xOffset + xSize)
        {
            if (clickY >= yOffset && clickY <= yOffset + ySize)
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
        gui.drawTexturedModalRect(xOffset, yOffset, textureX, textureY, xSize, ySize);
    }

    public void setTabGUIOffsets(int xOffset, int yOffset)
    {

        if (closedTab && TabSide.getTabSideFromRelativeSide(benchSide) == TabSide.LEFT)
        {
            this.xOffset = xOffset + xMax - xMin;
        } else
        {
            this.xOffset = xOffset;
        }
        this.yOffset = yOffset;
    }

    public void drawTooltipForTab(int mouseX, int mouseY)
    {
        int xPos = 6;
        int yPos = 8;
        int lineHeight = 11;
        gui.mc.fontRenderer.drawStringWithShadow("type: " + this.blockType.toString(), mouseX + xPos, mouseY + yPos, 0xFFFFFF);
        gui.mc.fontRenderer.drawStringWithShadow("side: " + this.benchSide.toString(), mouseX + xPos, mouseY + yPos + lineHeight, 0xFFFFFF);
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
