package net.hakugyokurou.migocraft.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

import java.util.List;

import net.hakugyokurou.migocraft.util.CameraHelper;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

abstract class TransformerEntityRenderer {
	
	static byte[] transformEntityRenderer(byte[] bytes){
		ClassNode classNode = ASMHelper.makeClassNode(bytes);
		String methodName = ASMHelper.getActualName("orientCamera", "func_78467_g");
		for(MethodNode methodNode : (List<MethodNode>)classNode.methods)
		{
			if(methodNode.name.equals(methodName))
			{
				boolean prepare = false;
				for(AbstractInsnNode insnNode : methodNode.instructions.toArray())
				{
					if(insnNode.getOpcode() == Opcodes.LDC && ((LdcInsnNode)insnNode).cst.equals(-0.1f))
					{
						prepare = true;
					}
					else if(insnNode.getOpcode() == Opcodes.INVOKESTATIC 
							&& ((MethodInsnNode)insnNode).name.equals("glTranslatef")
							&& prepare){
						prepare = false;
						methodNode.instructions.insert(insnNode, 
								new MethodInsnNode(INVOKESTATIC, CameraHelper.CLASS_NAME,"rotateCameraFV", "(Lnet/minecraft/entity/Entity;)V"));
						methodNode.instructions.insert(insnNode,new VarInsnNode(ALOAD, 2));
					}
				}
			}
		}
		return ASMHelper.makeBytes(classNode);
	}
}
