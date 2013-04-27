package pdunham.aweird.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.EventBus;
import pdunham.aweird.achievements.CraftingHandler;
import pdunham.aweird.achievements.WeirdAchievementBetterBoom;
import pdunham.aweird.achievements.WeirdAchievementGrenadeCreeper;
import pdunham.aweird.achievements.WeirdAchievementOre;
import pdunham.aweird.achievements.WeirdAchievementPowder;
import pdunham.aweird.achievements.WeirdAchievementStartingOff;
import pdunham.aweird.achievements.WeirdAchievementStickyToIt;
import pdunham.aweird.client.model.ModelWeirdBaby;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdCoProxy;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;
import pdunham.aweird.entity.EntityGrenade;
import pdunham.aweird.entity.EntityPebble;
import pdunham.aweird.entity.EntityStickyGrenade;
import pdunham.aweird.entity.EntityStrongGrenade;
import pdunham.aweird.entity.EntityWeirdBaby;
import pdunham.aweird.renderer.RenderGrenade;
import pdunham.aweird.renderer.RenderPebble;
import pdunham.aweird.renderer.RenderWeirdBaby;

@SideOnly(Side.CLIENT)
public class WeirdClProxy extends WeirdCoProxy {

	private static StandardLogger logger = new StandardLogger();
	
	public WeirdClProxy() {
		super();
		logger.info("c'tor() complete");
	}
	 
	@Override
	public void postInit() {
		super.postInit();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerSounds() {
		MinecraftForge.EVENT_BUS.register(new WeirdEventSounds());
		logger.info("registerSounds complete");
	}
	
    @Override
	public void registerTextures() {
    		// Preload our texture palettes so we have all of the icons and textures before we need them.
    		MinecraftForgeClient.preloadTexture(WeirdConstants.pathTexturesIcons);
    		MinecraftForgeClient.preloadTexture(WeirdConstants.pathTexturesArmor);
    		
		logger.info("registerTextures complete");
	}

    @Override
	public void registerRenderers(WeirdMain weirdMain) {
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
		EntityRegistry.registerModEntity(EntityPebble.class,	"Pebble", id	, weirdMain, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityPebble.class, new RenderPebble());

		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityGrenade.class, "WeirdGrenade", id);
		EntityRegistry.registerModEntity(EntityGrenade.class, "WeirdGrenade", id, weirdMain, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade(3, 1));
		
		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityStickyGrenade.class, "WeirdStickyGrenade", id);
		EntityRegistry.registerModEntity(EntityStickyGrenade.class, "WeirdStickyGrenade", id, weirdMain, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityStickyGrenade.class, new RenderGrenade(9, 1));

		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityStrongGrenade.class, "WeirdStrongGrenade", id);
		EntityRegistry.registerModEntity(EntityStrongGrenade.class, "WeirdStrongGrenade", id, weirdMain, 100, 10, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityStrongGrenade.class, new RenderGrenade(10, 1));

		id = ModLoader.getUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityWeirdBaby.class, "WeirdBaby", id);
		EntityRegistry.registerModEntity(EntityWeirdBaby.class, "WeirdBaby", id, weirdMain, 100, 10, false);
		RenderingRegistry.registerEntityRenderingHandler(EntityWeirdBaby.class, 
						new RenderWeirdBaby(new ModelWeirdBaby(), 
										    new ModelWeirdBaby(0.5F), 0.7F));
		EntityList.entityEggs.put(id, new EntityEggInfo(id, 0xffffff, 0x000000));
		
		EntityRegistry.addSpawn(EntityWeirdBaby.class, 20, 1, 2, 
				EnumCreatureType.monster, 
				BiomeGenBase.beach, 
				BiomeGenBase.desert,
//				BiomeGenBase.desertHills,
//				BiomeGenBase.extremeHills, 
//				BiomeGenBase.extremeHillsEdge, 
				BiomeGenBase.forest, 
//				BiomeGenBase.forestHills, 
//				BiomeGenBase.frozenOcean,
//				BiomeGenBase.frozenRiver,
				BiomeGenBase.hell,
//				BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains,
				BiomeGenBase.jungle, 
//				BiomeGenBase.jungleHills,
				BiomeGenBase.mushroomIsland, 
//				BiomeGenBase.mushroomIslandShore,
//				BiomeGenBase.ocean, 
				BiomeGenBase.plains, 
//				BiomeGenBase.river, 
				BiomeGenBase.swampland,
				BiomeGenBase.taiga
//				BiomeGenBase.taigaHills
				);		
		
		LanguageRegistry.instance().addStringLocalization("entity.WeirdBaby.name", "Baby");
		
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
		logger.info("registerAchievements complete");
	}
}
