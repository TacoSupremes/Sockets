package com.tacosupremes.sockets.common.item.socket;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;

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

}
