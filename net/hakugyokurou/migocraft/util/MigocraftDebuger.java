package net.hakugyokurou.migocraft.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.hakugyokurou.migocraft.MigocraftCorePlugin;
import net.hakugyokurou.migocraft.client.render.CameraRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.unoya.NBTDemo;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;

public class MigocraftDebuger {
	
	public static MigocraftDebuger INSTANCE;
	
	public MigocraftDebuger()
	{
		INSTANCE = this;
	}
	
	@ForgeSubscribe
	public void PlayerCommand(ServerChatEvent event)
	{
		if(!event.username.equals("szszss") || !event.message.startsWith("debug"))
			return;
		event.setCanceled(true);
		if(event.message.indexOf("pitch") != -1)
		{
			event.player.addChatMessage(turnPitch(event.message));
		}
		else if(event.message.indexOf("roll") != -1)
		{
			event.player.addChatMessage(turnRoll(event.message));
		}
		
		else if(event.message.indexOf("cgs") != -1)
		{
			event.player.addChatMessage(changeGravitySource(event.message,event.player));
		}
	}
	
	private String turnPitch(String arg)
	{
		try {
			String[] args = arg.split(" ");
			float f = Float.parseFloat(args[args.length-1]);
			//double[] fs = GravityHelper.transformVectorToAngle(Minecraft.getMinecraft().thePlayer.gravitySource);
			//fs[1] = (double)f;
			//Minecraft.getMinecraft().thePlayer.gravitySource = GravityHelper.transformAngleToVector(fs[0], fs[1], fs[2]);
			//return "转换结果:"+fs[0]+" "+fs[1]+" "+fs[2];
			
			CameraRenderer.daze[1] = f;
			return "转换结果:"+CameraRenderer.daze[0]+" "+CameraRenderer.daze[1]+" "+CameraRenderer.daze[2];
		} catch (Exception e) {
			e.printStackTrace();
			return "转换失败.";
		}
	}
	
	private String turnRoll(String arg)
	{
		try {
			String[] args = arg.split(" ");
			float f = Float.parseFloat(args[args.length-1]);
			CameraRenderer.daze[2] = f;
			return "转换结果:"+CameraRenderer.daze[0]+" "+CameraRenderer.daze[1]+" "+CameraRenderer.daze[2];
		} catch (Exception e) {
			e.printStackTrace();
			return "转换失败.";
		}
	}
	
	private String changeGravitySource(String arg,EntityPlayer player)
	{
		try {
			String[] args = arg.split(" ");
			for(Object p : Minecraft.getMinecraft().theWorld.playerEntities)
			{
				((EntityPlayer)p).gravitySource = Vec3.createVectorHelper(Double.parseDouble(args[args.length-3]), Double.parseDouble(args[args.length-2]), Double.parseDouble(args[args.length-1])).normalize();
			}
			player.gravitySource = Vec3.createVectorHelper(Double.parseDouble(args[args.length-3]), Double.parseDouble(args[args.length-2]), Double.parseDouble(args[args.length-1])).normalize();
			//player = Minecraft.getMinecraft().thePlayer;
			//player.gravitySource = Vec3.createVectorHelper(Double.parseDouble(args[args.length-3]), Double.parseDouble(args[args.length-2]), Double.parseDouble(args[args.length-1]));
			return "转换结果:"+player.gravitySource.xCoord+" "+player.gravitySource.yCoord+" "+player.gravitySource.zCoord;
		} catch (Exception e) {
			e.printStackTrace();
			return "转换失败.";
		}
	}
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void renderGameOverlayEvent(RenderGameOverlayEvent event)
	{
		if(event.type == ElementType.TEXT && RenderManager.instance.getFontRenderer() != null && MigocraftCorePlugin.inDev)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			Vec3 vec3 = player.gravitySource;
			double[] ds = MathUtil.transformVectorToAngle(vec3);
			RenderManager.instance.getFontRenderer().drawStringWithShadow("yaw:"+ds[0], 50, 24, 0xFFFFFF);
			RenderManager.instance.getFontRenderer().drawStringWithShadow("pitch:"+ds[1], 50, 32, 0xFFFFFF);
			RenderManager.instance.getFontRenderer().drawStringWithShadow("roll:"+ds[2], 50, 40, 0xFFFFFF);
		}
	}

}
