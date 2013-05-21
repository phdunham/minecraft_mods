package pdunham.weird.commands;

import java.util.Arrays;
import java.util.List;

import pdunham.weird.common.StandardLogger;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandDrill extends CommandBase {
	private static StandardLogger logger = new StandardLogger();
	private String drillMode = "worm";
	private String validShapes [] = { "cone", "cube", "hall", "tube", "worm" };
	
	public String getDrillMode() {
		return drillMode;
	}

	public String getCommandName() {
        return "drill";
    }

    // Return the required permission level for this command.
    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getCommandUsage(ICommandSender par1ICommandSender) {
        return par1ICommandSender.translateString("commands.drill.usage", new Object[0]);
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    		logger.info(" processCommand " + par1ICommandSender + " : " + par2ArrayOfStr);
    		
    		// The drill offers a variety of shapes for the user to chose from. Store the
    		// shape selected in the drillMode variable.
        if (par2ArrayOfStr.length == 1) {
        		String arg = par2ArrayOfStr[0].toLowerCase();

        		if (Arrays.asList(validShapes).indexOf(arg) >= 0) {
        			drillMode = arg;
                notifyAdmins(par1ICommandSender, "commands.drill." + arg, new Object[] { arg });
                return;
        		}
        }
        throw new WrongUsageException("commands.drill.usage", new Object[0]);
    }

    // Adds the strings available in this command to the given list of tab completion options.
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return getListOfStringsMatchingLastWord(par2ArrayOfStr, validShapes);
    }
}
