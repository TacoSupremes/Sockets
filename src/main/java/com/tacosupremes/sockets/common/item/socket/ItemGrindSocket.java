package com.tacosupremes.sockets.common.item.socket;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import com.tacosupremes.sockets.common.item.ModItems;
import com.tacosupremes.sockets.common.lib.LibMisc;
import com.tacosupremes.sockets.common.recipes.ModRecipes;
import com.tacosupremes.sockets.common.utils.BlockUtils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemGrindSocket extends ItemSocket {

	public ItemGrindSocket() {
		super("grindSocket", SocketType.Item);
		
		//TODO:NAME QUARTZ TEETH
		
	}

	@Override
	public ItemStack affectItem(ItemStack is) {
		
		ItemStack r = ModRecipes.grinderio.get(is.getUnlocalizedName());
		
		return r != null ? new ItemStack(r.getItem(),r.getCount(),r.getItemDamage()) : null;
	
		
	}

	public List<ItemStack> getTarget(World w, BlockPos pos, int fortune){
			
		return Arrays.asList(new ItemStack[]{BlockUtils.toItemStack(w.getBlockState(pos))});
		
	}







	@Override
	public int getPriority() {
	
		return 3;
	}



	@Override
	public EnumParticleTypes getParticle() {
		
		return EnumParticleTypes.EXPLOSION_NORMAL;
	}

	@Override
	public Color getSocketColor() {
		
		return Color.YELLOW;
	}

	@Override
	public ItemStack getChestItem(IInventory ii) {
	
		for(int i = 0; i<ii.getSizeInventory();i++){
			
			if(ii.getStackInSlot(i) != null){
				
				if(ii.getStackInSlot(i).getItem() instanceof ItemPickaxe){
					
					return ii.getStackInSlot(i);
				}
				
			}
			
		}
		
		
		return null;
	}

	@Override
	public void affectChestItem(IInventory ii) {
		
		if(getChestItem(ii).attemptDamageItem(1, itemRand))
			getChestItem(ii).shrink(1);
		
	}
	
	
	

}
