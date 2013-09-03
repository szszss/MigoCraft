package net.hakugyokurou.migocraft.client.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.hakugyokurou.migocraft.util.CameraHelper;
import net.hakugyokurou.migocraft.util.GravityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;

@Deprecated
@SideOnly(value = Side.CLIENT)
public abstract class CameraRenderer {
	
	public static final Vec3 NEGATIVE_NORMAL = Vec3.createVectorHelper(-1d, -1d, -1d);
	public static float[] daze = new float[] {0f,0f,0f};
	
	/**
	 * 如果你不知道它的作用的话,就不要调用它.
	 */
	public static void turnCam()
	{
		/*
		double[] doubles = GravityHelper.transformVectorToAngle(Minecraft.getMinecraft().thePlayer.gravitySource);
		double yaw = doubles[0];
		double pitch = doubles[1];
		double roll = doubles[2];
		GL11.glRotatef((float)yaw, 0f, 1f, 0f);
		GL11.glRotatef((float)pitch, 1f, 0f, 0f);
		GL11.glRotatef((float)roll, 0f, 0f, 1f);*/
		GL11.glRotatef(daze[0], 0f, 1f, 0f);
		GL11.glRotatef(daze[1], 1f, 0f, 0f);
		GL11.glRotatef(daze[2], 0f, 0f, 1f);	
	}

}
