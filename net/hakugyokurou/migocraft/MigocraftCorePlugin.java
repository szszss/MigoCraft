package net.hakugyokurou.migocraft;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

/**
 * MigoCraft载入入口类<br/><br/>
 * 
 * Coremod的载入入口类.
 * @author szszss
 */
@TransformerExclusions(value={"net.hakugyokurou.migocraft.asm"})
public class MigocraftCorePlugin implements IFMLLoadingPlugin{

	public static boolean inDev = false;
	
	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"net.hakugyokurou.migocraft.asm.MigocraftASMTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return "net.hakugyokurou.migocraft.Migocraft";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		Object isInDev = data.get("runtimeDeobfuscationEnabled");
		if(isInDev!=null)
			inDev = !((Boolean)isInDev).booleanValue();
	}

}
