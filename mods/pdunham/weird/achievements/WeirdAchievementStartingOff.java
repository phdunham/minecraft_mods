package pdunham.weird.achievements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class WeirdAchievementStartingOff extends Achievement {
	
	private static StandardLogger logger;

	public WeirdAchievementStartingOff() {
		// id, name, x, y, item display, prerequisite
		super(2003, "Weird Starting Off", 3, 11, WeirdMain.weirdPickaxe, WeirdMain.weirdAchievementOre);
		registerAchievement();

		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("c'tor() complete");
	}

    @SideOnly(Side.CLIENT)
    @Override
    public String getName() {
		return "Starting off weird!";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getDescription() {
	    return "Making a weird tool!";
    }
}
