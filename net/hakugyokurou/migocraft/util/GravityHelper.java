package net.hakugyokurou.migocraft.util;

import static net.hakugyokurou.migocraft.util.MathUtil.*;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeHooks;

public abstract class GravityHelper {

	public static final String CLASS_NAME = "net/hakugyokurou/migocraft/util/GravityHelper";
	
	public static void jump(EntityLivingBase entityLiving)
	{
		entityLiving.motionX = -0.41999998688697815D*entityLiving.gravitySource.xCoord;
		entityLiving.motionY = -0.41999998688697815D*entityLiving.gravitySource.yCoord;
		entityLiving.motionZ = -0.41999998688697815D*entityLiving.gravitySource.zCoord;

        if (entityLiving.isPotionActive(Potion.jump))
        {
        	entityLiving.motionX -= (double)((float)(entityLiving.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F)*entityLiving.gravitySource.xCoord;
        	entityLiving.motionY -= (double)((float)(entityLiving.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F)*entityLiving.gravitySource.yCoord;
        	entityLiving.motionZ -= (double)((float)(entityLiving.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F)*entityLiving.gravitySource.zCoord;
        }

        //XXX:让其它重力源也实现疾跑跳跃.
        if (entityLiving.isSprinting() && entityLiving.gravitySource.yCoord == -1d)
        {
            float f = entityLiving.rotationYaw * 0.017453292F;
            entityLiving.motionX -= (double)(MathHelper.sin(f) * 0.2F);
            entityLiving.motionZ += (double)(MathHelper.cos(f) * 0.2F);
        }

        entityLiving.isAirBorne = true;
        ForgeHooks.onLivingJump(entityLiving);
	}
	
	public static void isOnGround(Entity entity, double xCollided, double yCollided, double zCollided)
	{
		if(entity.gravitySource.yCoord == -1d)
			return;
		double y = entity.gravitySource.yCoord;
		if(y <= NEG_HALF_SQRT3) //Floor is down.
		{
			if(yCollided < 0 && entity.isCollidedVertically)
				entity.onGround = true;
			else
				entity.onGround = false;
			return;
		}
		if(y >= MathUtil.HALF_SQRT3) //Floor is up.
		{
			if(yCollided > 0 && entity.isCollidedVertically)
				entity.onGround = true;
			else
				entity.onGround = false;
			return;
		}
		double x = entity.gravitySource.xCoord;
		double z = entity.gravitySource.zCoord;
		double angel = Math.atan2(z, x);
		if(angel >= NEG_QUARTER_PI && angel <= QUARTER_PI) //Floor is east.
		{
			if(xCollided > 0 && entity.isCollidedHorizontally)
				entity.onGround = true;
			else
				entity.onGround = false;
			return;
		}
		if(angel >= QUARTER_PI && angel <= THREE_QUARTER_PI) //Floor is north.
		{
			if(zCollided > 0 && entity.isCollidedHorizontally)
				entity.onGround = true;
			else
				entity.onGround = false;
			return;
		}
		if(angel >= NEG_THREE_QUARTER_PI && angel <= NEG_QUARTER_PI) //Floor is south.
		{
			if(zCollided < 0 && entity.isCollidedHorizontally)
				entity.onGround = true;
			else
				entity.onGround = false;
			return;
		}
		if(xCollided < 0 && entity.isCollidedHorizontally) //Floor is west
			entity.onGround = true;
		else
			entity.onGround = false;
		return;
	}
	
	public static void fallToGravitySourceInAir(EntityLivingBase entityLiving, double velocity)
	{
		if(entityLiving.gravitySource.yCoord == -1d)
		{
			entityLiving.motionY -= velocity;
			return;
		}
        entityLiving.motionX += velocity*entityLiving.gravitySource.xCoord;
		entityLiving.motionY += velocity*entityLiving.gravitySource.yCoord;
		entityLiving.motionZ += velocity*entityLiving.gravitySource.zCoord;
		
        //entityLiving.motionX *= natureResistance;
        //entityLiving.motionY *= natureResistance;
        //entityLiving.motionZ *= natureResistance;
	}
	
	public static void resistance(EntityLivingBase entityLiving)
	{
		float natureResistance = 0.91f;
		if(entityLiving.onGround)
		{
			natureResistance = 0.54600006f;
			int i = entityLiving.worldObj.getBlockId(MathHelper.floor_double(entityLiving.posX), MathHelper.floor_double(entityLiving.boundingBox.minY) - 1, MathHelper.floor_double(entityLiving.posZ));

	        if (i > 0)
	        {
	        	natureResistance = Block.blocksList[i].slipperiness * 0.91F;
	        }
		}
        entityLiving.motionX *= natureResistance;
        entityLiving.motionY *= 0.9800000190734863D;
        entityLiving.motionZ *= natureResistance;
	}
	
}
