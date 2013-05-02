package pdunham.weird.achievements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class WeirdAchievementBetterBoom extends Achievement {
	
	private static StandardLogger logger = new StandardLogger();

	public WeirdAchievementBetterBoom() {
		// id, name, x, y, item display, prerequisite
		super(2005, "Weird Better Boom", 7, 13, WeirdMain.weirdTNT, WeirdMain.weirdAchievementPowder);
		registerAchievement();

		logger.info("c'tor() complete");
	}

    @SideOnly(Side.CLIENT)
    @Override
    public String getName() {
		return "Better Boom";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getDescription() {
	    return "Construct weird TNT from weird powder";
    }
}
