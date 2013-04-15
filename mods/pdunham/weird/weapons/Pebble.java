package pdunham.weird.weapons;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class Pebble extends Item {

	private static StandardLogger logger;

 	// Standard c'tor
	public Pebble(int id) {
        super(id);
        
        // Max Limit on stack size
        setMaxStackSize(64);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabMaterials);
        
        // Set the internal name
        setItemName("Pebble");
        
        // Set the texture.
        setIconCoord(15, 0);
        
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "Pebble");

		// Set the external name
		LanguageRegistry.addName(this, "Pebble");

		// Complex Recipe for Pebbles
		GameRegistry.addShapelessRecipe(new ItemStack(WeirdMain.pebble, 2), new ItemStack(Block.cobblestone));
		GameRegistry.addShapelessRecipe(new ItemStack(WeirdMain.pebble, 4), new ItemStack(Block.cobblestoneMossy));

		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getTextureFile(){
		return WeirdConstants.pathIcons;
	}	
}
