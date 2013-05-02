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

public class WeirdCasing extends Item {

	private static StandardLogger logger = new StandardLogger();

 	// Standard c'tor
	public WeirdCasing(int id) {
        super(id);
        
        // Max Limit on stack size
        setMaxStackSize(64);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabMaterials);
        
        // Set the internal name
        setItemName("WeirdCasing");
        
        // Set the texture.
        setIconCoord(0, 1);
        
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "WeirdCasing");

		// Set the external name
		LanguageRegistry.addName(this, "Weird Casing");

		// Recipe for make weirdCasing
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdCasing), "gig", "w w", "gig",
				'w', new ItemStack(WeirdMain.weirdIngot), 
				'i', new ItemStack(Item.ingotIron),
				'g', new ItemStack(Item.ingotGold));

		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
