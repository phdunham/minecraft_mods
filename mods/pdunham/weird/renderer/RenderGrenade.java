package pdunham.weird.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.potion.PotionHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdConstants;

@SideOnly(Side.CLIENT)
public class RenderGrenade extends Render {
	private static StandardLogger logger;
	private int iconIndex = 19;

    public RenderGrenade(int x, int y) {
    		iconIndex = x + (y * 16);
	    logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
		logger.info("c'tor() complete");
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        loadTexture(getTextureFile());
        Tessellator tessellator = Tessellator.instance;
        drawIt(tessellator);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    private void drawIt(Tessellator tessellator) {
        float var3 = (float)(iconIndex % 16 * 16 + 0) / 256.0F;
        float var4 = (float)(iconIndex % 16 * 16 + 16) / 256.0F;
        float var5 = (float)(iconIndex / 16 * 16 + 0) / 256.0F;
        float var6 = (float)(iconIndex / 16 * 16 + 16) / 256.0F;
        float var7 = 1.0F;
        float var8 = 0.5F;
        float var9 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV((double)(0.0F - var8), (double)(0.0F - var9), 0.0D, (double)var3, (double)var6);
        tessellator.addVertexWithUV((double)(var7 - var8), (double)(0.0F - var9), 0.0D, (double)var4, (double)var6);
        tessellator.addVertexWithUV((double)(var7 - var8), (double)(var7 - var9), 0.0D, (double)var4, (double)var5);
        tessellator.addVertexWithUV((double)(0.0F - var8), (double)(var7 - var9), 0.0D, (double)var3, (double)var5);
        tessellator.draw();
    }

	public String getTextureFile() {
		return WeirdConstants.pathTexturesIcons;
	}	
}
