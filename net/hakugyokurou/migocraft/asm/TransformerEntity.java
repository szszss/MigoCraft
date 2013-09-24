package net.hakugyokurou.migocraft.asm;

import static net.hakugyokurou.migocraft.MigocraftCorePlugin.inDev;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.DCONST_0;
import static org.objectweb.asm.Opcodes.DLOAD;
import static org.objectweb.asm.Opcodes.FLOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.PUTFIELD;
import static org.objectweb.asm.Opcodes.RETURN;

import java.util.List;

import net.hakugyokurou.migocraft.util.CameraHelper;
import net.hakugyokurou.migocraft.util.GravityHelper;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

abstract class TransformerEntity {

	static byte[] transformEntity(String name,byte[] bytes){
		ClassNode classNode = ASMHelper.makeClassNode(bytes);
		if(!inDev)
		{
			classNode.fields.add(new FieldNode(ACC_PUBLIC, "gravitySource", "Lnet/minecraft/util/Vec3;", null, null));
		}
		String methodSetAngles = ASMHelper.getActualName("setAngles", "func_70082_c");
		for(MethodNode methodNode : (List<MethodNode>)classNode.methods)
		{
			if(methodNode.name.equals("<init>") && !inDev)
			{
				String methodName = ASMHelper.getActualName("createVectorHelper", "func_72443_a");
				InsnList insnList = new InsnList();
				insnList.add(new VarInsnNode(ALOAD, 0));
				insnList.add(new InsnNode(DCONST_0));
				insnList.add(new LdcInsnNode(Double.valueOf(-1.0D)));
				insnList.add(new InsnNode(DCONST_0));
				insnList.add(new MethodInsnNode(INVOKESTATIC, "net/minecraft/util/Vec3", methodName, "(DDD)Lnet/minecraft/util/Vec3;"));
				insnList.add(new FieldInsnNode(PUTFIELD, name.replace('.', '/'), "gravitySource", "Lnet/minecraft/util/Vec3;"));
				AbstractInsnNode ain = methodNode.instructions.getFirst();
				while(!(ain instanceof MethodInsnNode))
				{
					ain = ain.getNext(); //should be "invokespecial java/lang/Object/<init>()V"
				}
				methodNode.instructions.insert(ain, insnList);
			}
			else if(methodNode.name.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName("net/minecraft/entity/Entity", "moveEntity", "(DDD)V")))
			{
				String fieldName = FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(name.replace('.', '/'), "onGround", "Z");
				for(AbstractInsnNode insnNode : methodNode.instructions.toArray())
				{
					if(insnNode.getOpcode() == Opcodes.PUTFIELD && ((FieldInsnNode)insnNode).name.equals(fieldName))
					{
						methodNode.instructions.insert(insnNode, new MethodInsnNode(
								INVOKESTATIC, GravityHelper.CLASS_NAME, "isOnGround",
								"(L"+name.replace('.', '/')+";DDD)V"));
						methodNode.instructions.insert(insnNode, new VarInsnNode(DLOAD, 17));//DLOAD 17
						methodNode.instructions.insert(insnNode, new VarInsnNode(DLOAD, 15));//DLOAD 15
						methodNode.instructions.insert(insnNode, new VarInsnNode(DLOAD, 13));//DLOAD 13
						methodNode.instructions.insert(insnNode, new VarInsnNode(ALOAD, 0));//ALOAD_0
					}
				}
			}
			else if(methodNode.name.equals(methodSetAngles))
			{
				AbstractInsnNode firstNode = methodNode.instructions.getFirst();
				while(!(firstNode instanceof VarInsnNode))
				{
					firstNode = firstNode.getNext();
				}
				firstNode = firstNode.getPrevious(); //Find out the first valid bytecode and return back to its back.
				methodNode.instructions.insert(firstNode, new InsnNode(RETURN));
				methodNode.instructions.insert(firstNode, new MethodInsnNode(
						INVOKESTATIC, CameraHelper.CLASS_NAME, "setAngles",
						"(Lnet/minecraft/entity/Entity;FF)V"));
				methodNode.instructions.insert(firstNode, new VarInsnNode(FLOAD, 2));//FLOAD 2
				methodNode.instructions.insert(firstNode, new VarInsnNode(FLOAD, 1));//FLOAD 1
				methodNode.instructions.insert(firstNode, new VarInsnNode(ALOAD, 0));//ALOAD_0
			}
		}
		return ASMHelper.makeBytes(classNode,ClassWriter.COMPUTE_FRAMES);
	}
}
