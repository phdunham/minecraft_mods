package pdunham.aweird.armor;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class WeirdBoots extends ItemArmor implements IArmorTextureProvider {

	private static StandardLogger logger = new StandardLogger();
	
	public WeirdBoots(int i) {
		// super(id, material, subset, boots=3);
		super(i, WeirdConstants.armorWEIRD, 4, 3);
		
		setIconCoord(7, 1);
		setTextureFile(WeirdConstants.pathTexturesIcons);
		setItemName("WeirdBoots");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabCombat);
	
        logger.info("ArmorTextureFile " + WeirdConstants.pathTexturesArmor);
        logger.info("c'tor() complete");
    }

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "WeirdBoots");
		
		// Set the external name
		LanguageRegistry.addName(this, "Weird Boots");

		// How to make a weird boots
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdBoots), "   ", "w w", "w w",
							'w', new ItemStack(WeirdMain.weirdPlating));
		
		logger.info("postInit() complete newId: " + itemID);
	}
	
	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		long index = (Minecraft.getSystemTime() / 250) % 16;
		return WeirdConstants.pathTexturesArmorAnimated + index + ".png";
	}
}
