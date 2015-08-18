package werkbench.bench;

import jakimbox.prefab.container.BasicInventoryContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public final class BenchContainer extends BasicInventoryContainer
{

    private final BenchTileEntity bench;

    /**
     * Container object for the workbench
     *
     * @param inventoryPlayer the player's inventory
     * @param bench           the bench TileEntity
     */
    public BenchContainer(InventoryPlayer inventoryPlayer, BenchTileEntity bench)
    {
        super(inventoryPlayer, bench);

        this.bench = bench;
        this.bench.doCacheUpdateNow();

        if (this.bench.craftMatrix == null)
        {
            this.bench.craftMatrix = new InventoryCrafting(this, 3, 3);
            this.bench.restoreGridFromSavedNBT();
        }
        if (this.bench.craftResult == null)
        {
            this.bench.craftResult = new InventoryCraftResult();
        }

        bindCraftGrid(inventoryPlayer);

        onCraftMatrixChanged(this.bench.craftMatrix);
    }

    /**
     * Add the crafting grid to the GUI
     *
     * @param bench the bench TileEntity
     */
    private void bindCraftGrid(InventoryPlayer inventoryPlayer)
    {
        bindCraftGridInput();

        bindCraftGridOutput(inventoryPlayer);
    }

    private void bindCraftGridInput()
    {
        int x, y;
        int slot = 0;
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                x = 86 + j * 18;
                y = 92 + i * 18;

                addSlotToContainer(new Slot(this.bench.craftMatrix, slot++, x, y));
            }
        }
    }

    private void bindCraftGridOutput(InventoryPlayer inventoryPlayer)
    {
        addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.bench.craftMatrix, this.bench.craftResult, 0, 155, 110));
    }

    /**
     * Update the crafting result when the grid changes
     *
     * @param inventory
     */
    @Override
    public void onCraftMatrixChanged(IInventory inventory)
    {
        this.bench.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.bench.craftMatrix, bench.getWorldObj()));
    }

    /**
     * Shift click transfer mechanic
     *
     * @param player the player object
     * @param slotID int ID of the slot
     * @return itemstack from the slot
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        // @TODO - implement shift clicking
        // I've opted to remove this for now.
        // Shift clicking is difficult for me to get working well in most ideal case,
        // and this expanded craft grid has so much to take into account.

        return null;
    }

//    public void removeSlots()
//    {
//        for (int i = 0; i < 9; i++)
//        {
//            if (inventorySlots.get(i) != null)
//            {
//                inventorySlots.remove(i);
//            }
//        }
//    }
//
//    /**
//     * the slot is assumed empty
//     */
//    protected Slot addSlotToContainer(Slot p_75146_1_)
//    {
//        p_75146_1_.slotNumber = this.inventorySlots.size();
//        this.inventorySlots.add(p_75146_1_);
//        this.inventoryItemStacks.add((Object) null);
//        return p_75146_1_;
//    }
}
