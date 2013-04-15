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

public class WeirdGrenade extends Item {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdGrenade(int id) {
        super(id);
        
        // Max Limit on stack size
        setMaxStackSize(64);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabMaterials);
        
        // Set the internal name
        setItemName("WeirdGrenade");
        
        // Set the texture.
        setIconCoord(3, 1);
        
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "WeirdGrenade");

		// Set the external name
		LanguageRegistry.addName(this, "Weird grenage");

		// Recipe for make weirdGrenade
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdGrenade), " c ", "ipi", " i ",
				'c', new ItemStack(WeirdMain.weirdCasing), 
				'i', new ItemStack(Item.ingotIron),
				'p', new ItemStack(WeirdMain.weirdPowder));

		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getTextureFile(){
		return WeirdConstants.pathIcons;
	}	
}
