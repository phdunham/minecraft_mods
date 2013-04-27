package pdunham.aweird.weapons;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class WeirdStickyCasing extends Item {

	private static StandardLogger logger = new StandardLogger();

 	// Standard c'tor
	public WeirdStickyCasing(int id) {
        super(id);
        
        // Max Limit on stack size
        setMaxStackSize(64);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabMaterials);
        
        // Set the internal name
        setItemName("WeirdStickyCasing");
        
        // Set the texture.
        setIconCoord(2, 1);
        
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "WeirdStickyCasing");

		// Set the external name
		LanguageRegistry.addName(this, "Weird sticky casing");

		// Recipe for make weirdStickyCasing
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdStickyCasing), "gig", "sws", "gig",
				'w', new ItemStack(WeirdMain.weirdIngot), 
				'i', new ItemStack(Item.ingotIron),
				'g', new ItemStack(Item.ingotGold),
				's', new ItemStack(Item.slimeBall));

		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
