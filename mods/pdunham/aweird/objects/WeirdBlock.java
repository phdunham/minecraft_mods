package pdunham.aweird.objects;

import java.util.logging.Logger;

import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class WeirdBlock extends Block {

	private static StandardLogger logger = new StandardLogger();

 	// Standard c'tor
	public WeirdBlock(int id) {
		
		// Call the parent class c'tor first.
		super(id, Material.iron);

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
		
		// Pick the correct icon from the .png file.
		blockIndexInTexture = 4;

        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerBlock(this, "weirdBlock");

		// Set the external name
		LanguageRegistry.addName(this, "A block of weird");

		// A solid block is crafted from 9 weirdIngots in the crafting table
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdBlock), "www", "www", "www",
							'w', new ItemStack(WeirdMain.weirdIngot));

		logger.info("postInit() complete newId: " + blockID);
	}

	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
