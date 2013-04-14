package pdunham.weird.client;

import net.minecraftforge.client.MinecraftForgeClient;
import pdunham.weird.common.CommonProxy;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import pdunham.weird.objects.WeirdOre;

public class ClientProxy extends CommonProxy {

	private static StandardLogger logger;

    @Override
	public void registerTextures() {
		MinecraftForgeClient.preloadTexture(WeirdMain.pathTexture);
		logger = new StandardLogger("weird.ClientProxy");
		logger.info("registerRenderInformation complete");
	}
}
