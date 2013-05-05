package pdunham.quickedit.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.item.Item;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

import pdunham.quickedit.common.core.handlers.QuickEditConnectionHandler;
import pdunham.quickedit.tools.QuickEditWand;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

public class QuickEditCommonProxy { // implements IGuiHandler {

	private static StandardLogger logger = new StandardLogger();
	
    @Init
    public void init() {
        logger.info("init() start");
        logger.info("init() complete");
    }
    
//	@Override
//	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
//		return null;
//	}
//
//	@Override
//	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
//		return null;
//	}

	// A helper function when we register items
	// Don't change the IDs
	public void registerItems() {
        logger.info("registerItems() start");

		QuickEditMain.quickEditWand 	= new QuickEditWand(4227);

        logger.info("registerItems() complete");
	}

	// A helper function when we register tiles
	public void registerTiles(){
        logger.info("registerTiles() start");
        logger.info("registerTiles() complete");
	}

	// A helper function when we register blocks
	// Don't change the IDs
	public void registerBlocks(){
        logger.info("registerBlocks() start");
        logger.info("registerBlocks() complete");
	}

	public void postInit() {
        logger.info("postInit() start");
        ((QuickEditWand) QuickEditMain.quickEditWand).postInit();
		logger.info("postInit() complete");
	}

	public void registerSounds() {
        logger.info("registerSounds() start");
        logger.info("registerSounds() complete");
	}
	
	// A helper function when we register textures
	// gets overridden by the clientProxy
	public void registerTextures() {
        logger.info("registerTextures() start");
        logger.info("registerTextures() complete");
	}
	
    // Handle registering Entities (common), Renderers (client) and Models (client)
	public void registerRenderers(QuickEditMain quickEditMain) {
        logger.info("registerRenderers() start");
	    logger.info("registerRenderers() complete");
	}
	
	public void registerHandlers() {
		logger.info("registerConnectionHandler() " + FMLCommonHandler.instance().getEffectiveSide() + " start");
		NetworkRegistry.instance().registerConnectionHandler(new QuickEditConnectionHandler());
		logger.info("registerConnectionHandler() " + FMLCommonHandler.instance().getEffectiveSide() + " compelte");
	}
	
	public void registerAchievements() {
		logger.info("registerAchievements() start");
		logger.info("registerAchievements() complete");
	}

	public void sendTextToServer(String msg) {
		logger.info("sendTextToServer(" + FMLCommonHandler.instance().getEffectiveSide() + ") start " + msg);
		logger.info("sendTextToServer(" + FMLCommonHandler.instance().getEffectiveSide() + ") compelte " + msg);
	}

	public void sendTextToClient(String msg, Player toPlayer) {
		logger.info("sendTextToClient(" + FMLCommonHandler.instance().getEffectiveSide() + ") start to " + toPlayer + ", " + msg);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeBytes(msg);
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = QuickEditConstants.packetChannelName;
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			PacketDispatcher.sendPacketToPlayer(packet, toPlayer);
			logger.info("sendTextToClient(" + FMLCommonHandler.instance().getEffectiveSide() + ") complete to " + toPlayer + ", " + msg);
		} catch (Exception ex) {
			logger.warn("sendTextToClient(" + FMLCommonHandler.instance().getEffectiveSide() + ") failed to " + toPlayer + ", " + msg);
		    ex.printStackTrace();
		}
	}
}
