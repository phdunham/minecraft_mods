package pdunham.weird.objects;

import java.util.logging.Logger;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
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
        
        logger = new StandardLogger("weirdIngot");
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdIngot");
		// Set the external name
		LanguageRegistry.addName(this, "Weird ingot");
		
		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getTextureFile(){
		return WeirdOre.pathTexture;
	}	
}
