package werkbench.network.message;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import werkbench.bench.BenchTileEntity;
import werkbench.network.MessageHandler;

/**
 * A client sends this message to the server when the bench GUI needs a furnace update
 *
 * @author jakimfett
 */
public class FurnaceUpdateRequestMessage implements IMessage, IMessageHandler<FurnaceUpdateRequestMessage, IMessage>
{
    private int benchX, benchY, benchZ;
    private int furnaceSideOrdinal;
    private int furnaceX;
    private int furnaceY;
    private int furnaceZ;

    public FurnaceUpdateRequestMessage()
    {
    }

    public FurnaceUpdateRequestMessage(TileEntityFurnace furnace, BenchTileEntity bench)
    {
        furnaceX = furnace.xCoord;
        furnaceY = furnace.yCoord;
        furnaceZ = furnace.zCoord;
        //this.furnaceSideOrdinal = side.ordinal();

        benchX = bench.xCoord;
        benchY = bench.yCoord;
        benchZ = bench.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        furnaceX = buf.readInt();
        furnaceY = buf.readInt();
        furnaceZ = buf.readInt();
        furnaceSideOrdinal = buf.readInt();

        benchX = buf.readInt();
        benchY = buf.readInt();
        benchZ = buf.readInt();
    }

    /**
     * When the server receives the message from the client, this method is called
     *
     * @param message
     * @param ctx
     * @return
     */
    @Override
    public IMessage onMessage(FurnaceUpdateRequestMessage message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getTileEntity(message.furnaceX, message.furnaceY, message.furnaceZ);
        if (tileEntity instanceof TileEntityFurnace)
        {
            TileEntity sourceTileEntity = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getTileEntity(message.benchX, message.benchY, message.benchZ);
            if (sourceTileEntity instanceof BenchTileEntity)
            {
                EntityPlayerMP player = ((NetHandlerPlayServer) ctx.netHandler).playerEntity;

                MessageHandler.INSTANCE.sendTo(new FurnaceUpdateResponseMessage(((TileEntityFurnace) tileEntity), ((BenchTileEntity) sourceTileEntity), message.furnaceSideOrdinal), player);
            }
        }
        return null;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(furnaceX);
        buf.writeInt(furnaceY);
        buf.writeInt(furnaceZ);
        buf.writeInt(furnaceSideOrdinal);

        buf.writeInt(benchX);
        buf.writeInt(benchY);
        buf.writeInt(benchZ);
    }
}
