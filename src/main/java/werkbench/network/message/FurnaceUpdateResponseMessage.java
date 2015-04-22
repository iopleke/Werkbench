package werkbench.network.message;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import werkbench.bench.BenchTileEntity;
import werkbench.reference.Compendium.RelativeBenchSide;

/**
 * The server sends this to the client in response to the request for values
 *
 * @author jakimfett
 */
public class FurnaceUpdateResponseMessage implements IMessage, IMessageHandler<FurnaceUpdateResponseMessage, IMessage>
{
    private int furnaceCookTime, furnaceBurnTime, furnaceSideOrdinal;
    private int benchX, benchY, benchZ;

    public FurnaceUpdateResponseMessage()
    {
    }

    public FurnaceUpdateResponseMessage(TileEntityFurnace furnace, BenchTileEntity bench, int furnaceSideOrdinal)
    {
        this.furnaceSideOrdinal = furnaceSideOrdinal;

        furnaceCookTime = furnace.furnaceCookTime;
        furnaceBurnTime = furnace.furnaceBurnTime;

        benchX = bench.xCoord;
        benchY = bench.yCoord;
        benchZ = bench.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        furnaceSideOrdinal = buf.readInt();
        furnaceCookTime = buf.readInt();
        furnaceBurnTime = buf.readInt();

        benchX = buf.readInt();
        benchY = buf.readInt();
        benchZ = buf.readInt();
    }

    /**
     * The client calls this method when the message is received from the server
     *
     * @param message
     * @param ctx
     * @return
     */
    @Override
    public IMessage onMessage(FurnaceUpdateResponseMessage message, MessageContext ctx)
    {

        TileEntity tileEntity = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getTileEntity(message.benchX, message.benchY, message.benchZ);
        if (tileEntity instanceof BenchTileEntity)
        {
            RelativeBenchSide furnaceSide = RelativeBenchSide.values()[furnaceSideOrdinal];
            int[] values = new int[]
            {
                message.furnaceBurnTime, message.furnaceCookTime
            };
            ((BenchTileEntity) tileEntity).setFurnaceValuesForSide(furnaceSide, values);
        }
        return null;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(furnaceSideOrdinal);
        buf.writeInt(furnaceCookTime);
        buf.writeInt(furnaceBurnTime);

        buf.writeInt(benchX);
        buf.writeInt(benchY);
        buf.writeInt(benchZ);
    }
}