package pdunham.weird.objects;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;

public class WeirdSlingShot extends ItemBow {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdSlingShot(int id) {
        super(id);

        // Limit the stack size to a weird number
        setMaxStackSize(2);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabTools);
        
        // Set the internal name
        setItemName("weirdSlingShot");
        
        // Set the texture.
        setIconCoord(9, 0);
        
        logger = new StandardLogger("weirdSlingShot");
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdSlingShot");
		// Set the external name
		LanguageRegistry.addName(this, "Weird sling shot");

		// A weird slingShot is 3 weird ingots and 2 iron ingots
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdSlingShot), "wsw", "k k", " i ",
							'w', new ItemStack(WeirdMain.weirdIngot),
							'i', new ItemStack(Item.ingotIron),
							's', new ItemStack(Item.silk),
							'k', new ItemStack(Item.stick));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	// Return true if the slingShot can harvest the block type.
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
		return WeirdMain.pathTexture;
	}	
}
