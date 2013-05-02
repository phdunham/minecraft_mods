package pdunham.aweird.tools;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSpade;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;

public class WeirdShovel extends ItemSpade {

	private static StandardLogger logger = new StandardLogger();

 	// Standard c'tor
	public WeirdShovel(int id) {
        super(id, EnumToolMaterial.EMERALD);

        // Limit the stack size to a weird number
        setMaxStackSize(2);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabTools);
        
        // Set the internal name
        setItemName("weirdShovel");
        
        // Set the texture.
        setIconCoord(7, 0);
        
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdShovel");
		// Set the external name
		LanguageRegistry.addName(this, "Weird Shovel");

		// A weird shovel is 1 weird ingots and 2 iron ingots
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdShovel), " w ", " i ", " i ",
							'w', new ItemStack(WeirdMain.weirdIngot),
							'i', new ItemStack(Item.ingotIron));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	// Return true if the shovel can harvest the block type.
	public boolean canHarvestBlock(Block par1Block)
    {
        return true;
    }

     // Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if sword
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return super.getStrVsBlock(par1ItemStack, par2Block);
    }
    
	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
