package net.hakugyokurou.migocraft.asm;

import static net.hakugyokurou.migocraft.MigocraftCorePlugin.inDev;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public abstract class ASMHelper {
	
	public static ClassNode makeClassNode(byte[] bytes)
	{
		ClassReader classReader = new ClassReader(bytes);
		ClassNode classNode = new ClassNode(Opcodes.ASM4);
		classReader.accept(classNode, ClassReader.EXPAND_FRAMES);
		return classNode;
	}
	
	public static byte[] makeBytes(ClassNode classNode)
	{
		return makeBytes(classNode, ClassWriter.COMPUTE_FRAMES);
	}
	
	public static byte[] makeBytes(ClassNode classNode,int flag)
	{
		MigoClassWriter classWriter = new MigoClassWriter(flag);
		classNode.accept(classWriter);
		return classWriter.toByteArray();
	}
	
	/**
	 * Get an actual name of a field/method in current environment.
	 * <br/>In development environment, Minecraft uses unobscure name (a.k.a MCP name), 
	 * and in game environment, Minecraft, be adjusted by FML, uses Searge name.
	 * 
	 * @param unobName The MCP name of a field/method, such as "currentItem"/"getRandomItem"
	 * @param srgName The Searge name of a field/method, such as "field_xxxxx"/"func_xxxxx"  
	 * @return The name of a field/method in current environment.
	 * @author szszss
	 */
	public static String getActualName(String unobName,String srgName)
	{
		return inDev?unobName:srgName;
	}

}
