package pdunham.weird.achievements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class WeirdAchievementPowder extends Achievement {
	
	private static StandardLogger logger;

	public WeirdAchievementPowder() {
		// id, name, x, y, item display, prerequisite
		super(2004, "Weird Powder", 5, 11, WeirdMain.weirdPowder, WeirdMain.weirdAchievementStartingOff);
		registerAchievement();

		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("c'tor() complete");
	}

    @SideOnly(Side.CLIENT)
    @Override
    public String getName() {
		return "Better than gun powder!";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getDescription() {
	    return "Making weird powder!";
    }
}
