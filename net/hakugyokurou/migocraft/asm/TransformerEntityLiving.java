package net.hakugyokurou.migocraft.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.RETURN;

import java.util.List;

import net.hakugyokurou.migocraft.util.GravityHelper;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

abstract class TransformerEntityLiving {

	static byte[] transformEntityLiving(String name,byte[] bytes){
		ClassNode classNode = ASMHelper.makeClassNode(bytes);
		String fieldName = FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(name.replace('.', '/'), "motionZ", "D");
		for(MethodNode methodNode : (List<MethodNode>)classNode.methods)
		{
			if(methodNode.name.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName("net/minecraft/entity/EntityLivingBase", "jump", "()V")))
			{
				AbstractInsnNode firstNode = methodNode.instructions.getFirst();
				while(!(firstNode instanceof VarInsnNode))
				{
					firstNode = firstNode.getNext();
				}
				firstNode = firstNode.getPrevious(); //Find out the first valid bytecode and return back to its back.
				methodNode.instructions.insert(firstNode, new InsnNode(RETURN));
				//methodNode.instructions.insert(firstNode, new FrameNode(F_SAME,1,new Object[]{name.replace('.', '/')},0,new Object[0]));
				methodNode.instructions.insert(firstNode, new MethodInsnNode(
						INVOKESTATIC, GravityHelper.CLASS_NAME, "jump",
						"(L"+name.replace('.', '/')+";)V"));
				methodNode.instructions.insert(firstNode, new VarInsnNode(ALOAD, 0));//ALOAD_0
			}
			else if(methodNode.name.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName("net/minecraft/entity/EntityLivingBase", "moveEntityWithHeading", "(FF)V")))
			{
				int hit = 0;
				for(AbstractInsnNode insnNode : methodNode.instructions.toArray())
				{
					//XXX:代码重构
					//Invalidate "motionZ -= 0.02D", and add "GravityHelper.fallToGravitySourceInAir(this,0.02D)".
					if(insnNode.getOpcode() == Opcodes.LDC && ((LdcInsnNode)insnNode).cst.equals(0.02D))
					{
						if(insnNode.getPrevious().getOpcode() == Opcodes.GETFIELD)
						{
							((LdcInsnNode)insnNode).cst = 0.0D;
							AbstractInsnNode nextAbstractInsnNode = insnNode.getNext();
							//Java6 doesn't allow to insert our bytecodes in a series of consecutive bytecodes, so we have to find out the end of them.
							while(nextAbstractInsnNode.getOpcode() != Opcodes.PUTFIELD)
							{
								nextAbstractInsnNode = nextAbstractInsnNode.getNext();
							}
							methodNode.instructions.insert(nextAbstractInsnNode, new MethodInsnNode(
									INVOKESTATIC, GravityHelper.CLASS_NAME, "fallToGravitySourceInAir",
									"(L"+name.replace('.', '/')+";D)V"));
							methodNode.instructions.insert(nextAbstractInsnNode, new LdcInsnNode(Double.valueOf(0.02D)));
							methodNode.instructions.insert(nextAbstractInsnNode, new VarInsnNode(ALOAD, 0));//ALOAD_0
						}
					}
					//Invalidate "motionZ -= 0.08D", and add "GravityHelper.fallToGravitySourceInAir(this,0.08D)".
					if(insnNode.getOpcode() == Opcodes.LDC && ((LdcInsnNode)insnNode).cst.equals(0.08D))
					{
						((LdcInsnNode)insnNode).cst = 0.0D;
						AbstractInsnNode nextAbstractInsnNode = insnNode.getNext();
						//Java6 doesn't allow to insert our bytecodes in a series of consecutive bytecodes, so we have to find out the end of them.
						while(nextAbstractInsnNode.getOpcode() != Opcodes.PUTFIELD)
						{
							nextAbstractInsnNode = nextAbstractInsnNode.getNext();
						}
						methodNode.instructions.insert(nextAbstractInsnNode, new MethodInsnNode(
								INVOKESTATIC, GravityHelper.CLASS_NAME, "fallToGravitySourceInAir",
								"(L"+name.replace('.', '/')+";D)V"));
						methodNode.instructions.insert(nextAbstractInsnNode, new LdcInsnNode(Double.valueOf(0.08D)));
						methodNode.instructions.insert(nextAbstractInsnNode, new VarInsnNode(ALOAD, 0));//ALOAD_0
						hit++;
					} //Invalidate "this.motionY *= 0.9800000190734863D".
					else if(insnNode.getOpcode() == Opcodes.LDC && ((LdcInsnNode)insnNode).cst.equals(0.9800000190734863D))
					{
						((LdcInsnNode)insnNode).cst = 1.0D;
						hit++;
					} //Invalidate "this.motionX *= (double)f2".
					else if(insnNode.getOpcode() == Opcodes.FLOAD && hit>=2)
					{
						methodNode.instructions.set(insnNode, new LdcInsnNode(Double.valueOf(1.0D)));
						hit++;
					} //too
					else if(insnNode.getOpcode() == Opcodes.F2D && hit>=2)
					{
						methodNode.instructions.remove(insnNode);
						hit++;
					} //Add "GravityHelper.resistance(this)".
					else if(insnNode.getOpcode() == Opcodes.PUTFIELD && ((FieldInsnNode)insnNode).name.equals(fieldName) && hit>=6)
					{
						methodNode.instructions.insert(insnNode, new MethodInsnNode(
								INVOKESTATIC, GravityHelper.CLASS_NAME, "resistance",
								"(L"+name.replace('.', '/')+";)V"));
						methodNode.instructions.insert(insnNode, new VarInsnNode(ALOAD, 0));//ALOAD_0
						break;
					}
				}
			}
		}
		//SHIT I WAS FUCKED BY ENTITYLIVING
		return ASMHelper.makeBytes(classNode,ClassWriter.COMPUTE_FRAMES);
	}
}
