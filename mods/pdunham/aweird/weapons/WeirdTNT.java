package pdunham.aweird.weapons;

import java.util.logging.Logger;

import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class WeirdTNT extends BlockTNT {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdTNT(int id) {
		
		// Call the parent class c'tor first.
		super(id, 0);

		// Set the internal reference name
		setBlockName("weirdTNT");
		
		// Put this block on the block tab
		setCreativeTab(CreativeTabs.tabRedstone);
		
		// The one for dirt is 0.5F. The one for obsidian is 50.0F
		setHardness(5.6F);
		
		// Resistance to explosions. The resistance for bedrock is 6000000.0F
		setResistance(56.34F);
		
		// The sound we make when walked on it and when we break it.
		setStepSound(this.soundMetalFootstep);
		
		// Make it glow bright.
		setLightValue(1);
		
		// Pick the correct icon from the .png file.
		blockIndexInTexture = 11;

        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerBlock(this, "weirdTNT");

		// Set the external name
		LanguageRegistry.addName(this, "A weird TNT");

		// A recipe for weird TNT!
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdTNT), "wfw", "gtg", "wfw",
				'w', new ItemStack(WeirdMain.weirdPowder), 
				't', new ItemStack(Block.tnt),
				'f', new ItemStack(Item.flintAndSteel),
				'g', new ItemStack(Item.gunpowder));

		logger.info("postInit() complete newId: " + blockID);
	}

	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
