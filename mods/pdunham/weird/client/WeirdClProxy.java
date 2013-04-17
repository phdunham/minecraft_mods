package pdunham.weird.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import pdunham.weird.achievements.WeirdAchievementOre;
import pdunham.weird.common.WeirdCoProxy;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import pdunham.weird.weapons.EntityGrenade;
import pdunham.weird.weapons.EntityPebble;
import pdunham.weird.weapons.RenderGrenade;
import pdunham.weird.weapons.RenderPebble;

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
    		EntityRegistry.registerGlobalEntityID(EntityGrenade.class, "WeirdGrenade", id);
		EntityRegistry.registerModEntity(EntityGrenade.class, "WeirdGrenade", id, wm, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade());
		
		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityPebble.class, "Pebble", id);
		EntityRegistry.registerModEntity(EntityPebble.class,	"Pebble", id	, wm, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityPebble.class, new RenderPebble());
    		
		logger.info("registerRenderers complete");
	}

    @Override
	public void registerAchievements() {
    		WeirdMain.weirdAchievementOre = new WeirdAchievementOre();
    		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("registerAchievements complete");
	}
}
