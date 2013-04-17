package pdunham.weird.weapons;

import pdunham.weird.common.StandardLogger;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGrenade extends EntityThrowable {

	private StandardLogger logger = null;

	private void init() {
		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
	    logger.info("C'tor() complete");
	}
	
	public EntityGrenade(World par1World) {
        super(par1World);
        init();
    }

    public EntityGrenade(World par1World, EntityLiving par2EntityLiving){
        super(par1World, par2EntityLiving);
        init();
    }

    public EntityGrenade(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        init();
    }

    // Called when the grenade hits something.
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        // createExplosion(Entity, posX, posY, posZ, radius, smokes on impact);
    		// This causes all the damage
        worldObj.createExplosion(this, posX, posY, posZ, 3.0f, true);

        // Once it hits something, it disappears
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
