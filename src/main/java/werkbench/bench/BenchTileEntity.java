package werkbench.bench;

import java.util.EnumMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import werkbench.network.MessageHandler;
import werkbench.network.message.BenchUpdateMessage;
import werkbench.reference.Compendium;
import werkbench.reference.Config;

public class BenchTileEntity extends TileEntity implements IInventory
{
    private TileEntityChest chestLeft;
    private final Map<ForgeDirection, Boolean> chestOnSide = new EnumMap<ForgeDirection, Boolean>(ForgeDirection.class);
    private TileEntityChest chestRight;
    private int selectedWerkspace;

    // The inventory is a 3x3 grid (for crafting)
    public ItemStack[][] craftGrid = new ItemStack[Config.werkspaceCount][9];

    public BenchTileEntity()
    {
        super();
        selectedWerkspace = 0;
        for (ForgeDirection VALID_DIRECTION : ForgeDirection.VALID_DIRECTIONS)
        {
            chestOnSide.put(VALID_DIRECTION, false);
        }

    }

    public boolean chestIsDouble(ForgeDirection direction)
    {
        TileEntity potentialChest = this.worldObj.getTileEntity(direction.offsetX + direction.offsetX + xCoord, direction.offsetY + direction.offsetY + yCoord, direction.offsetZ + direction.offsetZ + zCoord);
        if (potentialChest instanceof TileEntityChest)
        {
            return true;
        }
        return false;
    }

    @Override
    public void closeInventory()
    {
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
        ItemStack item = this.craftGrid[selectedWerkspace][slot];
        if (item != null)
        {
            if (item.stackSize <= amount)
            {
                this.craftGrid[selectedWerkspace][slot] = null;
            } else
            {
                item = this.craftGrid[selectedWerkspace][slot].splitStack(amount);
                if (this.craftGrid[selectedWerkspace][slot].stackSize < 1)
                {
                    this.craftGrid[selectedWerkspace][slot] = null;
                }
            }
            markDirty();
        }
        return item;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        this.writeToNBT(new NBTTagCompound());
        return MessageHandler.INSTANCE.getPacketFrom(new BenchUpdateMessage(this));
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
     * Check if the bench has a chest on the left side
     *
     * @return boolean
     */
    public boolean getHasLeftChest()
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
    public boolean getHasRightChest()
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

    public TileEntityChest getLeftChestDoubleTileEntity()
    {

        TileEntity potentialChest = this.worldObj.getTileEntity(getLeftChestDirection().offsetX + getLeftChestDirection().offsetX + xCoord, getLeftChestDirection().offsetY + getLeftChestDirection().offsetY + yCoord, getLeftChestDirection().offsetZ + getLeftChestDirection().offsetZ + zCoord);
        if (potentialChest instanceof TileEntityChest)
        {
            chestLeft = ((TileEntityChest) potentialChest);
        } else
        {
            chestLeft = null;
        }
        return chestLeft;
    }

    public TileEntityChest getLeftChestSingleTileEntity()
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

    /**
     * Get the ForgeDirection for the bench's right side
     *
     * @return ForgeDirection
     */
    public ForgeDirection getRightChestDirection()
    {
        return getLeftChestDirection().getOpposite();
    }

    public TileEntityChest getRightChestDoubleTileEntity()
    {

        TileEntity potentialChest = this.worldObj.getTileEntity(getRightChestDirection().offsetX + getRightChestDirection().offsetX + xCoord, getRightChestDirection().offsetY + getRightChestDirection().offsetY + yCoord, getRightChestDirection().offsetZ + getRightChestDirection().offsetZ + zCoord);
        if (potentialChest instanceof TileEntityChest)
        {
            chestRight = ((TileEntityChest) potentialChest);
        } else
        {
            chestRight = null;
        }
        return chestRight;
    }

    public TileEntityChest getRightChestSingleTileEntity()
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

    public int getSelectedWerkspace()
    {
        return this.selectedWerkspace;
    }

    /**
     * Get the size of the inventory
     *
     * @return int size of the inventory object
     */
    @Override
    public int getSizeInventory()
    {
        return craftGrid[selectedWerkspace].length;
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
        return craftGrid[selectedWerkspace][slot];
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
        ItemStack stack = craftGrid[selectedWerkspace][slot];
        craftGrid[selectedWerkspace][slot] = null;
        return stack;
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

    public void setSelectedWorkspace(int selectedWerkspace)
    {
        this.selectedWerkspace = selectedWerkspace;
    }

    public void incrementSelectedWorkspace()
    {
        if (selectedWerkspace < craftGrid.length - 1)
        {
            selectedWerkspace++;
        } else
        {
            selectedWerkspace = 0;
        }
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

    /**
     * Read saved values from NBT
     *
     * @param nbtTag
     */
    @Override
    public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
        selectedWerkspace = nbtTag.getInteger("selectedWerkspace");

        for (int s = 0; s < Config.werkspaceCount; s++)
        {
            NBTTagList nbtList = nbtTag.getTagList("BenchInventory" + s, Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < craftGrid[s].length; i++)
            {
                craftGrid[s][i] = ItemStack.loadItemStackFromNBT(nbtList.getCompoundTagAt(i));
            }
        }
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
        craftGrid[selectedWerkspace][slot] = stack;
        markDirty();
    }

    /**
     * Checks for chests on each side on update
     */
    @Override
    public void updateEntity()
    {
        updateSideChecks();
    }

    public void updateSideChecks()
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
     * Save data to NBT
     *
     * @param nbttagcompound
     */
    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        nbttagcompound.setInteger("selectedWerkspace", selectedWerkspace);

        for (int s = 0; s < Config.werkspaceCount; s++)
        {
            for (ItemStack stack : craftGrid[s])
            {
                if (stack != null)
                {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    stack.writeToNBT(nbttagcompound1);
                    nbttaglist.appendTag(nbttagcompound1);
                } else
                {
                    nbttaglist.appendTag(new NBTTagCompound());
                }
            }
            nbttagcompound.setTag("BenchInventory" + s, nbttaglist);
        }
    }
}
