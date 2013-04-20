package pdunham.weird.client.model;

import pdunham.weird.common.StandardLogger;
import net.minecraft.client.model.ModelQuadruped;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWeirdBaby extends ModelQuadruped
{
	private static StandardLogger logger;

	public ModelWeirdBaby()
    {
        this(0.0F);
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
    		logger.info("c'tor() complete");
    }

    public ModelWeirdBaby(float par1)
    {
        super(6, par1);
        this.head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, par1);
        this.field_78145_g = 4.0F;
    }
}
