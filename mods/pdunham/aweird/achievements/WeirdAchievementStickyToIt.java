package pdunham.aweird.achievements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdMain;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class WeirdAchievementStickyToIt extends Achievement {
	
	private static StandardLogger logger = new StandardLogger();

	public WeirdAchievementStickyToIt() {
		// id, name, x, y, item display, prerequisite
		super(2006, "Weird Sticky To It", 7, 9, WeirdMain.weirdStickyGrenade, WeirdMain.weirdAchievementPowder);
		registerAchievement();

		logger.info("c'tor() complete");
	}

    @SideOnly(Side.CLIENT)
    @Override
    public String getName() {
		return "Sticky To It";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getDescription() {
	    return "Construct and detonate a weird sticky grenade";
    }
}
