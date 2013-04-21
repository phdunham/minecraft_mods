package pdunham.weird.weapons;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemStack;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;

public class WeirdSword extends ItemSword {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdSword(int id) {
        super(id, EnumToolMaterial.EMERALD);

        // Limit the stack size to a weird number
        setMaxStackSize(2);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabCombat);
        
        // Set the internal name
        setItemName("weirdSword");
        
        // Set the texture.
        setIconCoord(14, 0);
        
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdSword");
		// Set the external name
		LanguageRegistry.addName(this, "Weird sword");

		// A weird sword is 2 weird ingots and 1 iron ingot
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdSword), " w ", " w ", " i ",
							'w', new ItemStack(WeirdMain.weirdIngot), 
							'i', new ItemStack(Item.ingotIron));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	// Do 1.05x more damage than Diamond
    public int getDamageVsEntity(Entity par1Entity) {
		return (int) (super.getDamageVsEntity(par1Entity) * 1.05f);
	}
    
	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
