package werkbench.bench;

import jakimbox.prefab.gui.Tabs;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.Tabs.TabType;
import jakimbox.prefab.gui.tabTypes.ChestTab;
import jakimbox.prefab.gui.tabTypes.FurnaceTab;
import jakimbox.reference.RelativeDirection;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import werkbench.reference.Compendium;

public class BenchGUI extends GuiContainer
{
    /**
     * Overlap adjustment based on texture
     */
    public int tabWidthOverlap = 24;
    public int guiTabUsableHeight = 172;

    private Tabs tabs;

    /**
     * Texture dimensions for centering calculations
     */
    public int textureSizeX = 222;
    public int textureSizeY = 256;

    // A full height texture (256 in height) is too tall for the default GUI size by 8px
    public int positionOffsetY = -8;

    public BenchGUI(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {
        super(new BenchContainer(inventoryPlayer, bench));

        tabs = new Tabs(8);
        int indexCounter = 0;

        for (Map.Entry<RelativeDirection, Block> entry : bench.getBlockCache().entrySet())
        {
            if (entry.getValue() == Blocks.chest)
            {
                // @TODO - add logic to check for double/ender chests
                tabs.addTab(new ChestTab(Compendium.Naming.id, RelativeDirection.getRelativeDirectionTabSide(entry.getKey()), TabType.CHEST_SINGLE), indexCounter);
                indexCounter++;
            } else if (entry.getValue() == Blocks.ender_chest)
            {
                tabs.addTab(new ChestTab(Compendium.Naming.id, RelativeDirection.getRelativeDirectionTabSide(entry.getKey()), TabType.CHEST_ENDER), indexCounter);
                indexCounter++;
            } else if (entry.getValue() == Blocks.furnace)
            {
                tabs.addTab(new FurnaceTab(Compendium.Naming.id, RelativeDirection.getRelativeDirectionTabSide(entry.getKey())), indexCounter);
                indexCounter++;
            }
        }
        xSize = textureSizeX + (tabs.getTabsWidth() - tabWidthOverlap);
        ySize = textureSizeY;
    }

    private void bindGUITexture()
    {
        this.mc.renderEngine.bindTexture(Compendium.TextureResource.GUI.background);
    }

    private void drawBenchBackground()
    {
        // The background texture has no offset
        int textureOffsetX = 0;
        int textureOffsetY = 0;

        drawTexturedModalRect(getOffsetX() + (tabs.getTabsWidth() - tabWidthOverlap) / 2, getOffsetY() + positionOffsetY, textureOffsetX, textureOffsetY, textureSizeX, textureSizeY);
    }

    private int getOffsetX()
    {
        return (int) (width - xSize) / 2;
    }

    private int getOffsetY()
    {
        return (int) (height - ySize) / 2;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mouseX, int mouseY)
    {
        // Main GUI texture rendering
        bindGUITexture();
        updateGUIOffsets();
        drawBenchBackground();
        tabs.renderTabs(this);
    }

    @Override
    protected void mouseClicked(int clickX, int clickY, int button)
    {
        super.mouseClicked(clickX, clickY, button);

        TabSide sideClicked;
        if (clickX < this.width / 2)
        {
            sideClicked = TabSide.LEFT;
        } else
        {
            sideClicked = TabSide.RIGHT;
        }

        tabs.doTabClicks(clickX, clickY, sideClicked);
    }

    @Override
    protected void mouseMovedOrUp(int x, int y, int which)
    {
        super.mouseMovedOrUp(x, y, which);
//        List<String> hoverText = tabs.getTabToolTip(x, y);
//        if (hoverText != null)
//        {
//            drawHoveringText(hoverText, x, y, mc.fontRenderer);
//        }

    }

    private void updateGUIOffsets()
    {
        int xCoordOffset = width / 2 - textureSizeX / 2 + tabWidthOverlap - Tabs.iconWidth;
        int yCoordOffset = height / 2 + textureSizeY / 2 - guiTabUsableHeight + positionOffsetY;
        tabs.setDefaultGUICoordinates(xCoordOffset, yCoordOffset, textureSizeX - tabWidthOverlap * 2 + 1);
    }
}
