package com.tacosupremes.sockets.common.utils;

import net.minecraft.item.ItemStack;

public class RecHolder {
	
	
	private ItemStack out;
	private boolean shapeless;
	private boolean furnace;
	private Object[] o;

	public ItemStack getOut() {
		return out;
	}

	public boolean isShapeless() {
		return shapeless;
	}

	public boolean isFurnace() {
		return furnace;
	}

	public Object[] getO() {
		return o;
	}

	public RecHolder(ItemStack stack, boolean shapeless, boolean furnace, Object... recipeComponents){
		this.out = stack;
		this.shapeless = shapeless;
		this.furnace = furnace;
		this.o = recipeComponents;
	}

}
