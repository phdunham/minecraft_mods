package pdunham.aweird.entity;

import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdMain;
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

	private static StandardLogger logger = new StandardLogger();

	private int ticksAlive;
	private int ticksLifetime = 100; // only lives for this long (in ticks - 1/10 sec?)
	protected float explosionRadius = 2.0f;
	protected boolean sticky = false;
	
	protected void init() {
	    ticksAlive = 0;
	}
	
	public EntityGrenade(World par1World) {
        super(par1World);
        init();
	    logger.info("C'tor(1) complete");
    }

    public EntityGrenade(World par1World, EntityLiving par2EntityLiving){
        super(par1World, par2EntityLiving);
        init();
        logger.info("C'to(2) complete sticky " + sticky + ", radius " + explosionRadius);
    }   

    public EntityGrenade(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        init();
        logger.info("C'to(3) complete");
    }
    
    private void explodes() {
        // createExplosion(Entity, posX, posY, posZ, radius, smokes on impact);
		// This causes all the damage
    		worldObj.createExplosion(this, posX, posY, posZ, explosionRadius, true);

	    // Once it hits something, it disappears
	    if (!this.worldObj.isRemote) {
	        this.setDead();
	    }
    }

    // Called when the grenade hits something.
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
    		// Sticky grenades don't explode on impact. They stick and explode by timer.
    		logger.info("onImpact sticky " + sticky + ", inGround " + inGround + ", radius " + explosionRadius);
    		if (!sticky) {
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
    		} else {
    	        inGround = true;
    			EntityLiving el = getThrower();
    			if (el instanceof EntityPlayer) {
    				// Trigger Achievement
    				((EntityPlayer)el).triggerAchievement(WeirdMain.weirdAchievementStickyToIt);
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
