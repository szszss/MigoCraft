package net.hakugyokurou.migocraft.physics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeDirection;

/**
 * 独立坐标系
 * 
 * ICS(Independent Cordinate System 独立坐标系)提供了局部坐标系与全局坐标系间换算处理.<br/>
 * 
 * @author szszss
 */
public class ICS implements IBlockAccess{
	
	//TODO:有无必要建立一个ICS池?我认为没有必要,ICS不会大量创建,也很难做到复用.
	
	/**
	 * The world where "you" are.
	 */
	private IBlockAccess theWorld;
	
	public double posX;
	public double posY;
	public double posZ;
	
	public Vec3 aocX;
	public Vec3 aocY;
	public Vec3 aocZ;
	
	public ICS(IBlockAccess world,double x,double y,double z,Vec3 caX,Vec3 caY,Vec3 caZ)
	{
		theWorld = world;
		posX = x;
		posY = y;
		posZ = z;
		aocX = caX;
		aocY = caY;
		aocZ = caZ;
		
		
		
	}

	@Override
	public int getBlockId(int i, int j, int k) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TileEntity getBlockTileEntity(int i, int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getLightBrightnessForSkyBlocks(int i, int j, int k, int l) {
		return theWorld.getLightBrightnessForSkyBlocks(i, j, k, l);
	}

	@Override
	public int getBlockMetadata(int i, int j, int k) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getBrightness(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getLightBrightness(int i, int j, int k) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Material getBlockMaterial(int i, int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isBlockOpaqueCube(int i, int j, int k) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlockNormalCube(int i, int j, int k) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isAirBlock(int i, int j, int k) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BiomeGenBase getBiomeGenForCoords(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean extendedLevelsInChunkCache() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesBlockHaveSolidTopSurface(int i, int j, int k) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vec3Pool getWorldVec3Pool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isBlockProvidingPowerTo(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isBlockSolidOnSide(int x, int y, int z, ForgeDirection side,
			boolean _default) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//public int getIntCoordinate()

}
