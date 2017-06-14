package com.tacosupremes.sockets.common.item.socket;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import com.tacosupremes.sockets.common.lib.LibMisc;
import com.tacosupremes.sockets.common.utils.BlockUtils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSmeltSocket extends ItemSocket {

	public ItemSmeltSocket() {
		super("smeltSocket", SocketType.Item);
	
		//TODO:NAME LAVA CRYSTAL
	}
	

	@Override
	public boolean onBlockStartBreak(ItemStack is, BlockPos pos, EntityPlayer player) {
		
		World w = player.getEntityWorld();

		ItemStack dust = FurnaceRecipes.instance().getSmeltingResult(w.getBlockState(pos).getBlock().getDrops(w, pos, w.getBlockState(pos), EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, is)).get(0));
		
		if(dust != null){
			
			ItemStack dust2 = new ItemStack(dust.getItem(),1,dust.getItemDamage());
			
			
					
			if(!w.isRemote){
		
					w.setBlockToAir(pos);
					
					w.getBlockState(pos).getBlock().spawnAsEntity(w, pos, dust2);
			}
		
		}else
			return false;
		
		
		
		return true;
	}


	@Override
	public ItemStack affectItem(ItemStack is) {
		
		ItemStack r = FurnaceRecipes.instance().getSmeltingResult(is);
		
		return r != null ? new ItemStack(r.getItem(),is.getCount(),r.getItemDamage()) : null;
	}
	

	@Override
	public int getPriority() {
	
		return 5;
	}
	
public List<ItemStack> getTarget(World w, BlockPos pos, int fortune){
		
	
		
		return w.getBlockState(pos).getBlock().getDrops(w, pos, w.getBlockState(pos), 0);
		
	}


@Override
public EnumParticleTypes getParticle() {
	
	return EnumParticleTypes.FLAME;
}

@Override
public Color getSocketColor() {
	
	return Color.RED;
}


@Override
public ItemStack getChestItem(IInventory ii) {

	for(int i = 0; i<ii.getSizeInventory();i++){
		
		if(ii.getStackInSlot(i) != null){
			
			if(TileEntityFurnace.isItemFuel(ii.getStackInSlot(i))){
				
				return ii.getStackInSlot(i);
			}
			
		}
		
	}
	
	
	return null;
}


}
