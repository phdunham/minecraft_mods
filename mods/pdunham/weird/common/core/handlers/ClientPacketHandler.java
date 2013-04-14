package pdunham.weird.common.core.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import pdunham.weird.common.StandardLogger;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ClientPacketHandler implements IPacketHandler {

	private static StandardLogger logger;

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		// Handles incoming data
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("OnPacketData recieved");
	}
}
