package werkbench.bench;

import jakimbox.prefab.gui.Tabs;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.prefab.gui.tabTypes.ChestTab;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;
import werkbench.reference.Compendium;

public class BenchGUI extends GuiContainer
{
    private BenchTileEntity bench;
    private Tabs tabs;
    private int xOffset;
    private int yOffset;

    public BenchGUI(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {
        super(new BenchContainer(inventoryPlayer, bench));
        Mouse.setGrabbed(false);
        tabs = new Tabs(2);
        tabs.addTab(new ChestTab(Compendium.Naming.id, TabSide.LEFT), 0);
        this.bench = bench;

        this.xSize = 420;
        this.ySize = 206;

        updateOffsetCoordinates();
    }

    private void bindGUITexture()
    {
        this.mc.renderEngine.bindTexture(Compendium.TextureResource.GUI.background);
    }

    private void drawBenchBackground()
    {
        drawTexturedModalRect(xOffset + 98, yOffset - 50, 0, 0, 222, 256);
    }

    private void updateOffsetCoordinates()
    {
        xOffset = (width - xSize) / 2;
        yOffset = (height - ySize) / 2;
        tabs.setDefaultGUICoordinates(xOffset, yOffset);
    }

    private void updateTabs()
    {
        tabs.setDefaultGUICoordinates(xOffset, yOffset);
//        if (block on side != null)
//        {
//            if (tab for block == null)
//            {
//                tabs.addTab(new tab for block on side, id);
//            } else if (tab in memory type != block on side tab type)
//            {
//                tabs.remove(id);
//                tabs.addTab(null, width);
//            }
//
//        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mouseX, int mouseY)
    {
        // Main GUI texture rendering
        updateOffsetCoordinates();
        bindGUITexture();
        drawBenchBackground();

        // Tab operations
        updateTabs();
        tabs.renderTabs(this);

        List<String> hoverText = tabs.getTabToolTip(mouseX, mouseY);
        if (hoverText != null)
        {
            drawHoveringText(hoverText, mouseX, mouseY, mc.fontRenderer);
        }
    }

    @Override
    protected void mouseClicked(int clickX, int clickY, int button)
    {
        super.mouseClicked(clickX, clickY, button);
        tabs.doTabClicks(clickX, clickY);
    }
}
