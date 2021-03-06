package pdunham.weird.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

import pdunham.weird.achievements.CraftingHandler;
import pdunham.weird.achievements.WeirdAchievementBetterBoom;
import pdunham.weird.achievements.WeirdAchievementGrenadeCreeper;
import pdunham.weird.achievements.WeirdAchievementGetWeird;
import pdunham.weird.achievements.WeirdAchievementPowder;
import pdunham.weird.achievements.WeirdAchievementStartingOff;
import pdunham.weird.achievements.WeirdAchievementStickyToIt;
import pdunham.weird.armor.WeirdBoots;
import pdunham.weird.armor.WeirdChestPlate;
import pdunham.weird.armor.WeirdHelmet;
import pdunham.weird.armor.WeirdLeggins;
import pdunham.weird.armor.WeirdPlating;
import pdunham.weird.commands.CommandDrill;
import pdunham.weird.common.core.handlers.WeirdClientPacketHandler;
import pdunham.weird.common.core.handlers.WeirdServerPacketHandler;
import pdunham.weird.entity.EntityGrenade;
import pdunham.weird.entity.EntityPebble;
import pdunham.weird.entity.EntityWeirdBaby;
import pdunham.weird.objects.WeirdBlock;
import pdunham.weird.objects.WeirdFurnace;
import pdunham.weird.objects.WeirdIngot;
import pdunham.weird.objects.WeirdOre;
import pdunham.weird.objects.WeirdPoop;
import pdunham.weird.objects.WeirdPowder;
import pdunham.weird.renderer.RenderPebble;
import pdunham.weird.tools.WeirdAxe;
import pdunham.weird.tools.WeirdHoe;
import pdunham.weird.tools.WeirdPickaxe;
import pdunham.weird.tools.WeirdShovel;
import pdunham.weird.tools.WeirdWormDrill;
import pdunham.weird.weapons.Pebble;
import pdunham.weird.weapons.WeirdCasing;
import pdunham.weird.weapons.WeirdGrenade;
import pdunham.weird.weapons.WeirdSlingShot;
import pdunham.weird.weapons.WeirdStickyCasing;
import pdunham.weird.weapons.WeirdStrongCasing;
import pdunham.weird.weapons.WeirdSword;
import pdunham.weird.weapons.WeirdTNT;
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
import cpw.mods.fml.common.event.FMLServerStartingEvent;
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

public class WeirdMain extends BaseMod {
	public static final String version = "0.1.1";
	public static final String modid = "WeirdMod";
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

    @Instance("WeirdWormDrill")
  	public static WeirdWormDrill weirdWormDrill;

    @Instance("CommandDrill")
  	public static CommandDrill commandDrill;

    @Instance("WeirdSlingShot")
    public static WeirdSlingShot weirdSlingShot;
    
    @Instance("WeirdPowder")
    public static WeirdPowder weirdPowder;

    @Instance("WeirdTNT")
    public static WeirdTNT weirdTNT;

    @Instance("WeirdFurnace")
    public static WeirdFurnace weirdFurnace;

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
    
    @Instance("WeirdAchievementGetWeird")
    public static WeirdAchievementGetWeird weirdAchievemenGetWeird;

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
    @SidedProxy(clientSide="pdunham.weird.client.WeirdClProxy", 
                serverSide="pdunham.weird.common.WeirdCoProxy")

	@Instance("WeirdCoProxy")
    public static WeirdCoProxy proxy;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
    		logger.info("preInit start " + event);
        proxy.registerSounds();
        WeirdConfig.load(event);
        logger.info("preInit complete.");
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

	// trigger the acheivement for picking up WeirdOre for the first time.
	@Override
	public void onItemPickup(EntityPlayer entityPlayer, ItemStack itemStack)
	{
		if(itemStack.itemID == WeirdMain.weirdOre.blockID) {
			entityPlayer.addStat(WeirdMain.weirdAchievemenGetWeird, 1);
		}
		super.onItemPickup(entityPlayer, itemStack);
		logger.info("onItemPickup " + itemStack.getDisplayName() + " " + entityPlayer);
	}
	
	public void load() {
        logger.info("*********************************************load() start");
        logger.info("*********************************************load() complete");
	}
	
	 @Mod.ServerStarting
     public void ServerStarting(FMLServerStartingEvent event) {
		 logger.info("ServerStarting() start");
		 ServerCommandManager manager = (ServerCommandManager)event.getServer().getCommandManager();
		 commandDrill = new CommandDrill();
         manager.registerCommand(commandDrill);
		 logger.info("ServerStarting() end");
	 }
}
