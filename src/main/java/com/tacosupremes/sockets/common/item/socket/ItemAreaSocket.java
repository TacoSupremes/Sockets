package com.tacosupremes.sockets.common.item.socket;

import java.util.ArrayList;
import java.util.List;

import com.tacosupremes.sockets.common.utils.BlockUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemAreaSocket extends ItemSocket {

	public ItemAreaSocket() {
		super("areaSocket", SocketType.Block);
		
	}

	@Override
	public ItemStack affectItem(ItemStack is) {
		
		return null;
	}

	@Override
	public EnumParticleTypes getParticle() {

		return null;
	}

	@Override
	public int getPriority() {
	
		return 1;
	}

	@Override
	public List<BlockPos> getBlocks(World w, BlockPos pos, EntityPlayer player) {
		
		RayTraceResult r = BlockUtils.rayTrace2(w, player, 6);
		
		
		
		List<BlockPos> l = new ArrayList<BlockPos>();
		
		if(r == null)
			return l;
		
	switch(r.sideHit){
	
	case DOWN:{
		for(int xD =-1;xD<=1;xD++){
			for(int zD =-1;zD<=1;zD++){
				l.add(pos.add(xD,0,zD));
			}
		}
		break;
	}
	
	case UP:{
		for(int xD =-1;xD<=1;xD++){
			for(int zD =-1;zD<=1;zD++){
				l.add(pos.add(xD,0,zD));
			}
		}
		break;
	}

	case EAST:{
		for(int xD =-1;xD<=1;xD++){
			for(int zD =-1;zD<=1;zD++){
				l.add(pos.add(0,xD,zD));
			}
		}
		break;
	}

	case WEST:{
		for(int xD =-1;xD<=1;xD++){
			for(int zD =-1;zD<=1;zD++){
				l.add(pos.add(0,xD,zD));
			}
		}
		break;
	}
	
	case NORTH:{
		for(int xD =-1;xD<=1;xD++){
			for(int zD =-1;zD<=1;zD++){
				l.add(pos.add(xD,zD,0));
			}
		}
		break;
	}
	
	case SOUTH:{
		for(int xD =-1;xD<=1;xD++){
			for(int zD =-1;zD<=1;zD++){
				l.add(pos.add(xD,zD,0));
			}
		}
		break;
	}
	
	default:
		break;
	
	}
		
		return l;
	}
	
	
	

}
