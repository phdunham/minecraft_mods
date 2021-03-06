package pdunham.weird.achievements;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler {
	
	private static StandardLogger logger = new StandardLogger();

	public CraftingHandler() {
		logger.info("c'tor() complete");
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
		logger.info("onCrafting '" + item.getDisplayName() + "' - " + player);
        if ((item.itemID == WeirdMain.weirdAxe.itemID)     ||
        		(item.itemID == WeirdMain.weirdPickaxe.itemID) ||
        		(item.itemID == WeirdMain.weirdShovel.itemID)  ||
        		(item.itemID == WeirdMain.weirdHoe.itemID)     ||
        		(item.itemID == WeirdMain.weirdSword.itemID)   ||
        		(item.itemID == WeirdMain.weirdSlingShot.itemID)) {
        		player.triggerAchievement(WeirdMain.weirdAchievementStartingOff);
        } else if (item.itemID == WeirdMain.weirdPowder.itemID) {
            player.triggerAchievement(WeirdMain.weirdAchievementPowder);
        } else if (item.itemID == WeirdMain.weirdTNT.blockID) {
    			player.triggerAchievement(WeirdMain.weirdAchievementBetterBoom);
        } else if (item.itemID == WeirdMain.weirdStickyGrenade.itemID) {
    			player.triggerAchievement(WeirdMain.weirdAchievementStickyToIt);
        }
    }


	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		logger.info("onSmelting '" + item.getDisplayName() + "' - " + player);
		if (item.itemID == WeirdMain.weirdIngot.itemID) {
			player.triggerAchievement(WeirdMain.weirdAchievemenGetWeird);
		}
	} 
}