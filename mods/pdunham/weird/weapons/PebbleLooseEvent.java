package pdunham.weird.weapons;

import pdunham.weird.common.StandardLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Cancelable;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class PebbleLooseEvent extends PlayerEvent
{
	private static StandardLogger logger = new StandardLogger();

	public final ItemStack sling;
    public int charge;
    
    public PebbleLooseEvent(EntityPlayer player, ItemStack sling, int charge)
    {
        super(player);
        this.sling= sling;
        this.charge = charge;

        logger.info("c'tor() complete player: " + player + ", sling " + sling + ", charge " + charge);
    }
}
