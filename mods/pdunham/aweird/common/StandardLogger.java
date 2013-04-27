package pdunham.aweird.common;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class StandardLogger {
	private Logger logger = null;
	
	private StandardLogger(String name) {
        logger = Logger.getLogger(name);
        logger.setParent(FMLLog.getLogger());        
	}
	
	// A helper function to create the logger when you have multiple c'tors
	public static StandardLogger getLogger(StandardLogger callerLogger, String name) {
		if (callerLogger == null) {
			callerLogger = new StandardLogger(name);
		}
		return callerLogger;
	}

	public void info(String str) {
		if (logger != null) {
			logger.log(java.util.logging.Level.INFO, str);
		}
    }

	public void fine(String str) {
		if (logger != null) {
			logger.log(java.util.logging.Level.FINE, str);
		}
    }
	
	public void debug(String str) {
		fine(str);
	}

	public void finest(String str) {
		if (logger != null) {
			logger.log(java.util.logging.Level.FINEST, str);
		}
    }
	
}
