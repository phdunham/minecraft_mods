package pdunham.weird.common;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class StandardLogger {
	private Logger logger = null;
	private String side;
	
	public StandardLogger() {
	}
	
	private boolean init() {
		try {
			side = FMLCommonHandler.instance().getEffectiveSide().toString();
			if (logger == null) {
				// Go up 3 on the stack to get the name of the class calling StandardLogger
				String name = new Exception().getStackTrace()[2].getClassName();
				name = name.substring(name.lastIndexOf('.')+1);
		        logger = Logger.getLogger(name);
		        logger.setParent(FMLLog.getLogger());
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private String decorate(String str) {
		return "(" + side + ") " + str;
	}
	
	public void info(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.WARNING, decorate(str));
		}
    }

	public void fine(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.WARNING, decorate(str));
		}
    }
	
	public void warn(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.WARNING, decorate(str));
		}
	}

	public void error(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.WARNING, decorate(str));
		}
	}

	public void finest(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.WARNING, decorate(str));
		}
    }
}
