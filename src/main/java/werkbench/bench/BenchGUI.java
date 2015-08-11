package werkbench.bench;

import jakimbox.prefab.gui.Tabs;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.tabTypes.ChestTab;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import werkbench.reference.Compendium;

public class BenchGUI extends GuiContainer
{
    private Tabs tabs;

    /**
     * Texture dimensions for centering calculations
     */
    private int textureSizeX = 222;
    private int textureSizeY = 256;

    /**
     * Overlap adjustment based on texture
     */
    private int tabWidthOverlap = 24;

    public BenchGUI(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {
        super(new BenchContainer(inventoryPlayer, bench));

        tabs = new Tabs(1);
        tabs.addTab(new ChestTab(Compendium.Naming.id, TabSide.LEFT), 0);

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

        // A full height texture (256 in height) is too tall for the default GUI size by 8px
        int positionOffsetY = -8;

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
        drawBenchBackground();
    }

//    @Override
//    protected void mouseClicked(int clickX, int clickY, int button)
//    {
//        super.mouseClicked(clickX, clickY, button);
//    }
}
