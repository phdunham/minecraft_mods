package pdunham.weird.weapons;

import pdunham.weird.common.StandardLogger;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityStrongGrenade extends EntityGrenade {
	
	protected void init() {
		super.init();
		explosionRadius = 4.0f;		
	}
	
	public EntityStrongGrenade(World par1World) {
        super(par1World);
        init();
	}
	
    public EntityStrongGrenade(World par1World, EntityLiving par2EntityLiving){
        super(par1World, par2EntityLiving);
        init();
    }   

    public EntityStrongGrenade(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        init();
    }	

}
