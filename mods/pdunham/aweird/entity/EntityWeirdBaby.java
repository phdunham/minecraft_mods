package pdunham.aweird.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;

public class EntityWeirdBaby extends EntityMob {
	private static StandardLogger logger = new StandardLogger();
	
	// Distance when the baby starts interacting w/ things
	private static float rangeOfInterest = 40.0f;
	private static float baseMoveSpeed = 0.25f;
	private static float daySpeedMultiplier = 1.0f;
	private static float nightSpeedMultiplier = 1.0f;
	private boolean isDay = true;
	private int trashTalkCounter = 0;
	protected static final String [] trashTalkMsgs = { 
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
	protected static final String [] calebTrashMsgs = { 
		"I'm scared of babies!",
		"Ooooh babies are soooo cute!",
	};
	
	
	public EntityWeirdBaby(World par1World) {
        super(par1World);
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

	@Override
	public String getTexture() {
		return (isDay ? WeirdConstants.pathTexturesBaby : WeirdConstants.pathTexturesBabyZombie);
	}
	
	// This is used on the client side to keep track of day vs night.
	private void updateDayNight() {
		// The .isDayTime() only works on the server, so on the client we need to actually check the time.
		isDay = ((this.worldObj.getWorldTime() % 24000) < 12500);
	}

	// Periodic updates to the entitity. 
	// Baby looks evil at night.
	// Baby is fast at night.
	public void onLivingUpdate() {
		updateDayNight();
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
		} else if (random > 6) {
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
        return (isDay ? "baby.baby" : "baby.babyZombie");
    }

    protected String getHurtSound() {
        return (isDay ? "baby.babyHurt" : "baby.babyZombieHurt");
    }

    protected String getDeathSound()  {
        return (isDay ? "baby.babyDeath" : "baby.babyZombieDeath");
    }

    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.zombie.step", 0.15F, 1.0F);
    }

    // Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a weirdBaby.
//    public boolean interact(EntityPlayer par1EntityPlayer) {
//    		logger.info("Interact with Baby,");
//        if (super.interact(par1EntityPlayer))
//        {
//            return true;
//        }
//        return false;
//    }

    public int getAttackStrength(Entity par1Entity) {
		int damage = isDay ? 1 : 4; // 4x damage at night
	
		// For Calebs, do a lot of damage.
//		if (isCaleb(par1Entity)) {
//			damage = 50;
//		}

		// Number of 1/2 hearts damage to do.
		return damage;
    }
    
	@Override
    public void handleHealthUpdate(byte par1) {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		logger.info("handleHealthUpdate thePlayer " + player);
		trashTalk(player);
		super.handleHealthUpdate(par1);
    }
    
	protected boolean isPersonOfInterest(EntityClientPlayerMP player) {
		if (isCaleb(player) ||
			(player.getEntityName().toLowerCase().indexOf("arn") >= 0) ||
			(player.getEntityName().toLowerCase().indexOf("dun") >= 0) ||
			(player.getEntityName().toLowerCase().indexOf("player") >= 0)) {
			return true;
		}
		return false;
	}

	protected boolean isCaleb(EntityClientPlayerMP player) {
		return (player.getEntityName().toLowerCase().indexOf("caleb") >= 0);
	}

	protected void damageDealtEvent(EntityClientPlayerMP player) {
		if (isPersonOfInterest(player)) {
		}
	}

	protected void trashTalk(EntityClientPlayerMP player) {
		// For certain players, send a message
		// Trash talk on the client side only.
		if (!isPersonOfInterest(player)) {
			return;
		}
		if ((trashTalkCounter % 10) == 0) {
			logger.info("Trash talk start cnt " + trashTalkCounter + ", " + player.getEntityName());
	
			// We need to be careful here because getClass().getName() returns "iq",
			// but we are hoping for an EntityClientPlayerMP
			if (isCaleb(player)) {
				player.addChatMessage(calebTrashMsgs[this.rand.nextInt(calebTrashMsgs.length)]);
			} else {
				player.addChatMessage(trashTalkMsgs[this.rand.nextInt(trashTalkMsgs.length)]);
			}
			logger.info("Trash talk happened " + player.getEntityName());
		}
		trashTalkCounter++;
		logger.info("trashTalk cnt++ " + trashTalkCounter + ", " + player.getEntityName());
	}
}
