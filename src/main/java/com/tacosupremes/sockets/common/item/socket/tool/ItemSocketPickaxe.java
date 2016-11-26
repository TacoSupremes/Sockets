package com.tacosupremes.sockets.common.item.socket.tool;

import java.util.ArrayList;
import java.util.List;

import com.tacosupremes.sockets.Sockets;
import com.tacosupremes.sockets.common.item.ModItems;
import com.tacosupremes.sockets.common.item.socket.ISocketable;
import com.tacosupremes.sockets.common.item.socket.ItemSocket;
import com.tacosupremes.sockets.common.utils.BlockUtils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemSocketPickaxe extends ItemPickaxe implements ISocketable{

	public ItemSocketPickaxe() {
		super(ToolMaterial.DIAMOND);
		this.setUnlocalizedName("socketPickaxe");
		this.setRegistryName("socketPickaxe");
		this.setCreativeTab(Sockets.tab);
		ModItems.nitems.add(this);
		GameRegistry.register(this);
		
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		
		World w = player.getEntityWorld();
		if(!itemstack.hasTagCompound())
			return false;
		
		if(w.getBlockState(pos).getBlock().getDrops(w, pos, w.getBlockState(pos), 0).isEmpty())
			return false;
		
		if(ItemSocket.getSockets(itemstack).isEmpty())
			return false;
		List<ItemStack> result = ItemSocket.getSockets(itemstack).get(0).getTarget(w, pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack));
		
		//boolean workDone = false;
		List<ItemSocket> us = new ArrayList<ItemSocket>();
	for(ItemSocket i : ItemSocket.getSockets(itemstack)){
		List<ItemStack> result2 = new ArrayList<ItemStack>();
		
		
		for(ItemStack is : result){
			
			
			if(is == null)
				continue;
			
			ItemStack r = i.affectItem(is.copy());
			
			System.out.println(r == null ? "NULL" : r.getDisplayName());
			if(r != null){
				result2.add(r.copy());
				System.out.println("R ADDED");
		//		workDone = true;
				us.add(i);
			}else
				result2.add(is.copy());
		}
		
		result = new ArrayList<ItemStack>();
		result.addAll(result2);
		
	}
	
//	if(result.isEmpty())
//		return false;
	
	
	boolean ff = ItemStack.areItemsEqual(result.get(0), ItemSocket.getSockets(itemstack).get(0).getTarget(w, pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack)).get(0));
	
	if(!us.isEmpty()){
		
		for(ItemSocket i : us){
			BlockUtils.fillBlock(w, pos, i.getParticle());
		}
		
		if(!w.isRemote){
					
			w.setBlockToAir(pos);
			
			
			for(ItemStack is : result){
				w.getBlockState(pos).getBlock().spawnAsEntity(w, pos, is.copy());
			}
	}
			
		return true;
	}
		
		return false;
	}
		
}
