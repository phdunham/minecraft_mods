package pdunham.weird.tools;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;

public class WeirdPickaxe extends ItemPickaxe {

	private static StandardLogger logger = new StandardLogger();

 	// Standard c'tor
	public WeirdPickaxe(int id) {
        super(id, EnumToolMaterial.EMERALD);

        // Limit the stack size to a weird number
        setMaxStackSize(2);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabTools);
        
        // Set the internal name
        setItemName("weirdPickaxe");
        
        // Set the texture.
        setIconCoord(5, 0);
        
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdPickaxe");
		// Set the external name
		LanguageRegistry.addName(this, "Weird Pickaxe");

		// A weird pickaxe is 3 weird ingots and 2 iron ingots
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdPickaxe), "www", " i ", " i ",
							'w', new ItemStack(WeirdMain.weirdIngot),
							'i', new ItemStack(Item.ingotIron));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	// Return true if the pickaxe can harvest the block type.
	public boolean canHarvestBlock(Block par1Block)
    {
        return true;
    }

     //  Make weird Pickaxe 1.5 x more efficient than diamond.
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return super.getStrVsBlock(par1ItemStack, par2Block) * 1.5f;
    }
    
	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
