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
import pdunham.weird.weapons.EntityPebble;
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
	    ((RenderPebble) WeirdMain.renderPebble).postInit();
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
	public void registerRenderers() {
    		// Globally Register the EntityPebble so we render it in the air 
      	WeirdMain.renderPebble = new RenderPebble();
    		EntityRegistry.registerGlobalEntityID(EntityPebble.class, "Pebble", ModLoader.getUniqueEntityId());
    		logger.info("registerGlobalEntityID " + EntityPebble.class + ", \"Pebble\", " + ModLoader.getUniqueEntityId());

    		// register the render class for EntityPebble which renders Pebble.
    		RenderingRegistry.registerEntityRenderingHandler(EntityPebble.class, WeirdMain.renderPebble);
    		logger.info("registerEntityRenderingHandler " + EntityPebble.class + ", " + WeirdMain.renderPebble);
    		
    		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("registerRenderers complete");
	}

    @Override
	public void registerAchievements() {
    		WeirdMain.weirdAchievementOre = new WeirdAchievementOre();
    		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("registerAchievements complete");
	}
}
