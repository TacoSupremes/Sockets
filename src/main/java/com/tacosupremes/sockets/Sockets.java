package com.tacosupremes.sockets;


import java.util.Random;

import com.tacosupremes.sockets.common.block.ModBlocks;
import com.tacosupremes.sockets.common.item.ModItems;
import com.tacosupremes.sockets.common.lib.LibMisc;
import com.tacosupremes.sockets.common.recipes.ModRecipes;
import com.tacosupremes.sockets.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LibMisc.MODID, version = LibMisc.VERSION)
public class Sockets
{

    
    @SidedProxy(clientSide = LibMisc.CLIENTPROXY, serverSide = LibMisc.COMMONPROXY)
    public static CommonProxy proxy;
    
    public static CreativeTabs tab;
    
    @Instance(LibMisc.MODID)
    public static Sockets instance;
	
    public static Random rand;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	tab = new RTab();	
  
    	rand = new Random();
    	

    	ModBlocks.preInit();
    	
    	ModItems.preInit();
    	
    	LibMisc.Ores.preInit();
    }
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	ModItems.init();
    //	NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		proxy.registerRenders();
		

    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	ModRecipes.postInit();
    	LibMisc.Ores.postInit();
    	//Pages.postInit();
    	
    	
   
		
    }
    
    public static int randInt(int max, int exclude){
    	
    	int i = rand.nextInt(max);
    	
    	while(i == exclude)
    		i = rand.nextInt(max);
    	
    		
    	
    	return i;
    }
    
    public class RTab extends CreativeTabs {

		public RTab() {
			super(CreativeTabs.getNextID(), LibMisc.MODID);
			
		}

		@Override
		public Item getTabIconItem() {
		
			return Item.getItemFromBlock(Blocks.OBSIDIAN);
		}
    	
    }
}
