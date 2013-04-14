package pdunham.weird.achievements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class WeirdAchievementOre extends Achievement {
	
	private static StandardLogger logger;

	public WeirdAchievementOre() {
		// id, name, x, y, item display, prerequisite
		super(2001, "Weird ore", 5, 4, WeirdMain.weirdOre, null); //AchievementList.buildPickaxe);
		registerAchievement();

		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("c'tor() complete");
	}

    @SideOnly(Side.CLIENT)
    @Override
    public String getName() {
//		logger.info("getName");
		return "Get weird!";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getDescription() {
        logger.info("getDescription");
	    return "Mined weird ore!";
    }
}
