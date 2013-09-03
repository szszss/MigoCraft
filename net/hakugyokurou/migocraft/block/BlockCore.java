package net.hakugyokurou.migocraft.block;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.hakugyokurou.migocraft.api.event.EventCorePlaced;
import net.hakugyokurou.migocraft.block.tileentity.TileEntityCore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;

public abstract class BlockCore extends BlockContainer{
	
	public Icon sideIcon;
	public Icon frontIcon;

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return par1==par2?frontIcon:sideIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public abstract void registerIcons(IconRegister par1IconRegister);

	public BlockCore(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setHardness(5f);
		this.setResistance(1f);
	}

	@Override
	public abstract TileEntity createNewTileEntity(World world);
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		EventCorePlaced event = new EventCorePlaced(par1World, par2, par3, par4, par5EntityLiving, this, 
				BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLiving));
		boolean ohyeah = MinecraftForge.EVENT_BUS.post(event);
		if(ohyeah || event.getResult() == Result.DENY)
		{
			par1World.setBlockToAir(par2, par3, par4);
			return;
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, event.face, 4);
	}
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,int par4, int par5) 
	{
		if(!par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
		{
			((TileEntityCore)(par1World.getBlockTileEntity(par2, par3, par4))).setPowered(false);
			return;
		}	
		((TileEntityCore)(par1World.getBlockTileEntity(par2, par3, par4))).setPowered(true);
	}
	
	public void onStart(World world, int x, int y,int z)
	{
		
	}
	
	//TODO:红石电力传输功能尚未完成.
	/**
	 * 在激活时核心砖块可以传输电力.
	 * Core block may transmit redstone power while it is activated.
	 */
	@Override
	public boolean canProvidePower() {
		return false;
	}
	//TODO:红石电力传输功能尚未完成.
	/**
	 * 激活时提供电力.
	 * Providing power when it's activated.
	 */
	@Override
	public int isProvidingWeakPower(IBlockAccess par1iBlockAccess, int par2,
			int par3, int par4, int par5) {
		if(((TileEntityCore)(par1iBlockAccess.getBlockTileEntity(par2,par3,par4))).isPowered())
		{
			return 15;
		}
		return 0;
	}
	//TODO:红石电力传输功能尚未完成.
	/**
	 * 激活时提供电力.
	 * Providing power when it's activated.
	 */
	@Override
	public int isProvidingStrongPower(IBlockAccess par1iBlockAccess, int par2,
			int par3, int par4, int par5) {
		return isProvidingWeakPower(par1iBlockAccess, par2, par3, par4, par5);
	}
	
	/**
	 * A wrapper of TileEntityCore's buildBody.
	 * @param par1iBlockAccess
	 * @param x
	 * @param y
	 * @param z
	 */
	public void buildBody(IBlockAccess par1iBlockAccess, int x, int y, int z)
	{
		TileEntityCore tileEntity = (TileEntityCore) par1iBlockAccess.getBlockTileEntity(x, y, z);
		tileEntity.buildBody(par1iBlockAccess, x, y, z);
	}

}
