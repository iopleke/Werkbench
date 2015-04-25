package werkbench.bench;

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
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import werkbench.helper.SpatialHelper;
import werkbench.reference.Compendium.AdjacentBlockType;
import werkbench.reference.Compendium.RelativeBenchSide;

public final class BenchContainer extends Container
{

    private final BenchTileEntity bench;

    private Map<ForgeDirection, int[]> directionalSlots = new EnumMap<ForgeDirection, int[]>(ForgeDirection.class);
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

        bindTileEntitySlotsForSide(inventoryPlayer, RelativeBenchSide.LEFT);
        bindTileEntitySlotsForSide(inventoryPlayer, RelativeBenchSide.RIGHT);
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

    private void bindSlotsForChest(TileEntityChest chest, RelativeBenchSide side, AdjacentBlockType chestType)
    {
        int slot, xOffset, yOffset, count = 0;
        int[] guiOffsets;

        int[] slotArray = new int[27];

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                slot = j + i * 9;
                xOffset = i * 18;
                yOffset = j * 18;
                slotArray[count++] = slot;
                guiOffsets = AdjacentBlockType.getGUISlotCoordinates(side, chestType);
                addSlotToContainer(new Slot(chest, slot, guiOffsets[0] + xOffset, guiOffsets[1] + yOffset));
            }
        }
        directionalSlots.put(SpatialHelper.getDirectionFromRelativeSide(bench, RelativeBenchSide.LEFT), slotArray);
    }

    private void bindSlotsForFurnace(TileEntityFurnace furnace, InventoryPlayer inventoryPlayer, RelativeBenchSide side)
    {
        int slot = 0;
        int[] guiOffsets = AdjacentBlockType.getGUISlotCoordinates(side, AdjacentBlockType.FURNACE_INACTIVE);
        addSlotToContainer(new Slot(furnace, slot++, guiOffsets[0], guiOffsets[1]));
        addSlotToContainer(new Slot(furnace, slot++, guiOffsets[0] + 21, guiOffsets[1] + 44));
        addSlotToContainer(new SlotFurnace(inventoryPlayer.player, furnace, slot++, guiOffsets[0] + 42, guiOffsets[1]));
    }

    private void bindTileEntitySlotsForSide(InventoryPlayer inventoryPlayer, RelativeBenchSide side)
    {
        TileEntity tileEntity = SpatialHelper.getTileEntityForRelativeSide(bench, side);
        if (tileEntity instanceof TileEntityChest)
        {
            bindSlotsForChest(((TileEntityChest) tileEntity), side, AdjacentBlockType.CHEST_SINGLE);
            TileEntity offsetTileEntity = SpatialHelper.getTileEntityForRelativeSide(bench, side, 2);
            if (offsetTileEntity instanceof TileEntityChest)
            {
                bindSlotsForChest(((TileEntityChest) offsetTileEntity), side, AdjacentBlockType.CHEST_DOUBLE);
            }
        } else if (tileEntity instanceof TileEntityFurnace)
        {
            bindSlotsForFurnace(((TileEntityFurnace) tileEntity), inventoryPlayer, side);
        }
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
