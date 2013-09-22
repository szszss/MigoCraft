package net.hakugyokurou.migocraft.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;

public abstract class RenderHelper {
	
	public static final String CLASS_NAME = "net/hakugyokurou/migocraft/util/RenderHelper";
	
	public static void rotateModel(Entity entity)
	{
		if(entity.gravitySource.yCoord!=-1d)
		{
			//yawY,pitchX,rollZ
			double[] ds = MathUtil.transformVectorToAngle(entity.gravitySource);
			GL11.glRotated(-ds[0],0f,1f,0f);
			GL11.glRotated(ds[1]+90d, 1f,0f,0f);
			GL11.glRotated(ds[2]+90d, 0f,0f,1f);
		}
	}
}
