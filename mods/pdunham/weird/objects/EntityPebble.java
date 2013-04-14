package pdunham.weird.objects;

import cpw.mods.fml.client.registry.RenderingRegistry;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPebble extends EntityThrowable {

	private StandardLogger logger = null;
	
    public EntityPebble(World par1World)
    {
       super(par1World);
       logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
       logger.info("C'tor1() complete");
    }
    
    public EntityPebble(World par1World, EntityLiving par2EntityLiving)
    {
       super(par1World, par2EntityLiving);
       logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
       logger.info("C'tor2() complete");
    }
    
    public EntityPebble(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("C'tor3() complete");
    }

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 0.1f, true);
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
        logger.info("onImpact()");
	}
}
