package pdunham.weirdBlock.common;

import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.material.Material;
import net.minecraftforge.client.MinecraftForgeClient;
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
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import pdunham.weirdBlock.client.WeirdBlockClientProxy;
import pdunham.weirdBlock.common.WeirdBlockCommonProxy;
import pdunham.weirdBlock.common.WeirdBlock;
import pdunham.weirdBlock.common.core.handlers.ClientPacketHandler;
import pdunham.weirdBlock.common.core.handlers.ServerPacketHandler;

@NetworkMod(clientSideRequired = true, serverSideRequired = false, // Whether client side and server side are needed
			clientPacketHandlerSpec = @SidedPacketHandler(channels = {"TutorialGeneral" }, 
			packetHandler = ClientPacketHandler.class), //For clientside packet handling
			serverPacketHandlerSpec = @SidedPacketHandler(channels = {}, 
			packetHandler = ServerPacketHandler.class)) //For serverside packet handling
@Mod(modid = "weirdBlock", name = "A Weird Block", version = "0.1.0")

public class WeirdBlockMain {
	
	@Instance("weirdBlock")
	public static WeirdBlockMain instance = new WeirdBlockMain();

	// Tell Forge where the proxies are.
	@SidedProxy(clientSide="pdunham.weirdBlock.client.WeirdBlockClientProxy", 
			    serverSide="pdunham.weirdBlock.common.WeirdBlockCommonProxy")
	
	public static WeirdBlockCommonProxy proxy;
	
	public static Block weirdBlock;
	
	public static Logger logger;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		logger = Logger.getLogger("weirdBlock");
		logger.setParent(FMLLog.getLogger());		
		logger.log(java.util.logging.Level.INFO, "preInit() complete");
	}

	@Init
	public void init(FMLInitializationEvent event) {
		logger.log(java.util.logging.Level.INFO, "init()");

		// Registers this class that deals with GUI data
		NetworkRegistry.instance().registerGuiHandler(this, proxy);
		
		// Create a block 1st parm is unique block id
		weirdBlock = (new WeirdBlock(3123));

		logger.log(java.util.logging.Level.INFO, "snowBlock.getTextureFile() returns " + Block.snow.getTextureFile());

		logger.log(java.util.logging.Level.INFO, "getTextureFile() returns " + weirdBlock.getTextureFile());

		// 
		proxy.registerBlocks();
		
		// Register the block w/ MineCraft
		GameRegistry.registerBlock(WeirdBlockMain.weirdBlock, "weirdBlock");

		// Add a human readable name
		LanguageRegistry.addName(WeirdBlockMain.weirdBlock , "A weird block");
		
		// Only iron and above pick axe can mine this block 
		MinecraftForge.setBlockHarvestLevel(weirdBlock, "pickaxe", 2);

		logger.log(java.util.logging.Level.INFO, "init() complete");
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {
		logger.log(java.util.logging.Level.INFO, "postInit() complete");
	}
}
