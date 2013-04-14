package pdunham.weird.objects;

import pdunham.weird.common.StandardLogger;
import pdunham.weird.common.WeirdMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderPebble extends Render {

	private StandardLogger logger = null;
	
	private int itemIconIndex = 15;
	
	public RenderPebble() {
	    logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
	    logger.info("C'tor() complete");
	}

	public void postInit() {
		logger.info("postInit() complete");
	}	

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        logger.info("doRender(par1Entity " + par1Entity + ", par2 " + par2 + ", par4 " + par4 + 
    		   					", par6 " + par6 + ", par8 " + par8 + ", par9 " + par9 + ")");
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.loadTexture(WeirdMain.pathTexture);
        Tessellator var10 = Tessellator.instance;

//        if (this.itemIconIndex == 154)
//        {
//            int var11 = PotionHelper.func_77915_a(((EntityPotion)par1Entity).getPotionDamage(), false);
//            float var12 = (float)(var11 >> 16 & 255) / 255.0F;
//            float var13 = (float)(var11 >> 8 & 255) / 255.0F;
//            float var14 = (float)(var11 & 255) / 255.0F;
//            GL11.glColor3f(var12, var13, var14);
//            GL11.glPushMatrix();
//            this.drawIt(var10, 141);
//            GL11.glPopMatrix();
//            GL11.glColor3f(1.0F, 1.0F, 1.0F);
//        }

        this.drawIt(var10, this.itemIconIndex);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    private void drawIt(Tessellator par1Tessellator, int par2)
    {
        logger.info("drawIt(Tessellator " + par1Tessellator + ", par2 " + par2 + ")"); 
        float var3 = (float)(par2 % 16 * 16 + 0) / 256.0F;
        float var4 = (float)(par2 % 16 * 16 + 16) / 256.0F;
        float var5 = (float)(par2 / 16 * 16 + 0) / 256.0F;
        float var6 = (float)(par2 / 16 * 16 + 16) / 256.0F;
        float var7 = 1.0F;
        float var8 = 0.5F;
        float var9 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(0.0F - var9), 0.0D, (double)var3, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(0.0F - var9), 0.0D, (double)var4, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(var7 - var9), 0.0D, (double)var4, (double)var5);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(var7 - var9), 0.0D, (double)var3, (double)var5);
        par1Tessellator.draw();
    }
}
