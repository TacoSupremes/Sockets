package com.tacosupremes.sockets.common.item.socket;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tacosupremes.sockets.common.item.ItemMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemSocket extends ItemMod {
//TODO Make type an Array for multiple types
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
	
	
public static boolean addSocket(ItemStack is, ItemSocket s){
		
	
	if(hasSocket(is,s))
		return false;
	
		for(int i = 1; i<=3;i++){
			
		
		if(setSocket(is,s,i))
			return true;
		}
		
		
		return false;
	}
	

public static boolean hasSocket(ItemStack is, ItemSocket socket){
	
	if(!is.hasTagCompound())
		return false;
	
	for(int i = 1; i<=3;i++){
		
		if(is.getTagCompound().getInteger("S"+i) == Item.getIdFromItem(socket))
			return true;
		
	}
	
	return false;
}


public static List<ItemSocket> getSockets(ItemStack is){
	
	List<ItemSocket> l = new ArrayList<ItemSocket>();
	
	if(!is.hasTagCompound())
		return l;
	
	for(int i=1;i<=3;i++){
		
		
		if(is.getTagCompound().hasKey("S"+i)){
			if(Item.getItemById(is.getTagCompound().getInteger("S"+i)) instanceof ItemSocket)
			l.add((ItemSocket)Item.getItemById(is.getTagCompound().getInteger("S"+i)));
		}
		
	}
	
	List<ItemSocket> l2 = new ArrayList<ItemSocket>();
	
	
	
	while (!l.isEmpty()){
		
		int lp = 1000;
		int li = 0;
		
	for(int i=0;i<l.size();i++){
		ItemSocket so = l.get(i);
		
		if(so.getPriority() < lp){
			li = i;
			lp = so.getPriority();
		}
	}
	
	l2.add(l.get(li));
	l.remove(li);
	
	}
	
	
	
	
	return l2;
}

public static List<ItemSocket> getSocketsEX(ItemStack is, SocketType t){
	
	List<ItemSocket> l = new ArrayList<ItemSocket>();
	
	if(!is.hasTagCompound())
		return l;
	
	for(int i=1;i<=3;i++){
		
		
		if(is.getTagCompound().hasKey("S"+i))
			l.add((ItemSocket)Item.getItemById(is.getTagCompound().getInteger("S"+i)));
		
		
	}
	
	List<ItemSocket> l2 = new ArrayList<ItemSocket>();
	
	
	
	while (!l.isEmpty()){
		
		int lp = 1000;
		int li = 0;
		
	for(int i=0;i<l.size();i++){
		ItemSocket so = l.get(i);
		
		if(so.getPriority() < lp && so.getType() != t){
			li = i;
			lp = so.getPriority();
		}
	}
	
	l2.add(l.get(li));
	l.remove(li);
	
	if(l.size() == 1 && l.get(0).getType() == t)
		l2.add(l.get(0));
	
	}
	
	
	
	
	return l2;
}

public static boolean areSocketsCompatible(List<ItemSocket> l){
	
	if(l.size() == 1)
		return true;
	
	for(int i = 0; i < l.size(); i++){
		
		ItemSocket socket1 = l.get(i);
		
		for(int i2 = 0; i2 < l.size(); i2++){
		
			ItemSocket socket2 = l.get(i2);
			
			if(i == i2)
				continue;
			
			if(socket1.getPriority() == socket2.getPriority())
				return false;
			
		}
		
	}
	
	
	return true;
}

public static boolean addSockets(ItemStack is, List<ItemSocket> s) {
	
	if(!areSocketsCompatible(s))
		return false;
	
	List<ItemSocket> ss = new ArrayList<ItemSocket>();
	
	ss.addAll(s);
	
	ss.addAll(ItemSocket.getSockets(is));
		
	if(!ItemSocket.areSocketsCompatible(ss))
		return false;
	
	for(ItemSocket i : s){
		
		addSocket(is, i);
	}
	
	return true;
	
}


public int getPriority(){
	return 0;
}


public List<ItemStack> getTarget(World w, BlockPos pos, int fortune){
	
	return w.getBlockState(pos).getBlock().getDrops(w, pos, w.getBlockState(pos), fortune);
	
}


public List<BlockPos> getBlocks(World w, BlockPos pos, EntityPlayer player){
	
	
	return Arrays.asList(pos);
	
}



public SocketType getType() {
	return Type;
}


public abstract ItemStack affectItem(ItemStack is);

public abstract EnumParticleTypes getParticle();

public abstract ItemStack getChestItem(IInventory ii);

public void affectChestItem(IInventory ii){
	
	getChestItem(ii).shrink(1);
	
}

public abstract Color getSocketColor();
	
	public enum SocketType {
		
		Item, Block, Armor
	}

	
}
