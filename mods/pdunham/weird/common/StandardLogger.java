package pdunham.weird.common;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class StandardLogger {
	private Logger logger = null;

	public void info(String str) {
		if (logger != null) {
			logger.log(java.util.logging.Level.INFO, str);
		}
    }

	public void debug(String str) {
		if (logger != null) {
			logger.log(java.util.logging.Level.FINE, str);
		}
    }

	public void fine(String str) {
		if (logger != null) {
			logger.log(java.util.logging.Level.FINEST, str);
		}
    }
	
	public StandardLogger(String name) {
        logger = Logger.getLogger(name);
        logger.setParent(FMLLog.getLogger());        
        info("Logger created");		
	}
}
