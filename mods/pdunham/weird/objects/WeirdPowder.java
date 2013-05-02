package pdunham.weird.objects;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class WeirdPowder extends Item {

	private static StandardLogger logger = new StandardLogger();

 	// Standard c'tor
	public WeirdPowder(int id) {
        super(id);
     
        // Limit the stack size to a weird number
        setMaxStackSize(29);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabMaterials);
        
        // Set the internal name
        setItemName("weirdPowder");
        
        // Set the texture.
        setIconCoord(10, 0);
        
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdPowder");

		// Set the external name
		LanguageRegistry.addName(this, "Weird Powder");

		// Complex Recipe for weird Powder
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdPowder), "sws", "ggg", "sws",
				'w', new ItemStack(WeirdMain.weirdIngot), 
				's', new ItemStack(Block.sand),
				'g', new ItemStack(Item.gunpowder));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
