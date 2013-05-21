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
	private int areaDrilledCount = 0;
	private int blockHarvestCount = 0;


 	// Standard c'tor
	public WeirdWormDrill(int id) {
        super(id, EnumToolMaterial.EMERALD);

        // Limit the stack size
        setMaxStackSize(1);
        
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
	
	// Attempt to harvest the block at the coordinates. 
	private boolean harvestBlock(EntityPlayer player, World world, int x, int y, int z) {
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
				blockHarvestCount++;
				harvested = true;
			}
			// Remove the block at the location
			world.setBlockAndMetadata(x, y, z, 0, 0);
		}
		areaDrilledCount++;
		return harvested;
	}
	
	// Take the original coords, combine them with the deltas and the direction we are facing (blockFace), 
	// normalize them and harvest the block at the normalized coordinates
	private boolean harvestBlockWithOffset(EntityPlayer player, World world, int xs, int ys, int zs, int blockFace, int d1, int d2, int d3, int delta) {
			int x = 0, y = 0, z = 0;
				switch (blockFace) {
				// Block faces:
				// +y direction
	            case 0:
 					x = xs + d2;
 					y = ys + d3 + delta;
 					z = zs + d1;
                break;
				
                // -y direction
	            case 1:
 					x = xs + d2;
 					y = ys - d3 - delta;
 					z = zs + d1;
                break;
				
                // +z direction
	            case 2:
 					x = xs + d1;
 					y = ys + d2 + delta;
 					z = zs + d3 + delta;
                break;
			
                // -z direction
	            case 3:
 					x = xs + d1;
 					y = ys + d2 + delta;
 					z = zs - d3 - delta;
                break;
					
                // +x direction
	            case 4:
 					x = xs + d3 + delta;
 					y = ys + d2 + delta;
 					z = zs + d1;
                break;

                // -x direction
	            case 5:
 					x = xs - d3 - delta;
 					y = ys + d2 + delta;
 					z = zs + d1;
                break;
		}
		return harvestBlock(player, world, x, y, z);
	}
	
	// Harvest a sorta sphere shaped area
	private void activateWormDrill(EntityPlayer player, World world, int x, int y, int z, int blockFace) {
		int width = 2;
		for (int d1 = -width; d1 <= width; d1++) {
			for (int d2 = Math.abs(d1) - width; d2 <= width - Math.abs(d1); d2++) {
				for (int d3 = Math.abs(d1) - width; d3 <= width - Math.abs(d1); d3++) {
					harvestBlockWithOffset(player, world, x, y, z, blockFace, d1, d2, d3, width);
				}
			}
		}
	}

	// Harvest a rectangular tube area
	private void activateTubeDrill(EntityPlayer player, World world, int x, int y, int z, int blockFace) {
		int size = 1;
		for (int width = -size; width <= size; width++) {
			for (int height = 0; height <= (size * 2) + 1; height++) {
				for (int depth = 0; depth <= 15; depth++) {
					harvestBlockWithOffset(player, world, x, y, z, blockFace, width, height, depth, 0);
				}
			}
		}
	}

	// Harvest a long thin hallway area
	private void activateHallDrill(EntityPlayer player, World world, int x, int y, int z, int blockFace) {
		int d1 = 0;
		for (int d2 = 0; d2 < 2; d2++) {
			for (int d3 = 0; d3 < 45; d3++) {
				harvestBlockWithOffset(player, world, x, y, z, blockFace, d1, d2, d3, 0);
			}
		}
	}
	
	// Harvest a large cube area
	private void activateCubeDrill(EntityPlayer player, World world, int x, int y, int z, int blockFace) {
		int width = 2;
		for (int d1 = -width; d1 <= width; d1++) {
			for (int d2 = 0; d2 <= (width * 2) + 1; d2++) {
				for (int d3 = 0; d3 <= (width * 2) + 1; d3++) {
					harvestBlockWithOffset(player, world, x, y, z, blockFace, d1, d2, d3, 0);
				}
			}
		}
	}

	// Harvest a large cube area
	private void activateConeDrill(EntityPlayer player, World world, int x, int y, int z, int blockFace) {
		int width = 1;
		for (int d3 = 0; d3 <= (width * 2) + 1; d3++) {
			for (int d1 = -d3; d1 <= d3; d1++) {
				for (int d2 = -d3 - 1; d2 <= d3 + 1; d2++) {
					harvestBlockWithOffset(player, world, x, y, z, blockFace, d1, d2, d3, 0);
				}
			}
		}
	}
	
	@Override
	// When the drill is right clicked on a block, select the harvest shape and harvest it.
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int blockFace, float par8, float par9, float par10) {
		
		// Only works on the !remote side
		if (world.isRemote) {
			return false;
		}

		String mode = WeirdMain.commandDrill.getDrillMode();
		logger.info("onItemUse location " + x + ", " + z + "  h=" + y + " mode='" + mode + "'");
		
		// Reset the statistics
		areaDrilledCount = 0;
		blockHarvestCount = 0;

		if (mode.equals("cone")) {
			activateConeDrill(player, world, x, y, z, blockFace);
		} else if (mode.equals("cube")) {
			activateCubeDrill(player, world, x, y, z, blockFace);
		} else if (mode.equals("hall")) {
			activateHallDrill(player, world, x, y, z, blockFace);
		} else if (mode.equals("tube")) {
			activateTubeDrill(player, world, x, y, z, blockFace);
		} else {
			// 'worm' is the default
			activateWormDrill(player, world, x, y, z, blockFace);
		} 
		logger.info(mode + " mode " + x + ", " + y + ", " + z + ", " + blockFace + " area harvested: " + areaDrilledCount + " blocks reclaimed " + blockHarvestCount);
		return true;
	}
}
