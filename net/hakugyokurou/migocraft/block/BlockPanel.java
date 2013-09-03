package net.hakugyokurou.migocraft.block;

import static net.minecraftforge.common.ForgeDirection.*;
import java.util.Random;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.hakugyokurou.migocraft.Migocraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public abstract class BlockPanel extends Block{

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World,
			int x, int y, int z) {
		switch (par1World.getBlockMetadata(x, y, z)) {
		case 0:
			return AxisAlignedBB.getAABBPool().getAABB((double)x+0d, (double)y+0.9d, (double)z+0d, (double)x+1d, (double)y+1d, (double)z+1d);
		case 1:
			return AxisAlignedBB.getAABBPool().getAABB((double)x+0d, (double)y+0d, (double)z+0d, (double)x+1d, (double)y+0.1d, (double)z+1d);
		case 2:
			return AxisAlignedBB.getAABBPool().getAABB((double)x+0d, (double)y+0d, (double)z+0.9d, (double)x+1d, (double)y+1d, (double)z+1d);
		case 3:
			return AxisAlignedBB.getAABBPool().getAABB((double)x+0d, (double)y+0d, (double)z+0d, (double)x+1d, (double)y+1d, (double)z+0.1d);
		case 4:
			return AxisAlignedBB.getAABBPool().getAABB((double)x+0.9d, (double)y+0d, (double)z+0d, (double)x+1d, (double)y+1d, (double)z+1d);
		case 5:
			return AxisAlignedBB.getAABBPool().getAABB((double)x+0d, (double)y+0d, (double)z+0d, (double)x+0.1d, (double)y+1d, (double)z+1d);
		default:
			return super.getSelectedBoundingBoxFromPool(par1World, x, y, z);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}
	
	@Override
	public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        
        if (l == 0)
        {
            this.setBlockBounds(0f, 0.9f, 0f, 1f, 1f, 1f);
        }
        else if (l == 1)
        {
            this.setBlockBounds(0f, 0f, 0f, 1.0F, 0.1F, 1f);
        }
        else if (l == 2)
        {
            this.setBlockBounds(0f, 0f, 0.9f, 1F, 1F, 1f);
        }
        else if (l == 3)
        {
        	this.setBlockBounds(0f, 0f, 0f, 1F, 1F, 0.1f);
        }
        else if (l == 4)
        {
        	this.setBlockBounds(0.9f, 0f, 0f, 1F, 1F, 1f);
        }
        else if (l == 5)
        {
        	this.setBlockBounds(0f, 0f, 0f, 0.1F, 1F, 1f);
        }
        else
        {
            this.setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
        }

        return super.collisionRayTrace(par1World, par2, par3, par4, par5Vec3, par6Vec3);
    }

	protected int renderType = Migocraft.blockAntigravityPanelRenderType;
	
	public BlockPanel(int par1, Material par2Material) {
		super(par1, par2Material);
		// TODO Auto-generated constructor stub
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
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST ) ||
               par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST ) ||
               par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH) ||
               par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH) ||
               par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN) ||
               par1World.isBlockSolidOnSide(par2, par3 - 1, par4, UP);
    }

    @Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        int j1 = par9;
        
        if ((par7 < 0.0001f) && par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN))
        {
            j1 = 0;
            return j1;
        }
        
        if ((par7 > 0.9999f || j1 == 0 || par5 == 1) && par1World.isBlockSolidOnSide(par2, par3 - 1, par4, UP))
        {
            j1 = 1;
        }
        if ((j1 == 0 || par5 == 2) && par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH))
        {
            j1 = 2;
        }
        if ((j1 == 0 || par5 == 3) && par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH))
        {
            j1 = 3;
        }

        if ((j1 == 0 || par5 == 4) && par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST))
        {
            j1 = 4;
        }

        if ((j1 == 0 || par5 == 5) && par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST))
        {
            j1 = 5;
        }
        
        System.out.println("SIDE:"+j1);

        return j1;
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        boolean flag = false;

        if (i1 == 0 && par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN))
        {
            flag = true;
        }
        
        if (i1 == 1 && par1World.isBlockSolidOnSide(par2, par3 - 1, par4, UP))
        {
            flag = true;
        }
        
        if (i1 == 2 && par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH))
        {
            flag = true;
        }

        if (i1 == 3 && par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH))
        {
            flag = true;
        }

        if (i1 == 4 && par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST))
        {
            flag = true;
        }

        if (i1 == 5 && par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST))
        {
            flag = true;
        }

        if (!flag)
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, i1, 0);
            par1World.setBlockToAir(par2, par3, par4);
        }

        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
	
}
