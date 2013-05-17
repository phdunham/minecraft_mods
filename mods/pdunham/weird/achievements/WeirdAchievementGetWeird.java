package pdunham.weird.achievements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class WeirdAchievementGetWeird extends Achievement {
	
	private static StandardLogger logger = new StandardLogger();

	public WeirdAchievementGetWeird() {
		// id, name, x, y, item display, prerequisite
		super(2001, "Weird ingot", 1, 11, WeirdMain.weirdIngot, null); //, AchievementList.acquireIron); 
		registerAchievement();

		logger.info("c'tor() complete");
	}

    @SideOnly(Side.CLIENT)
    @Override
    public String getName() {
//		logger.info("getName");
		return "Get weird";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getDescription() {
	    return "Smelted weird ore";
    }
}
