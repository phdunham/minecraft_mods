package pdunham.weird.objects;

import java.util.logging.Logger;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;

public class WeirdIngot extends Item {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdIngot(int id) {
        super(id);
     
        setMaxStackSize(29);
        setCreativeTab(CreativeTabs.tabMaterials);
        setItemName("weirdIngot");

        logger = new StandardLogger("weirdIngot");
        logger.info("c'tor() complete");
	}

	public void postInit() {
		LanguageRegistry.addName(this, "A weird ingot");
		logger.info("postInit() complete");
	}
}
