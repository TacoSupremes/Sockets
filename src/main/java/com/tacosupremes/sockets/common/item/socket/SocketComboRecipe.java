package com.tacosupremes.sockets.common.item.socket;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class SocketComboRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		
		boolean foundSocketItem = false;
		boolean foundSocket = false;
		
		for(int i = 0; i<inv.getSizeInventory();i++){
			ItemStack is = inv.getStackInSlot(i);
			
			if(is == null)
				continue;
			
			if(is.getItem() instanceof ItemSocket){
				
				if(!foundSocket)
					foundSocket = true;
				else
					return false;
			}
			
			
			if(is.getItem() instanceof ISocketable){
				
				if(!foundSocketItem)
					foundSocketItem = true;
				else
					return false;
				continue;
			}
			
			
		}
		
		return foundSocket && foundSocketItem;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		
		ItemStack socketItem = null;
		ItemStack socket = null;
		
		for(int i = 0; i<inv.getSizeInventory();i++){
			ItemStack is = inv.getStackInSlot(i);
			
			if(is == null)
				continue;
			
			if(is.getItem() instanceof ItemSocket){
				
				socket = is;
			}
			
		
			
			if(is.getItem() instanceof ISocketable){
				
				socketItem = is;
			}
			
			if(socketItem != null && socket != null)
				break;
		}
	
		
		ItemSocket.addSocket(socketItem, (ItemSocket)socket.getItem());
		
		return socketItem;
	}

	@Override
	public int getRecipeSize() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		inv.clear();
		return new ItemStack[inv.getSizeInventory()];
	}

}
