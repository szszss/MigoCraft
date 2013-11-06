package net.hakugyokurou.migocraft.util;

import net.minecraft.util.Vec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 虚拟视场球<br/>
 * <br/>
 * 虚拟视场球(VVB)是一个用于视野变换的类,从功能上讲,它是用来处理重力源不在正下方的玩家的鼠标移动操纵.<br/>
 * 通常来说,若一个玩家的重力源位于他的正下方(换句话说,它的重力源向量指向Y轴负方向),那么它的视野范围就是一个球型,
 * 视角方向向量不能与重力源的向量平行.基于这个原理,VVB可以在内部模拟这样的一个视场,从外部观测,它是随着玩家的重力源
 * 向量的变化而转动的,它的Y轴在全局空间上始终指向重力源向量的负方向.但在内部,它依然维持着一个旧的视野变换规则,使用时,
 * 游戏将参数传给VVB,VVB进行运算并初始化,随后游戏将更多的数据传给VVB,VVB算出最终结果,最后,游戏从VVB中取出结果,并使用.
 * <br/><br/>
 * 运算量暂可不必考虑,毕竟这是个客户端类.
 * 
 * @author szszss
 */
@SideOnly(Side.CLIENT)
public class VirtualViewportBall {

	public static VirtualViewportBall instance = new VirtualViewportBall();
	
	private float virtualRotationPitch = 0f;
	private float virtualRotationYaw = 0f;
	public float realRotationPitch = 0f;
	public float realRotationYaw = 0f;
	private float diffPitch;
	private float diffYaw;
	
	public void setGravitySource(Vec3 source,float rotationPitch,float rotationYaw)
	{
		//yawY,pitchX,rollZ
		double[] ds = MathUtil.transformVectorToAngle(source);
		diffYaw = (float)ds[0];
		diffPitch = (float)ds[1];
		virtualRotationYaw = rotationYaw + diffYaw;
		virtualRotationPitch = rotationPitch - (diffPitch + 90f);
	}
	
	public void process(Vec3 source,float x,float y)
	{
		virtualRotationYaw = (float)((double)virtualRotationYaw + (double)x * 0.15D);
		virtualRotationPitch = (float)((double)virtualRotationPitch - (double)y * 0.15D);
		if (virtualRotationPitch < -90.0F)
        {
			virtualRotationPitch = -90.0F;
        }

        if (virtualRotationPitch > 90.0F)
        {
        	virtualRotationPitch = 90.0F;
        }
        realRotationYaw = virtualRotationYaw - diffYaw;
        realRotationPitch = virtualRotationPitch + (diffPitch + 90f);
	}
}
