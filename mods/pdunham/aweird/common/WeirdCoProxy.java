package pdunham.aweird.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.world.World;
import net.minecraft.item.Item;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

import pdunham.aweird.common.core.handlers.ConnectionHandler;
import pdunham.aweird.armor.WeirdBoots;
import pdunham.aweird.armor.WeirdChestPlate;
import pdunham.aweird.armor.WeirdHelmet;
import pdunham.aweird.armor.WeirdLeggins;
import pdunham.aweird.armor.WeirdPlating;
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

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class WeirdCoProxy implements IGuiHandler {

	private static StandardLogger logger = new StandardLogger();
	
    @Init
    public void init() {
        logger.info("init() complete");
    }
    
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
		return null;
	}

	// A helper function when we register items
	// Don't change the IDs
	public void registerItems() {
		int id = WeirdMain.configFirstItemID;
		// *** Do not change the order of these c'tors
		WeirdMain.weirdIngot               = new WeirdIngot(id++);
		WeirdMain.weirdPickaxe           = new WeirdPickaxe(id++);
		WeirdMain.weirdAxe =                   new WeirdAxe(id++);
        WeirdMain.weirdShovel =             new WeirdShovel(id++);
        WeirdMain.weirdHoe =                   new WeirdHoe(id++);
        WeirdMain.weirdSlingShot =       new WeirdSlingShot(id++);
        WeirdMain.weirdPowder =             new WeirdPowder(id++);
        WeirdMain.weirdSword =               new WeirdSword(id++);
        WeirdMain.pebble =                       new Pebble(id++);
        WeirdMain.weirdCasing =             new WeirdCasing(id++);
        WeirdMain.weirdStrongCasing = new WeirdStrongCasing(id++);
        WeirdMain.weirdStickyCasing = new WeirdStickyCasing(id++);
        WeirdMain.weirdGrenade           = new WeirdGrenade(id++, "WeirdGrenade", "Weird grenade", WeirdMain.weirdCasing, false, 3, 1, 2.0f);
        WeirdMain.weirdHelmet             = new WeirdHelmet(id++);
        WeirdMain.weirdChestPlate     = new WeirdChestPlate(id++);
        WeirdMain.weirdLeggins           = new WeirdLeggins(id++);
        WeirdMain.weirdBoots               = new WeirdBoots(id++);
        WeirdMain.weirdPlating           = new WeirdPlating(id++);
        WeirdMain.weirdStickyGrenade           = new WeirdGrenade(id++, "WeirdStickyGrenade", "Weird sticky grenade", WeirdMain.weirdStickyCasing,  true,  9, 1, 2.0f);
        WeirdMain.weirdStrongGrenade           = new WeirdGrenade(id++, "WeirdStrongGrenade", "Weird strong grenade", WeirdMain.weirdStrongCasing, false, 10, 1, 4.0f);
        WeirdMain.weirdPoop                    = new WeirdPoop(id++);
        logger.info("registerItems() complete");
	}

	// A helper function when we register tiles
	public void registerTiles(){
        logger.info("registerTiles() complete");
	}

	// A helper function when we register blocks
	// Don't change the IDs
	public void registerBlocks(){
		int id = WeirdMain.configFirstBlockID;
        WeirdMain.weirdOre = (new WeirdOre(id++));
		WeirdMain.weirdBlock = new WeirdBlock(id++);
        WeirdMain.weirdTNT= new WeirdTNT(id++);
        logger.info("registerBlocks() complete");
	}

	public void postInit() {
        ((WeirdOre) WeirdMain.weirdOre).postInit();
		for (int i = 0; i < Item.itemsList.length; i++) {
			if (Item.itemsList[i] != null) {
				logger.info(i + " " + Item.itemsList[i].getItemName());
			}
		}
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
		logger.info("postInit() complete");
	}

	public void registerSounds() {
        logger.info("registerSounds() complete");
	}
	
	// A helper function when we register textures
	// gets overridden by the clientProxy
	public void registerTextures() {
        logger.info("registerTextures() complete");
	}
	
    // Nothing here as the server doesn't render graphics!
	// gets overridden by the clientProxy
	public void registerRenderers(WeirdMain weirdMain) {
        logger.info("registerRenderers() complete");
	}
	
	public void registerHandlers() {
		NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());		
	}
	
	public void registerAchievements() {
		logger.info("registerAchievements complete");
	}

	public void sendTextToServer(String msg) {
		logger.info("sendTextToServer " + msg);
	}

	public void sendTextToClient(String msg, Player toPlayer) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeBytes(msg);
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = WeirdConstants.packetChannelName;
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			PacketDispatcher.sendPacketToPlayer(packet, toPlayer);
			logger.info("(" + FMLCommonHandler.instance().getEffectiveSide() + ") sendTextToClient to " + toPlayer + ", " + msg);
		} catch (Exception ex) {
			logger.warn("(" + FMLCommonHandler.instance().getEffectiveSide() + ") sendTextToClient failed to " + toPlayer + ", " + msg);
		    ex.printStackTrace();
		}
	}
}
