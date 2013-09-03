package net.hakugyokurou.migocraft.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.hakugyokurou.migocraft.CommonProxy;
import net.hakugyokurou.migocraft.client.render.RenderBlockAGP;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void init()
	{
		super.init();
		RenderingRegistry.registerBlockHandler(new RenderBlockAGP());
	}
}
