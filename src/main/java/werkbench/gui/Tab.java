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
    private int[] defaultTextureCoordinatesClosed;
    private int[] defaultTextureCoordinatesOpen;
    private BenchGUI gui;
    private int[] guiCoordinates;
    private TabState state;
    private int[] tabSize;

    private int[] tabSizeMax;
    //private int[] minimumTabSize;
    private int[] textureCoordinates;
    public AdjacentBlockType blockType;
    public RelativeBenchSide side;

    public Tab(BenchGUI gui, AdjacentBlockType blockType, RelativeBenchSide side)
    {
        Mouse.setGrabbed(false);
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
        setMaxTabSize(new int[]
        {
            68, 176
        });
        setMinTabSize(new int[]
        {
            15, 18
        });
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
        if (tabSize[0] - speed * 2 > getMinTabSize()[0])
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
            tabSize[0] = getMinTabSize()[0];
        }
        if (tabSize[1] - speed * 5 > getMinTabSize()[1])
        {
            tabSize[1] = tabSize[1] - speed * 5;
        } else
        {
            tabSize[1] = getMinTabSize()[1];
        }
        if (tabSize[0] <= getMinTabSize()[0] && tabSize[1] <= getMinTabSize()[1])
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
            coordinates[0] = defaultGUICoordinates[0] + getMaxTabSize()[0] - getMinTabSize()[0];
        }
        return coordinates;
    }

    private void incrementTabValues(int speed)
    {
        if (tabSize[0] + speed * 2 < getMaxTabSize()[0])
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
            tabSize[0] = getMaxTabSize()[0];
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
        if (tabSize[1] + speed * 5 < getMaxTabSize()[1])
        {
            tabSize[1] = tabSize[1] + speed * 5;
        } else
        {
            tabSize[1] = getMaxTabSize()[1];
        }

        if (tabSize[0] >= getMaxTabSize()[0] && tabSize[1] >= getMaxTabSize()[1])
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
        if (state == TabState.CLOSED)
        {
            tabSize = getMinTabSize();
        } else
        {
            tabSize = getMaxTabSize();
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

    private void setMaxTabSize(int[] newMaxTabSize)
    {
        this.tabSizeMax = newMaxTabSize;
    }

    private void setMinTabSize(int[] newMinTabSize)
    {
        //minimumTabSize = newMinTabSize;
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

    public int[] getMaxTabSize()
    {
        return tabSizeMax;
    }

    public int[] getMinTabSize()
    {
        return new int[]
        {
            15, 18
        };
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
