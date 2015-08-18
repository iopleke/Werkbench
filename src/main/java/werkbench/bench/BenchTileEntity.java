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

    private NBTTagCompound tag;

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
        tag = nbttagcompound;
        if (craftMatrix != null)
        {
            restoreGridFromSavedNBT();
        }
    }

    public void restoreGridFromSavedNBT()
    {
        if (tag != null && craftMatrix != null)
        {
            NBTTagList nbttaglist = tag.getTagList(craftMatrix.getInventoryName(), Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < 9; i++)
            {
                craftMatrix.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i)));
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
            nbttagcompound.setTag(craftMatrix.getInventoryName(), nbttaglist);
        }
    }
}
