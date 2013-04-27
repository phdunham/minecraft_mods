package pdunham.aweird.common.core.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import pdunham.aweird.common.StandardLogger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler {

	private static StandardLogger logger = new StandardLogger();

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player){
		// Handles incoming data
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		EntityPlayer sender = (EntityPlayer) player;

		logger.info("OnPacketData recieved");		
	}
}
