package pdunham.weird.client.model;

import org.lwjgl.opengl.GL11;

import pdunham.weird.common.StandardLogger;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWeirdBaby extends ModelQuadruped
{
	private static StandardLogger logger;
    public ModelRenderer calf1;
    public ModelRenderer calf2;

	public ModelWeirdBaby()
    {
        this(0.0F);
    }

    public ModelWeirdBaby(float scaleFactor)
    {
        super(6, scaleFactor);
        int legLength = 6;
        int calfLength = 3;
        int armLength = 12;
        logger = StandardLogger.getLogger(logger, this.getClass().getSimpleName());
        
        // This is a modified version of ModelQuadraped. 
        // I have tweaked the head and body sizes
        // ModelRenderer(this, texture x offset, texture y offset)
		this.head= new ModelRenderer(this, 0, 0);
		
		// addBox(originX, originY, originZ, width, height, depth, scaleFactor)
		this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, scaleFactor);
		
		// setRotationPoint(rotationPointX, rotationPointY, rotationPointZ);
		this.head.setRotationPoint(0.0F, (float)(18 - legLength), -6.0F);
		
		// Repeat for Body
		this.body = new ModelRenderer(this, 28, 8);
		this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, scaleFactor);
		this.body.setRotationPoint(0.0F, (float)(17 - legLength), 2.0F);
		
		// Repeat for Legs
		this.leg1 = new ModelRenderer(this, 0, 16);
		this.leg1.addBox(-2.0F, 0.0F, -3.0F, 4, legLength, 4, scaleFactor);
		this.leg1.setRotationPoint(-3.0F, (float)(24 - legLength), 7.0F);
		
		this.leg2 = new ModelRenderer(this, 0, 16);
		this.leg2.addBox(-2.0F, 0.0F, -3.0F, 4, legLength, 4, scaleFactor);
		this.leg2.setRotationPoint(3.0F, (float)(24 - legLength), 7.0F);

		// Repeat for calves
		this.calf1 = new ModelRenderer(this, 0, 16);
		this.calf1.addBox(-2.0F, 0.0f, 1.0F, 4, calfLength, 4, scaleFactor);
		this.calf1.setRotationPoint(-3.0F, (float)(24 - calfLength), 7.0F);
		
		this.calf2 = new ModelRenderer(this, 0, 16);
		this.calf2.addBox(-2.0F, 0.0f, 1.0F, 4, calfLength, 4, scaleFactor);
		this.calf2.setRotationPoint(3.0F, (float)(24 - calfLength), 7.0F);

		
		// Repeat for Arms - Arms are longer than legs and stick out
		// from the side of the body so the baby has shoulders.
		this.leg3 = new ModelRenderer(this, 0, 16);
		this.leg3.addBox(-4.0F, 0.0F, -2.0F, 4, armLength, 4, scaleFactor);
		this.leg3.setRotationPoint(-3.0F, (float)(24 - armLength), -5.0F);
		
		this.leg4 = new ModelRenderer(this, 0, 16);
		this.leg4.addBox(0.0F, 0.0F, -2.0F, 4, armLength, 4, scaleFactor);
		this.leg4.setRotationPoint(3.0F, (float)(24 - armLength), -5.0F);
		logger.info("c'tor() complete");
    }
    
    @Override
    // Adding calves to the render
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        if (this.isChild)
        {
            float var8 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.calf1.render(par7);
            this.calf2.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.calf1.render(par7);
            this.calf2.render(par7);
        }
    }

    @Override
    // Adding calves to the rotation
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
    		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        this.calf1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.calf2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
    }
}
