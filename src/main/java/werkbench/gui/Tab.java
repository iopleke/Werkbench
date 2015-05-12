package werkbench.gui;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import werkbench.bench.BenchGUI;
import werkbench.helper.LogHelper;
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
    private int[] defaultTextureCoordinatesClosed;
    private int[] defaultTextureCoordinatesOpen;
    private BenchGUI gui;
    private int[] guiCoordinates;
    private TabState state;
    private int[] tabSize;

    private final int tabSizeMaxX;
    private final int tabSizeMaxY;
    private final int tabSizeMinX;
    private final int tabSizeMinY;
    private int[] textureCoordinates;
    public AdjacentBlockType blockType;
    public RelativeBenchSide side;

    public Tab(BenchGUI gui, AdjacentBlockType blockType, RelativeBenchSide side)
    {
        tabSizeMinX = 15;
        tabSizeMinY = 18;
        tabSizeMaxX = 68;
        tabSizeMaxY = 176;

        this.gui = gui;
        setBlockType(blockType);
        setResourceForType(blockType);
        setRelativeBenchSide(side);
        setTabState(TabState.CLOSED);

        absolutePosition();

        resetTabTextureCoordinates();
        resetTabGUICoordinates();

    }

    /**
     * The values in this function need to be set dynamically once I get the animations fixed
     */
    private void absolutePosition()
    {
        resetTabSize();
        setDefaultTabTextureCoordinatesClosed(new int[]
        {
            0, 0
        });

        setDefaultTabTextureCoordinatesOpen(new int[]
        {
            0, 18
        });
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
        if (tabSize[0] - speed * 2 > getMinTabSizeX())
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
        } else
        {
            tabSize[0] = getMinTabSizeX();
        }
        if (tabSize[1] - speed * 5 > getMinTabSizeY())
        {
            tabSize[1] = tabSize[1] - speed * 5;
        } else
        {
            tabSize[1] = getMinTabSizeY();
        }
        if (tabSize[0] <= getMinTabSizeX() && tabSize[1] <= getMinTabSizeY())
        {
            setTabState(TabState.CLOSED);
            resetTabSize();
            resetTabTextureCoordinates();
            resetTabGUICoordinates();
        }
    }

    private int[] getDefaultTabGUICoordinates()
    {
        int[] coordinates = defaultGUICoordinates;
        if (state == TabState.CLOSED && TabSide.getTabSideFromRelativeSide(side) == TabSide.LEFT)
        {
            coordinates[0] = defaultGUICoordinates[0] + getMaxTabSizeX() - getMinTabSizeX();
        }
        return coordinates;
    }

    private void incrementTabValues(int speed)
    {
        if (tabSize[0] + speed * 2 < getMaxTabSizeX())
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

        } else
        {
            tabSize[0] = getMaxTabSizeX();
            setTabTextureCoordinates(new int[]
            {
                defaultTextureCoordinatesOpen[0], textureCoordinates[1]
            });
            resetTabGUICoordinates();
            setTabGUICoordinates(new int[]
            {
                defaultGUICoordinates[0], guiCoordinates[1]
            });
        }
        if (tabSize[1] + speed * 5 < getMaxTabSizeY())
        {
            tabSize[1] = tabSize[1] + speed * 5;
        } else
        {
            tabSize[1] = getMaxTabSizeY();
        }

        if (tabSize[0] >= getMaxTabSizeX() && tabSize[1] >= getMaxTabSizeY())
        {
            setTabState(TabState.OPEN);
            resetTabSize();
            resetTabTextureCoordinates();
            resetTabGUICoordinates();
        }
    }

    private void resetTabGUICoordinates()
    {
        setDefaultTabGUICoordinates(AdjacentBlockType.getGUIBackgroundCoordinates(side, blockType));
        setTabGUICoordinates(getDefaultTabGUICoordinates());
    }

    private void resetTabSize()
    {
        if (tabSize == null)
        {
            tabSize = new int[2];
        }
        if (state == TabState.CLOSED)
        {
            tabSize[0] = getMinTabSizeX();
            tabSize[1] = getMinTabSizeY();
        } else
        {
            tabSize[0] = getMaxTabSizeX();
            tabSize[1] = getMaxTabSizeY();
        }
    }

    private void resetTabTextureCoordinates()
    {
        if (state == TabState.OPEN)
        {
            textureCoordinates = defaultTextureCoordinatesOpen;
        } else
        {
            textureCoordinates = defaultTextureCoordinatesClosed;
        }
    }

    private void setBlockType(AdjacentBlockType blockType)
    {
        this.blockType = blockType;
    }

    private void setDefaultTabTextureCoordinatesClosed(int[] newDefaultTextureCoordinates)
    {
        this.defaultTextureCoordinatesClosed = newDefaultTextureCoordinates;
    }

    private void setDefaultTabTextureCoordinatesOpen(int[] newDefaultTextureCoordinates)
    {
        this.defaultTextureCoordinatesOpen = newDefaultTextureCoordinates;
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
            case FURNACE_ACTIVE:
            case FURNACE_INACTIVE:
                tabBackground = new ResourceLocation(Compendium.Naming.id, Compendium.Texture.GUI.furnace);
                break;
            default:
                break;
        }
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

    public AdjacentBlockType getBlockType()
    {
        return blockType;
    }

    public int getMaxTabSizeX()
    {
        return tabSizeMaxX;
    }

    public int getMaxTabSizeY()
    {
        return tabSizeMaxY;
    }

    public int getMinTabSizeX()
    {
        return tabSizeMinX;
    }

    public int getMinTabSizeY()
    {
        return tabSizeMinY;
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
            setTabState(TabState.OPENING);

            setTabTextureCoordinates(new int[]
            {
                53, 18
            });

        } else if (state == TabState.OPEN)
        {
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

    public final void setDefaultTabGUICoordinates(int[] newDefaultGUICoordinates)
    {
        defaultGUICoordinates = newDefaultGUICoordinates;
    }

    public List<String> tooltipForTab(int mouseX, int mouseY)
    {
        LogHelper.debug("Tab on " + getTabSide().toString() + " is hovered!");
        List<String> toolTipText = new ArrayList<String>();
        toolTipText.add("type: " + blockType.toString());
        toolTipText.add("side: " + side.toString());
        return toolTipText;

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
