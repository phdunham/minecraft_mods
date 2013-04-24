package pdunham.weird.client;

import java.net.URL;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

@SideOnly(Side.CLIENT)
public class WeirdEventSounds {

	private static StandardLogger logger;

	public WeirdEventSounds() {
		logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("c'tor() complete");
	}
	
	// Called when it is time to register our custom sounds w/ the system.
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) {
		String [] soundFiles = {
			"baby1.ogg",				"baby2.ogg",				"baby3.ogg",
			"babyHurt1.ogg",			"babyHurt2.ogg",			"babyHurt3.ogg",
			"babyDeath1.ogg",		"babyDeath2.ogg",
			
			"babyZombie1.ogg",		"babyZombie2.ogg",		"babyZombie3.ogg",
			"babyZombieHurt1.ogg",	"babyZombieHurt2.ogg",	"babyZombieHurt3.ogg",
			"babyZombieDeath1.ogg",	"babyZombieDeath2.ogg"			
		};


		// Map the sound name to the sound path
		String babyPrefix = "baby/";
		for (int i = 0; i < soundFiles.length; i++) {
			String soundPath = WeirdConstants.baseSounds + babyPrefix + soundFiles[i];
			String soundName = babyPrefix + soundFiles[i];
			URL url = this.getClass().getResource(soundPath);

			// Check the path to see if the file exists.
			if (url != null) {
				logger.info("Sound " + soundName + " -> " + url);
				event.manager.soundPoolSounds.addSound(soundName, url);
			} else {
				logger.info("Sound not found " + soundName + " -> " + soundPath);
			}
		}
	}
}
