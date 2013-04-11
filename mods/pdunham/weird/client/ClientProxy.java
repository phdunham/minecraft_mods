package pdunham.weird.client;

import net.minecraftforge.client.MinecraftForgeClient;
import pdunham.weird.common.CommonProxy;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.objects.WeirdBlock;

public class ClientProxy extends CommonProxy {

	private static StandardLogger logger;

	public void registerRenderInformation() {
		MinecraftForgeClient.preloadTexture(WeirdBlock.pathTexture);
		logger = new StandardLogger("weird.ClientProxy");
		logger.info("registerRenderInformation complete");
	}
}
