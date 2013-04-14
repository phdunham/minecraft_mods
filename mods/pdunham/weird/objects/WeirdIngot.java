package pdunham.weird.objects;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

public class WeirdIngot extends Item {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdIngot(int id) {
        super(id);
     
        // Limit the stack size to a weird number
        setMaxStackSize(29);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabMaterials);
        
        // Set the internal name
        setItemName("weirdIngot");
        
        // Set the texture.
        setIconCoord(3, 0);
        
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdIngot");

		// Set the external name
		LanguageRegistry.addName(this, "Weird ingot");

		// Smelting 1 ore produces 1 ingot and 1 experience
		GameRegistry.addSmelting(WeirdMain.weirdOre.blockID, new ItemStack(WeirdMain.weirdIngot, 1), 1.0f);

		// Allow user to break blocks back into ingots
		GameRegistry.addShapelessRecipe(new ItemStack(WeirdMain.weirdIngot, 9), new ItemStack(WeirdMain.weirdBlock));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getTextureFile(){
		return WeirdMain.pathTexture;
	}	
}
