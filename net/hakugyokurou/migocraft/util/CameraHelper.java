package net.hakugyokurou.migocraft.util;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * 镜头运算辅助类<br/><br/>
 * 
 * 包括一些玩家视角/视场/镜头调整以及运算的方法,主要被ASM修改后的类调用.
 * @author szszss
 */
public abstract class CameraHelper {
	
	public static final String CLASS_NAME = "net/hakugyokurou/migocraft/util/CameraHelper";
	
	@SideOnly(value = Side.CLIENT)
	public static void rotateCameraFV(Entity entity)
	{
		//if(entity.gravitySource.yCoord == -1d)
		//	return;
		double[] ds = MathUtil.transformVectorToAngle(entity.gravitySource);
		//yawY,pitchX,rollZ
		//GL11.glRotated(ds[0]    ,0f,1f,0f);
		//GL11.glRotated(ds[1]+90d,1f,0f,0f);
		//GL11.glRotated(ds[2]+90d,0f,0f,1f);
	}
	
	@SideOnly(Side.CLIENT)
    public static void setAngles(Entity entity, float par1, float par2)
    {
		if(entity.gravitySource.yCoord == -1d)
		{
			float f2 = entity.rotationPitch;
	        float f3 = entity.rotationYaw;
	        entity.rotationYaw = (float)((double)entity.rotationYaw + (double)par1 * 0.15D);
	        entity.rotationPitch = (float)((double)entity.rotationPitch - (double)par2 * 0.15D);
	
	        if (entity.rotationPitch < -90.0F)
	        {
	        	entity.rotationPitch = -90.0F;
	        }
	
	        if (entity.rotationPitch > 90.0F)
	        {
	        	entity.rotationPitch = 90.0F;
	        }
	
	        entity.prevRotationPitch += entity.rotationPitch - f2;
	        entity.prevRotationYaw += entity.rotationYaw - f3;
		}
		else
		{
			double[] ds = MathUtil.transformVectorToAngle(entity.gravitySource);
			float f2 = entity.rotationPitch;
	        float f3 = entity.rotationYaw;
	        entity.rotationYaw = (float)((double)entity.rotationYaw + (double)par1 * 0.15D);
	        entity.rotationPitch = (float)((double)entity.rotationPitch - (double)par2 * 0.15D);
	
	        if (entity.rotationPitch < -90.0F)
	        {
	        	entity.rotationPitch = -90.0F;
	        }
	
	        if (entity.rotationPitch > 90.0F)
	        {
	        	entity.rotationPitch = 90.0F;
	        }
	
	        entity.prevRotationPitch += entity.rotationPitch - f2;
	        entity.prevRotationYaw += entity.rotationYaw - f3;
		}
		
    }
	
	@SideOnly(value = Side.CLIENT)
	@Deprecated
	public static float[] getCamRoll()
	{
		return getCamRool(Minecraft.getMinecraft().thePlayer);
	}
	@Deprecated
	public static float[] getCamRool(EntityLivingBase entityLiving)
	{
		
		return new float[]{0f,0f,0f};
	}

}
