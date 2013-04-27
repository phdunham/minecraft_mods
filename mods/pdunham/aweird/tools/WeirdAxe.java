package pdunham.aweird.tools;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;

public class WeirdAxe extends ItemAxe {

	private static StandardLogger logger = new StandardLogger();
	
 	// Standard c'tor
	public WeirdAxe(int id) {
        super(id, EnumToolMaterial.EMERALD);

        // Limit the stack size to a weird number
        setMaxStackSize(2);
        
        // How many time you can use this tool
        setMaxDamage(1561);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabTools);
        
        // Set the internal name
        setItemName("weirdAxe");
        
        // Set the texture.
        setIconCoord(6, 0);
        
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdAxe");
		// Set the external name
		LanguageRegistry.addName(this, "Weird axe");

		// A weird pickaxe is 3 weird ingots and 2 iron ingots
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdAxe), " ww", " iw", " i ",
							'w', new ItemStack(WeirdMain.weirdIngot), 
							'i', new ItemStack(Item.ingotIron));
		
		logger.info("postInit() complete newId: " + itemID);
	}

    // Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if sword
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
    		// Super efficient on these materials
    		if (par2Block != null &&
        		(par2Block.blockMaterial == Material.wood || 
        		 par2Block.blockMaterial == Material.plants ||
        		 par2Block.blockMaterial == Material.vine)) {
    			return 12.0f;
    		}
    		
    		// Default for the rest
    		return super.getStrVsBlock(par1ItemStack, par2Block);
	}
    
	@Override
    public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
