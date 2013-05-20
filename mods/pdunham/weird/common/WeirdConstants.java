package pdunham.weird.common;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;

public class WeirdConstants {

	// addArmorMaterial(textureName, armorDurability, absorbedDamage = { helmet, chest, legs, boots}, enchantibility);
    public static EnumArmorMaterial armorWEIRD = EnumHelper.addArmorMaterial("WEIRD", 40, new int[] {4, 9, 7, 4}, 30);
    
    public static final String baseSounds = "/pdunham/weird/sounds/";
    public static final String baseTextures = "/pdunham/weird/textures/";
    public static final String baseLanguageLocalizations = "/pdunham/weird/lang/";
    public static final String packetChannelName = "pdunhamWeird";

    public static final String pathTexturesIcons 			= baseTextures + "weirdIcons.png";
    public static final String pathTexturesArmor 			= baseTextures + "weirdArmor.png";
    public static final String pathTexturesArmorAnimated 		= baseTextures + "weirdArmor"; // the #.png is added by the class animation
    public static final String pathTexturesArmorLegs        	= baseTextures + "weirdArmorLegs.png";
    public static final String pathTexturesArmorLegsAnimated 	= baseTextures + "weirdArmorLegs"; // the #.png is added by the class animation
    public static final String pathTexturesBaby 				= baseTextures + "weirdBaby.png";
    public static final String pathTexturesBabyZombie 		= baseTextures + "weirdBabyZombie.png";

    public static final String pathSoundsBaby 				= baseSounds + "baby"; // the #.ogg is added by the class

    
	// Make private so we never actuall make one.
	private WeirdConstants() { }

}
