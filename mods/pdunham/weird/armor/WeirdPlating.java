package pdunham.weird.armor;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

public class WeirdPlating extends Item {

	private static StandardLogger logger = new StandardLogger();

 	// Standard c'tor
	public WeirdPlating(int id) {
        super(id);
     
        // Limit the stack size to a weird number
        setMaxStackSize(29);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabMaterials);
        
        // Set the internal name
        setItemName("weirdPlating");
        
        // Set the texture.
        setIconCoord(8, 1);
        
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdPlating");

		// Set the external name
		LanguageRegistry.addName(this, "Weird Plating");

		// How to make a weird platin
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdPlating), "wiw", "g g", "wiw",
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
