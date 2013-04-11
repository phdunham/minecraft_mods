package pdunham.weird.common;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class StandardLogger {
	private static Logger logger;

	public void info(String str) {
        logger.log(java.util.logging.Level.INFO, str);
    }

	public void debug(String str) {
        logger.log(java.util.logging.Level.FINE, str);
    }
	
	public StandardLogger(String name) {
        logger = Logger.getLogger(name);
        logger.setParent(FMLLog.getLogger());        
        info("c'tor() complete");		
	}
}
