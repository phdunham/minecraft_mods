package pdunham.weird.objects;

import java.util.logging.Logger;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;

public class WeirdOre extends Item  {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdOre(int id) {
        super(id);
     
        setMaxStackSize(31);
        setCreativeTab(CreativeTabs.tabMaterials);
        setItemName("weirdOre");
        logger = new StandardLogger("weirdOre");
        logger.info("c'tor() complete");
	}
    
	public void postInit() {
		LanguageRegistry.addName(this, "A weird ore");
		logger.info("postInit() complete");
	}
}
