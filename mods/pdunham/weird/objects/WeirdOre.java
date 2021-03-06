package pdunham.weird.objects;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeirdOre extends Block {

	private static StandardLogger logger = new StandardLogger();

	public WeirdOre(int id) {
		
		// Call the parent class c'tor first.
		super(id, Material.iron);

		// Set the internal reference name
		setBlockName("weirdOre");
		
		// Put this block on the block tab
		setCreativeTab(CreativeTabs.tabBlock);
		
		// The one for dirt is 0.5F. The one for obsidian is 50.0F
		setHardness(5.6F);
		
		// Resistance to explosions. The resistance for bedrock is 6000000.0F. 10.0f is the same as Stone Blocks
		setResistance(11.0F);
		
		// The sound we make when walked on it and when we break it.
		setStepSound(this.soundMetalFootstep);
		
		// Make it glow some.
		setLightValue(0.25f);

		// Pick the correct icon from the .png file.
		blockIndexInTexture = 2;
		
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
	    GameRegistry.registerBlock(this, "weirdOre");
	
	    // Only iron and above pick axe can mine this block 
	    MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 2);
	
	    // Add a human readable name
	    LanguageRegistry.addName(this, "Weird Ore");

		logger.info("postInit() complete newId: " + blockID);
	}

	// Define what the block drops when mined
	public int idDropped(int zero1, Random random, int zero2) {
		int itemId = WeirdMain.weirdOre.blockID;
		logger.info("idDropped() returns weirdOre: " + itemId);
        return itemId;
	}	
	
	// Define how many are dropped.
	public int quantityDropped(Random par1Random) {
		logger.info("quantityDropped() returns 1");
		return 1;
	}
	
	// Marks a method as client side only, typically for graphics and rendering
	@SideOnly(Side.CLIENT) 
	public int getBlockTextureFromSide(int i) {
		// Which texture to use from the .png file
		return 1; // The first icon on the spritesheet
	}

	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
