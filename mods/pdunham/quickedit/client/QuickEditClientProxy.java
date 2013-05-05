package pdunham.quickedit.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import pdunham.quickedit.common.QuickEditCommonProxy;
import pdunham.quickedit.common.QuickEditConstants;
import pdunham.quickedit.common.StandardLogger;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.EventBus;

@SideOnly(Side.CLIENT)
public class QuickEditClientProxy extends QuickEditCommonProxy {

	private static StandardLogger logger = new StandardLogger();
	
	public QuickEditClientProxy() {
		super();
		logger.info("c'tor() complete");
	}

	@Override
	public void init() {
        logger.info("init() start");
		super.init();
        logger.info("init() complete");
	}
	 
	@Override
	public void postInit() {
        logger.info("postInit() start");
		super.postInit();
        logger.info("postInit() complete");
	}

	@Override
	public void registerSounds() {
        logger.info("registerSounds() start");
        super.registerSounds();
		logger.info("registerSounds complete");
	}
	
    @Override
	public void registerTextures() {
		logger.info("registerTextures start");
		super.registerTextures();
    		// Preload our texture palettes so we have all of the icons and textures before we need them.
    		MinecraftForgeClient.preloadTexture(QuickEditConstants.pathTexturesIcons);
		logger.info("registerTextures complete");
	}

//    @Override
//	public void registerRenderers(QuickEditMain quickeditMain) {
//        logger.info("registerRenderers() start");
//        logger.info("registerRenderers() complete");
//	}
	
    @Override
	public void registerAchievements() {
		logger.info("registerAchievements start");
		super.registerAchievements();
		logger.info("registerAchievements complete");
	}

	@Override
	public void sendTextToServer(String msg) {
		logger.info("sendTextToServer(" + FMLCommonHandler.instance().getEffectiveSide() + ") start " + msg);
		super.sendTextToServer(msg);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeBytes(msg);
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = QuickEditConstants.packetChannelName;
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			PacketDispatcher.sendPacketToServer(packet);
			logger.info("sendTextToServer(" + FMLCommonHandler.instance().getEffectiveSide() + ") complete " + msg);
		} catch (Exception ex) {
			logger.warn("sendTextToServer(" + FMLCommonHandler.instance().getEffectiveSide() + ") failed " + msg);
		    ex.printStackTrace();
		}
	}
}
