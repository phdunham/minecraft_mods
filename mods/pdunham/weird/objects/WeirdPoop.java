package pdunham.weird.objects;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class WeirdPoop extends Item {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdPoop(int id) {
        super(id);
     
        // Limit the stack size to a weird number
        setMaxStackSize(29);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabMaterials);
        
        // Set the internal name
        setItemName("weirdPoop");
        
        // Set the texture.
        setIconCoord(11, 1);
        
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("c'tor() complete id: " + id);
	}
	
	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdPoop");

		// Set the external name
		LanguageRegistry.addName(this, "Poop");

		logger.info("postInit() complete newId: " + itemID);
	}
	
	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
	
	// Try to fertilize stuff w/ poop.
	@Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
            return false;
        }
    
        int blockToFertilizeID;
        int fertilizeGrassLoopIndex;
        int sproutID;

    		blockToFertilizeID = par3World.getBlockId(par4, par5, par6);
    		
    		BonemealEvent event = new BonemealEvent(par2EntityPlayer, par3World, blockToFertilizeID, par4, par5, par6);
    		if (MinecraftForge.EVENT_BUS.post(event)) {
    			return false;
    		}

        if (event.getResult() == Result.ALLOW) {
            if (!par3World.isRemote) {
                par1ItemStack.stackSize--;
            }
            return true;
        }

        if (blockToFertilizeID == Block.sapling.blockID) {
            if (!par3World.isRemote) {
                ((BlockSapling)Block.sapling).growTree(par3World, par4, par5, par6, par3World.rand);
                --par1ItemStack.stackSize;
            }
            return true;
        }

        if (blockToFertilizeID == Block.mushroomBrown.blockID || blockToFertilizeID == Block.mushroomRed.blockID) {
            if (!par3World.isRemote && ((BlockMushroom)Block.blocksList[blockToFertilizeID]).fertilizeMushroom(par3World, par4, par5, par6, par3World.rand)) {
                --par1ItemStack.stackSize;
            }
            return true;
        }

        if (blockToFertilizeID == Block.melonStem.blockID || blockToFertilizeID == Block.pumpkinStem.blockID) {
            if (par3World.getBlockMetadata(par4, par5, par6) == 7) {
                return false;
            }

            if (!par3World.isRemote) {
                ((BlockStem)Block.blocksList[blockToFertilizeID]).fertilizeStem(par3World, par4, par5, par6);
                --par1ItemStack.stackSize;
            }
            return true;
        }

        if (blockToFertilizeID > 0 && Block.blocksList[blockToFertilizeID] instanceof BlockCrops) {
            if (par3World.getBlockMetadata(par4, par5, par6) == 7) {
                return false;
            }

            if (!par3World.isRemote) {
                ((BlockCrops)Block.blocksList[blockToFertilizeID]).fertilize(par3World, par4, par5, par6);
                --par1ItemStack.stackSize;
            }
	            return true;
        }

        if (blockToFertilizeID == Block.cocoaPlant.blockID) {
            if (!par3World.isRemote) {
                par3World.setBlockMetadataWithNotify(par4, par5, par6, 8 | BlockDirectional.getDirection(par3World.getBlockMetadata(par4, par5, par6)));
                --par1ItemStack.stackSize;
            }
            return true;
        }

        if (blockToFertilizeID == Block.grass.blockID) {
            if (!par3World.isRemote) {
                --par1ItemStack.stackSize;
                label133:

                for (fertilizeGrassLoopIndex = 0; fertilizeGrassLoopIndex < 128; ++fertilizeGrassLoopIndex) {
                		sproutID = par4;
                    int var14 = par5 + 1;
                    int var15 = par6;

                    for (int var16 = 0; var16 < fertilizeGrassLoopIndex / 16; ++var16) {
                    	sproutID += itemRand.nextInt(3) - 1;
                        var14 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
                        var15 += itemRand.nextInt(3) - 1;

                        if (par3World.getBlockId(sproutID, var14 - 1, var15) != Block.grass.blockID || par3World.isBlockNormalCube(sproutID, var14, var15)) {
                            continue label133;
                        }
                    }

                    if (par3World.getBlockId(sproutID, var14, var15) == 0) {
                        if (itemRand.nextInt(10) != 0) {
                            if (Block.tallGrass.canBlockStay(par3World, sproutID, var14, var15)) {
                                par3World.setBlockAndMetadataWithNotify(sproutID, var14, var15, Block.tallGrass.blockID, 1);
                            }
                        }
                        else
                        {
                            ForgeHooks.plantGrass(par3World, sproutID, var14, var15);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}