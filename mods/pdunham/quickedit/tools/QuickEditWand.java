package pdunham.quickedit.tools;

import net.sf.cglib.proxy.*;

import java.util.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCloth;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import pdunham.quickedit.common.StandardLogger;
import pdunham.quickedit.common.QuickEditConstants;
import pdunham.quickedit.common.QuickEditMain;


public class QuickEditWand implements MethodInterceptor {

	private static StandardLogger logger = new StandardLogger();
	   
	private enum ClickState {
		waitingFirstClick,
		waitingSecondClick	
	};
	
	private ClickState clickState = ClickState.waitingFirstClick;
	private int x1, x2;
	private int y1, y2;
	private int z1, z2;
	
	static QuickEditWand callback = new QuickEditWand();

	private QuickEditWand() {
	}
	
	// There is where we instantiate the class that we are wrapping.
	public static Object newInstance(Class objClass, Class[] argTypes, Object[] args) {
		try {
			Enhancer e = new Enhancer();
			e.setSuperclass(objClass);
	        e.setCallback(callback);
	        return e.create(argTypes, args);
		} catch (Throwable e) {
			e.printStackTrace(); 
	       	throw new Error(e.getMessage());
	    }  
	}

	// This function is called everytime a method is called on the wrapped class.
    public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args, MethodProxy proxy) throws Throwable {
        
        // Check for the two function we want to override. Everything else we just pass to the original class
		Class<?> c = Class.forName("pdunham.quickedit.tools.QuickEditWand");
		String methodName = method.getName(); 
		// logger.info("intercepted " + methodName);
        if (methodName.equals("onItemUse")) {
            // logger.info("intercepted " + method);
        		Class[] argTypes =  {   ItemStack.class, 
				                    EntityPlayer.class, 
				                    World.class, 
				                    int.class, int.class, int.class, int.class,
				                    float.class, float.class, float.class };
        		Method  myMethod = c.getDeclaredMethod ("onItemUse", argTypes);
        		return myMethod.invoke (this, args);
        } else if (methodName.equals("getTextureFile")) {
            //logger.info("intercepted " + method);
        		Method  myMethod = c.getDeclaredMethod ("getTextureFile");
        		return myMethod.invoke (this);
        }

        // Fall through and call the original class.
        try {
        		return proxy.invokeSuper(obj, args);
        } catch (Throwable t) {
            logger.error("throw " + t) ;
            throw t.fillInStackTrace();
        }
    }
    
	private void rightClick(EntityPlayer player, World world, int x, int y, int z) {
		// Default is, not item in slot #9, so use delete mode.
		int currentBlockMetaData = 0;
		int currentBlockID = 0;
		String currentBlockName = "Delete mode";
		ItemStack itemStack = player.inventory.mainInventory[8];

		// If there is an item in slot #9, check to see if it is a block item. If not, return;
		if (itemStack != null) {
			Item item = itemStack.getItem();
			currentBlockID = itemStack.itemID;
			currentBlockName = itemStack.getDisplayName();
			currentBlockMetaData = itemStack.getItemDamageForDisplay();

			// Check the right most inventory bar item. If it is not a Block based item do nothing.
			if (!ItemBlock.class.isInstance(item)) {
				logger.info("rightClick slot #9 is not a block: " + currentBlockName);
				return;
			}
		}
		logger.info("rightClick slot #9 is " + currentBlockName + " meta is " + currentBlockMetaData);

		if (clickState == ClickState.waitingFirstClick) {
			x1 = x;
			y1 = y;
			z1 = z;
			Item.goldenCarrot.setIconCoord(1, 0);
	        clickState = ClickState.waitingSecondClick;
	        return;
		}
		
		if (clickState == ClickState.waitingSecondClick) {
			x2 = x;
			y2 = y;
			z2 = z;
			Item.goldenCarrot.setIconCoord(0, 0);
	        clickState = ClickState.waitingFirstClick;

	        
			// Setup a 3d for loop to populate the blocks
			int xStart = (x1 <= x2) ? x1 : x2;
			int xStop  = (x1 <= x2) ? x2 : x1;
			int yStart = (y1 <= y2) ? y1 : y2;
			int yStop  = (y1 <= y2) ? y2 : y1;
			int zStart = (z1 <= z2) ? z1 : z2;
			int zStop  = (z1 <= z2) ? z2 : z1;

			int blockCount = 0;
			logger.finest("Params " + x1 + ", " + z1 + " h=" + y1);
			logger.finest("Params " + x2 + ", " + z2 + " h=" + y2);
			
			for (int ix = xStart; ix <= xStop; ix++) {
				for (int iy = yStart; iy <= yStop; iy++) {
					for (int iz = zStart; iz <= zStop; iz++) {
						// logger.finest("rightClick Filling " + ix + ", " + iz + " h=" + iy + " id=" + currentBlockID + " meta=" + currentBlockMetaData);
						// World.addBlockEvent(X,Y,Z, BlockID, EventID, EventParameter)
						world.setBlockAndMetadata(ix, iy, iz, currentBlockID, currentBlockMetaData);
						blockCount++;
					}
				}
			}
			player.addChatMessage(blockCount + " blocks created");
	        return;
		}
		logger.error("rightClick invalid click state = " + clickState);
	}
	
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if (!world.isRemote) {
			logger.info("onItemUse location " + x + ", " + z + "  h=" + y + " player=" + player);
			rightClick(player, world, x, y, z);
		}
		return true;
	}
   
    public String getTextureFile(){
		return QuickEditConstants.pathTexturesIcons;
	}
}
