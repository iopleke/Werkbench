package werkbench.bench;

import jakimbox.prefab.tileEntity.TabbedInventoryTileEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import werkbench.reference.Compendium;

public class BenchTileEntity extends TabbedInventoryTileEntity
{
    public InventoryCrafting craftMatrix;
    public IInventory craftResult = new InventoryCraftResult();

    public ItemStack[] craftGridCache = new ItemStack[9];

    public BenchTileEntity()
    {
        super(Compendium.Naming.block, 9);
    }

    /**
     * Read saved crafting grid from NBT
     *
     * @param nbttagcompound
     */
    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);

        if (craftMatrix != null)
        {
            NBTTagList nbttaglist = nbttagcompound.getTagList(name, Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < 9; i++)
            {
                craftMatrix.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i)));
            }
        } else
        {
            cacheCraftGrid(nbttagcompound);
        }
    }

    private void cacheCraftGrid(NBTTagCompound nbttagcompound)
    {
        NBTTagList nbttaglist = nbttagcompound.getTagList(name, Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < 9; i++)
        {
            craftGridCache[i] = ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i));
        }
    }

    public void restoreGridFromCache()
    {
        if (craftMatrix != null)
        {
            for (int i = 0; i < 9; i++)
            {
                craftMatrix.setInventorySlotContents(i, craftGridCache[i]);
            }
        }
    }

    /**
     * Save crafting grid to NBT
     *
     * @param nbttagcompound
     */
    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);

        if (craftMatrix != null)
        {
            NBTTagList nbttaglist = new NBTTagList();

            for (int i = 0; i < 9; i++)
            {
                ItemStack stack = craftMatrix.getStackInSlot(i);
                if (stack != null)
                {
                    NBTTagCompound stackNBT = new NBTTagCompound();
                    stack.writeToNBT(stackNBT);
                    nbttaglist.appendTag(stackNBT);
                } else
                {
                    nbttaglist.appendTag(new NBTTagCompound());
                }
            }
            nbttagcompound.setTag(name, nbttaglist);
        }
    }
}
