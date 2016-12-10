package com.tacosupremes.sockets.common.item;

import java.util.ArrayList;
import java.util.List;

import com.tacosupremes.sockets.common.item.socket.ItemAreaSocket;
import com.tacosupremes.sockets.common.item.socket.ItemGrindSocket;
import com.tacosupremes.sockets.common.item.socket.ItemSmeltSocket;
import com.tacosupremes.sockets.common.item.socket.tool.ItemSocketPickaxe;
import com.tacosupremes.sockets.common.lib.LibMisc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ModItems {
	
	public static List<ItemMod> items = new ArrayList<ItemMod>();
	
	public static List<Item> nitems = new ArrayList<Item>();
	
	public static Item modBook;
	
	public static Item soulGem;
	
	public static Item enderShard;
	
	public static Item fluidGem;
	
	public static Item dusts;
	
	public static Item mobRepeller;
	
	public static Item builderScroll;
	
	public static Item socketPick;
	
	public static Item grindSocket;
	
	public static Item smeltSocket;
	
	public static Item areaSocket;
	
	public static void preInit(){
		
		soulGem = new ItemSoulGem();
		
		grindSocket = new ItemGrindSocket();
		
		smeltSocket = new ItemSmeltSocket();
		
		areaSocket = new ItemAreaSocket();
		
		socketPick = new ItemSocketPickaxe();
		//builderScroll = new ItemBuilderScroll();
		
	

	}
	
	
	
	public static void init(){
		dusts = new ItemDust(LibMisc.Ores.oreName.toArray(new String[LibMisc.Ores.oreName.size()]));
	}
	
	public static void registerRenders(){
		
		
		for(ItemMod i : items){
			
			if(i.meta !=0){
				
				ResourceLocation[] s = new ResourceLocation[i.meta+1];
				
				for(int i2 = 0; i2<i.meta+1;i2++){
					
					s[i2] = new ResourceLocation(LibMisc.MODID + ":" + i.getUnlocalizedName().substring(5) +(i2 == 0 ? "" : i2));
					
					
				}
				
				
				ModelBakery.registerItemVariants(i, s);
				
				if(!i.skipVariants()){
				for(int i2 = 0; i2<=i.meta;i2++){
					ModItems.registerItemRender(i, i2);
					
				}
			}
				
			}
			
			if(i.meta == 0 || i.skipVariants())
				ModItems.registerItemRender(i, 0);
		}
		
		
		for(Item i : nitems){
			registerItemRender(i, 0);
		}
		
		
		
	}
	
	public static void registerItemRender(Item i, int meta){
		
		if(i == null)
			return;
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, meta, new ModelResourceLocation(LibMisc.MODID+":"+i.getUnlocalizedName().substring(5)+ (meta == 0 ? "" : String.valueOf(meta)), "inventory"));
	}
	
	

}
