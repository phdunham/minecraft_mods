package pdunham.weird.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pdunham.weird.objects.Pebble;
import pdunham.weird.objects.WeirdAxe;
import pdunham.weird.objects.WeirdHoe;
import pdunham.weird.objects.WeirdOre;
import pdunham.weird.objects.WeirdIngot;
import pdunham.weird.objects.WeirdBlock;
import pdunham.weird.objects.WeirdPickaxe;
import pdunham.weird.objects.WeirdPowder;
import pdunham.weird.objects.WeirdShovel;
import pdunham.weird.objects.WeirdSlingShot;
import pdunham.weird.objects.WeirdSword;
import pdunham.weird.objects.WeirdTNT;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

	private static StandardLogger logger;
	
    @Init
    public void init() {
    		logger = new StandardLogger("weird.CommonProxy");
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
	public void registerItems(){
		// Create the Ingots
		WeirdMain.weirdIngot = new WeirdIngot(7237);
        
		// Create the Tools
		WeirdMain.weirdPickaxe = new WeirdPickaxe(7238);
		WeirdMain.weirdAxe = new WeirdAxe(7239);
        WeirdMain.weirdShovel = new WeirdShovel(7240);
        WeirdMain.weirdHoe = new WeirdHoe(7241);
        WeirdMain.weirdSlingShot = new WeirdSlingShot(7242);
        WeirdMain.weirdPowder = new WeirdPowder(7243);
        WeirdMain.weirdSword = new WeirdSword(7244);
        WeirdMain.pebble = new Pebble(7245);
		
		logger.info("registerItems() complete");
	}

	// A helper function when we register tiles
	public void registerTiles(){
        logger.info("registerTiles() complete");
	}

	// A helper function when we register textures
	public void registerTextures()
	{
        logger.info("registerTextures() complete");
	}

	// A helper function when we register blocks
	// Don't change the IDs
	public void registerBlocks(){
        WeirdMain.weirdOre = (new WeirdOre(3125));
		WeirdMain.weirdBlock = new WeirdBlock(3126);
        WeirdMain.weirdTNT= new WeirdTNT(3127);
       
        logger.info("registerBlocks() complete");
	}
	
	public void postInit() {
        ((WeirdOre) WeirdMain.weirdOre).postInit();
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
	}
}
