package pdunham.aweird.common;
import pdunham.aweird.armor.WeirdBoots;
import pdunham.aweird.armor.WeirdChestPlate;
import pdunham.aweird.armor.WeirdHelmet;
import pdunham.aweird.armor.WeirdLeggins;
import pdunham.aweird.armor.WeirdPlating;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.objects.WeirdBlock;
import pdunham.aweird.objects.WeirdIngot;
import pdunham.aweird.objects.WeirdOre;
import pdunham.aweird.objects.WeirdPoop;
import pdunham.aweird.objects.WeirdPowder;
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
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;


public class WeirdConfig {
	private static StandardLogger logger = new StandardLogger();

	// Items
	public static int weirdIngotID			= 7238;
	public static int weirdPickaxeID			= 7239;
	public static int weirdAxeID				= 7240;
	public static int weirdShovelID			= 7241;
	public static int weirdHoeID				= 7242;
	public static int weirdSlingShotID		= 7243;
	public static int weirdPowderID			= 7244;
	public static int weirdSwordID			= 7245;
	public static int pebbleID				= 7246;
	public static int weirdCasingID			= 7247;
	public static int weirdStrongCasingID	= 7248;
	public static int weirdStickyCasingID 	= 7249;
	public static int weirdGrenadeID			= 7250;
	public static int weirdHelmetID			= 7251;
	public static int weirdChestPlateID		= 7252;
	public static int weirdLegginsID			= 7253;
	public static int weirdBootsID			= 7254;
	public static int weirdPlatingID			= 7255;
	public static int weirdStickyGrenadeID	= 7256;
	public static int weirdStrongGrenadeID	= 7257;
	public static int weirdPoopID			= 7258;
	
	// Blocks
	public static int weirdOreID				= 3125;
	public static int weirdBlockID			= 3126;
	public static int weirdTNTID				= 3127;

	public static void load(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		// Items
		weirdIngotID			= config.getItem("weirdIngotItemID", 		weirdIngotID, "Item ID").getInt();
		weirdPickaxeID		= config.getItem("weirdPickaxeItemID", 		weirdPickaxeID, "Item ID").getInt();
		weirdAxeID			= config.getItem("weirdAxeItemID", 			weirdAxeID, "Item ID").getInt();
        weirdShovelID		= config.getItem("weirdShovelItemID", 		weirdShovelID, "Item ID").getInt();
        weirdHoeID			= config.getItem("weirdHoeItemID", 			weirdHoeID, "Item ID").getInt();
        weirdSlingShotID		= config.getItem("weirdSlingShotItemID", 	weirdSlingShotID, "Item ID").getInt();
        weirdPowderID		= config.getItem("weirdPowderItemID", 		weirdPowderID, "Item ID").getInt();
        weirdSwordID			= config.getItem("weirdSwordItemID", 		weirdSwordID, "Item ID").getInt();
        pebbleID				= config.getItem("pebbleItemID", 			pebbleID, "Item ID").getInt();
        weirdCasingID		= config.getItem("weirdCasingItemID", 		weirdCasingID, "Item ID").getInt();
        weirdStrongCasingID	= config.getItem("weirdStrongCasingItemID", 	weirdStrongCasingID, "Item ID").getInt();
        weirdStickyCasingID 	= config.getItem("weirdStickyCasingItemID", 	weirdStickyCasingID, "Item ID").getInt();
        weirdGrenadeID		= config.getItem("weirdGrenadeItemID", 		weirdGrenadeID, "Item ID").getInt();
        weirdHelmetID		= config.getItem("weirdHelmetItemID", 		weirdHelmetID, "Item ID").getInt();
        weirdChestPlateID	= config.getItem("weirdChestPlateItemID", 	weirdChestPlateID, "Item ID").getInt();
        weirdLegginsID		= config.getItem("weirdLegginsItemID", 		weirdLegginsID, "Item ID").getInt();
        weirdBootsID			= config.getItem("weirdBootsItemID", 		weirdBootsID, "Item ID").getInt();
        weirdPlatingID		= config.getItem("weirdPlatingItemID",		weirdPlatingID, "Item ID").getInt();
        weirdStickyGrenadeID	= config.getItem("weirdStickyGrenadeItemID", weirdStickyGrenadeID, "Item ID").getInt();
        weirdStrongGrenadeID	= config.getItem("weirdStrongGrenadeItemID", weirdStrongGrenadeID, "Item ID").getInt();
        weirdPoopID			= config.getItem("weirdPoopItemID", 			weirdPoopID, "Item ID").getInt();
        
        // Blocks
        weirdOreID			= config.getBlock("weirdOreBlockID", 		weirdOreID, "Item ID").getInt();
        weirdBlockID			= config.getBlock("weirdBlockBlockID", 		weirdBlockID, "Item ID").getInt();
        weirdTNTID			= config.getBlock("weirdTNTBlockID", 		weirdTNTID, "Item ID").getInt();
        
        // Resave out the config so we always have a default config after 1st run.
		config.save();    
		logger.info("Configuration file loaded");
	}
}
