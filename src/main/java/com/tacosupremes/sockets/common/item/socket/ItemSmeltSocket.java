package com.tacosupremes.sockets.common.item.socket;

import com.tacosupremes.sockets.common.lib.LibMisc;
import com.tacosupremes.sockets.common.utils.BlockUtils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSmeltSocket extends ItemSocket {

	public ItemSmeltSocket() {
		super("smeltSocket", SocketType.Item);
	
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
		// TODO Auto-generated method stub
		ItemStack r = FurnaceRecipes.instance().getSmeltingResult(is);
		
		return r != null ? new ItemStack(r.getItem(),is.stackSize,r.getItemDamage()) : null;
	}
	

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 3;
	}
	

}
