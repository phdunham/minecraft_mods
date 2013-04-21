package pdunham.weird.entity;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import pdunham.weird.common.WeirdMain;
import net.minecraft.client.entity.EntityPlayerSP;
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
import net.minecraft.src.ModLoader;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWeirdBaby extends EntityMob {
	private static StandardLogger logger;
	
	// Distance when the baby starts interacting w/ things
	private static float rangeOfInterest = 40.0f;
	private static float baseMoveSpeed = 0.25f;
	private static float daySpeedMultiplier = 1.0f;
	private static float nightSpeedMultiplier = 1.0f;
	private static boolean isDay = false;  // not sure why this only works as static.
	private boolean inDaylight = false;
	private int calebCounter = 0;
	
	public EntityWeirdBaby(World par1World) {
        super(par1World);
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        this.texture = getTexture();
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
    }
	
	protected boolean isAIEnabled() {
        return true;
    }
	
	public int getMaxHealth() {
		return 20;
	}
	
	public int getTotalArmorValue() {
		// Each value is 1/2 an armor bar. So 20 is max armor.
        return 8;
    }
	private boolean isDaytime() {
		if (this.worldObj.isRemote) {
			logger.info("isDaytime can only be called when !this.worldObj.isRemote");
		}
		// Only works when !this.worldObj.isRemote is true
		return this.worldObj.isDaytime();
	}
	
	private boolean isInSunlight() {
		boolean isBrightEnough = this.getBrightness(1.0F) > 0.5F;
        boolean isSkyVisible = this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
        
		// Return true is it is day, we can see the sky and the sun is bright enough.
        return (isDaytime() && isSkyVisible && isBrightEnough);
	}
	
	@Override
	public String getTexture() {
		return (isDay ? WeirdConstants.pathTexturesBaby : WeirdConstants.pathTexturesBabyZombie);
	}

	// Periodic updates to the entitity. 
	// Baby looks evil at night.
	// Bady is fast at night.
	public void onLivingUpdate() {
		if (!this.worldObj.isRemote) {
			isDay = isDaytime();
			inDaylight = isInSunlight();
		}
		super.onLivingUpdate();
    }
	
	// Check the sunlight on update. Babies move faster at night.
	public float getSpeedModifier() {
		return ((isDay ? daySpeedMultiplier : nightSpeedMultiplier) * super.getSpeedModifier());
    }

	public EnumCreatureAttribute getCreatureAttribute() {
		// Sets up potion effects to be like zombies
        return EnumCreatureAttribute.UNDEAD;
    }
	
	public int getAttackStrength(Entity par1Entity) {
		// For certain players, send a message
		if (((calebCounter++ % 20) == 0) &&
			(par1Entity.getEntityName().toLowerCase().indexOf("caleb") >= 0) ||
			(par1Entity.getEntityName().toLowerCase().indexOf("arntemp") >= 0) ||
			(par1Entity.getEntityName().toLowerCase().indexOf("pdunham") >= 0)) {
			String [] msgs = { 
					"I'm scared of babies!",
					"Ooooh babies are soooo cute!",
					"This is *not* a cute baby!",
					"Babies are killing!",
					"Seriously, babies?",
					"I don't like babies!",
					"Please stop baby. I will be nice to you.",
					"Look baby, here is a nice cookie.",
					"Are you serious, you don't know? Man, everyone knows babies never go full retard.",
					"Babies hate me!",
					"Does baby want burpies?",
					"Hey look! A baby.",
					"Baby, what are you doing? No baby! Bad baby! Aaaaaahhhhhhh....",
					"Why baby? Why?",							
					"Look out!! Babies!!!",
					"I have had it with these m#$@^#f^#*$in' babies on this m#$@^#f^#*$in' plane!",
					"When I am older I want lots of babies."
			};
			ModLoader.getMinecraftInstance().thePlayer.addChatMessage(msgs[this.rand.nextInt(msgs.length)]);
			
			// For Calebs, do a lot of damage.
			if (par1Entity.getEntityName().toLowerCase().indexOf("caleb") >= 0) {
				return 50;
			}
		}

		// Number of 1/2 hearts damage to do.
		return 3;
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
		int random = this.rand.nextInt(10); 
		if (random == 0) {
			this.dropItem(Item.bed.itemID, 1);
		} else if (random > 7) {
			this.dropItem(WeirdMain.weirdPoop.itemID, 1);
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
        return (isDay ? "baby" : "babyZombie");
    }

    protected String getHurtSound() {
        return (isDay ? "babyHurt" : "babyZombieHurt");
    }

    protected String getDeathSound()  {
        return (isDay ? "babyDeath" : "babyZombieDeath");
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
