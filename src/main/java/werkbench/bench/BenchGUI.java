package werkbench.bench;

import jakimbox.prefab.gui.BasicTabbedGUI;
import jakimbox.prefab.gui.Tabs;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import werkbench.reference.Compendium.Naming;

public class BenchGUI extends BasicTabbedGUI
{
    public int guiTabUsableHeight = 172;
    // A full height texture (256 in height) is too tall for the default GUI size by 8px
    public int positionOffsetY = -8;

    public BenchGUI(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {
        super(Naming.id, new BenchContainer(inventoryPlayer, bench), bench, world, 8);

        textureWidth = 222;
        xSize = textureWidth + tabs.getTabsWidth() - tabWidthOverlap;
        ySize = textureHeight;
    }

    /**
     * Draw the GUI background layer
     */
    @Override
    protected void drawGUIBackground()
    {
        drawTexturedModalRect(getGUIOffsetX() + (tabs.getTabsWidth() / 2) - this.tabWidthOverlap + 12, getGUIOffsetY() + positionOffsetY, textureX, textureY, textureWidth, textureHeight);
    }

    @Override
    protected void updateGUIOffsets()
    {
        int xCoordOffset = width / 2 - textureWidth / 2 + tabWidthOverlap - Tabs.iconWidth;
        int yCoordOffset = height / 2 + textureHeight / 2 - guiTabUsableHeight + positionOffsetY;
        tabs.setDefaultGUICoordinates(xCoordOffset, yCoordOffset, textureWidth - tabWidthOverlap * 2 + 1);
    }
}
