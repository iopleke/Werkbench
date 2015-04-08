package werkbench.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import werkbench.network.message.BenchUpdateMessage;
import werkbench.reference.Compendium;

/**
 *
 * @author jakimfett
 */
public class MessageHandler implements IMessageHandler
{

    public static SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(Compendium.Naming.id);

    public static void init()
    {
        int packetID = 0;
        INSTANCE.registerMessage(BenchUpdateMessage.class, BenchUpdateMessage.class, packetID++, Side.SERVER);
    }

    @Override
    public IMessage onMessage(IMessage message, MessageContext ctx)
    {
        return null;
    }
}
