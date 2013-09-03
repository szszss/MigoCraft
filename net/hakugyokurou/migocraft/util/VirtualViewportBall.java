package net.hakugyokurou.migocraft.util;

import net.minecraft.util.Vec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VirtualViewportBall {

	public static VirtualViewportBall instance = new VirtualViewportBall();
	
	private float virtualRotationPitch = 0f;
	private float virtualRotationYaw = 0f;
	private float realRotationPitch = 0f;
	private float realRotationYaw = 0f;
	
	public void setGravitySource(Vec3 source,float rotationPitch,float rotationYaw)
	{
		virtualRotationPitch = 0f;
		virtualRotationYaw = 0f;
	}
	
	public void process(float x,float y,float rotationPitch,float rotationYaw)
	{
		realRotationYaw = (float)((double)rotationYaw + (double)x * 0.15D);
		realRotationPitch = (float)((double)rotationPitch - (double)y * 0.15D);
		if (realRotationPitch < -90.0F)
        {
			realRotationPitch = -90.0F;
        }

        if (realRotationPitch > 90.0F)
        {
        	realRotationPitch = 90.0F;
        }
	}
}
