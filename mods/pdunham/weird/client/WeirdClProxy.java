package pdunham.weird.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

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
import pdunham.weird.achievements.CraftingHandler;
import pdunham.weird.achievements.WeirdAchievementBetterBoom;
import pdunham.weird.achievements.WeirdAchievementGrenadeCreeper;
import pdunham.weird.achievements.WeirdAchievementGetWeird;
import pdunham.weird.achievements.WeirdAchievementPowder;
import pdunham.weird.achievements.WeirdAchievementStartingOff;
import pdunham.weird.achievements.WeirdAchievementStickyToIt;
import pdunham.weird.client.model.ModelWeirdBaby;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdCoProxy;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import pdunham.weird.entity.EntityGrenade;
import pdunham.weird.entity.EntityPebble;
import pdunham.weird.entity.EntityStickyGrenade;
import pdunham.weird.entity.EntityStrongGrenade;
import pdunham.weird.entity.EntityWeirdBaby;
import pdunham.weird.renderer.RenderGrenade;
import pdunham.weird.renderer.RenderPebble;
import pdunham.weird.renderer.RenderWeirdBaby;

@SideOnly(Side.CLIENT)
public class WeirdClProxy extends WeirdCoProxy {

	private static StandardLogger logger = new StandardLogger();
	
	public WeirdClProxy() {
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
		MinecraftForge.EVENT_BUS.register(new WeirdEventSounds());
		logger.info("registerSounds complete");
	}
	
    @Override
	public void registerTextures() {
		logger.info("registerTextures start");
		super.registerTextures();
    		// Preload our texture palettes so we have all of the icons and textures before we need them.
    		MinecraftForgeClient.preloadTexture(WeirdConstants.pathTexturesIcons);
    		MinecraftForgeClient.preloadTexture(WeirdConstants.pathTexturesArmor);
		logger.info("registerTextures complete");
	}

    @Override
	public void registerRenderers(WeirdMain weirdMain) {
        logger.info("registerRenderers() start");
        super.registerRenderers(weirdMain);
		RenderingRegistry.registerEntityRenderingHandler(EntityPebble.class, new RenderPebble());
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade(3, 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityStickyGrenade.class, new RenderGrenade(9, 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityStrongGrenade.class, new RenderGrenade(10, 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityWeirdBaby.class, 
						new RenderWeirdBaby(new ModelWeirdBaby(), 
										    new ModelWeirdBaby(0.5F), 0.7F));
        logger.info("registerRenderers() complete");
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
			packet.channel = WeirdConstants.packetChannelName;
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
