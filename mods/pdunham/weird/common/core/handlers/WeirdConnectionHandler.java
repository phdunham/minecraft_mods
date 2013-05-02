package pdunham.weird.common.core.handlers;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;


public class WeirdConnectionHandler implements IConnectionHandler {

	private static StandardLogger logger = new StandardLogger();
	
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		((EntityPlayerMP)player).addChatMessage("Welcome to " + WeirdMain.name + " v" + WeirdMain.version);
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		logger.info("connectionReceived");
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
		logger.info("connectionOpened " + server + ":" + port);
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
		logger.info("connectionOpened " + server);
	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		logger.info("connectionClosed");
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
		logger.info("clientLoggedIn " + login);
	}
}
