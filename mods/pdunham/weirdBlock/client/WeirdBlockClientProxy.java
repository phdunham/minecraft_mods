package pdunham.weirdBlock.client;

import net.minecraftforge.client.MinecraftForgeClient;
import pdunham.weirdBlock.common.WeirdBlock;
import pdunham.weirdBlock.common.WeirdBlockCommonProxy;

public class WeirdBlockClientProxy extends WeirdBlockCommonProxy {

	public void registerRenderInformation() {
		MinecraftForgeClient.preloadTexture(WeirdBlock.pathTexture);
	}
}
