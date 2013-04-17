package pdunham.weird.weapons;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGrenade extends EntityThrowable {

	private StandardLogger logger = null;
	private int ticksAlive;
	private int ticksLifetime = 40; // only lives for this long (in ticks - 1/10 sec?)

	private void init() {
		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
	    logger.info("C'tor() complete");
	    ticksAlive = 0;
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
    
    private void explodes() {
        // createExplosion(Entity, posX, posY, posZ, radius, smokes on impact);
		// This causes all the damage
    		worldObj.createExplosion(this, posX, posY, posZ, 2.0f, true);

	    // Once it hits something, it disappears
	    if (!this.worldObj.isRemote) {
	        this.setDead();
	    }
    }

    // Called when the grenade hits something.
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
    		explodes();
    		
    		// If we hit an entity
    		if (par1MovingObjectPosition.entityHit != null) {
        		
        		// And that entity is a creeper
    			if (par1MovingObjectPosition.entityHit.getEntityName().toLowerCase().indexOf("creeper") >= 0) {
        			logger.info("Got '" + par1MovingObjectPosition.entityHit.getEntityName() + "'");

            		// And the throw is a real player
	    			EntityLiving el = getThrower();
	    			if (el instanceof EntityPlayer) {
	    				// Trigger Achievement
	    				((EntityPlayer)el).triggerAchievement(WeirdMain.weirdAchievementGrenadeCreeper);
	    			}
    			}
    		}
    }
 
    // Override the onUpdate() so we can measure the time of the grenades existence
    public void onUpdate() {
    		super.onUpdate();

    		// Have the grenade explode after a fixed amout of time.
    		if (ticksAlive++ >= ticksLifetime) {
    			explodes();
    		}
    	
    }
}
