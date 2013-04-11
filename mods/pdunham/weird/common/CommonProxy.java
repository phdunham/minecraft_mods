package pdunham.weird.common;

import java.util.logging.Logger;

import pdunham.weird.objects.WeirdBlock;
import pdunham.weird.objects.WeirdIngot;
import pdunham.weird.objects.WeirdOre;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

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
	public void registerItems(){
		logger.info("Registering Items");

		// All of our items (not blocks) start at this id #
		int idStart = 7237;

		// Create the Ore
		WeirdMain.weirdOre = new WeirdOre(idStart++);
        ((WeirdOre) WeirdMain.weirdOre).postInit();

		// Create the Ingots
		WeirdMain.weirdIngot = new WeirdIngot(idStart++);
        ((WeirdIngot) WeirdMain.weirdIngot).postInit();
	}

	// A helper function when we register tiles
	public void registerTiles(){
	}

	// A helper function when we register textures
	public void registerTextures()
	{
	}
	// A helper function when we register blocks
	public void registerBlocks(){
		int blockStart = 3123;
		
        // Create a block param is unique block id
        logger.info("Registering WeirdBlock");
        WeirdMain.weirdBlock = (new WeirdBlock(blockStart++));
        ((WeirdBlock) WeirdMain.weirdBlock).postInit();
	}

	// A helper function when we register recipes
	public void registerRecipes(){
	}
}
