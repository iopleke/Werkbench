package werkbench.gui;

import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import werkbench.bench.BenchGUI;
import werkbench.reference.Compendium;
import werkbench.reference.Compendium.AdjacentBlockType;
import werkbench.reference.Compendium.RelativeBenchSide;
import werkbench.reference.Compendium.TabState;

/**
 *
 * @author jakimfett
 */
public class Tab
{
    public static ResourceLocation tabBackground;

    private int[] defaultGUICoordinates;
    private int[] defaultTextureCoordinates;
    private BenchGUI gui;
    private int[] guiCoordinates;
    private TabState state;
    private int[] tabSize;

    private int[] tabSizeMax;
    private int[] tabSizeMin;
    private int[] textureCoordinates;
    public AdjacentBlockType blockType;
    public RelativeBenchSide side;

    public Tab(BenchGUI gui, AdjacentBlockType blockType, RelativeBenchSide side, int[] guiCoordinates)
    {
        Mouse.setGrabbed(false);
        this.gui = gui;
        setBlockType(blockType);
        setResourceForType(blockType);
        setRelativeBenchSide(side);
        setTabState(TabState.CLOSED);
        setMaxTabSize(new int[]
        {
            68, 176
        });
        setMinTabSize(new int[]
        {
            15, 18
        });
        resetTabSize();
        setDefaultTabTextureCoordinates(new int[]
        {
            0, 0
        });
        setTabTextureCoordinates(defaultTextureCoordinates);
        setDefaultTabGUICoordinates(guiCoordinates);
        setTabGUICoordinates(defaultGUICoordinates);

    }

    private void animateTab()
    {
        if (state == TabState.OPENING)
        {
            incrementTabValues(2);
        } else if (state == TabState.CLOSING)
        {
            decrementTabValues(2);
        }
    }

    private void decrementTabValues(int speed)
    {
        if (tabSize[0] > tabSizeMin[0])
        {
            tabSize[0] = tabSize[0] - speed * 2;
            setTabTextureCoordinates(new int[]
            {
                textureCoordinates[0] + speed * 2, textureCoordinates[1]
            });
            setTabGUICoordinates(new int[]
            {
                guiCoordinates[0] + speed * 2, guiCoordinates[1]
            });
        }
        if (tabSize[1] > tabSizeMin[1])
        {
            tabSize[1] = tabSize[1] - speed * 5;
        }
        if (tabSize[0] <= tabSizeMin[0] && tabSize[1] <= tabSizeMin[1])
        {
            tabSize = tabSizeMin;
            setTabTextureCoordinates(defaultTextureCoordinates);
            setTabGUICoordinates(defaultGUICoordinates);
            setTabDimensions(tabSizeMin);
            setTabState(TabState.CLOSED);
        }
    }

    private void incrementTabValues(int speed)
    {
        if (tabSize[0] < tabSizeMax[0])
        {

            tabSize[0] = tabSize[0] + speed * 2;
            setTabTextureCoordinates(new int[]
            {
                textureCoordinates[0] - speed * 2, textureCoordinates[1]
            });
            setTabGUICoordinates(new int[]
            {
                guiCoordinates[0] - speed * 2, guiCoordinates[1]
            });

        }
        if (tabSize[1] < tabSizeMax[1])
        {
            tabSize[1] = tabSize[1] + speed * 5;
        }

        if (tabSize[0] >= tabSizeMax[0] && tabSize[1] >= tabSizeMax[1])
        {
            tabSize = tabSizeMax;
            setTabState(TabState.OPEN);
        }
    }

    private void resetTabSize()
    {
        tabSize = tabSizeMin;
    }

    private void setBlockType(AdjacentBlockType blockType)
    {
        this.blockType = blockType;
    }

    private void setDefaultTabGUICoordinates(int[] newDefaultGUICoordinates)
    {
        defaultGUICoordinates = newDefaultGUICoordinates;
        if (state == TabState.CLOSED && TabSide.getTabSideFromRelativeSide(side) == TabSide.LEFT)
        {
            defaultGUICoordinates[0] = defaultGUICoordinates[0] + this.tabSizeMax[0] - this.tabSizeMin[0];
        }
    }

    private void setDefaultTabTextureCoordinates(int[] newDefaultTextureCoordinates)
    {
        this.defaultTextureCoordinates = newDefaultTextureCoordinates;
    }

    private void setMaxTabSize(int[] newMaxTabSize)
    {
        this.tabSizeMax = newMaxTabSize;
    }

    private void setMinTabSize(int[] newMinTabSize)
    {
        this.tabSizeMin = newMinTabSize;
    }

    private void setRelativeBenchSide(RelativeBenchSide side)
    {
        this.side = side;
    }

    private void setResourceForType(AdjacentBlockType type)
    {
        switch (type)
        {
            case CHEST_SINGLE:
                tabBackground = new ResourceLocation(Compendium.Naming.id, Compendium.Texture.GUI.chestTabBackground);
                break;
        }
    }

    private void setTabDimensions(int[] newTabSize)
    {
        tabSize = newTabSize;
    }

    private void setTabGUICoordinates(int[] newGUICoordinates)
    {
        guiCoordinates = newGUICoordinates;
    }

    private void setTabState(TabState state)
    {
        this.state = state;
    }

    private void setTabTextureCoordinates(int[] newTextureCoordinates)
    {
        this.textureCoordinates = newTextureCoordinates;
    }

    public void drawTooltipForTab(int mouseX, int mouseY)
    {
        int xPos = 6;
        int yPos = 8;
        int lineHeight = 11;
        gui.mc.fontRenderer.drawStringWithShadow("type: " + this.blockType.toString(), mouseX + xPos, mouseY + yPos, 0xFFFFFF);
        gui.mc.fontRenderer.drawStringWithShadow("side: " + this.side.toString(), mouseX + xPos, mouseY + yPos + lineHeight, 0xFFFFFF);
    }

    public int[] getTabDimensions()
    {
        return tabSize;
    }

    public Object getTabSide()
    {
        return TabSide.getTabSideFromRelativeSide(side);
    }

    public void initializeTabAnimation()
    {
        if (state == TabState.CLOSED)
        {
            setTabDimensions(new int[]
            {
                25, 25
            });
            setTabState(TabState.OPENING);
            setTabTextureCoordinates(new int[]
            {
                43, 18
            });

        } else if (state == TabState.OPEN)
        {
            decrementTabValues(2);
            setTabState(TabState.CLOSING);
        }

    }

    public boolean intersectsWithTab(int clickX, int clickY)
    {

        if (clickX >= guiCoordinates[0] && clickX <= guiCoordinates[0] + tabSize[0])
        {
            if (clickY >= guiCoordinates[1] && clickY <= guiCoordinates[1] + tabSize[1])
            {
                return true;
            }
        }
        return false;
    }

    public void renderTab()
    {
        gui.mc.renderEngine.bindTexture(tabBackground);
        animateTab();
        gui.drawTexturedModalRect(guiCoordinates[0], guiCoordinates[1], textureCoordinates[0], textureCoordinates[1], tabSize[0], tabSize[1]);
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
