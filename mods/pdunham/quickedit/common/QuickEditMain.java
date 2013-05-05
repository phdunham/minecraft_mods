package pdunham.quickedit.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

import pdunham.quickedit.common.core.handlers.QuickEditClientPacketHandler;
import pdunham.quickedit.common.core.handlers.QuickEditServerPacketHandler;
import pdunham.quickedit.tools.QuickEditWand;

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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@NetworkMod(
			// For client side creation and packet handling
			clientSideRequired = true, 
            clientPacketHandlerSpec = @SidedPacketHandler(channels = { QuickEditConstants.packetChannelName }, 
            											packetHandler = QuickEditClientPacketHandler.class),
			// For Server side creation and packet handling
            serverSideRequired = false,
            serverPacketHandlerSpec = @SidedPacketHandler(channels = { QuickEditConstants.packetChannelName }, 
            											packetHandler = QuickEditServerPacketHandler.class))

@Mod(   modid   = QuickEditMain.modid, 
		name    = QuickEditMain.name, 
		version = QuickEditMain.version)

public class QuickEditMain /* extends BaseMod */ {
	public static final String version = "0.1.0";
	public static final String modid = "QuickEdit";
	public static final String name = "Quick Edit";

	private static StandardLogger logger = new StandardLogger();

	@Instance("QuickEditMain")
    public static QuickEditMain instance = new QuickEditMain();

    @Instance("QuickEditWand")
  	public static QuickEditWand quickEditWand;

    // Tell Forge where the proxies are.
    @SidedProxy(clientSide="pdunham.quickedit.client.QuickEditClientProxy")

	@Instance("QuickEditCommonProxy")
    public static QuickEditCommonProxy proxy;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
    		logger.info("preInit start " + event);
        proxy.registerSounds();
        logger.info("preInit complete.");
	}

    @Init
    public void init(FMLInitializationEvent event) {
        logger.info("init() start");
        
        // Call help functions in the common proxy to register all of the quickedit objects
        proxy.init();
        proxy.registerHandlers();
        proxy.registerTextures();
        proxy.registerTiles();
        proxy.registerBlocks();
        proxy.registerItems();
        proxy.registerAchievements();
        logger.info("init() complete");
    }
    
    @PostInit
    public static void postInit(FMLPostInitializationEvent event) {
        logger.info("postInit() start");
    		proxy.postInit();
        logger.info("postInit() complete");
    }

	public String getVersion() {
        logger.info("getVersion()");
		return QuickEditMain.version;
	}
}
