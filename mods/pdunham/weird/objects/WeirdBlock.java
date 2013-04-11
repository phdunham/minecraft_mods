package pdunham.weird.objects;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeirdBlock extends Block {

	private static StandardLogger logger;
	
	public static String pathTexture = "/pdunham/weird/weirdBlock.png";

	public WeirdBlock(int ID) {
		
		// Call the parent class c'tor first.
		super(ID, Material.iron);

		// Set the internal reference name
		setBlockName("weirdBlock");
		
		// Put this block on the block tab
		setCreativeTab(CreativeTabs.tabBlock);
		
		// The one for dirt is 0.5F. The one for obsidian is 50.0F
		setHardness(5.6F);
		
		// Resistance to explosions. The resistance for bedrock is 6000000.0F
		setResistance(56.34F);
		
		// The sound we make when walked on it and when we break it.
		setStepSound(this.soundMetalFootstep);
		
		// Make it glow bright.
		setLightValue(1);
		
        logger = new StandardLogger("weirdBlock");
        logger.info("c'tor() complete");		
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
	    GameRegistry.registerBlock(this, "weirdBlock");
	
	    // Only iron and above pick axe can mine this block 
	    MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 2);
	
	    // Add a human readable name
	    LanguageRegistry.addName(this, "A weird block");

		logger.info("postInit() complete");
	}
	
	// Marks a method as client side only, typically for graphics and rendering
	@SideOnly(Side.CLIENT) 
	public int getBlockTextureFromSide(int i) {
		// Which texture to use from the .png file
		return 1; // The first icon on the spritesheet
	}

	@Override
	public String getTextureFile(){
		return pathTexture;
	}	
}
