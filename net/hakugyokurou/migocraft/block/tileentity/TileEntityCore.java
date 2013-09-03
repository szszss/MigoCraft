package net.hakugyokurou.migocraft.block.tileentity;

import net.hakugyokurou.migocraft.physics.ICS;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import static net.hakugyokurou.migocraft.MigocraftConst.*;

public abstract class TileEntityCore extends TileEntity{

	
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		NBTTagCompound nbtTagCompound = par1nbtTagCompound.getCompoundTag(BLOCK_CORE_NBT_COMPOUND);
		setPowered(nbtTagCompound.getBoolean(BLOCK_CORE_NBT_POWERED));
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		NBTTagCompound nbtTagCompound = par1nbtTagCompound.getCompoundTag(BLOCK_CORE_NBT_COMPOUND);
		nbtTagCompound.setBoolean(BLOCK_CORE_NBT_POWERED, powered);
		
		par1nbtTagCompound.setCompoundTag(BLOCK_CORE_NBT_COMPOUND, nbtTagCompound);
	}

	private ICS body;
	private int face = 0;
	private boolean powered = false;
	
	public boolean isPowered()
	{
		return powered;
	}
	
	public void setPowered(boolean power)
	{
		powered = power;
	}

	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		this.face = face;
	}
	
	public void buildBody(IBlockAccess par1iBlockAccess, int x, int y, int z)
	{
		//body = new ICS(par1iBlockAccess, x, y, z, caX, caY, caZ)
	}
	
}
