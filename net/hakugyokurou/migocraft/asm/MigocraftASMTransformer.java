package net.hakugyokurou.migocraft.asm;

import static org.objectweb.asm.Opcodes.*;
import static net.hakugyokurou.migocraft.MigocraftCorePlugin.inDev;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import net.hakugyokurou.migocraft.util.CameraHelper;
import net.hakugyokurou.migocraft.util.GravityHelper;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import cpw.mods.fml.relauncher.CoreModManager;

/*
                           /---------------\
                           |      The      |
                           |Nameless Heroes|
                           |  ' Memorial   |
                           |               |
                           |               |
                           |               |
                           | For the people|
                           |who fought with|
                           | Java Bytecode |
                           |    and ASM.   |
                           |               |
                           |               |
                         |-------------------|
                         |                   |
                         ---------------------
*/

public class MigocraftASMTransformer implements IClassTransformer{
	
	public static MigocraftASMTransformer INSTANCE;
	
	public MigocraftASMTransformer()
	{
		INSTANCE = this;
	}

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		//System.out.println(transformedName);
		if(name.indexOf("MigocraftASMTransformer")!=-1)
			return bytes;
		try {
			if(transformedName.equals("net.minecraft.client.renderer.EntityRenderer"))
			{
				return transformEntityRenderer(bytes);
			}
			if(transformedName.equals("net.minecraft.entity.Entity"))
			{
				return transformEntity(name,bytes);
			}
			if(transformedName.equals("net.minecraft.entity.EntityLivingBase"))
			{
				return transformEntityLiving(name,bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//TODO:CATCH EXCEPTION
		}
		//System.out.println(transformedName+" out");
		return bytes;
	}
	
	private byte[] transformEntityRenderer(byte[] bytes){
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
	
	private byte[] transformEntity(String name,byte[] bytes){
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
				String className = FMLDeobfuscatingRemapper.INSTANCE.map("net/minecraft/util/Vec3");
				//String classDesc = FMLDeobfuscatingRemapper.INSTANCE.mapDesc("net/minecraft/util/Vec3");
				String fieldDesc = "L"+className+";";//Lnet/minecraft/util/Vec3;
				String methodDesc = "(DDD)"+fieldDesc;//(DDD)Lnet/minecraft/util/Vec3;
				String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName("net/minecraft/util/Vec3", "createVectorHelper", methodDesc);
				InsnList insnList = new InsnList();
				insnList.add(new InsnNode(42));//ALOAD_0
				insnList.add(new InsnNode(DCONST_0));
				insnList.add(new LdcInsnNode(Double.valueOf(-1.0D)));
				insnList.add(new InsnNode(DCONST_0));
				insnList.add(new MethodInsnNode(INVOKESTATIC, className, methodName, methodDesc));
				insnList.add(new FieldInsnNode(PUTFIELD, name.replace('.', '/'), "gravitySource", fieldDesc));
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
	
	private byte[] transformEntityLiving(String name,byte[] bytes){
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
