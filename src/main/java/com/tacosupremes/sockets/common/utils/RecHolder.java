package com.tacosupremes.sockets.common.utils;

import net.minecraft.item.ItemStack;

public class RecHolder {
	
	
	private ItemStack out;
	int id;
	private Object[] o;

	public ItemStack getOut() {
		return out;
	}

	public boolean isShapeless() {
		return id == 0;
	}

	public boolean isFurnace() {
		return id == 1;
	}
	
	public boolean isGrinder() {
		return id == 2;
	}

	public Object[] getO() {
		return o;
	}

	public RecHolder(ItemStack stack, int id, Object... recipeComponents){
		this.out = stack;
		this.id = id;
		this.o = recipeComponents;
	}

}
