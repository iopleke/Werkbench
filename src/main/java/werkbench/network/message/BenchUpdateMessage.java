package werkbench.network.message;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import werkbench.bench.BenchTileEntity;

/**
 *
 * @author jakimfett
 */
public class BenchUpdateMessage implements IMessage, IMessageHandler<BenchUpdateMessage, IMessage>
{
    private int x, y, z;

    public BenchUpdateMessage()
    {
    }

    public BenchUpdateMessage(BenchTileEntity bench)
    {
        x = bench.xCoord;
        y = bench.yCoord;
        z = bench.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public IMessage onMessage(BenchUpdateMessage message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getTileEntity(message.x, message.y, message.z);
        if (tileEntity instanceof BenchTileEntity)
        {
            //((BenchTileEntity) tileEntity).setSelectedWorkspace(message.selectedWerkspace);
        }
        return null;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }
}
