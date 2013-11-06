package net.hakugyokurou.migocraft.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.util.Vec3;

/**
 * 数学运算工具类<br/><br/>
 * 
 * 包括一些向量运算的辅助函数.
 * @author szszss
 */
public abstract class MathUtil {

	public static final double HALF_SQRT3 = Math.sqrt(3)/2;
	public static final double NEG_HALF_SQRT3 = -Math.sqrt(3)/2;
	public static final double QUARTER_PI = Math.PI/4;
	public static final double THREE_QUARTER_PI = (Math.PI/4)*3;
	public static final double NEG_QUARTER_PI = -Math.PI/4;
	public static final double NEG_THREE_QUARTER_PI = -(Math.PI/4)*3;
	
	public static float clamp(float number,float min,float max)
	{
		return number<min?min:
			   number>max?max:
					      number;
	}
	
	public static double clamp(double number,double min,double max)
	{
		return number<min?min:
			   number>max?max:
					      number;
	}
	
	public static double[] transformVectorToAngle(Vec3 vec3)
	{
		return transformVectorToAngle(vec3.xCoord, vec3.yCoord, vec3.zCoord);
	}
	
	public static double[] transformVectorToAngle(double x,double y,double z)
	{
		double pitch = Math.asin(y)/Math.PI*180d;
		double yaw = Math.atan2(-x, z)/Math.PI*180d;
		double roll = Math.atan2(x, -y)/Math.PI*180d;
		return new double[]{yaw,pitch,roll};
	}
	
	@Deprecated
	public static Vec3 transformAngleToVector(double yaw,double pitch,double roll)
	{
		double x = Math.cos(roll/180*Math.PI);
		double y = Math.cos(pitch/180*Math.PI);
		double z = Math.sin(pitch/180*Math.PI);
		return Vec3.createVectorHelper(x, y, z).normalize();
	}

}
