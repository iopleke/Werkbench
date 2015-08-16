package werkbench.bench;

import jakimbox.prefab.tileEntity.TabbedInventoryTileEntity;
import werkbench.reference.Compendium;

public class BenchTileEntity extends TabbedInventoryTileEntity
{
    public BenchTileEntity()
    {
        super(Compendium.Naming.block, 9);
    }
}
