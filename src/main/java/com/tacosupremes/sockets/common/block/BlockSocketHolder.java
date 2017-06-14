package com.tacosupremes.sockets.common.block;

import com.tacosupremes.sockets.common.block.tile.TileSocketHolder;
import com.tacosupremes.sockets.common.item.socket.ItemSocket;
import com.tacosupremes.sockets.common.utils.InventoryHelper;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSocketHolder extends BlockModContainer {

	public BlockSocketHolder() {
		super(Material.ROCK, "socketHolder");
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileSocketHolder();
	}

	@Override
	protected Class<? extends TileEntity> tile() {
		
		return TileSocketHolder.class;
	}

	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {

		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	
		ItemStack is = playerIn.getHeldItem(hand);
		
		if(is != null){
			
			if(is.getItem() instanceof ItemSocket){
				
				IInventory ii = InventoryHelper.getInventory(worldIn, pos);
				
				ii.setInventorySlotContents(0, is);
				
				
					is.shrink(1);
					
				
				
				
				return true;
				
			}
			
		}
		
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	

}
