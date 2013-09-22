package net.hakugyokurou.migocraft.asm;

import java.util.List;

import net.hakugyokurou.migocraft.util.RenderHelper;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

abstract class TransformerRenderManager {

	static byte[] transformRenderEntity(String name,byte[] bytes){
		ClassNode classNode = ASMHelper.makeClassNode(bytes);
		String methodRenderEntityWithPosYaw = ASMHelper.getActualName("renderEntityWithPosYaw", "func_78719_a");
		String methodDoRenderShadowAndFire = ASMHelper.getActualName("doRenderShadowAndFire", "func_76979_b");
		for(MethodNode methodNode : (List<MethodNode>)classNode.methods)
		{
			if(methodNode.name.equals(methodRenderEntityWithPosYaw))
			{
				int hit = 0;
				for(AbstractInsnNode insnNode : methodNode.instructions.toArray())
				{
					if(insnNode.getOpcode() == Opcodes.ALOAD && ((VarInsnNode)insnNode).var==10)
					{
						if(++hit==2)
						{
							//rotateModele(entity);
							methodNode.instructions.insert(insnNode, new MethodInsnNode(Opcodes.INVOKESTATIC, RenderHelper.CLASS_NAME,
									"rotateModel", "(Lnet/minecraft/entity/Entity;)V"));
							methodNode.instructions.insert(insnNode, new VarInsnNode(Opcodes.ALOAD, 1));
							//GL11.glPushMatrix()
							methodNode.instructions.insert(insnNode, 
									new MethodInsnNode(Opcodes.INVOKESTATIC,
											"org/lwjgl/opengl/GL11", "glPushMatrix", "()V"));
						}
					}
					if(insnNode.getOpcode() == Opcodes.INVOKEVIRTUAL && ((MethodInsnNode)insnNode).name.equals(methodDoRenderShadowAndFire))
					{
						//GL11.glPopMatrix()
						methodNode.instructions.insert(insnNode, 
								new MethodInsnNode(Opcodes.INVOKESTATIC,
										"org/lwjgl/opengl/GL11", "glPopMatrix", "()V"));
					}
				}
			}
		}
		return ASMHelper.makeBytes(classNode);
	}
}
