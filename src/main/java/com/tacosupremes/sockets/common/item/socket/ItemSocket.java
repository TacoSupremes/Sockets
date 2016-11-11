package com.tacosupremes.sockets.common.item.socket;

import com.tacosupremes.sockets.common.item.ItemMod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class ItemSocket extends ItemMod {

	private SocketType Type;



	public ItemSocket(String s, SocketType type) {
		super(s);
		this.Type = type;
		
	}
	
	
	public static ItemSocket getSocket(ItemStack is, int slot){
		
		if(is.hasTagCompound()){
			
			if(slot == -1){
				
				for(int i=1;i<=3;i++){
					if(is.getTagCompound().hasKey("S"+slot)){
						return (ItemSocket)Item.getItemById(is.getTagCompound().getInteger("S"+slot));
					}
				}
				
			}else{
				
				if(is.getTagCompound().hasKey("S"+slot)){
					return (ItemSocket)Item.getItemById(is.getTagCompound().getInteger("S"+slot));
				}
				
				
			}
			
		}
		
		
		
		return null;
		
	}
	
	public static boolean setSocket(ItemStack is, ItemSocket s, int slot){
		
		if(!is.hasTagCompound())
			is.setTagCompound(new NBTTagCompound());
		
		if(is.getTagCompound().hasKey("S"+slot))
			return false;
			
		is.getTagCompound().setInteger("S"+slot, Item.getIdFromItem(s));
			
			return true;
	}
	
	
public static boolean addSocket(ItemStack is, ItemSocket s, int slot){
		
	
		
		for(int i = 0; i<=3;i++){
			
		
		if(setSocket(is,s,i))
			return true;
		}
		
		
		return false;
	}
	
	
	public enum SocketType {
		
		Item, Block, Armor
	}

}
