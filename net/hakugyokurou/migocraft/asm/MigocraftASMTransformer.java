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
				return TransformerEntityRenderer.transformEntityRenderer(bytes);
			}
			if(transformedName.equals("net.minecraft.entity.Entity"))
			{
				return TransformerEntity.transformEntity(name,bytes);
			}
			if(transformedName.equals("net.minecraft.entity.EntityLivingBase"))
			{
				return TransformerEntityLiving.transformEntityLiving(name,bytes);
			}
			if(transformedName.equals("net.minecraft.client.renderer.entity.RenderManager"))
			{
				return TransformerRenderManager.transformRenderEntity(name, bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//TODO:CATCH EXCEPTION
		}
		//System.out.println(transformedName+" out");
		return bytes;
	}
}
