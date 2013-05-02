package pdunham.aweird.armor;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class WeirdHelmet  extends ItemArmor implements IArmorTextureProvider {

	private static StandardLogger logger = new StandardLogger();
	
	public WeirdHelmet(int i) {
		// super(id, material, subset, helmet=0);
		super(i, WeirdConstants.armorWEIRD, 4, 0);

		setIconCoord(4, 1);
		setTextureFile(WeirdConstants.pathTexturesIcons);
		setItemName("WeirdHelmet");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabCombat);
		
        logger.info("ArmorTextureFile " + WeirdConstants.pathTexturesArmor);
        logger.info("c'tor() complete");
    }
	
	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "WeirdHelmet");
		
		// Set the external name
		LanguageRegistry.addName(this, "Weird Helmet");

		// How to make a weird helmet
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdHelmet), "www", "wgw", "   ",
							'w', new ItemStack(WeirdMain.weirdPlating),
							'g', new ItemStack(Block.glass));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		long index = (Minecraft.getSystemTime() / 250) % 16;
		return WeirdConstants.pathTexturesArmorAnimated + index + ".png";
	}
}
