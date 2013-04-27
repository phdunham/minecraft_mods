package pdunham.aweird.entity;

import pdunham.aweird.common.StandardLogger;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityStickyGrenade extends EntityGrenade {
	
	protected void init() {
		super.init();
		sticky = true;		
	}
	
	public EntityStickyGrenade(World par1World) {
        super(par1World);
        init();
	}
	
    public EntityStickyGrenade(World par1World, EntityLiving par2EntityLiving){
        super(par1World, par2EntityLiving);
        init();
    }   

    public EntityStickyGrenade(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        init();
    }	

}
