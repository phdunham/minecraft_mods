package pdunham.weird.weapons;

import cpw.mods.fml.client.registry.RenderingRegistry;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPebble extends EntityArrow {

	private StandardLogger logger = null;
	
	private void init() {
		canBePickedUp = 0;
		setKnockbackStrength(2);
		setDamage(0);
		setIsCritical(true);
		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
	    logger.info("C'tor() complete");
	}
	
    public EntityPebble(World par1World) {
       super(par1World);
       init();
    }
    
    public EntityPebble(World par1World, EntityLiving par2EntityLiving) {
    		super(par1World, par2EntityLiving, 72000);
    	       init();
    }
    
    public EntityPebble(World par1World, EntityLiving par2EntityLiving, float par3) {
       super(par1World, par2EntityLiving, par3);
       init();
    }
    
    public EntityPebble(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        init();
    }
    
    public EntityPebble(World par1World, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5) {
	    super(par1World, par2EntityLiving, par3EntityLiving, par4, par5);
	    init();
    }
    
    @Override
    // When true causes arrow to show particles in the air
    public boolean getIsCritical() {
    		return true;
    }

    @Override
    public void setDamage(double par1) {
    		super.setDamage(5.0D);
    }
}
