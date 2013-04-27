package pdunham.aweird.common;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class StandardLogger {
	private Logger logger = null;
	
	public StandardLogger() {
	}
	
	private boolean init() {
		if (logger == null) {
			try {
				// Go up 3 on the stack to get the name of the class calling StandardLogger
				String name = new Exception().getStackTrace()[2].getClassName();
				name = name.substring(name.lastIndexOf('.')+1);
		        logger = Logger.getLogger(name);
		        logger.setParent(FMLLog.getLogger());
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	public void info(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.INFO, str);
		}
    }

	public void fine(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.FINE, str);
		}
    }
	
	public void warn(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.WARNING, str);
		}
	}

	public void error(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.SEVERE, str);
		}
	}

	public void finest(String str) {
		if (init()) {
			logger.log(java.util.logging.Level.FINEST, str);
		}
    }
}
