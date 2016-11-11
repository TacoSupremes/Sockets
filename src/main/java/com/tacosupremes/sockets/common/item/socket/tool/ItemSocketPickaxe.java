package com.tacosupremes.sockets.common.item.socket.tool;

import com.tacosupremes.sockets.Sockets;
import com.tacosupremes.sockets.common.item.ModItems;
import com.tacosupremes.sockets.common.item.socket.ItemSocket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemSocketPickaxe extends ItemPickaxe{

	public ItemSocketPickaxe() {
		super(ToolMaterial.DIAMOND);
		this.setUnlocalizedName("socketPickaxe");
		this.setRegistryName("socketPickaxe");
		this.setCreativeTab(Sockets.tab);
	
		GameRegistry.register(this);
		
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		
	ItemSocket i = ItemSocket.getSocket(itemstack, 1);
	
	if(i != null)
		i.onBlockStartBreak(itemstack, pos, player);
	
		
		return super.onBlockStartBreak(itemstack, pos, player);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		
		ItemSocket.setSocket(itemStackIn, (ItemSocket)ModItems.grindSocket, 1);
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	

}
