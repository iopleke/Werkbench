package werkbench.bench;

import jakimbox.prefab.container.BasicInventoryContainer;
import jakimbox.prefab.gui.Tabs.TabSide;
import jakimbox.reference.RelativeDirection;
import java.util.EnumMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
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

    private final Map<RelativeDirection, int[]> slotIDs = new EnumMap<RelativeDirection, int[]>(RelativeDirection.class);

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
                IInventory chestInventory = ((BlockChest) entry.getValue()).func_149951_m(this.bench.getWorldObj(), x, y, z);
                if (chestInventory != null)
                {
                    bindChest(chestInventory, entry.getKey());
                }
            }
        }
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

    public void moveBoundSlots(RelativeDirection direction, TabSide side)
    {
        int tabSlots[] = slotIDs.get(direction);
        if (tabSlots != null)
        {
            int count = 0;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    if (side == TabSide.LEFT)
                    {
                        ((Slot) this.inventorySlots.get(tabSlots[count])).xDisplayPosition = i * 18 - 1;
                    } else
                    {
                        ((Slot) this.inventorySlots.get(tabSlots[count])).xDisplayPosition = 270 + i * 18;
                    }

                    ((Slot) this.inventorySlots.get(tabSlots[count])).yDisplayPosition = 73 + j * 18;
                    count++;

                }
            }
        }
    }

    public void resetBoundSlots(RelativeDirection direction)
    {
        int tabSlots[] = slotIDs.get(direction);
        if (tabSlots != null)
        {
            int count = 0;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    ((Slot) this.inventorySlots.get(tabSlots[count])).xDisplayPosition = -999;
                    ((Slot) this.inventorySlots.get(tabSlots[count])).yDisplayPosition = -999;
                    count++;
                }
            }
        }
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
