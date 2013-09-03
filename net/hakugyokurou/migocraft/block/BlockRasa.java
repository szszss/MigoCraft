package net.hakugyokurou.migocraft.block;

import java.util.Random;

import net.hakugyokurou.migocraft.Migocraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

//TODO:写一个好的Javadoc
/**
 * Ellen said hello to you.</br>
 * -<i>Phantasmagoria of Dim. Dream</i>
 * </br></br>
 * BlockRasa是个啥啊.
 * @author szszss
 */
public class BlockRasa extends Block{

	private int renderType = Migocraft.blockRasaRenderType;
	
	public BlockRasa(int par1, Material par2Material) {
		super(par1, par2Material);
		disableStats();
	}
	
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	
	@Override
	public boolean isCollidable()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return renderType;
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }
}
