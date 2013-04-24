package pdunham.weird.common;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.world.World;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

import pdunham.weird.armor.WeirdBoots;
import pdunham.weird.armor.WeirdChestPlate;
import pdunham.weird.armor.WeirdHelmet;
import pdunham.weird.armor.WeirdLeggins;
import pdunham.weird.armor.WeirdPlating;
import pdunham.weird.objects.WeirdOre;
import pdunham.weird.objects.WeirdIngot;
import pdunham.weird.objects.WeirdBlock;
import pdunham.weird.objects.WeirdPoop;
import pdunham.weird.objects.WeirdPowder;
import pdunham.weird.tools.WeirdAxe;
import pdunham.weird.tools.WeirdHoe;
import pdunham.weird.tools.WeirdPickaxe;
import pdunham.weird.tools.WeirdShovel;
import pdunham.weird.weapons.Pebble;
import pdunham.weird.weapons.WeirdCasing;
import pdunham.weird.weapons.WeirdGrenade;
import pdunham.weird.weapons.WeirdSlingShot;
import pdunham.weird.weapons.WeirdStickyCasing;
import pdunham.weird.weapons.WeirdStrongCasing;
import pdunham.weird.weapons.WeirdSword;
import pdunham.weird.weapons.WeirdTNT;

import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class WeirdCoProxy implements IGuiHandler {

	private static StandardLogger logger;
	
    @Init
    public void init() {
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
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
	
	public void registerAchievements() {
		logger.info("registerAchievements complete");
	}
}
