package pdunham.weirdBlock.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFirst extends Block {

	public BlockFirst(int par1, Material par2Material) {
		// Call the parent class c'tor first.
		super(par1, Material.iron);
		
		// Put this block on the block tab
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		// The one for dirt is 0.5F. The one for obsidian is 50.0F
		this.setHardness(5.6F);
		
		// Resistance to explosions. The resistance for bedrock is 6000000.0F
		this.setResistance(56.34F);
		
		// The sound we make when walked on
		this.setStepSound(this.soundStoneFootstep);
		
		// Make it glow.
		this.setLightValue(1);
		
	}
}
