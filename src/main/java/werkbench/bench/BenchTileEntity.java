package werkbench.bench;

import codechicken.lib.inventory.InventoryUtils;
import java.util.EnumMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import werkbench.reference.Compendium;

public class BenchTileEntity extends TileEntity implements IInventory
{

    // The inventory is a 3x3 grid (for crafting)
    public ItemStack[] craftGrid = new ItemStack[9];
    private final Map<ForgeDirection, Boolean> chestOnSide = new EnumMap<ForgeDirection, Boolean>(ForgeDirection.class);
    private TileEntityChest chestLeft;
    private TileEntityChest chestRight;

    public BenchTileEntity()
    {
        super();
        for (ForgeDirection VALID_DIRECTION : ForgeDirection.VALID_DIRECTIONS)
        {
            chestOnSide.put(VALID_DIRECTION, false);
        }

    }

    public TileEntityChest getChestLeftTileEntity()
    {

        TileEntity potentialChest = this.worldObj.getTileEntity(getLeftChestDirection().offsetX + xCoord, getLeftChestDirection().offsetY + yCoord, getLeftChestDirection().offsetZ + zCoord);
        if (potentialChest instanceof TileEntityChest)
        {
            chestLeft = ((TileEntityChest) potentialChest);
        } else
        {
            chestLeft = null;
        }
        return chestLeft;
    }

    public TileEntityChest getChestRightTileEntity()
    {

        TileEntity potentialChest = this.worldObj.getTileEntity(getRightChestDirection().offsetX + xCoord, getRightChestDirection().offsetY + yCoord, getRightChestDirection().offsetZ + zCoord);
        if (potentialChest instanceof TileEntityChest)
        {
            chestRight = ((TileEntityChest) potentialChest);
        } else
        {
            chestRight = null;
        }
        return chestRight;
    }

    /**
     * Check if the bench has a chest on a specific side
     *
     * @param direction direction to check
     * @return boolean if there's a chest on the given side
     */
    public boolean getHasChestOnSide(ForgeDirection direction)
    {
        return chestOnSide.get(direction);
    }

    /**
     * Get the ForgeDirection for the bench's left side
     *
     * @return ForgeDirection
     */
    public ForgeDirection getLeftChestDirection()
    {
        int meta = this.getBlockMetadata();
        switch (meta)
        {
            case 0:
                return ForgeDirection.EAST;
            case 1:
                return ForgeDirection.SOUTH;
            case 2:
                return ForgeDirection.WEST;
            case 3:
                return ForgeDirection.NORTH;
            default:
                return ForgeDirection.UNKNOWN;
        }
    }

    /**
     * Get the ForgeDirection for the bench's right side
     *
     * @return ForgeDirection
     */
    public ForgeDirection getRightChestDirection()
    {
        return getLeftChestDirection().getOpposite();
    }

    /**
     * Check if the bench has a chest on the left side
     *
     * @return boolean
     */
    public boolean getHasChestLeft()
    {
        int meta = this.getBlockMetadata();
        switch (meta)
        {
            case 0:
                return chestOnSide.get(ForgeDirection.EAST);
            case 1:
                return chestOnSide.get(ForgeDirection.SOUTH);
            case 2:
                return chestOnSide.get(ForgeDirection.WEST);
            case 3:
                return chestOnSide.get(ForgeDirection.NORTH);
            default:
                return false;
        }
    }

    /**
     * Check if the bench has a chest on the right side
     *
     * @return boolean
     */
    public boolean getHasChestRight()
    {
        int meta = this.getBlockMetadata();
        switch (meta)
        {
            case 0:
                return chestOnSide.get(ForgeDirection.WEST);
            case 1:
                return chestOnSide.get(ForgeDirection.NORTH);
            case 2:
                return chestOnSide.get(ForgeDirection.EAST);
            case 3:
                return chestOnSide.get(ForgeDirection.SOUTH);
            default:
                return false;
        }
    }

    public boolean chestIsDouble(ForgeDirection direction)
    {
        TileEntity potentialChest = this.worldObj.getTileEntity(direction.offsetX + xCoord, direction.offsetY + yCoord, direction.offsetZ + zCoord);
        if (potentialChest instanceof TileEntityChest)
        {
            ((TileEntityChest) potentialChest).checkForAdjacentChests();
        }
        return false;
    }

    /**
     * Checks for chests on each side on update
     */
    @Override
    public void updateEntity()
    {
        for (int i = 0; i < chestOnSide.size(); i++)
        {
            ForgeDirection direction = ForgeDirection.getOrientation(i);
            TileEntity potentialChest = this.worldObj.getTileEntity(direction.offsetX + xCoord, direction.offsetY + yCoord, direction.offsetZ + zCoord);
            if (potentialChest instanceof TileEntityChest)
            {
                chestOnSide.put(direction, true);
            } else
            {
                chestOnSide.put(direction, false);
            }

        }
    }

    /**
     * Get the size of the inventory
     *
     * @return int size of the inventory object
     */
    @Override
    public int getSizeInventory()
    {
        return craftGrid.length;
    }

    /**
     * Get the item from a specific slot
     *
     * @param slot slot to get the item from
     * @return the itemstack from the slot
     */
    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return craftGrid[slot];
    }

    /**
     * Decrease the stack in a given slot by a given amount
     *
     * @param slot   the inventory slot
     * @param amount the amount to decrease the stack by
     * @return ItemStack the stack from the slot
     */
    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        return InventoryUtils.decrStackSize(this, slot, amount);
    }

    /**
     * Get the stack in a given slot on GUI close
     *
     * @param slot the slot to get from
     * @return ItemStack the stack from the slot
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return InventoryUtils.getStackInSlotOnClosing(this, slot);
    }

    /**
     * Set the inventory slot to a given itemstack
     *
     * @param slot  which slot should the itemstack go into
     * @param stack the stack to put into the slot
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        craftGrid[slot] = stack;
        markDirty();
    }

    /**
     * Get the inventory name
     *
     * @return String the unlocalized inventory name
     */
    @Override
    public String getInventoryName()
    {
        return Compendium.Naming.inventoryName;
    }

    /**
     * Check if the inventory has a custom name
     *
     * @return false
     */
    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    /**
     * Get the stack size limit for the inventory
     *
     * @return 64
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Check if the player can use the inventory
     *
     * @param entityPlayer the player object
     * @return boolean based on distance and tileEntity status
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        double dist = entityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D);
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : dist <= 64.0D;
    }

    @Override
    public void openInventory()
    {
    }

    @Override
    public void closeInventory()
    {
    }

    /**
     * Can an item be put into the slot
     *
     * @param slot  slot to check
     * @param stack itemstack to check
     * @return true
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return true;
    }

    /**
     * Read saved values from NBT
     *
     * @param nbttagcompound
     */
    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);

        NBTTagList nbttaglist = nbttagcompound.getTagList("BenchInventory", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < craftGrid.length; i++)
        {
            craftGrid[i] = ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i));
        }

    }

    /**
     * Save data to NBT
     *
     * @param nbttagcompound
     */
    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < craftGrid.length; i++)
        {
            if (craftGrid[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                craftGrid[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            } else
            {
                nbttaglist.appendTag(new NBTTagCompound());
            }

        }
        nbttagcompound.setTag("BenchInventory", nbttaglist);
    }
}
