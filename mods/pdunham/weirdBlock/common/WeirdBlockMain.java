package pdunham.weirdBlock.common;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import pdunham.weirdBlock.client.WeirdBlockClientProxy;
import pdunham.weirdBlock.common.WeirdBlockCommonProxy;
import pdunham.weirdBlock.common.BlockFirst;

@Mod(modid = "weirdBlock", name = "A Weird Block", version = "0.1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class WeirdBlockMain {
	
	@Instance("weirdBlock")
	public static WeirdBlockMain instance;

	@SidedProxy(clientSide="pdunham.weirdBlock.client.WeirdBlockClientProxy", serverSide="pdunham.weirdBlock.common.WeirdBlockCommonProxy")
	public static WeirdBlockCommonProxy proxy;
	
	public static Block weirdBlock;
	
	public static Logger logger;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		logger = Logger.getLogger("weirdBlock");
		logger.setParent(FMLLog.getLogger());		
	}

	@Init
	public void init(FMLInitializationEvent event) {
		// Create a block
		weirdBlock = (new BlockFirst(3123, Material.iron));
		
		// Set the internal reference name
		weirdBlock.setUnlocalizedName("weirdBlock");

		// Add a human read
		LanguageRegistry.addName(weirdBlock , "A weird block");

		// Only iron and above pick axe can mine this block 
		MinecraftForge.setBlockHarvestLevel(weirdBlock, "pickaxe", 2);

		// Register the block w/ minecraft
		GameRegistry.registerBlock(weirdBlock, "weirdBlock");
		
		logger.log(java.util.logging.Level.INFO, System.getProperty("user.dir"));
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {
		
	}
}
