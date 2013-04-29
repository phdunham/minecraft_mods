package pdunham.aweird.common;

import java.util.Random;


import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WeirdWorldGenerator implements IWorldGenerator{

	private static StandardLogger logger = new StandardLogger();

	public WeirdWorldGenerator() {
        logger.info("c'tor() complete");
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.dimensionId){
		case -1:
		    generate("Nether", world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 0:
		    generate("Surface", world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 1:
//		    generate("End", world, random, chunkX * 16, chunkZ * 16);
		    break;
		}		
	}

	private void generate(String name, World world, Random random, int chunkX, int chunkZ) {
        for(int k = 0; k < 2; k++) {
	        	int x = chunkX + random.nextInt(16);
	        	int y = random.nextInt(20);
	        	int z = chunkZ + random.nextInt(16);
	        	int id = WeirdMain.weirdOre.blockID;

	        	logger.finest("generateMinable(" + name + ", x " + x + ", y " + y + ", z " + z + ", BlockID " + id);

	        	WorldGenMinable wgm = new WorldGenMinable(id, random.nextInt(8) + 4); 
	        	wgm.generate(world, random, x, y, z);
        }
    
	}
}
