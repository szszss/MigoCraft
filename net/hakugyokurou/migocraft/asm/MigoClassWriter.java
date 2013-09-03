package net.hakugyokurou.migocraft.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class MigoClassWriter extends ClassWriter{

	public MigoClassWriter(ClassReader classReader, int flags) {
		super(classReader, flags);
	}
	
	public MigoClassWriter(int flags) {
		super(flags);
	}
	
	@Override
	protected String getCommonSuperClass(final String type1, final String type2) {
		Class<?> c, d;
        ClassLoader classLoader = getClass().getClassLoader();
        if(type1.equals("net/minecraft/entity/player/EntityPlayer"))
        {
        	return type2;
        }
        try {
            c = Class.forName(type1.replace('/', '.'), false, classLoader);
            d = Class.forName(type2.replace('/', '.'), false, classLoader);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
        if (c.isAssignableFrom(d)) {
            return type1;
        }
        if (d.isAssignableFrom(c)) {
            return type2;
        }
        if (c.isInterface() || d.isInterface()) {
            return "java/lang/Object";
        } else {
            do {
                c = c.getSuperclass();
            } while (!c.isAssignableFrom(d));
            return c.getName().replace('.', '/');
        }
	}

}
