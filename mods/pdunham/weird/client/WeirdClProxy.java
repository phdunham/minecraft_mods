package pdunham.weird.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.MinecraftForgeClient;
import pdunham.weird.achievements.CraftingHandler;
import pdunham.weird.achievements.WeirdAchievementBetterBoom;
import pdunham.weird.achievements.WeirdAchievementGrenadeCreeper;
import pdunham.weird.achievements.WeirdAchievementOre;
import pdunham.weird.achievements.WeirdAchievementPowder;
import pdunham.weird.achievements.WeirdAchievementStartingOff;
import pdunham.weird.achievements.WeirdAchievementStickyToIt;
import pdunham.weird.client.model.ModelWeirdBaby;
import pdunham.weird.common.WeirdCoProxy;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import pdunham.weird.entity.EntityGrenade;
import pdunham.weird.entity.EntityPebble;
import pdunham.weird.entity.EntityStickyGrenade;
import pdunham.weird.entity.EntityStrongGrenade;
import pdunham.weird.entity.EntityWeirdBaby;
import pdunham.weird.renderer.RenderGrenade;
import pdunham.weird.renderer.RenderPebble;
import pdunham.weird.renderer.RenderWeirdBaby;

public class WeirdClProxy extends WeirdCoProxy {

	private static StandardLogger logger;
	
	public WeirdClProxy() {
		super();
		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("c'tor() complete");
	}
	
	@Override
	public void postInit() {
		super.postInit();
	}

    @Override
	public void registerTextures() {
    		// Preload our texture palettes so we have all of the icons and textures before we need them.
    		MinecraftForgeClient.preloadTexture(WeirdConstants.pathIcons);
    		MinecraftForgeClient.preloadTexture(WeirdConstants.pathArmor);
    		
    		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("registerTextures complete");
	}

    @Override
	public void registerRenderers(WeirdMain wm) {
    		// Get a unique entity id
    		// Register the entity id so it doesn't duplicate
		// Then register the mod entity type with FML
			// @param entityClass The entity class
			// @param entityName A unique name for the entity
			// @param id A mod specific ID for the entity
			// @param mod The 'this' for weird main.
			// @param trackingRange The range at which MC will send tracking updates
			// @param updateFrequency The frequency of tracking updates
			// @param sendsVelocityUpdates Whether to send velocity information packets as well
		// Lastly, map the Entity class to its associated Render class.
    		int id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityPebble.class, "Pebble", id);
		EntityRegistry.registerModEntity(EntityPebble.class,	"Pebble", id	, wm, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityPebble.class, new RenderPebble());

		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityGrenade.class, "WeirdGrenade", id);
		EntityRegistry.registerModEntity(EntityGrenade.class, "WeirdGrenade", id, wm, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade(3, 1));
		
		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityStickyGrenade.class, "WeirdStickyGrenade", id);
		EntityRegistry.registerModEntity(EntityStickyGrenade.class, "WeirdStickyGrenade", id, wm, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityStickyGrenade.class, new RenderGrenade(9, 1));

		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityStrongGrenade.class, "WeirdStrongGrenade", id);
		EntityRegistry.registerModEntity(EntityStrongGrenade.class, "WeirdStrongGrenade", id, wm, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityStrongGrenade.class, new RenderGrenade(10, 1));

		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityWeirdBaby.class, "WeirdBaby", id);
		EntityRegistry.registerModEntity(EntityWeirdBaby.class, "WeirdBaby", id, wm, 100, 10, false);
		RenderingRegistry.registerEntityRenderingHandler(EntityWeirdBaby.class, 
						new RenderWeirdBaby(new ModelWeirdBaby(), 
										    new ModelWeirdBaby(0.5F), 0.7F));
		
		EntityRegistry.addSpawn(EntityWeirdBaby.class, 10, 2, 4, 
				EnumCreatureType.monster, 
				BiomeGenBase.beach, 
				BiomeGenBase.extremeHills, 
				BiomeGenBase.extremeHillsEdge, 
				BiomeGenBase.forest, 
				BiomeGenBase.forestHills, 
				BiomeGenBase.jungle, 
				BiomeGenBase.jungleHills, 
				BiomeGenBase.mushroomIsland, 
				BiomeGenBase.mushroomIslandShore, 
				BiomeGenBase.ocean, 
				BiomeGenBase.plains, 
				BiomeGenBase.river, 
				BiomeGenBase.swampland);		
		
		LanguageRegistry.instance().addStringLocalization("entity.weird.WeirdBaby.name", "Baby");
		
		logger.info("registerRenderers complete");
	}

    @Override
	public void registerAchievements() {
    		// Order matters. You must have the dependent Achievements registered first.
    		WeirdMain.weirdAchievementOre = new WeirdAchievementOre();
    		WeirdMain.weirdAchievementStartingOff = new WeirdAchievementStartingOff();
    		WeirdMain.weirdAchievementPowder = new WeirdAchievementPowder();
    		WeirdMain.weirdAchievementGrenadeCreeper = new WeirdAchievementGrenadeCreeper();
    		WeirdMain.weirdAchievementBetterBoom = new WeirdAchievementBetterBoom();
    		WeirdMain.weirdAchievementStickyToIt = new WeirdAchievementStickyToIt();
    		
    		// The Crafting handler let's us hook crafting and smelting to trigger achievements
    		WeirdMain.craftingHandler = new CraftingHandler();
    		GameRegistry.registerCraftingHandler(WeirdMain.craftingHandler);    		
    		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("registerAchievements complete");
	}
}
