package werkbench.bench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class BenchContainer extends Container
{
    private final BenchTileEntity bench;

    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    private final World world;

    /**
     * Container object for the workbench
     *
     * @param inventoryPlayer the player's inventory
     * @param bench           the bench TileEntity
     * @param world           the world object
     */
    public BenchContainer(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
    {

        this.world = world;
        this.bench = bench;

        bindPlayerInventory(inventoryPlayer);
        bindCraftGrid(bench);

        if (bench.getHasChestLeft())
        {
            bindLeftChestSingle(bench);
        }
        if (bench.getHasChestRight())
        {
            bindRightChestSingle(bench);
        }

        // Add the crafting output to the right side
        addSlotToContainer(new SlotCrafting(inventoryPlayer.player, bench, this.craftResult, 0, 131, 60));
    }

    /**
     * Add the left chest slots
     *
     * @param BenchTileEntity the bench tile entity
     */
    private void bindLeftChestSingle(BenchTileEntity bench)
    {
        TileEntityChest chestLeft = bench.getChestLeftTileEntity();
        if (chestLeft instanceof TileEntityChest)
        {

            for (int i = 0; i < 3; i++)
            {

                for (int j = 0; j < 9; j++)
                {
                    addSlotToContainer(new Slot(chestLeft, j + i * 9, i * 18 - 60, j * 18 + 28));
                }
            }
        }

    }

    /**
     * Add the right chest slots
     *
     * @param BenchTileEntity the bench tile entity
     */
    private void bindRightChestSingle(BenchTileEntity bench)
    {
        TileEntityChest chestRight = bench.getChestRightTileEntity();
        if (chestRight instanceof TileEntityChest)
        {

            for (int i = 0; i < 3; i++)
            {

                for (int j = 0; j < 9; j++)
                {
                    addSlotToContainer(new Slot(chestRight, j + i * 9, i * 18 + 184, j * 18 + 28));
                }
            }
        }

    }

    /**
     * Add the crafting grid to the GUI
     *
     * @param bench the bench TileEntity
     */
    private void bindCraftGrid(BenchTileEntity bench)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                addSlotToContainer(new Slot(bench, j + i * 3, 62 + j * 18, 42 + i * 18));
            }
        }
    }

    /**
     * Add the player's inventory slots to the GUI
     *
     * @param inventoryPlayer the player's inventory
     */
    private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 114 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 172));
        }
    }

    /**
     * Update the crafting result when the grid changes
     *
     * @param inventory
     */
    @Override
    public void onCraftMatrixChanged(IInventory inventory)
    {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
    }

    /**
     * Send inventory changes to listeners and sync craftMatrix with TE inventory
     */
    @Override
    public void detectAndSendChanges()
    {
        for (int i = 0; i < this.inventorySlots.size(); ++i)
        {
            ItemStack itemstack = ((Slot) this.inventorySlots.get(i)).getStack();
            ItemStack itemstack1 = (ItemStack) this.inventoryItemStacks.get(i);

            if (!ItemStack.areItemStacksEqual(itemstack1, itemstack))
            {
                itemstack1 = itemstack == null ? null : itemstack.copy();
                this.inventoryItemStacks.set(i, itemstack1);

                for (int s = 0; s < bench.getSizeInventory(); s++)
                {
                    craftMatrix.setInventorySlotContents(s, bench.getStackInSlot(s));
                }

                for (int j = 0; j < this.crafters.size(); ++j)
                {
                    ((ICrafting) this.crafters.get(j)).sendSlotContents(this, i, itemstack1);
                }
            }
        }
    }

    /**
     * Determine if the player can interact with the container
     *
     * @param entityPlayer the player entity
     * @return boolean
     */
    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return true;
    }

    /**
     * Called when a player shift-clicks on a slot.
     *
     * Shift clicking currently ignores the first slot: http://pics.jakimfett.com/2015-01-17_21-05-09.png
     *
     * @param player EntityPlayer object
     * @param slotID int ID of the slot
     * @return ItemStack for the slotID
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotID);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotID == 0)
            {
                if (!this.mergeItemStack(itemstack1, 9, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotID >= 10 && slotID < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            } else if (slotID >= 37 && slotID < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            } else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

}
