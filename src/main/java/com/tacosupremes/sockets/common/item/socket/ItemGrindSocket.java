package com.tacosupremes.sockets.common.item.socket;

import com.tacosupremes.sockets.common.lib.LibMisc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemGrindSocket extends ItemSocket {

	public ItemGrindSocket() {
		super("grindSocket", SocketType.Item);
		
		
		
	}

	




	@Override
	public boolean onBlockStartBreak(ItemStack is, BlockPos pos, EntityPlayer player) {
		
		World w = player.getEntityWorld();

		ItemStack dust = LibMisc.Ores.toDust(w.getBlockState(pos));
		
		if(dust != null){
			
			ItemStack dust2 = new ItemStack(dust.getItem(),2,dust.getItemDamage());
					
			if(!w.isRemote){
		
					w.setBlockToAir(pos);
					
					w.getBlockState(pos).getBlock().spawnAsEntity(w, pos, dust2);
			}
		
		}else
			return false;
		
		
		
		return true;
	}
	
	
	

}
