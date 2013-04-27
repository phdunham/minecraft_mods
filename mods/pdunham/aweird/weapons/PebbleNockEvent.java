package pdunham.aweird.weapons;
import pdunham.aweird.common.StandardLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Cancelable;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class PebbleNockEvent extends PlayerEvent
{
    public ItemStack result;
	private static StandardLogger logger;
    
    public PebbleNockEvent(EntityPlayer player, ItemStack result)
    {
        super(player);
        this.result = result;

        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        logger.info("c'tor() complete player: " + player + ", result " + result);
    }
}
