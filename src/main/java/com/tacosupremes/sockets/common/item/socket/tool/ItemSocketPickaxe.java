package com.tacosupremes.sockets.common.item.socket.tool;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.tacosupremes.sockets.Sockets;
import com.tacosupremes.sockets.common.item.ModItems;
import com.tacosupremes.sockets.common.item.socket.ISocketable;
import com.tacosupremes.sockets.common.item.socket.ItemSocket;
import com.tacosupremes.sockets.common.item.socket.ItemSocket.SocketType;
import com.tacosupremes.sockets.common.utils.BlockUtils;

import net.minecraft.client.renderer.color.IItemColor;
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
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos opos, EntityPlayer player) {
		
		World w = player.getEntityWorld();
		if(!itemstack.hasTagCompound())
			return false;
		
		if(w.getBlockState(opos).getBlock().getDrops(w, opos, w.getBlockState(opos), 0).isEmpty())
			return false;
		
		if(ItemSocket.getSockets(itemstack).isEmpty())
			return false;
		
		if(ItemSocket.getSockets(itemstack).get(0).getBlocks(w, opos, player).isEmpty())
			return false;
		
		if(this.getStrVsBlock(itemstack, w.getBlockState(opos))!= this.efficiencyOnProperMaterial)
			return false;
		
		for(BlockPos pos : ItemSocket.getSockets(itemstack).get(0).getBlocks(w, opos, player)){
		
			if(this.getStrVsBlock(itemstack, w.getBlockState(pos)) != this.efficiencyOnProperMaterial)
				continue;
		List<ItemStack> result = ItemSocket.getSocketsEX(itemstack, SocketType.Block).get(0).getTarget(w, pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack));
		
		
		List<ItemSocket> us = new ArrayList<ItemSocket>();
	for(ItemSocket i : ItemSocket.getSockets(itemstack)){
		List<ItemStack> result2 = new ArrayList<ItemStack>();
		
		if(i.getType() == SocketType.Block){
			us.add(null);
			continue;
		}
		
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
	

	
//	boolean ff = ItemStack.areItemsEqual(result.get(0), ItemSocket.getSockets(itemstack).get(0).getTarget(w, pos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack)).get(0));
	
	if(!us.isEmpty()){
		
		for(ItemSocket i : us){
			if(i!= null)
			BlockUtils.fillBlock(w, pos, i.getParticle());
		}
		
		if(!w.isRemote){
					
			w.setBlockToAir(pos);
			
			
			for(ItemStack is : result){
				w.getBlockState(pos).getBlock().spawnAsEntity(w, pos, is.copy());
			}
	}
	
		
	}
	//	return true;
	}
		
		return false;
	}

public static IItemColor getColor(){
		
		
		return new IItemColor(){

			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				
				if(tintIndex == 0)
					return -1;
				
				List<ItemSocket> s = ItemSocket.getSockets(stack);
				
				if(!s.isEmpty()){
					
					int r = 0;
					int g = 0;
					int b = 0;
					
					for(ItemSocket is : s){
						
						Color c = is.getSocketColor();
						
						r += c.getRed();
						g += c.getGreen();
						b += c.getBlue();
						
					}
				
					return new Color(r / s.size(), g / s.size(), b / s.size()).getRGB();
					
				}else
					return -1;
				
			}
			
		};
	}
		
}
