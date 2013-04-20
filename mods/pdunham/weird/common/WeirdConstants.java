package pdunham.weird.common;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;

public class WeirdConstants {

	// addArmorMaterial(textureName, armorDurability, absorbedDamage = { helmet, chest, legs, boots}, enchantibility);
    public static EnumArmorMaterial armorWEIRD = EnumHelper.addArmorMaterial("WEIRD", 40, new int[] {4, 9, 7, 4}, 30);
    
    public static String baseSounds = "/pdunham/weird/sounds/";
    public static String baseTextures = "/pdunham/weird/textures/";

    public static String pathTexturesIcons 				= baseTextures + "weirdIcons.png";
    public static String pathTexturesArmor 				= baseTextures + "weirdArmor.png";
    public static String pathTexturesArmorAnimated 		= baseTextures + "weirdArmor"; // the #.png is added by the class animation
    public static String pathTexturesArmorLegs        	= baseTextures + "weirdArmorLegs.png";
    public static String pathTexturesArmorLegsAnimated 	= baseTextures + "weirdArmorLegs"; // the #.png is added by the class animation
    public static String pathTexturesBaby 				= baseTextures + "weirdBaby.png";
    public static String pathTexturesBabyZombie 			= baseTextures + "weirdBabyZombie.png";

    public static String pathSoundsBaby 					= baseTextures + "baby"; // the #.ogg is added by the class

    
	// Make private so we never actuall make one.
	private WeirdConstants() { }

}
