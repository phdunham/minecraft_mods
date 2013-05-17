package pdunham.weird.common;

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

import pdunham.weird.armor.WeirdBoots;
import pdunham.weird.armor.WeirdChestPlate;
import pdunham.weird.armor.WeirdHelmet;
import pdunham.weird.armor.WeirdLeggins;
import pdunham.weird.armor.WeirdPlating;
import pdunham.weird.client.model.ModelWeirdBaby;
import pdunham.weird.common.WeirdConfig;
import pdunham.weird.common.core.handlers.WeirdConnectionHandler;
import pdunham.weird.entity.EntityGrenade;
import pdunham.weird.entity.EntityPebble;
import pdunham.weird.entity.EntityStickyGrenade;
import pdunham.weird.entity.EntityStrongGrenade;
import pdunham.weird.entity.EntityWeirdBaby;
import pdunham.weird.objects.WeirdBlock;
import pdunham.weird.objects.WeirdIngot;
import pdunham.weird.objects.WeirdOre;
import pdunham.weird.objects.WeirdPoop;
import pdunham.weird.objects.WeirdPowder;
import pdunham.weird.renderer.RenderGrenade;
import pdunham.weird.renderer.RenderPebble;
import pdunham.weird.renderer.RenderWeirdBaby;
import pdunham.weird.tools.QuickEditWand;
import pdunham.weird.tools.WeirdAxe;
import pdunham.weird.tools.WeirdHoe;
import pdunham.weird.tools.WeirdPickaxe;
import pdunham.weird.tools.WeirdWormDrill;
import pdunham.weird.tools.WeirdShovel;
import pdunham.weird.weapons.Pebble;
import pdunham.weird.weapons.WeirdCasing;
import pdunham.weird.weapons.WeirdGrenade;
import pdunham.weird.weapons.WeirdSlingShot;
import pdunham.weird.weapons.WeirdStickyCasing;
import pdunham.weird.weapons.WeirdStrongCasing;
import pdunham.weird.weapons.WeirdSword;
import pdunham.weird.weapons.WeirdTNT;

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

public class WeirdCoProxy { // implements IGuiHandler {

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

		WeirdMain.weirdIngot        	= new WeirdIngot(WeirdConfig.weirdIngotID);
		WeirdMain.weirdPickaxe      	= new WeirdPickaxe(WeirdConfig.weirdPickaxeID);
		WeirdMain.weirdAxe 			= new WeirdAxe(WeirdConfig.weirdAxeID);
        WeirdMain.weirdShovel 		= new WeirdShovel(WeirdConfig.weirdShovelID);
        WeirdMain.weirdHoe 			= new WeirdHoe(WeirdConfig.weirdHoeID);
        WeirdMain.weirdSlingShot 	= new WeirdSlingShot(WeirdConfig.weirdSlingShotID);
        WeirdMain.weirdPowder 		= new WeirdPowder(WeirdConfig.weirdPowderID);
        WeirdMain.weirdSword 		= new WeirdSword(WeirdConfig.weirdSwordID);
        WeirdMain.pebble 			= new Pebble(WeirdConfig.pebbleID);
        WeirdMain.weirdCasing 		= new WeirdCasing(WeirdConfig.weirdCasingID);
        WeirdMain.weirdStrongCasing 	= new WeirdStrongCasing(WeirdConfig.weirdStrongCasingID);
        WeirdMain.weirdStickyCasing 	= new WeirdStickyCasing(WeirdConfig.weirdStickyCasingID);
        WeirdMain.weirdGrenade      	= new WeirdGrenade(WeirdConfig.weirdGrenadeID, "WeirdGrenade", "Weird Grenade", WeirdMain.weirdCasing, false, 3, 1, 2.0f);
        WeirdMain.weirdHelmet       	= new WeirdHelmet(WeirdConfig.weirdHelmetID);
        WeirdMain.weirdChestPlate   	= new WeirdChestPlate(WeirdConfig.weirdChestPlateID);
        WeirdMain.weirdLeggins      	= new WeirdLeggins(WeirdConfig.weirdLegginsID);
        WeirdMain.weirdBoots        	= new WeirdBoots(WeirdConfig.weirdBootsID);
        WeirdMain.weirdPlating      	= new WeirdPlating(WeirdConfig.weirdPlatingID);
        WeirdMain.weirdStickyGrenade	= new WeirdGrenade(WeirdConfig.weirdStickyGrenadeID, "WeirdStickyGrenade", "Weird Sticky Grenade", WeirdMain.weirdStickyCasing,  true,  9, 1, 2.0f);
        WeirdMain.weirdStrongGrenade	= new WeirdGrenade(WeirdConfig.weirdStrongGrenadeID, "WeirdStrongGrenade", "Weird Strong Grenade", WeirdMain.weirdStrongCasing, false, 10, 1, 4.0f);
        WeirdMain.weirdPoop         	= new WeirdPoop(WeirdConfig.weirdPoopID);
		WeirdMain.weirdWormDrill    	= new WeirdWormDrill(WeirdConfig.weirdWormDrillID);
        
        // Delete the original axe
        int id = 30;
        Item oldAxe = Item.itemsList[256 + id];
        Item.itemsList[256 + id] = null;
        Item.axeGold = new QuickEditWand(id);
        logger.info("registerItems() complete");

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
        WeirdMain.weirdOre = (new WeirdOre(WeirdConfig.weirdOreID));
		WeirdMain.weirdBlock = new WeirdBlock(WeirdConfig.weirdBlockID);
        WeirdMain.weirdTNT= new WeirdTNT(WeirdConfig.weirdTNTID);
        logger.info("registerBlocks() complete");
	}

	public void postInit() {
        logger.info("postInit() start");

        ((WeirdOre) WeirdMain.weirdOre).postInit();
//		for (int i = 0; i < Item.itemsList.length; i++) {
//			if (Item.itemsList[i] != null) {
//				logger.finest(i + " " + Item.itemsList[i].getItemName());
//			}
//		}
        ((WeirdBlock) WeirdMain.weirdBlock).postInit();
        ((WeirdIngot) WeirdMain.weirdIngot).postInit();
        ((WeirdPickaxe) WeirdMain.weirdPickaxe).postInit();
        ((WeirdAxe) WeirdMain.weirdAxe).postInit();
        ((WeirdShovel) WeirdMain.weirdShovel).postInit();
        ((WeirdHoe) WeirdMain.weirdHoe).postInit();
        ((WeirdSlingShot) WeirdMain.weirdSlingShot).postInit();
        ((WeirdPowder) WeirdMain.weirdPowder).postInit();
        ((WeirdTNT) WeirdMain.weirdTNT).postInit();
        ((WeirdSword) WeirdMain.weirdSword).postInit();
        ((Pebble) WeirdMain.pebble).postInit();
        ((WeirdCasing) WeirdMain.weirdCasing).postInit();
        ((WeirdStrongCasing)WeirdMain.weirdStrongCasing).postInit();
        ((WeirdStickyCasing)WeirdMain.weirdStickyCasing).postInit();
        ((WeirdGrenade)WeirdMain.weirdGrenade).postInit();
        ((WeirdHelmet)WeirdMain.weirdHelmet).postInit();
        ((WeirdChestPlate)WeirdMain.weirdChestPlate).postInit();
        ((WeirdLeggins)WeirdMain.weirdLeggins).postInit();
        ((WeirdBoots)WeirdMain.weirdBoots).postInit();
        ((WeirdPlating)WeirdMain.weirdPlating).postInit();
        ((WeirdGrenade)WeirdMain.weirdStickyGrenade).postInit();
        ((WeirdGrenade)WeirdMain.weirdStrongGrenade).postInit();
        ((WeirdPoop) WeirdMain.weirdPoop).postInit();
        ((WeirdWormDrill) WeirdMain.weirdWormDrill).postInit();
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
	public void registerRenderers(WeirdMain weirdMain) {
        logger.info("registerRenderers() start");
		// Get a unique entity id
		// Register the entity id so it doesn't duplicate
		// Then register the mod entity type with FML
			// @param entityClass The entity class
			// @param entityName A unique name for the entity
			// @param id A mod specific ID for the entity
			// @param mod The 'this' for weird main.
			// @param trackingRange The range at which MC will send tracking updates
			// @param updateFrequency The frequency of tracking updates
			// @param sendsVelocityUpdates Whether to send velocity information packets as well
		// Lastly, map the Entity class to its associated Render class.
		int id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityPebble.class, "Pebble", id);
		EntityRegistry.registerModEntity(EntityPebble.class,	"Pebble", id	, weirdMain, 100, 10, true);
		
		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityGrenade.class, "WeirdGrenade", id);
		EntityRegistry.registerModEntity(EntityGrenade.class, "WeirdGrenade", id, weirdMain, 100, 10, true);
		
		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityStickyGrenade.class, "WeirdStickyGrenade", id);
		EntityRegistry.registerModEntity(EntityStickyGrenade.class, "WeirdStickyGrenade", id, weirdMain, 100, 10, true);
	
		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityStrongGrenade.class, "WeirdStrongGrenade", id);
		EntityRegistry.registerModEntity(EntityStrongGrenade.class, "WeirdStrongGrenade", id, weirdMain, 100, 10, true);
	
		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityWeirdBaby.class, "WeirdBaby", id);
		EntityRegistry.registerModEntity(EntityWeirdBaby.class, "WeirdBaby", id, weirdMain, 100, 10, false);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, 0xffffff, 0x000000));
		// Set the external name
		LanguageRegistry.instance().addStringLocalization("entity.WeirdBaby.name",  "Baby");
		
		EntityRegistry.addSpawn(EntityWeirdBaby.class, 20, 1, 2, 
				EnumCreatureType.monster, 
				BiomeGenBase.beach, 
				BiomeGenBase.desert,
				BiomeGenBase.desertHills,
	//			BiomeGenBase.extremeHills, 
	//			BiomeGenBase.extremeHillsEdge, 
				BiomeGenBase.forest, 
				BiomeGenBase.forestHills, 
	//			BiomeGenBase.frozenOcean,
	//			BiomeGenBase.frozenRiver,
				BiomeGenBase.hell,
	//			BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains,
				BiomeGenBase.jungle, 
				BiomeGenBase.jungleHills,
				BiomeGenBase.mushroomIsland, 
				BiomeGenBase.mushroomIslandShore,
	//			BiomeGenBase.ocean, 
				BiomeGenBase.plains, 
				BiomeGenBase.river, 
				BiomeGenBase.swampland,
				BiomeGenBase.taiga,
				BiomeGenBase.taigaHills
				);		
	    logger.info("registerRenderers() complete");
	}
	
	public void registerHandlers() {
		logger.info("registerConnectionHandler() " + FMLCommonHandler.instance().getEffectiveSide() + " start");
		NetworkRegistry.instance().registerConnectionHandler(new WeirdConnectionHandler());
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
			packet.channel = WeirdConstants.packetChannelName;
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
