package werkbench.bench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jakimbox.helper.LogHelper;
import jakimbox.prefab.container.BasicInventoryContainer;
import jakimbox.reference.RelativeDirection;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import org.apache.commons.lang3.ArrayUtils;

public final class BenchContainer extends BasicInventoryContainer
{

    private final BenchTileEntity bench;

    private final int[] craftGridIDs;

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
        this.bench.updateEntity();

        if (this.bench.craftMatrix == null || this.bench.craftMatrix.equals(new InventoryCrafting(this, 3, 3)))
        {
            this.bench.craftMatrix = new InventoryCrafting(this, 3, 3);
            this.bench.restoreGridFromCache();
        }
        if (this.bench.craftResult == null)
        {
            this.bench.craftResult = new InventoryCraftResult();
        }

        for (Map.Entry<RelativeDirection, Block> entry : this.bench.getBlockCache().entrySet())
        {
            if (entry.getValue() == Blocks.chest)
            {
                int x = this.bench.xCoord + entry.getKey().x;
                int y = this.bench.yCoord;
                int z = this.bench.zCoord + entry.getKey().z;
                Block storedBlock = entry.getValue();
                if (storedBlock instanceof BlockChest && this.bench.getWorldObj().getTileEntity(x, y, z) instanceof TileEntityChest)
                {

                    IInventory chestInventory = ((BlockChest) storedBlock).func_149951_m(this.bench.getWorldObj(), x, y, z);
                    if (chestInventory != null)
                    {
                        bindChest(chestInventory, entry.getKey());
                    }
                }
            } else if (entry.getValue() == Blocks.furnace || entry.getValue() == Blocks.lit_furnace)
            {
                int x = this.bench.xCoord + entry.getKey().x;
                int y = this.bench.yCoord;
                int z = this.bench.zCoord + entry.getKey().z;
                Block storedBlock = entry.getValue();
                TileEntity worldTileEntity = bench.getWorldObj().getTileEntity(x, y, z);

                if (storedBlock instanceof BlockFurnace)
                {
                    TileEntityFurnace furnace;
                    if (worldTileEntity instanceof TileEntityFurnace)
                    {
                        furnace = (TileEntityFurnace) worldTileEntity;
                    } else
                    {
                        furnace = new TileEntityFurnace();
                    }

                    addSlotToContainer(new Slot(furnace, 0, 10, 10));
                    addSlotToContainer(new Slot(furnace, 1, 10, 28));
                    addSlotToContainer(new SlotFurnace(inventoryPlayer.player, furnace, 2, 10, 46));

                }
            }
        }

        craftGridIDs = new int[10];
        bindCraftGrid(inventoryPlayer);

        onCraftMatrixChanged(this.bench.craftMatrix);
    }

    private void bindChest(IInventory chestInventory, RelativeDirection direction)
    {
        int slotID;
        int slots[] = new int[27];
        int count = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                slotID = j + i * 9;

                slots[count] = addSlotToContainer(new Slot(chestInventory, slotID, -999, -999)).slotNumber;
                count++;
            }
        }
        slotIDs.put(direction, slots);
    }

    /**
     * Add the crafting grid to the GUI
     *
     * @param bench the bench TileEntity
     */
    private void bindCraftGrid(InventoryPlayer inventoryPlayer)
    {
        //bindCraftGridInput();

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
                x = 135 + j * 18;
                y = 92 + i * 18;

                craftGridIDs[slot] = addSlotToContainer(new Slot(this.bench.craftMatrix, 3 + slot++, x, y)).slotNumber;
            }
        }
    }

    private void bindCraftGridOutput(InventoryPlayer inventoryPlayer)
    {
        craftGridIDs[9] = addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.bench.craftMatrix, this.bench.craftResult, 0, 204, 110)).slotNumber;
    }

    protected int getNextSlotId()
    {
        int nextSlot = 0;
        boolean doReturn = false;
        while (!doReturn)
        {
            if (!ArrayUtils.contains(craftGridIDs, nextSlot))
            {
                if (!ArrayUtils.contains(inventorySlotIDs, nextSlot))
                {
                    boolean contains = false;
                    for (Map.Entry<RelativeDirection, int[]> entry : slotIDs.entrySet())
                    {
                        if (ArrayUtils.contains(entry.getValue(), nextSlot))
                        {
                            contains = true;
                        }

                    }
                    if (!contains)
                    {
                        doReturn = true;
                    }

                }
            }
            if (!doReturn)
            {
                nextSlot++;
            }
        }
        return nextSlot;
    }

    protected boolean mergeIntoPlayerInventory(ItemStack stack, Slot craftSlot)
    {
        boolean ret = false;
        if (inventorySlotIDs != null)
        {
            for (int i = 0; i < inventorySlotIDs.length; i++)
            {
                Slot slot = (Slot) this.inventorySlots.get(inventorySlotIDs[i]);
                if (slot != null)
                {
                    if (slot.getHasStack())
                    {
                        ItemStack slotStack = slot.getStack();
                        if (slotStack.isItemEqual(stack))
                        {
                            int stackMax = slot.getStack().getMaxStackSize();
                            int newSize = stack.stackSize + slot.getStack().stackSize;
                            if (stackMax >= newSize)
                            {
                                slot.getStack().stackSize = stack.stackSize + slot.getStack().stackSize;
                                slot.onSlotChanged();
                                stack.stackSize = 0;
                                return true;
                            } else
                            {
                                stack.stackSize = stack.stackSize - (slot.getStack().getMaxStackSize() - slot.getStack().stackSize);
                                slot.getStack().stackSize = slot.getStack().getMaxStackSize();
                                slot.onSlotChanged();
                            }
                        }
                    }
                }
            }
            if (stack.stackSize > 0)
            {
                for (int i = 0; i < inventorySlotIDs.length; i++)
                {
                    Slot slot = (Slot) this.inventorySlots.get(inventorySlotIDs[i]);
                    if (!slot.getHasStack())
                    {
                        slot.putStack(stack.copy());

                        slot.onSlotChanged();
                        stack.stackSize = 0;
                        return true;
                    } else
                    {
                        // do nothing
                    }
                }
            }
        }

        return ret;
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
     * Place itemstacks into slots. Adds check for out of bounds exception.
     *
     * @param stack
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void putStacksInSlots(ItemStack[] stack)
    {
        for (int i = 0; i < stack.length; ++i)
        {
            try
            {
                this.getSlot(i).putStack(stack[i]);
            } catch (IndexOutOfBoundsException e)
            {
                LogHelper.warn("Index out of bounds!");
                LogHelper.warn("Length:" + stack.length);
                LogHelper.warn("Index:" + i);
            }
        }
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
        ItemStack originalStack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotID);

        if (slot != null && slot.getHasStack())
        {
            originalStack = slot.getStack();
            ItemStack stackToMerge = originalStack.copy();

            // Only allow shift clicking into the player's inventory, not out of it
            if (!ArrayUtils.contains(inventorySlotIDs, slotID))
            {
                if (!mergeIntoPlayerInventory(stackToMerge, slot))
                {
                    return null;
                }

                slot.putStack((ItemStack) null);

                slot.onSlotChange(stackToMerge, originalStack);
            }
            if (stackToMerge.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            } else
            {
                slot.onSlotChanged();
            }

            if (stackToMerge.stackSize == originalStack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, stackToMerge);
        }

        return originalStack;
    }
}
