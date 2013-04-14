package pdunham.weird.common;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import pdunham.weird.common.core.handlers.ClientPacketHandler;
import pdunham.weird.common.core.handlers.ServerPacketHandler;
import pdunham.weird.objects.Pebble;
import pdunham.weird.objects.WeirdAxe;
import pdunham.weird.objects.WeirdBlock;
import pdunham.weird.objects.WeirdHoe;
import pdunham.weird.objects.WeirdIngot;
import pdunham.weird.objects.WeirdOre;
import pdunham.weird.objects.WeirdPickaxe;
import pdunham.weird.objects.WeirdPowder;
import pdunham.weird.objects.WeirdShovel;
import pdunham.weird.objects.WeirdSlingShot;
import pdunham.weird.objects.WeirdSword;
import pdunham.weird.objects.WeirdTNT;
import pdunham.weird.objects.WeirdWorldGenerator;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@NetworkMod(clientSideRequired = true, serverSideRequired = false, // Whether client side and server side are needed
            clientPacketHandlerSpec = @SidedPacketHandler(channels = {"pdunhamWeird" }, 
            packetHandler = ClientPacketHandler.class), //For clientside packet handling
            serverPacketHandlerSpec = @SidedPacketHandler(channels = {"pdunhamWeird"}, 
            packetHandler = ServerPacketHandler.class)) //For serverside packet handling
@Mod(modid = "weird", name = "Weird stuff", version = "0.1.0")

public class WeirdMain {
    
    @Instance("WeirdMain")
    public static WeirdMain instance = new WeirdMain();

    @Instance("WeirdOre")
  	public static WeirdOre weirdOre;

    @Instance("WeirdSolidBlock")
 	public static WeirdBlock weirdBlock;

    @Instance("WeirdIngot")
  	public static WeirdIngot weirdIngot;

    @Instance("WeirdPickaxe")
  	public static WeirdPickaxe weirdPickaxe;

    @Instance("WeirdAxe")
  	public static WeirdAxe weirdAxe;

    @Instance("WeirdShovel")
    public static WeirdShovel weirdShovel;

    @Instance("WeirdHoe")
    public static WeirdHoe weirdHoe;

    @Instance("WeirdSlingShot")
    public static WeirdSlingShot weirdSlingShot;
    
    @Instance("WeirdPowder")
    public static WeirdPowder weirdPowder;

    @Instance("WeirdTNT")
    public static WeirdTNT weirdTNT;

    @Instance("WeirdSword")
    public static WeirdSword weirdSword;

    @Instance("Pebble")
  	public static Pebble pebble;

    @Instance("Logger")
    	private static Logger logger;
    
    public static WeirdWorldGenerator worldGen = new WeirdWorldGenerator();
    	
    // Tell Forge where the proxies are.
    @SidedProxy(clientSide="pdunham.weird.client.ClientProxy", 
                serverSide="pdunham.weird.common.CommonProxy")

	@Instance("CommonProxy")
    public static CommonProxy proxy;

    public static String pathTexture = "/pdunham/weird/weirdBlock.png";

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        logger = Logger.getLogger("WeirdMain");
        logger.setParent(FMLLog.getLogger());        
        logger.log(java.util.logging.Level.INFO, "preInit() complete");
    }

    @Init
    public void init(FMLInitializationEvent event) {
        logger.log(java.util.logging.Level.INFO, "init()");

        // Registers this class that deals with GUI data
        NetworkRegistry.instance().registerGuiHandler(this, proxy);
        
        // Call help functions in the common proxy to register all of the weird objects
        proxy.init();
        proxy.registerTextures();
        proxy.registerTiles();
        proxy.registerBlocks();
        proxy.registerItems();
        
        // Register our self with the world generator so weird ore will be inserted into new worlds.
        GameRegistry.registerWorldGenerator(worldGen);
        
        logger.log(java.util.logging.Level.INFO, "init() complete");
    }
    
    @PostInit
    public static void postInit(FMLPostInitializationEvent event) {
    		proxy.postInit();
        logger.log(java.util.logging.Level.INFO, "postInit() complete");
    }
}
