package net.hakugyokurou.migocraft.api.event;

import net.hakugyokurou.migocraft.block.BlockCore;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.Cancelable;
import net.minecraftforge.event.Event;

@Cancelable
@Event.HasResult
public class EventCorePlaced extends EventCore{

	public int face;
	public EntityLivingBase entity;
	
	public EventCorePlaced(World parWorld, int x, int y, int z,
			EntityLivingBase placer, BlockCore block, int blockFace) {
		super(parWorld, x, y, z, block);
		entity = placer;
		face = blockFace;
	}
}
