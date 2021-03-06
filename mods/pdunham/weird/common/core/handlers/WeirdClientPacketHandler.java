package pdunham.weird.common.core.handlers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import pdunham.weird.client.WeirdClProxy;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdCoProxy;
import pdunham.weird.common.WeirdConstants;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class WeirdClientPacketHandler implements IPacketHandler {

	private static StandardLogger logger = new StandardLogger();

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		// Handles incoming data
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(packet.data);
			InputStreamReader isr = new InputStreamReader(bais);
			BufferedReader br = new BufferedReader(isr);
			String msg = br.readLine();
			logger.info("OnPacketData recieved for " + player + ", " + msg);
		} catch (Exception e) {
			logger.warn("OnPacketData recieved failed for " + player);
		}
	}
}
