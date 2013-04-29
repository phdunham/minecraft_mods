package pdunham.aweird.client;

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
import pdunham.aweird.achievements.CraftingHandler;
import pdunham.aweird.achievements.WeirdAchievementBetterBoom;
import pdunham.aweird.achievements.WeirdAchievementGrenadeCreeper;
import pdunham.aweird.achievements.WeirdAchievementOre;
import pdunham.aweird.achievements.WeirdAchievementPowder;
import pdunham.aweird.achievements.WeirdAchievementStartingOff;
import pdunham.aweird.achievements.WeirdAchievementStickyToIt;
import pdunham.aweird.client.model.ModelWeirdBaby;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdCoProxy;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;
import pdunham.aweird.entity.EntityGrenade;
import pdunham.aweird.entity.EntityPebble;
import pdunham.aweird.entity.EntityStickyGrenade;
import pdunham.aweird.entity.EntityStrongGrenade;
import pdunham.aweird.entity.EntityWeirdBaby;
import pdunham.aweird.renderer.RenderGrenade;
import pdunham.aweird.renderer.RenderPebble;
import pdunham.aweird.renderer.RenderWeirdBaby;

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
	public void registerAchievements() {
		logger.info("registerAchievements start");
		super.registerAchievements();

		// Order matters. You must have the dependent Achievements registered first.
    		WeirdMain.weirdAchievementOre = new WeirdAchievementOre();
    		WeirdMain.weirdAchievementStartingOff = new WeirdAchievementStartingOff();
    		WeirdMain.weirdAchievementPowder = new WeirdAchievementPowder();
    		WeirdMain.weirdAchievementGrenadeCreeper = new WeirdAchievementGrenadeCreeper();
    		WeirdMain.weirdAchievementBetterBoom = new WeirdAchievementBetterBoom();
    		WeirdMain.weirdAchievementStickyToIt = new WeirdAchievementStickyToIt();
    		
    		// The Crafting handler let's us hook crafting and smelting to trigger achievements
    		WeirdMain.craftingHandler = new CraftingHandler();
    		GameRegistry.registerCraftingHandler(WeirdMain.craftingHandler);    		
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
