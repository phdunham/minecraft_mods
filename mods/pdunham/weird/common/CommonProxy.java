package pdunham.weird.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pdunham.weird.objects.WeirdOre;
import pdunham.weird.objects.WeirdIngot;
import pdunham.weird.objects.WeirdBlock;
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
	public void registerItems(){
		// All of our items (not blocks) start at this id #
		int idStart = 7237;

		// Create the Ingots
		WeirdMain.weirdIngot = new WeirdIngot(idStart++);
        ((WeirdIngot) WeirdMain.weirdIngot).postInit();
        
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
	public void registerBlocks(){
		int idStart = 3123;
		
        // Create a block param is unique block id
        WeirdMain.weirdOre = (new WeirdOre(idStart++));
        ((WeirdOre) WeirdMain.weirdOre).postInit();

		// Create the Solid Block
		WeirdMain.weirdBlock = new WeirdBlock(idStart++);
        ((WeirdBlock) WeirdMain.weirdBlock).postInit();
       
        logger.info("registerBlocks() complete");
	}

	// A helper function when we register recipes
	public void registerRecipes(){
		// A solid block is crafted from 9 weirdIngots in the crafting table
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdBlock), "xxx", "xxx", "xxx",
							'x', new ItemStack(WeirdMain.weirdIngot));

		// Allow user to break blocks back into ingots
		GameRegistry.addShapelessRecipe(new ItemStack(WeirdMain.weirdIngot, 9), new ItemStack(WeirdMain.weirdBlock));
		
		// Smelting 1 ore produces 1 ingot and 1 experience
		GameRegistry.addSmelting(WeirdMain.weirdOre.blockID, new ItemStack(WeirdMain.weirdIngot, 1), 1.0f);

		logger.info("registerRecipes() complete");
	}
}
