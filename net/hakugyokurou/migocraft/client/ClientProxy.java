package net.hakugyokurou.migocraft.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.hakugyokurou.migocraft.CommonProxy;
import net.hakugyokurou.migocraft.client.render.RenderBlockAGP;

/**
 * 客户端代理器<br/><br/>
 * 
 * 客户端Mod代理器,只有在客户端才会被加载调用.<br/>
 * 注意,客户端代理器的大部分方法(至少是init方法)都需要调用基类的方法.
 * @author szszss
 */
public class ClientProxy extends CommonProxy{
	
	@Override
	public void init()
	{
		super.init();
		RenderingRegistry.registerBlockHandler(new RenderBlockAGP());
	}
}
