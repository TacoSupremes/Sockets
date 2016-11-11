package com.tacosupremes.sockets.common.block;

import java.util.ArrayList;
import java.util.List;

import com.tacosupremes.sockets.common.item.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ModBlocks {
	
	public static List<Block> blocks = new ArrayList<Block>();
	
	public static List<Block> runes = new ArrayList<Block>();
	
	public static Block marker;

	public static Block furnaceBooster;
	
	public static Block grinder;
	
	public static void preInit(){
	
		
	}
	
	public static void registerRenders(){
		
		for(Block i : blocks){
			ModItems.registerItemRender(Item.getItemFromBlock(i), 0);
		}
		
		
	}
	
	

}
