package pdunham.aweird.achievements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdMain;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class WeirdAchievementOre extends Achievement {
	
	private static StandardLogger logger = new StandardLogger();

	public WeirdAchievementOre() {
		// id, name, x, y, item display, prerequisite
		super(2001, "Weird ore", 1, 11, WeirdMain.weirdOre, AchievementList.acquireIron); 
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
	    return "Mined weird ore";
    }
}
