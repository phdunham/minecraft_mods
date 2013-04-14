package pdunham.weird.objects;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;

public class WeirdSlingShot extends ItemBow {

	private static StandardLogger logger;

 	// Standard c'tor
	public WeirdSlingShot(int id) {
        super(id);

        // Limit the stack size to a weird number
        setMaxStackSize(2);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabCombat);
        
        // Set the internal name
        setItemName("weirdSlingShot");
        
        // Set the texture.
        setIconCoord(9, 0);
        
        logger = new StandardLogger("weirdSlingShot");
        logger.info("c'tor() complete id: " + id);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, "weirdSlingShot");
		// Set the external name
		LanguageRegistry.addName(this, "Weird sling shot");

		// A weird slingShot is 3 weird ingots and 2 iron ingots
		GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdSlingShot), "wsw", "k k", " i ",
							'w', new ItemStack(WeirdMain.weirdIngot),
							'i', new ItemStack(Item.ingotIron),
							's', new ItemStack(Item.silk),
							'k', new ItemStack(Item.stick));
		
		logger.info("postInit() complete newId: " + itemID);
	}

	// Return true if the slingShot can harvest the block type.
	@Override
	public boolean canHarvestBlock(Block par1Block)
    {
        return true;
    }

     // Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if sword
	@Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return super.getStrVsBlock(par1ItemStack, par2Block);
    }

    // Called when the user releases the ammunition (code copied from base class)
    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
    		logger.info("PlayerStoppedUsing(ItemStack " + par1ItemStack + 
    									 ", World " + par2World + 
    									 ", EntityPlayer " + par3EntityPlayer + 
    									 ", int " + par4 + ") arrowId " + Item.arrow.itemID + ", BowId " + Item.bow.itemID);
        int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;

        PebbleLooseEvent event = new PebbleLooseEvent(par3EntityPlayer, par1ItemStack, var6);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        var6 = event.charge;

        boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || 
        				  EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (var5 || par3EntityPlayer.inventory.hasItem(WeirdMain.pebble.itemID))
        {
            float var7 = (float)var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if ((double)var7 < 0.1D)
            {
                return;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            EntityPebble var8 = new EntityPebble(par2World, par3EntityPlayer, var7 * 2.0F);

            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
            if (var9 > 0)
            {
                // var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
                   var8.setDamage(var8.getDamage() + (double)var9 * 0.75D + 0.5D);
            } else {
            		// 1.5 x normal arrow damage
                var8.setDamage(var8.getDamage() * 1.5D);
            }

            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (var10 > 0)
            {
                var8.setKnockbackStrength(var10);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
                var8.setFire(100);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            if (var5)
            {
                var8.canBePickedUp = 2;
            }
            else
            {
                par3EntityPlayer.inventory.consumeInventoryItem(WeirdMain.pebble.itemID);
            }

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(var8);
            }
        }
    }

    // Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
    //  (code copied from base class)
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
	   logger.info("onItemRightClick(ItemStack " + par1ItemStack + ", World " + par2World + ", EntityPlayer " + par3EntityPlayer + ")");
	   PebbleNockEvent event = new PebbleNockEvent(par3EntityPlayer, par1ItemStack);
       MinecraftForge.EVENT_BUS.post(event);
       if (event.isCanceled())
       {
           return event.result;
       }

       if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(WeirdMain.pebble.itemID))
       {
           par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
       }

       return par1ItemStack;
   }

	@Override
   public int getItemEnchantability()
   {
       return 11;
   }
 
	@Override
	public String getTextureFile(){
		return WeirdMain.pathTexture;
	}	
}
