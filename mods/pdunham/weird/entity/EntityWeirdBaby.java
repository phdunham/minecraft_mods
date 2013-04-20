package pdunham.weird.entity;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWeirdBaby extends EntityMob {
	private static StandardLogger logger;
	
	// Distance when the baby starts interacting w/ things
	private static float rangeOfInterest = 20.0f;
	private static float baseMoveSpeed = 0.25f;

	public EntityWeirdBaby(World par1World) {
        super(par1World);
        this.texture = WeirdConstants.pathBaby;
        this.setSize(0.9F, 0.9F);
        this.getNavigator().setAvoidsWater(true);
        this.moveSpeed = baseMoveSpeed;
        int taskPriority = 0;
        int targetPriority = 0;

        // Highest priority first. Not drowning is a good top priority
        this.tasks.addTask(taskPriority++, new EntityAISwimming(this));
        this.tasks.addTask(taskPriority++, new EntityAIBreakDoor(this));
        this.tasks.addTask(taskPriority++, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
        this.tasks.addTask(taskPriority++, new EntityAIWatchClosest(this, EntityPlayer.class, this.rangeOfInterest));
        this.tasks.addTask(taskPriority++, new EntityAIWander(this, this.moveSpeed));
        this.tasks.addTask(taskPriority++, new EntityAILookIdle(this));
        
        // If the baby is attacked, fight back
        this.targetTasks.addTask(targetPriority++, new EntityAIHurtByTarget(this, false));

        // Make the baby aggressive
        this.targetTasks.addTask(targetPriority++, new EntityAINearestAttackableTarget(this, EntityPlayer.class, this.rangeOfInterest, 0, true));        

        // Move toward any target we find
        this.targetTasks.addTask(targetPriority++, new EntityAIMoveTowardsTarget(this, this.moveSpeed, this.rangeOfInterest));

        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
//    		logger.info("c'tor complete");
    }
	
	protected boolean isAIEnabled() {
        return true;
    }
	
	public int getMaxHealth() {
		return 20;
	}
	
	public int getTotalArmorValue() {
		// Each value is 1/2 an armor bar. So 20 is max armor.
        return 2;
    }
	
	// Check the sunlight on update. Babies move faster at night.
	public float getSpeedModifier() {
        float modifier = 3.0f;

		// If it is light, move slower
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float brightness = this.getBrightness(1.0F);
            boolean sky = this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
            if (brightness > 0.5F && sky) { 
            	modifier = 1.0f;
            }
        }
        modifier *= super.getSpeedModifier();
        logger.info("modifier " + modifier);
        return modifier;
    }
	
	public EnumCreatureAttribute getCreatureAttribute() {
		// Sets up potion effects to be like zombies
        return EnumCreatureAttribute.UNDEAD;
    }
	
	public int getAttackStrength(Entity par1Entity) {
		// Number of 1/2 hearts damage to do.
		return 4;
    }

	protected void dropRareDrop(int par1) {
        switch (this.rand.nextInt(2)) {
            case 0:
                this.dropItem(WeirdMain.weirdIngot.itemID, 1);
                break;
            case 1:
                this.dropItem(WeirdMain.weirdHelmet.itemID, 1);
                break;
        }
    }	
	
	protected void dropFewItems(boolean par1, int par2) {
		if(this.rand.nextInt(3) == 0) {
			this.dropItem(Item.bed.itemID, 1);
		}
	}
	
    protected void updateAITasks() {
        super.updateAITasks();
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    protected String getLivingSound() {
        return "mob.zombie.say";
    }

    protected String getHurtSound() {
        return "mob.zombie.say";
    }

    protected String getDeathSound()  {
        return "mob.zombie.death";
    }

    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.zombie.step", 0.15F, 1.0F);
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a weirdBaby.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        if (super.interact(par1EntityPlayer))
        {
            return true;
        }
        return false;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return this.isBurning() ? Item.porkCooked.itemID : Item.porkRaw.itemID;
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt)
    {
//        if (!this.worldObj.isRemote)
//        {
//            EntityWeirdBabyZombie var2 = new EntityWeirdBabyZombie(this.worldObj);
//            var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
//            this.worldObj.spawnEntityInWorld(var2);
//            this.setDead();
//        }
    }
}
