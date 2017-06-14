package com.tacosupremes.sockets.common.block.tile;

import com.tacosupremes.sockets.common.item.ModItems;
import com.tacosupremes.sockets.common.item.socket.ItemGrindSocket;
import com.tacosupremes.sockets.common.item.socket.ItemSocket;
import com.tacosupremes.sockets.common.utils.InventoryHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileSocketHolder extends TileEntity implements ITickable, IInventory{
	
	int ticks = 0;
	
	ItemStack is = null;
	
	@Override
	public void update() {
		
		ticks++;
		
		World w = this.getWorld();
		
		if(this.ticks %20 != 0)
			return;
		
		if(is == null)
			return;
		
		if(w.getTileEntity(pos.down()) != null){
			
			if(w.getTileEntity(pos.down()) instanceof IInventory){
				
				IInventory f = (IInventory)w.getTileEntity(pos.down());
				
				
				
				if(is.getItem() instanceof ItemSocket){
					
					ItemSocket g = (ItemSocket)is.getItem();
					
					
					for(int i = 0; i < f.getSizeInventory(); i++){
				
						if(f.getStackInSlot(i) == null)
							continue;
						
						ItemStack r = g.affectItem(f.getStackInSlot(i));
						
						
						
					
						if(r != null){
							
							if(g.getChestItem(f) != null){
								g.affectChestItem(f);
								f.markDirty();
							}else
								return;

							f.decrStackSize(i, 1);
							
							InventoryHelper.insertItemNew(r, f, true);
							return;
						}
						
						
					}
					
				}
			}
			
			
		}
		
	}
	
	
	@Override
	public String getName() {
		
		return "socketHolder";
	}

	@Override
	public boolean hasCustomName() {
		
		return false;
	}

	@Override
	public int getSizeInventory() {
		
		return 1;
	}

	@Override
	public boolean isEmpty() {
		
		return is == null;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		
		return is;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		

		return is.splitStack(count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		
		ItemStack is2 = is.copy();
		
		is = null;
		
		return is2;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		is = stack;
		
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		
		return is == null;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		is = null;
		
	}

}
