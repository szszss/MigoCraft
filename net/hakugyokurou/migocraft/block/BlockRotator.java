package net.hakugyokurou.migocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.hakugyokurou.migocraft.block.tileentity.TileEntityRotator;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRotator extends BlockCore{

	public BlockRotator(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		sideIcon = par1IconRegister.registerIcon("migocraft:blockCore");
		frontIcon = par1IconRegister.registerIcon("migocraft:blockRotator");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityRotator();
	}

}
