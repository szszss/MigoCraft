package net.hakugyokurou.migocraft.client.render;

import org.lwjgl.opengl.GL11;

import net.hakugyokurou.migocraft.Migocraft;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 反重力板砖块渲染类<br/><br/>
 * 
 * 用于渲染反重力板,反重力板的物理性质类似于UgoCraft的绝缘板,可以被贴在一个砖块上,<br/>
 * 它是一个非常薄的薄片,在渲染时,渲染器需要判断板子的朝向,并以此来渲染.
 * @author szszss
 */
@SideOnly(value = Side.CLIENT)
public class RenderBlockAGP implements ISimpleBlockRenderingHandler{

	public static RenderBlockAGP INSTANCE;
	
	public RenderBlockAGP()
	{
		INSTANCE = this;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
		Tessellator tessellator = Tessellator.instance;
		GL11.glColor4f(1f,1f,1f,1f);
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.drawCrossedSquares(block, metadata, -0.5D, -0.5D, -0.5D, 1.0F);
		tessellator.draw();
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		Tessellator tessellator = Tessellator.instance;
		GL11.glColor4f(1f,1f,1f,1f);
		
		Icon icon = renderer.getBlockIconFromSide(block, 0);
		if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }
		tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        float f = 1.0F;
        tessellator.setColorOpaque_F(f, f, f);
        double d0 = (double)icon.getMinU();
        double d1 = (double)icon.getMinV();
        double d2 = (double)icon.getMaxU();
        double d3 = (double)icon.getMaxV();
        int l = renderer.blockAccess.getBlockMetadata(x, y, z);
        double d4 = 0.005D;
        double d5 = 0.005D;

        if (l == 0)
        {
            tessellator.addVertexWithUV((double)(x + 0), (double)(y + 1) - d4, (double)(z + 0), d0, d1);
            tessellator.addVertexWithUV((double)(x + 1), (double)(y + 1) - d4, (double)(z + 0), d2, d1);
            tessellator.addVertexWithUV((double)(x + 1), (double)(y + 1) - d4, (double)(z + 1), d2, d3);
            tessellator.addVertexWithUV((double)(x + 0), (double)(y + 1) - d4, (double)(z + 1), d0, d3);
        }        
        else if (l == 1)
        {
        	tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0) + d4, (double)(z + 1), d0, d3);
        	tessellator.addVertexWithUV((double)(x + 1), (double)(y + 0) + d4, (double)(z + 1), d2, d3);
        	tessellator.addVertexWithUV((double)(x + 1), (double)(y + 0) + d4, (double)(z + 0), d2, d1);
        	tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0) + d4, (double)(z + 0), d0, d1);
        } 
        else if (l == 5)
        {
            tessellator.addVertexWithUV((double)x + d5, (double)(y + 1) + d4, (double)(z + 1) + d4, d0, d1);
            tessellator.addVertexWithUV((double)x + d5, (double)(y + 0) - d4, (double)(z + 1) + d4, d0, d3);
            tessellator.addVertexWithUV((double)x + d5, (double)(y + 0) - d4, (double)(z + 0) - d4, d2, d3);
            tessellator.addVertexWithUV((double)x + d5, (double)(y + 1) + d4, (double)(z + 0) - d4, d2, d1);
        }
        else if (l == 4)
        {
            tessellator.addVertexWithUV((double)(x + 1) - d5, (double)(y + 0) - d4, (double)(z + 1) + d4, d2, d3);
            tessellator.addVertexWithUV((double)(x + 1) - d5, (double)(y + 1) + d4, (double)(z + 1) + d4, d2, d1);
            tessellator.addVertexWithUV((double)(x + 1) - d5, (double)(y + 1) + d4, (double)(z + 0) - d4, d0, d1);
            tessellator.addVertexWithUV((double)(x + 1) - d5, (double)(y + 0) - d4, (double)(z + 0) - d4, d0, d3);
        }
        else if (l == 3)
        {
            tessellator.addVertexWithUV((double)(x + 1) + d4, (double)(y + 0) - d4, (double)z + d5, d2, d3);
            tessellator.addVertexWithUV((double)(x + 1) + d4, (double)(y + 1) + d4, (double)z + d5, d2, d1);
            tessellator.addVertexWithUV((double)(x + 0) - d4, (double)(y + 1) + d4, (double)z + d5, d0, d1);
            tessellator.addVertexWithUV((double)(x + 0) - d4, (double)(y + 0) - d4, (double)z + d5, d0, d3);
        }
        else if (l == 2)
        {
            tessellator.addVertexWithUV((double)(x + 1) + d4, (double)(y + 1) + d4, (double)(z + 1) - d5, d0, d1);
            tessellator.addVertexWithUV((double)(x + 1) + d4, (double)(y + 0) - d4, (double)(z + 1) - d5, d0, d3);
            tessellator.addVertexWithUV((double)(x + 0) - d4, (double)(y + 0) - d4, (double)(z + 1) - d5, d2, d3);
            tessellator.addVertexWithUV((double)(x + 0) - d4, (double)(y + 1) + d4, (double)(z + 1) - d5, d2, d1);
        }
        
        return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		return Migocraft.blockAntigravityPanelRenderType;
	}

}
