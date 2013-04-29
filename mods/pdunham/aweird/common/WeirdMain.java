package pdunham.aweird.common;

import java.net.URL;
import java.util.logging.Logger;

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

import pdunham.aweird.achievements.CraftingHandler;
import pdunham.aweird.achievements.WeirdAchievementBetterBoom;
import pdunham.aweird.achievements.WeirdAchievementGrenadeCreeper;
import pdunham.aweird.achievements.WeirdAchievementOre;
import pdunham.aweird.achievements.WeirdAchievementPowder;
import pdunham.aweird.achievements.WeirdAchievementStartingOff;
import pdunham.aweird.achievements.WeirdAchievementStickyToIt;
import pdunham.aweird.armor.WeirdBoots;
import pdunham.aweird.armor.WeirdChestPlate;
import pdunham.aweird.armor.WeirdHelmet;
import pdunham.aweird.armor.WeirdLeggins;
import pdunham.aweird.armor.WeirdPlating;
import pdunham.aweird.common.core.handlers.WeirdClientPacketHandler;
import pdunham.aweird.common.core.handlers.WeirdServerPacketHandler;
import pdunham.aweird.entity.EntityGrenade;
import pdunham.aweird.entity.EntityPebble;
import pdunham.aweird.entity.EntityWeirdBaby;
import pdunham.aweird.objects.WeirdBlock;
import pdunham.aweird.objects.WeirdIngot;
import pdunham.aweird.objects.WeirdOre;
import pdunham.aweird.objects.WeirdPoop;
import pdunham.aweird.objects.WeirdPowder;
import pdunham.aweird.renderer.RenderPebble;
import pdunham.aweird.tools.WeirdAxe;
import pdunham.aweird.tools.WeirdHoe;
import pdunham.aweird.tools.WeirdPickaxe;
import pdunham.aweird.tools.WeirdShovel;
import pdunham.aweird.weapons.Pebble;
import pdunham.aweird.weapons.WeirdCasing;
import pdunham.aweird.weapons.WeirdGrenade;
import pdunham.aweird.weapons.WeirdSlingShot;
import pdunham.aweird.weapons.WeirdStickyCasing;
import pdunham.aweird.weapons.WeirdStrongCasing;
import pdunham.aweird.weapons.WeirdSword;
import pdunham.aweird.weapons.WeirdTNT;
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
            clientPacketHandlerSpec = @SidedPacketHandler(channels = { WeirdConstants.packetChannelName }, 
            											packetHandler = WeirdClientPacketHandler.class),
			// For Server side creation and packet handling
            serverSideRequired = false,
            serverPacketHandlerSpec = @SidedPacketHandler(channels = { WeirdConstants.packetChannelName }, 
            											packetHandler = WeirdServerPacketHandler.class))

@Mod(   modid   = WeirdMain.modid, 
		name    = WeirdMain.name, 
		version = WeirdMain.version)

public class WeirdMain /* extends BaseMod */ {
	public static final String version = "0.1.1";
	public static final String modid = "aWeirdMod";
	public static final String name = "A Weird Mod";
	
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

    @Instance("WeirdCasing")
    public static WeirdCasing weirdCasing;
    
    @Instance("WeirdStrongCasing")
    public static WeirdStrongCasing weirdStrongCasing;

    @Instance("WeirdStickyCasing")
    public static WeirdStickyCasing weirdStickyCasing;
    
    @Instance("WeirdPlating")
    public static WeirdPlating weirdPlating;
    
    @Instance("WeirdGrenade")
    public static WeirdGrenade weirdGrenade;
    @Instance("WeirdStickyGrenade")
    public static WeirdGrenade weirdStickyGrenade;
    @Instance("WeirdStrongGrenade")
    public static WeirdGrenade weirdStrongGrenade;

    @Instance("WeirdSword")
    public static WeirdSword weirdSword;

    @Instance("Pebble")
  	public static Pebble pebble;
    
    @Instance("WeirdHelmet")
    public static WeirdHelmet weirdHelmet;
    
    @Instance("WeirdChestPlate")
    public static WeirdChestPlate weirdChestPlate;
    
    @Instance("WeirdLeggins")
    public static WeirdLeggins weirdLeggins;
    
    @Instance("WeirdBoots")
    public static WeirdBoots weirdBoots;

    @Instance("WeirdPoop")
    public static WeirdPoop weirdPoop;
    
    @Instance("WeirdAchievementOre")
    public static WeirdAchievementOre weirdAchievementOre;

    @Instance("WeirdAchievementGrenadeCreeper")
    public static WeirdAchievementGrenadeCreeper weirdAchievementGrenadeCreeper;

    @Instance("WeirdAchievementPowder")
    public static WeirdAchievementStartingOff weirdAchievementStartingOff;

    @Instance("WeirdAchievementPowder")
    public static WeirdAchievementPowder weirdAchievementPowder;

    @Instance("WeirdAchievementBetterBoom")
    public static WeirdAchievementBetterBoom weirdAchievementBetterBoom;

    @Instance("WeirdAchievementStickToIt")
    public static WeirdAchievementStickyToIt weirdAchievementStickyToIt;

    @Instance("CraftingHandler")
    public static CraftingHandler craftingHandler = new CraftingHandler();

    @Instance("WeirdWorldGenerator")
    public static WeirdWorldGenerator worldGen = new WeirdWorldGenerator();

	private static StandardLogger logger = new StandardLogger();
    
    // Tell Forge where the proxies are.
    @SidedProxy(clientSide="pdunham.aweird.client.WeirdClProxy", 
                serverSide="pdunham.aweird.common.WeirdCoProxy")

	@Instance("WeirdCoProxy")
    public static WeirdCoProxy proxy;

	public static int configFirstBlockID = 3125;
	public static int configFirstItemID = 7238; // 7237 is taken
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
    		logger.info("preInit start " + event);
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		configFirstBlockID = config.getBlock("FirstBlockID", 3125, "This first Block ID to use for this mod. Blocks assign sequentially starting from this ID").getInt();
		configFirstItemID = config.getItem("FirstItemID", 7238, "This first Item ID to use for this mod. Items assign sequentially starting from this ID").getInt();
		config.save();    
        proxy.registerSounds();
        logger.info("preInit complete. Configuration file loaded.  1st Block ID " + configFirstBlockID + ", 1st item ID " + configFirstBlockID);
	}

    @Init
    public void init(FMLInitializationEvent event) {
        logger.info("init() start");

        // Registers this class that deals with GUI data
        // NetworkRegistry.instance().registerGuiHandler(this, proxy);
        
        // Call help functions in the common proxy to register all of the weird objects
        proxy.init();
        proxy.registerHandlers();
        proxy.registerTextures();
        proxy.registerTiles();
        proxy.registerBlocks();
        proxy.registerItems();
        proxy.registerRenderers(this);
        proxy.registerAchievements();

        // Register our self with the world generator so weird ore will be inserted into new worlds.
        GameRegistry.registerWorldGenerator(worldGen);
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
		return WeirdMain.version;
	}

	public void load() {
        logger.info("*********************************************load() start");
        logger.info("*********************************************load() complete");
	}
}
