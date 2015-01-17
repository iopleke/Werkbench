package werkbench.bench;

import codechicken.lib.inventory.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import werkbench.reference.Compendium;

public class BenchTileEntity extends TileEntity implements IInventory
{
	private final ItemStack[] inventory;

	public BenchTileEntity()
	{
		// The inventory is a 3x3 grid (for crafting)
		inventory = new ItemStack[9];
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
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
		return inventory[slot];
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
		inventory[slot] = stack;
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
}
