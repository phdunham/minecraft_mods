package pdunham.aweird.weapons;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pdunham.aweird.common.StandardLogger;
import pdunham.aweird.common.WeirdConstants;
import pdunham.aweird.common.WeirdMain;
import pdunham.aweird.entity.EntityGrenade;
import pdunham.aweird.entity.EntityStickyGrenade;
import pdunham.aweird.entity.EntityStrongGrenade;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class WeirdGrenade extends Item {

	private static StandardLogger logger = new StandardLogger();
	
	// Defaults are all for a basic weird grenade.
	private String internalName = "WeirdGrenade";
	private String externalName = "Weird grenade";
	private Object casing = null;
	private boolean sticky = false;
	private int textureX = 3;
	private int textureY = 1;
	private float explosionRadius = 2.0f;

	public WeirdGrenade(int id, 
						String internalNamePar, 
						String externalNamePar, 
						Object casingPar, 
						boolean stickyPar, 
						int textureXPar, 
						int textureYPar,
						float explosionRadiusPar) {
        super(id);
		internalName = internalNamePar;
		externalName = externalNamePar;
		casing = casingPar;
		sticky = stickyPar;
		textureX = textureXPar;
		textureY = textureYPar;
		explosionRadius = explosionRadiusPar;
		
        // Max Limit on stack size
        setMaxStackSize(64);
        
        // Put on the materials tab
        setCreativeTab(CreativeTabs.tabCombat);
        
        // Set the internal name
        setItemName(internalName);
        
        // Set the texture.
        setIconCoord(textureX, textureY);
        
        logger.info("c'tor() complete id: " + id + ", internal " + internalName + ", external " + externalName +
        				", sticky " + sticky + ", X " + textureX + ", Y " + textureY + ", radius " + explosionRadius);
	}

	public void postInit() {
		// Set the graphics texture from a .png file
		setTextureFile(getTextureFile());

		// Register the block w/ MineCraft
		GameRegistry.registerItem(this, internalName);

		// Set the external name
		LanguageRegistry.addName(this, externalName);

		// Recipe for make weirdGrenade
		if (getItemName() == WeirdMain.weirdStrongGrenade.getItemName()) {
			GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdStrongGrenade), " c ", "ipi", " i ",
					'c', new ItemStack((Item)casing), 
					'i', new ItemStack(Item.ingotIron),
					'p', new ItemStack(WeirdMain.weirdPowder));
		} else if (getItemName() == WeirdMain.weirdStickyGrenade.getItemName()) {
			GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdStickyGrenade), " c ", "ipi", " i ",
					'c', new ItemStack((Item)casing), 
					'i', new ItemStack(Item.ingotIron),
					'p', new ItemStack(WeirdMain.weirdPowder));
		} else {
			GameRegistry.addRecipe(new ItemStack(WeirdMain.weirdGrenade), " c ", "ipi", " i ",
					'c', new ItemStack((Item)casing), 
					'i', new ItemStack(Item.ingotIron),
					'p', new ItemStack(WeirdMain.weirdPowder));
		}

		logger.info("postInit() complete newId: " + itemID);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		
		if (par3EntityPlayer.capabilities.isCreativeMode || 
			par3EntityPlayer.inventory.hasItem(itemID)) {
			par3EntityPlayer.inventory.consumeInventoryItem(itemID);
		} else {
			return par1ItemStack;
		}
		
		// Play a sound
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		// Create the pebble in the client.
		if (!par2World.isRemote) {
			String name = getItemName();
			logger.info("Spawning entity for " + name);
			if (name.indexOf("WeirdStickyGrenade") >= 0) {
				par2World.spawnEntityInWorld(new EntityStickyGrenade(par2World, par3EntityPlayer));
			} else if (name.indexOf("WeirdStrongGrenade") >= 0) {
				par2World.spawnEntityInWorld(new EntityStrongGrenade(par2World, par3EntityPlayer));
			} else {
				par2World.spawnEntityInWorld(new EntityGrenade(par2World, par3EntityPlayer));
			}
		}
		return par1ItemStack;
	}
	
	@Override
	public String getTextureFile(){
		return WeirdConstants.pathTexturesIcons;
	}	
}
