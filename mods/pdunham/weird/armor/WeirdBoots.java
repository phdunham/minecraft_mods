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

public class WeirdBoots extends ItemArmor implements IArmorTextureProvider {

	private static StandardLogger logger;
	
	public WeirdBoots(int i) {
		// super(id, material, subset, boots=3);
		super(i, WeirdConstants.armorWEIRD, 4, 3);
		
		setIconCoord(7, 1);
		setTextureFile(WeirdConstants.pathIcons);
		setItemName("WeirdBoots");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabCombat);
	
		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("ArmorTextureFile " + WeirdConstants.pathArmor);
        logger.info("c'tor() complete");
    }

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "WeirdBoots");
		
		// Set the external name
		LanguageRegistry.addName(this, "Weird boots");

		// How to make a weird boots
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdBoots), "   ", "w w", "w w",
							'w', new ItemStack(WeirdMain.weirdStrongCasing));
		
		logger.info("postInit() complete newId: " + itemID);
	}
	
	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		return WeirdConstants.pathArmor;
	}
}
