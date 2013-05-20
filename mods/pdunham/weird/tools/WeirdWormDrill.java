package pdunham.weird.tools;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;

public class WeirdWormDrill extends ItemPickaxe {

	private static StandardLogger logger = new StandardLogger();
	private static List<Integer> nonHarvestableBlocks = new ArrayList<Integer>();

 	// Standard c'tor
	public WeirdWormDrill(int id) {
        super(id, EnumToolMaterial.EMERALD);

        // Limit the stack size to a weird number
        setMaxStackSize(2);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabTools);
        
        // Set the internal name
        setItemName("weirdWormDrill");
        
        // Set the texture.
        setIconCoord(2, 2);
        
        // Set list of block that cannot be harvested.
        nonHarvestableBlocks.add(Block.bedrock.blockID);
        nonHarvestableBlocks.add(Block.lavaMoving.blockID);
        nonHarvestableBlocks.add(Block.lavaStill.blockID);
        nonHarvestableBlocks.add(Block.waterMoving.blockID);
        nonHarvestableBlocks.add(Block.waterStill.blockID);
        nonHarvestableBlocks.add(Block.fire.blockID);
        nonHarvestableBlocks.add(Block.leaves.blockID);
        
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdWormDrill");

		// Set the external name
		LanguageRegistry.addName(this, "Weird Worm Drive Drill");

		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdWormDrill), "www", " dw", "d w",
		  				  'w', new ItemStack(WeirdMain.weirdPlating),
						  'd', new ItemStack(Item.diamond));
		
		logger.info("postInit() complete newId: " + itemID);
	}
    
	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is) {
		return true;
	}
	
	private boolean harvest(EntityPlayer player, World world, int x, int y, int z) {
		// Check to see if there is a block at the location
		boolean harvested = false;
		int blockID = world.getBlockId(x,  y,  z);
		int meta = world.getBlockMetadata(x,  y,  z);
		
		// Don't harvest air or bed rock.
		if (blockID != 0 && blockID != Block.bedrock.blockID) {
			if (!nonHarvestableBlocks.contains(blockID)) { 

				// Drop the block on the ground if it is a 'normal' block
				Block block = Block.blocksList[blockID];
				block.dropBlockAsItem(world, x, y, z, meta, 1);
				// logger.info("Harvested " + block.getBlockName());
				harvested = true;
			}
			// Remove the block at the location
			world.setBlockAndMetadata(x, y, z, 0, 0);
		}
		return harvested;
	}
	private void activateWormDrill(EntityPlayer player, World world, int xs, int ys, int zs, int blockFace) {
		int delta = 2;
		int areaDrilledCount = 0;
		int blockHarvestCount = 0;
		for (int d1 = -delta; d1 <= delta; d1++) {
			for (int d3 = Math.abs(d1) - delta; d3 <= delta - Math.abs(d1); d3++) {
				for (int d2 = Math.abs(d1) - delta; d2 <= delta - Math.abs(d1); d2++) {
 					int x = 0, y = 0, z = 0;

 					switch (blockFace) {
 						// Block faces:
						//   y = 0
			            case 0:
		 					z = zs + d3;
		 					y = ys + d1 + delta;
		 					x = xs + d2;
	                    break;
	 				
	                    //  -y = 1
			            case 1:
		 					z = zs - d3;
		 					y = ys + d1 - delta;
		 					x = xs + d2;
	                    break;
	 				
	                    //   z = 2
			            case 2:
		 					z = zs + d3 + delta;
		 					x = xs + d1;
		 					y = ys + d2 + delta;
	                    break;
	 			
	                    //  -z = 3
			            case 3:
		 					z = zs - d3 - delta;
		 					x = xs + d1;
		 					y = ys + d2 + delta;
	                    break;
	 					
		                //   x = 4
			            case 4:
		 					x = xs + d3 + delta;
		 					z = zs + d1;
		 					y = ys + d2 + delta;
		                break;

		                //  -x = 5
			            case 5:
		 					x = xs - d3 - delta;
		 					z = zs + d1;
		 					y = ys + d2 + delta;
	                    break;
					}
					if (harvest(player, world, x, y, z)) {
						blockHarvestCount++;
					}
					areaDrilledCount++;
				}
			}
		}
		// create a circular hole. Set the direction based on which block face is clicked on.
		logger.info("Activated " + xs + ", " + ys + ", " + zs + ", " + blockFace + " area harvested: " + areaDrilledCount + " blocks reclaimed " + blockHarvestCount);
	}

	private void activateTubeDrill(EntityPlayer player, World world, int xs, int ys, int zs, int blockFace) {
		int delta = 1;
		int areaDrilledCount = 0;
		int blockHarvestCount = 0;
		for (int d1 = -delta; d1 <= delta; d1++) {
				for (int d2 = 0; d2 <= (delta * 2) + 1; d2++) {
					for (int d3 = 0; d3 <= 15; d3++) {
 					int x = 0, y = 0, z = 0;

 					switch (blockFace) {
 						// Block faces:
						//   y = 0
			            case 0:
		 					x = xs + d1;
		 					y = ys + d3;
		 					z = zs + d2;
	                    break;
	 				
	                    //  -y = 1
			            case 1:
		 					x = xs + d1;
		 					y = ys - d3;
		 					z = zs + d2;
	                    break;
	 				
	                    //   z = 2
			            case 2:
		 					x = xs + d1;
		 					y = ys + d2;
		 					z = zs + d3;
	                    break;
	 			
	                    //  -z = 3
			            case 3:
		 					x = xs + d1;
		 					y = ys + d2;
		 					z = zs - d3;
	                    break;
	 					
		                //   x = 4
			            case 4:
		 					x = xs + d3;
		 					y = ys + d2;
		 					z = zs + d1;
		                break;

		                //  -x = 5
			            case 5:
		 					x = xs - d3;
		 					y = ys + d2;
		 					z = zs + d1;
	                    break;
					}
					if (harvest(player, world, x, y, z)) {
						blockHarvestCount++;
					}
					areaDrilledCount++;
				}
			}
		}
		// create a circular hole. Set the direction based on which block face is clicked on.
		logger.info("Activated " + xs + ", " + ys + ", " + zs + ", " + blockFace + " area harvested: " + areaDrilledCount + " blocks reclaimed " + blockHarvestCount);
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int blockFace, float par8, float par9, float par10) {
		if (!world.isRemote) {
			// logger.info("onItemUse location " + x + ", " + z + "  h=" + y + " player=" + player);
			if (WeirdMain.commandDrill.getDrillMode() == "tube") {
				activateTubeDrill(player, world, x, y, z, blockFace);
				return true;
			}
			activateWormDrill(player, world, x, y, z, blockFace);
			return true;
		}
		return false;
	}
}
