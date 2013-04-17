package pdunham.weird.armor;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class WeirdLeggins extends ItemArmor implements IArmorTextureProvider {

	private static StandardLogger logger;
	
	public WeirdLeggins(int i) {
		// super(id, material, subset, leggins=2);
		super(i, WeirdConstants.armorWEIRD, 4, 2);

		setIconCoord(6, 1);
		setTextureFile(WeirdConstants.pathIcons);
		setItemName("WeirdLeggins");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabCombat);
		
		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("ArmorTextureFile " + WeirdConstants.pathArmorLegs);
        logger.info("c'tor() complete");
    }
	
	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "WeirdLeggins");
		
		// Set the external name
		LanguageRegistry.addName(this, "Weird leggins");

		// How to make a weird leggins
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdLeggins), "wiw", "w w", "w w",
							'w', new ItemStack(WeirdMain.weirdPlating),
							'i', new ItemStack(WeirdMain.weirdIngot));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		return WeirdConstants.pathArmorLegs;
	}
}
