package pdunham.weird.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;


public class QuickEditWand extends ItemAxe {

	private static StandardLogger logger = new StandardLogger();
	   
	private enum ClickState {
		waitingFirstClick,
		waitingSecondClick	
	};
	
	private ClickState clickState = ClickState.waitingFirstClick;
	private int x1, x2;
	private int y1, y2;
	private int z1, z2;
	private String mode = "created";

	public QuickEditWand(int id) {
		super(id, EnumToolMaterial.GOLD);
		setIconCoord(0, 2);
		setItemName("hatchetGold");
	}
   
	private void rightClick(EntityPlayer player, World world, int x, int y, int z) {
		// Default is, not item in slot #9, so use delete mode.
		int currentBlockMetaData = 0;
		int currentBlockID = 0;
		String currentBlockName = "Delete mode";
		mode = "deleted";
		ItemStack itemStack = player.inventory.mainInventory[8];

		// If there is an item in slot #9, check to see if it is a block item. If not, return;
		if (itemStack != null) {
			Item item = itemStack.getItem();
			currentBlockID = itemStack.itemID;
			currentBlockName = itemStack.getDisplayName();
			currentBlockMetaData = itemStack.getItemDamageForDisplay();

			// Check the right most inventory bar item. If it is not a Block based item do nothing.
			if (!ItemBlock.class.isInstance(item)) {
				logger.info("rightClick slot #9 is not a block: " + currentBlockName);
				return;
			}
			mode = "created";
		}

		if (clickState == ClickState.waitingFirstClick) {
			logger.info("rightClick 1 slot #9 is " + currentBlockName + " meta is " + currentBlockMetaData + " by " + player.getEntityName());
			x1 = x;
			y1 = Math.max(y,  2);
			z1 = z;
			setIconCoord(1, 2);
	        clickState = ClickState.waitingSecondClick;
	        return;
		}
		
		if (clickState == ClickState.waitingSecondClick) {
			x2 = x;
			y2 = Math.max(y, 2);
			z2 = z;
			setIconCoord(0, 2);
	        clickState = ClickState.waitingFirstClick;

			// Setup a 3d for loop to populate the blocks
			int xStart = (x1 <= x2) ? x1 : x2;
			int xStop  = (x1 <= x2) ? x2 : x1;
			int yStart = (y1 <= y2) ? y1 : y2;
			int yStop  = (y1 <= y2) ? y2 : y1;
			int zStart = (z1 <= z2) ? z1 : z2;
			int zStop  = (z1 <= z2) ? z2 : z1;
			int totalBlocks = (xStop - xStart) * (yStop - yStart) * (zStop - zStart);

			int blockCount = 0;
			// logger.finest("Params " + x1 + ", " + z1 + " h=" + y1);
			// logger.finest("Params " + x2 + ", " + z2 + " h=" + y2);
			
			for (int ix = xStart; ix <= xStop; ix++) {
				for (int iy = yStart; iy <= yStop; iy++) {
					for (int iz = zStart; iz <= zStop; iz++) {
						// logger.finest("rightClick Filling " + ix + ", " + iz + " h=" + iy + " id=" + currentBlockID + " meta=" + currentBlockMetaData);
						// World.addBlockEvent(X,Y,Z, BlockID, EventID, EventParameter)
						world.setBlockAndMetadata(ix, iy, iz, currentBlockID, currentBlockMetaData);
						blockCount++;
						if ((blockCount %25000) == 0) {
							player.addChatMessage(blockCount + " blocks out of " + totalBlocks + " " + mode);
						} 
					}
				}
			}
			player.addChatMessage(blockCount + " blocks " + mode);
			logger.info("rightClick 2 slot #9 is " + currentBlockName + " meta is " + currentBlockMetaData + " by " + player.getEntityName() + " blocks " + mode + ": " + blockCount);
	        return;
		}
		logger.error("rightClick invalid click state = " + clickState);
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if (!world.isRemote) {
			// logger.info("onItemUse location " + x + ", " + z + "  h=" + y + " player=" + player);
			rightClick(player, world, x, y, z);
		}
		return true;
	}
   
	@Override
    public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}
}
