package werkbench.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

/**
 *
 * @author jakimfett
 */
public class BenchUpdateMessage implements IMessage, IMessageHandler<BenchUpdateMessage, IMessage>
{
    public BenchUpdateMessage()
    {
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public IMessage onMessage(BenchUpdateMessage message, MessageContext ctx)
    {
        return null;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {

    }

}
