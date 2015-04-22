package werkbench.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import werkbench.network.message.FurnaceUpdateRequestMessage;
import werkbench.network.message.FurnaceUpdateResponseMessage;
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
        INSTANCE.registerMessage(FurnaceUpdateRequestMessage.class, FurnaceUpdateRequestMessage.class, packetID++, Side.SERVER);
        INSTANCE.registerMessage(FurnaceUpdateResponseMessage.class, FurnaceUpdateResponseMessage.class, packetID++, Side.CLIENT);
    }

    @Override
    public IMessage onMessage(IMessage message, MessageContext ctx)
    {
        return null;
    }
}
