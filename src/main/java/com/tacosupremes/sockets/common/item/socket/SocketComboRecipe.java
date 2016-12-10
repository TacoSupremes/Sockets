package com.tacosupremes.sockets.common.item.socket;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class SocketComboRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		
		ItemStack sI = null;
		
		List<ItemSocket> s = new ArrayList<ItemSocket>();
		
		for(int i = 0; i<inv.getSizeInventory();i++){
			
			ItemStack is = inv.getStackInSlot(i);
			
			if(is == null)
				continue;
			
			if(is.getItem() instanceof ItemSocket){
				
				if(s.size() < 3)
					s.add((ItemSocket)is.getItem());
				else
					return false;
			}
			
			
			if(is.getItem() instanceof ISocketable){
				
				if(sI == null)	
					sI = is;
				else
					return false;
				continue;
			}
			
			
		}
		
		if(sI != null && !s.isEmpty()){
			
			if(ItemSocket.getSockets(sI).size() + s.size() > 3)
				return false;
			
			if(!ItemSocket.areSocketsCompatible(s))
				return false;
			
			List<ItemSocket> ss = new ArrayList<ItemSocket>();
			
			ss.addAll(s);
			
			ss.addAll(ItemSocket.getSockets(sI));
				
			if(!ItemSocket.areSocketsCompatible(ss))
				return false;
			
			
			return true;
		}
		
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		
		ItemStack sI = null;
		
		List<ItemSocket> s = new ArrayList<ItemSocket>();
		
		for(int i = 0; i<inv.getSizeInventory();i++){
			
			ItemStack is = inv.getStackInSlot(i);
			
			if(is == null)
				continue;
			
			if(is.getItem() instanceof ItemSocket)
				s.add((ItemSocket)is.getItem());
			
			
			
			if(is.getItem() instanceof ISocketable)
				sI = is;
		
		}
		
		ItemStack is2 = sI.copy();
		
		ItemSocket.addSockets(is2, s);
		
		return is2;
	}

	@Override
	public int getRecipeSize() {
		
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput() {
		
		return null;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		inv.clear(); //TODO DECREASE USED STACKS TO PREVENT DESTRUCTION OF UNUSED ITEM EX HAVING STACK OF 3SOCKETS IN BENCH 
		return new ItemStack[inv.getSizeInventory()];
	}

}
