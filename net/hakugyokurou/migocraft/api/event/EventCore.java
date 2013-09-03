package net.hakugyokurou.migocraft.api.event;

import net.hakugyokurou.migocraft.block.BlockCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.Cancelable;
import net.minecraftforge.event.Event;

@Cancelable
@Event.HasResult
public class EventCore extends EventMigocraft{

	public final World world;
	public final int posX;
	public final int posY;
	public final int posZ;
	public final BlockCore core;
	
	public EventCore(World parWorld,int x,int y,int z,BlockCore block)
	{
		super();
		world = parWorld;
		posX = x;
		posY = y;
		posZ = z;
		core = block;
	}
}
