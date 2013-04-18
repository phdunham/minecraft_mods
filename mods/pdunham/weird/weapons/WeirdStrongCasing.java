package pdunham.weird.weapons;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class WeirdStrongCasing extends Item {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdStrongCasing(int id) {
        super(id);
        
        // Max Limit on stack size
        setMaxStackSize(64);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabMaterials);
        
        // Set the internal name
        setItemName("WeirdStrongCasing");
        
        // Set the texture.
        setIconCoord(1, 1);
        
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "WeirdStrongCasing");

		// Set the external name
		LanguageRegistry.addName(this, "Weird strong casing");

		// Recipe for make weirdStrongCasing
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdStrongCasing), "gdg", "w w", "gdg",
				'w', new ItemStack(WeirdMain.weirdIngot), 
				'd', new ItemStack(Item.diamond),
				'g', new ItemStack(Item.ingotGold));

		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getTextureFile(){
		return WeirdConstants.pathIcons;
	}	
}
