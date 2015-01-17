package werkbench.bench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class BenchContainer extends Container
{
	private final BenchTileEntity bench;

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	public IInventory craftResult = new InventoryCraftResult();
	private World world;

	public BenchContainer(InventoryPlayer inventoryPlayer, BenchTileEntity bench, World world)
	{

		this.world = world;
		this.bench = bench;
		bindPlayerInventory(inventoryPlayer);
		bindCraftGrid(inventoryPlayer);
		addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 131, 30));
	}

	private void bindCraftGrid(InventoryPlayer inventoryPlayer)
	{
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 62 + j * 18, 12 + i * 18));
			}
		}
	}

	private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	/**
	 *
	 * @param inventory
	 */
	public void onCraftMatrixChanged(IInventory inventory)
	{
		this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot.
	 */
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slotID == 0)
			{
				if (!this.mergeItemStack(itemstack1, 10, 46, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (slotID >= 10 && slotID < 37)
			{
				if (!this.mergeItemStack(itemstack1, 37, 46, false))
				{
					return null;
				}
			} else if (slotID >= 37 && slotID < 46)
			{
				if (!this.mergeItemStack(itemstack1, 10, 37, false))
				{
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 10, 46, false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			} else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

}
