package pdunham.aweird.common;

import java.util.Random;


import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WeirdWorldGenerator implements IWorldGenerator{

	private static StandardLogger logger;

	public WeirdWorldGenerator() {
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("c'tor() complete");
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.dimensionId){
		case -1:
		    generateNether(world, random, chunkX * 16, chunkZ * 16);
		    generateSurface(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 0:
		    generateSurface(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 1:
		    generateEnd(world, random, chunkX * 16, chunkZ * 16);
		    break;
		}		
	}

    private void generateEnd(World world, Random random, int i, int j) {
    	
    }

	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
        for(int k = 0; k < 2; k++) {
	        	int x = chunkX + random.nextInt(16);
	        	int y = random.nextInt(20);
	        	int z = chunkZ + random.nextInt(16);
	        	int id = WeirdMain.weirdOre.blockID;

	        	logger.finest("generateMinable(x " + x + ", y " + y + ", z " + z + ", BlockID " + id);

	        	WorldGenMinable wgm = new WorldGenMinable(id, random.nextInt(8) + 4); 
	        	wgm.generate(world, random, x, y, z);
        }
    
	}

	private void generateNether(World world, Random random, int i, int j) {
		
	}     

}
