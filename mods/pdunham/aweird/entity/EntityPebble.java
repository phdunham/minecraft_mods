package pdunham.aweird.entity;

import cpw.mods.fml.client.registry.RenderingRegistry;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdMain;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPebble extends EntityThrowable {

	private static StandardLogger logger = new StandardLogger();
	
	private void init() {
	}
	
	public EntityPebble(World par1World) {
        super(par1World);
        init();
    }

    public EntityPebble(World par1World, EntityLiving par2EntityLiving){
        super(par1World, par2EntityLiving);
        init();
    }

    public EntityPebble(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        init();
    }

    // Called when the pebble hits something.
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
    		// If we hit something, do it damage.
        if (par1MovingObjectPosition.entityHit != null) {
            byte damageDone = 3;
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damageDone);
        }

        // Once it hits something, it disappears
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
    protected float getGravityVelocity() {
    		// Pebbles are barely effected by gravity
        return 0.003F;
    }
}