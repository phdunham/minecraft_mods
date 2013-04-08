package pdunham.weirdBlock.common;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class WeirdBlock extends Block {

	public static String pathTexture = "/textures/blocks/weirdBlock.png";

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
		this.setLightValue(1);
		
	}
	
	// Marks a method as client side only, typically for graphics and rendering
	@SideOnly(Side.CLIENT) 
	public int getBlockTextureFromSide(int i) {
		// Which texture to use from the .png file
		return 1; // The first icon on the spritesheet
	}
	
	public String getTextureFile(){
		return pathTexture;
	}	
}
