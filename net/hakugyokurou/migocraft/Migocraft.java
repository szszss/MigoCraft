package net.hakugyokurou.migocraft;

import java.util.Arrays;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.hakugyokurou.migocraft.block.BlockAGP;
import net.hakugyokurou.migocraft.block.BlockCore;
import net.hakugyokurou.migocraft.block.BlockPanel;
import net.hakugyokurou.migocraft.block.BlockRasa;
import net.hakugyokurou.migocraft.block.BlockRotator;
import net.hakugyokurou.migocraft.block.tileentity.TileEntityCore;
import net.hakugyokurou.migocraft.block.tileentity.TileEntityRotator;
import net.hakugyokurou.migocraft.util.MigocraftDebuger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import static net.hakugyokurou.migocraft.MigocraftConst.*;

/**
 * Mod主类<br/><br/>
 * 
 * MigoCraft的Mod主类&Mod容器类.
 * @author szszss
 */
public class Migocraft extends DummyModContainer{
	
	public static String VERSION = "0.0.1";
	public static boolean inDev = false;
	
	@SidedProxy(serverSide="net.hakugyokurou.migocraft.CommonProxy",clientSide="net.hakugyokurou.migocraft.client.ClientProxy")
	public static CommonProxy proxy;
	
	public static BlockRotator blockRotator;
	public static int blockRotatorID = 3865;
	public static BlockPanel blockAntigravityPanel;
	public static int blockAntigravityPanelID = 3869;
	public static int blockAntigravityPanelRenderType = 101;
	public static BlockRasa blockRasa;
	public static int blockRasaID = 3870;
	public static int blockRasaRenderType = 100;
	
	public static int serverMaxRecursion = 7;
	
	public Migocraft()
	{
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
        meta.modId       = "migocraft";
        meta.name        = "MigoCraft";
        meta.version     = VERSION;
        meta.authorList  = Arrays.asList("szszss");
        meta.description = "Aghhhhhhhhhhh.I LOST ALL MY SANs!";
        //meta.url         = "http://www.mcmyskin.com/";
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}
	
	@Subscribe
	public void preInit(FMLPreInitializationEvent event)
	{
		//Load configuration file.
		Configuration configuration = new Configuration(event.getSuggestedConfigurationFile());
		configuration.load();
		//Load blocks and items.
		blockRotatorID = configuration.getBlock(CONFIG_BLOCK_ROTATOR, blockRotatorID, CONFIG_BLOCK_ROTATOR_COMMENT).getInt();
		blockAntigravityPanelID = configuration.getBlock(CONFIG_BLOCK_AGP, blockAntigravityPanelID, CONFIG_BLOCK_AGP_COMMENT).getInt();
		blockRasaID = configuration.getBlock(CONFIG_BLOCK_RASA, blockRasaID, CONFIG_BLOCK_RASA_COMMENT).getInt();
		//Load the renders of blocks.
		blockAntigravityPanelRenderType = configuration.get(CONFIG_RENDER, CONFIG_RENDER_AGP, blockAntigravityPanelRenderType).getInt();
		configuration.get(CONFIG_RENDER, CONFIG_RENDER_AGP, blockAntigravityPanelRenderType).comment = CONFIG_RENDER_AGP_COMMENT;
		blockRasaRenderType = configuration.get(CONFIG_RENDER, CONFIG_RENDER_RASA, blockRasaRenderType).getInt();
		configuration.get(CONFIG_RENDER, CONFIG_RENDER_RASA, blockRasaRenderType).comment = CONFIG_RENDER_RASA_COMMENT;
		//Load miscs.
		//serverMaxRecursion = configuration.get
		configuration.save();
		//Proxy init.
		proxy.init();
		//Initialize blocks.
		blockRotator = new BlockRotator(blockRotatorID, Material.iron);
		blockRotator.setUnlocalizedName("blockRotator");
		blockRotator.setCreativeTab(CreativeTabs.tabRedstone);
		GameRegistry.registerBlock(blockRotator,"migoRotator");
		GameRegistry.registerTileEntity(TileEntityRotator.class, "MigoRotator");
		LanguageRegistry.addName(blockRotator,"Rotator");
		
		blockAntigravityPanel = new BlockAGP(blockAntigravityPanelID, Material.iron);
		blockAntigravityPanel.setUnlocalizedName("blockAGP").setHardness(2f).setResistance(1f).setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(blockAntigravityPanel,"blockAGP");
		LanguageRegistry.addName(blockAntigravityPanel,"Antigravity Panel");
		
		blockRasa = new BlockRasa(blockRasaID, Material.air);
		blockRasa.setUnlocalizedName("migorasa");
		blockRasa.setHardness(0f);
		GameRegistry.registerBlock(blockRasa,"migorasa");
		LanguageRegistry.addName(blockRasa,"Rasa");
	}
	
	@Subscribe
	public void init(FMLInitializationEvent event)
	{
		
		/*blockCore = new BlockCore(blockCoreID, Material.iron);
		blockCore.setUnlocalizedName("blockCore");
		blockCore.setCreativeTab(CreativeTabs.tabRedstone);
		GameRegistry.registerBlock(blockCore,"testblock");
		GameRegistry.registerTileEntity(TileEntityCore.class, "MigoCore");
		LanguageRegistry.addName(blockCore,"TestBlock");*/
	}
	
	@Subscribe
	public void postInit(FMLPostInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new MigocraftDebuger());
	}

}
