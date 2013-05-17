package pdunham.weird.achievements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class WeirdAchievementGrenadeCreeper extends Achievement {
	
	private static StandardLogger logger = new StandardLogger();

	public WeirdAchievementGrenadeCreeper() {
		// id, name, x, y, item display, prerequisite
		super(2002, "Weird Grenade Creeper", 7, 11, WeirdMain.weirdGrenade, null); //, WeirdMain.weirdAchievementPowder);
		registerAchievement();

		logger.info("c'tor() complete");
	}

    @SideOnly(Side.CLIENT)
    @Override
    public String getName() {
		return "Returning the favor";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getDescription() {
	    return "Damage a creeper with a weird grenade";
    }
}
