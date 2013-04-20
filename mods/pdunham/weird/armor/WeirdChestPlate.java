package pdunham.weird.armor;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class WeirdChestPlate extends ItemArmor implements IArmorTextureProvider {

	private static StandardLogger logger;
	
	public WeirdChestPlate(int i) {
		// super(id, material, subset, chest=1);
		super(i, WeirdConstants.armorWEIRD, 4, 1);

		setIconCoord(5, 1);
		setTextureFile(WeirdConstants.pathTexturesIcons);
		setItemName("WeirdChestPlate");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabCombat);

		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("ArmorTextureFile " + WeirdConstants.pathTexturesArmor);
        logger.info("c'tor() complete");
    }

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "WeirdChestPlate");
		
		// Set the external name
		LanguageRegistry.addName(this, "Weird chest plate");

		// How to make a weird chest plate
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdChestPlate), "w w", "wdw", "www",
							'w', new ItemStack(WeirdMain.weirdPlating),
							'd', new ItemStack(Item.diamond));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack) {
		long index = (Minecraft.getSystemTime() / 250) % 16;
		return WeirdConstants.pathTexturesArmorAnimated + index + ".png";
	}
}
