package werkbench.bench;

import jakimbox.prefab.gui.BasicTabbedGUI;
import jakimbox.prefab.gui.Tabs;
import jakimbox.prefab.gui.Tabs.TabType;
import jakimbox.prefab.gui.tabTypes.AnvilTab;
import jakimbox.prefab.gui.tabTypes.ChestTab;
import jakimbox.prefab.gui.tabTypes.FurnaceTab;
import jakimbox.reference.RelativeDirection;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import werkbench.reference.Compendium;
import werkbench.reference.Compendium.Naming;

public class BenchGUI extends BasicTabbedGUI
{
    public int guiTabUsableHeight = 172;
    // A full height texture (256 in height) is too tall for the default GUI size by 8px
    public int positionOffsetY = -8;

    public BenchGUI(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {
        super(Naming.id, inventoryPlayer, bench, world, 8);

        tabs = new Tabs(8);
        int indexCounter = 0;

        for (Map.Entry<RelativeDirection, Block> entry : bench.getBlockCache().entrySet())
        {
            if (entry.getValue() == Blocks.chest)
            {
                // @TODO - add logic to check for double chests
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
            } else if (entry.getValue() == Blocks.anvil)
            {
                tabs.addTab(new AnvilTab(Compendium.Naming.id, RelativeDirection.getRelativeDirectionTabSide(entry.getKey())), indexCounter);
                indexCounter++;
            }
        }
        textureWidth = 222;
        xSize = textureWidth;
        ySize = textureHeight;
    }

    /**
     * Draw the GUI background layer
     */
    @Override
    protected void drawGUIBackground()
    {
        drawTexturedModalRect(getGUIOffsetX(), getGUIOffsetY() + positionOffsetY, textureX, textureY, xSize, ySize);
    }

    @Override
    protected void updateGUIOffsets()
    {
        int xCoordOffset = width / 2 - textureWidth / 2 + tabWidthOverlap - Tabs.iconWidth;
        int yCoordOffset = height / 2 + textureHeight / 2 - guiTabUsableHeight + positionOffsetY;
        tabs.setDefaultGUICoordinates(xCoordOffset, yCoordOffset, textureWidth - tabWidthOverlap * 2 + 1);
    }

}
