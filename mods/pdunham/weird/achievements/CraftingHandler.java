package pdunham.weird.achievements;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import pdunham.weird.tools.WeirdAxe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler {
	
	private static StandardLogger logger;

	public CraftingHandler() {
		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("c'tor() complete");
	}

	@Override
	public void onCrafting(net.minecraft.entity.player.EntityPlayer player,
						  net.minecraft.item.ItemStack item,
						  net.minecraft.inventory.IInventory craftMatrix) {
        if ((item.itemID == WeirdMain.weirdAxe.itemID)     ||
        		(item.itemID == WeirdMain.weirdPickaxe.itemID) ||
        		(item.itemID == WeirdMain.weirdShovel.itemID)  ||
        		(item.itemID == WeirdMain.weirdHoe.itemID)     ||
        		(item.itemID == WeirdMain.weirdSword.itemID)   ||
        		(item.itemID == WeirdMain.weirdSlingShot.itemID)) {
                player.triggerAchievement(WeirdMain.weirdAchievementStartingOff);
        } else if (item.itemID == WeirdMain.weirdPowder.itemID) {
                player.triggerAchievement(WeirdMain.weirdAchievementPowder);
        }
    }


	@Override
	public void onSmelting(net.minecraft.entity.player.EntityPlayer player,
						   net.minecraft.item.ItemStack item) {
	}
}