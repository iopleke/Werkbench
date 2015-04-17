package werkbench.bench;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import werkbench.network.MessageHandler;
import werkbench.network.message.BenchUpdateMessage;
import werkbench.reference.Compendium;
import werkbench.reference.Compendium.AdjacentBlockType;
import werkbench.reference.Config;

public class BenchTileEntity extends TileEntity implements IInventory
{
    private final Map<ForgeDirection, Enum> blockMemory = new EnumMap<ForgeDirection, Enum>(ForgeDirection.class);
    private int processingTicks;

    // The inventory is a 3x3 grid (for crafting)
    public ItemStack[] craftGrid = new ItemStack[9];

    public BenchTileEntity()
    {
        super();
        resetBlockMemory();
        resetProcessingTicks();

    }

    private void incrementProcessingTicks()
    {
        processingTicks++;
    }

    private void resetBlockMemory()
    {
        for (ForgeDirection VALID_DIRECTION : ForgeDirection.VALID_DIRECTIONS)
        {
            blockMemory.put(VALID_DIRECTION, AdjacentBlockType.EMPTY);
        }
    }

    private void resetProcessingTicks()
    {
        processingTicks = new Random().nextInt(Config.maxUpdateTickCount);
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
        ItemStack item = this.craftGrid[slot];
        if (item != null)
        {
            if (item.stackSize <= amount)
            {
                this.craftGrid[slot] = null;
            } else
            {
                item = this.craftGrid[slot].splitStack(amount);
                if (this.craftGrid[slot].stackSize < 1)
                {
                    this.craftGrid[slot] = null;
                }
            }
            markDirty();
        }
        return item;
    }

    /**
     * Check if the bench has a chest on a specific side
     *
     * @param direction direction to check
     * @return Enum type for side
     */
    public Enum getBlockForDirection(ForgeDirection direction)
    {
        return blockMemory.get(direction);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        // @TODO remove this if I don't end up needing custom packets
        this.writeToNBT(new NBTTagCompound());
        MessageHandler.INSTANCE.sendToServer(new BenchUpdateMessage(this));
        return null;
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
    public ForgeDirection getLeftDirection()
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

    public TileEntityChest getLeftDoubleTileEntity()
    {
        int xOffset = getLeftDirection().offsetX + getLeftDirection().offsetX + xCoord;
        int yOffset = getLeftDirection().offsetY + getLeftDirection().offsetY + yCoord;
        int zOffset = getLeftDirection().offsetZ + getLeftDirection().offsetZ + zCoord;

        TileEntity tileEntity = worldObj.getTileEntity(xOffset, yOffset, zOffset);
        if (tileEntity instanceof TileEntityChest)
        {
            return ((TileEntityChest) tileEntity);
        }
        return null;
    }

    public TileEntity getTileEntityForSide(ForgeDirection direction)
    {
        int xOffset = direction.offsetX + xCoord;
        int yOffset = direction.offsetY + yCoord;
        int zOffset = direction.offsetZ + zCoord;
        return worldObj.getTileEntity(xOffset, yOffset, zOffset);
    }

    /**
     * Get the ForgeDirection for the bench's right side
     *
     * @return ForgeDirection
     */
    public ForgeDirection getRightDirection()
    {
        return getLeftDirection().getOpposite();
    }

    public TileEntityChest getRightDoubleTileEntity()
    {
        int xOffset = getRightDirection().offsetX + getRightDirection().offsetX + xCoord;
        int yOffset = getRightDirection().offsetY + getRightDirection().offsetY + yCoord;
        int zOffset = getRightDirection().offsetZ + getRightDirection().offsetZ + zCoord;

        TileEntity tileEntity = worldObj.getTileEntity(xOffset, yOffset, zOffset);
        if (tileEntity instanceof TileEntityChest)
        {
            return ((TileEntityChest) tileEntity);
        }
        return null;
    }

    public TileEntityFurnace getRightFurnaceTileEntity()
    {
        int xOffset = getRightDirection().offsetX + xCoord;
        int yOffset = getRightDirection().offsetY + yCoord;
        int zOffset = getRightDirection().offsetZ + zCoord;

        TileEntity tileEntity = worldObj.getTileEntity(xOffset, yOffset, zOffset);
        if (tileEntity instanceof TileEntityFurnace)
        {
            return ((TileEntityFurnace) tileEntity);
        }
        return null;
    }

    public TileEntityFurnace getLeftFurnaceTileEntity()
    {
        int xOffset = getLeftDirection().offsetX + xCoord;
        int yOffset = getLeftDirection().offsetY + yCoord;
        int zOffset = getLeftDirection().offsetZ + zCoord;

        TileEntity tileEntity = worldObj.getTileEntity(xOffset, yOffset, zOffset);
        if (tileEntity instanceof TileEntityFurnace)
        {
            return ((TileEntityFurnace) tileEntity);
        }
        return null;
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
     * Get the stack in a given slot on GUI close
     *
     * @param slot the slot to get from
     * @return ItemStack the stack from the slot
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack stack = craftGrid[slot];
        craftGrid[slot] = null;
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

    public boolean isChestDouble(ForgeDirection direction)
    {
        TileEntity potentialChest = this.worldObj.getTileEntity(direction.offsetX + direction.offsetX + xCoord, direction.offsetY + direction.offsetY + yCoord, direction.offsetZ + direction.offsetZ + zCoord);
        if (potentialChest instanceof TileEntityChest)
        {
            return true;
        }
        return false;
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
        NBTTagList nbtList = nbtTag.getTagList("BenchInventory", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < craftGrid.length; i++)
        {
            craftGrid[i] = ItemStack.loadItemStackFromNBT(nbtList.getCompoundTagAt(i));
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
        craftGrid[slot] = stack;
        markDirty();
    }

    /**
     * Checks for chests on each side on update
     */
    @Override
    public void updateEntity()
    {
        // @TODO - figure out why the game crashes if you don't place a new bench on world load
        if (processingTicks >= Config.maxUpdateTickCount)
        {
            updateSideChecks();
            resetProcessingTicks();
        } else
        {
            incrementProcessingTicks();
        }
    }

    public void updateSideChecks()
    {
        for (int i = 0; i < blockMemory.size(); i++)
        {
            ForgeDirection direction = ForgeDirection.getOrientation(i);

            TileEntity tileEntity = getTileEntityForSide(direction);
            blockMemory.put(direction, getTypeFromTileEntity(tileEntity));
        }
    }

    private AdjacentBlockType getTypeFromTileEntity(TileEntity tileEntity)
    {
        if (tileEntity instanceof TileEntityChest)
        {
            return AdjacentBlockType.CHEST;
        } else if (tileEntity instanceof TileEntityFurnace)
        {
            if (isActiveFurnace(tileEntity))
            {
                return AdjacentBlockType.FURNACE_ACTIVE;
            } else
            {
                return AdjacentBlockType.FURNACE;
            }
        } else
        {
            return AdjacentBlockType.EMPTY;
        }
    }

    private boolean isActiveFurnace(TileEntity tileEntity)
    {
        Block block = worldObj.getBlock(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        if (block != null)
        {
            if (block.equals(Blocks.lit_furnace))
            {
                return true;
            }
        }
        return false;
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

        for (ItemStack stack : craftGrid)
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
        nbttagcompound.setTag("BenchInventory", nbttaglist);

    }
}
