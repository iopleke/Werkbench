package werkbench.bench;

import jakimbox.helper.SpatialHelper;
import jakimbox.reference.RelativeDirection;
import java.util.EnumMap;
import java.util.Map;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import werkbench.reference.GUIOffset;

public final class BenchContainer extends Container
{

    private final BenchTileEntity bench;

    private Map<RelativeDirection, Slot[]> slotCache = new EnumMap<RelativeDirection, Slot[]>(RelativeDirection.class);
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    boolean loading = false;

    /**
     * Container object for the workbench
     *
     * @param inventoryPlayer the player's inventory
     * @param bench           the bench TileEntity
     */
    public BenchContainer(InventoryPlayer inventoryPlayer, BenchTileEntity bench)
    {
        this.bench = bench;
        this.bench.updateSideChecks();

        loadCraftGridFromTileEntity();

        bindPlayerInventory(inventoryPlayer);
        bindCraftGrid(inventoryPlayer);
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
        int slot, x, y;
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                slot = j + i * 3;
                x = 184 + j * 18;
                y = 52 + i * 18;

                addSlotToContainer(new Slot(this.craftMatrix, slot, x, y));
            }
        }
    }

    private void bindCraftGridOutput(InventoryPlayer inventoryPlayer)
    {
        addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 253, 70));
    }

    /**
     * Add the player's inventory slots to the GUI
     *
     * @param inventoryPlayer the player's inventory
     */
    private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        int slot, x, y;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                slot = j + i * 9 + 9;
                x = 130 + j * 18;
                y = 124 + i * 18;
                addSlotToContainer(new Slot(inventoryPlayer, slot, x, y));
                if (i == 0)
                {
                    x = 130 + j * 18;
                    y = 182;
                    addSlotToContainer(new Slot(inventoryPlayer, j, x, y));
                }
            }
        }
    }

    /**
     * Slot binding iterator for a RelativeDirection
     *
     * @param direction
     */
    private void bindSlotsForDirection(RelativeDirection direction)
    {
        for (Slot slot : slotCache.get(direction))
        {
            addSlotToContainer(slot);
        }
    }

    private void cacheTileEntitySlotsForDirection(InventoryPlayer inventoryPlayer, RelativeDirection direction)
    {
        TileEntity tileEntity = SpatialHelper.getTileEntityForRelativeSide(bench, side);
        if (tileEntity instanceof TileEntityChest)
        {
            cacheSlotsForChest(direction, ((TileEntityChest) tileEntity));

        } else if (tileEntity instanceof TileEntityFurnace)
        {
            bindSlotsForFurnace(((TileEntityFurnace) tileEntity), inventoryPlayer, side);
        }
    }

    /**
     * Update the cached slots for a chest TileEntity
     *
     * @param direction
     * @param chest
     */
    private void cacheSlotsForChest(RelativeDirection direction, TileEntityChest chest)
    {
        int slot, xOffset, yOffset, count = 0;

        Slot[] slotArray = new Slot[27];
        if (slotCache.containsKey(direction))
        {
            slotCache.remove(direction);
        }

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                slot = j + i * 9;
                xOffset = i * 18 + GUIOffset.CHEST_SINGLE.getOffsetForSide(RelativeDirection.getRelativeDirectionTabSide(direction))[0];
                yOffset = j * 18 + GUIOffset.CHEST_SINGLE.getOffsetForSide(RelativeDirection.getRelativeDirectionTabSide(direction))[1];
                slotArray[count++] = new Slot(chest, slot, xOffset, yOffset);
            }
        }
        slotCache.put(direction, slotArray);
    }

    protected void resetSlotsForDirection(ForgeDirection direction)
    {
        int[] slotArray = this.directionalSlots.get(direction);
        for (int i = 0; i < slotArray.length; i++)
        {
            this.inventorySlots.remove(slotArray[i]);
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

                for (int j = 0; j < this.crafters.size(); ++j)
                {
                    ((ICrafting) this.crafters.get(j)).sendSlotContents(this, i, itemstack1);
                }
            }
        }
        loadCraftGridFromTileEntity();
    }

    public void loadCraftGridFromTileEntity()
    {
        if (bench.getWorldObj().isRemote && !loading)
        {
            bench.getDescriptionPacket();
        }
        loading = true;
        for (int s = 0; s < bench.getSizeInventory(); s++)
        {
            craftMatrix.setInventorySlotContents(s, bench.getStackInSlot(s));
        }
        loading = false;
    }

    /**
     * Update the crafting result when the grid changes
     *
     * @param inventory
     */
    @Override
    public void onCraftMatrixChanged(IInventory inventory)
    {
        if (bench.getWorldObj().isRemote && !loading)
        {
            bench.getDescriptionPacket();
        }
        craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.bench.getWorldObj()));
        if (!bench.getWorldObj().isRemote && !loading)
        {
            saveCraftGridToTileEntity();
        }
    }

    public void resetCraftingGrid()
    {
        craftMatrix = new InventoryCrafting(this, 3, 3);
        saveCraftGridToTileEntity();
    }

    public void saveCraftGridToTileEntity()
    {
        for (int s = 0; s < bench.getSizeInventory(); s++)
        {
            bench.setInventorySlotContents(s, craftMatrix.getStackInSlot(s));
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
        // @TODO - implement shift clicking
        // I've opted to remove this for now.
        // Shift clicking is difficult for me to get working well in most ideal case,
        // and this expanded craft grid has so much to take into account.

        return null;
    }
}
