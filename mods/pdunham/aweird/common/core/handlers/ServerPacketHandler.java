package pdunham.aweird.common.core.handlers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdCoProxy;
import pdunham.aweird.common.WeirdConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler {

	private static StandardLogger logger = new StandardLogger();

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player){
		EntityPlayer sender = (EntityPlayer) player;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(packet.data);
			InputStreamReader isr = new InputStreamReader(bais);
			BufferedReader br = new BufferedReader(isr);
			String msg = br.readLine();
			logger.info("OnPacketData recieved from " + sender + ", " + msg);
		} catch (Exception e) {
			logger.warn("OnPacketData recieved failed from " + sender);
		}
	}
}
