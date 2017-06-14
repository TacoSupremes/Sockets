package com.tacosupremes.sockets.common.item;

import java.awt.Color;
import java.util.List;

import com.tacosupremes.sockets.Sockets;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemMod extends Item{
	
	public int meta;
	
	
	
	public ItemMod(String s, int meta){	
		super();
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(Sockets.tab);
		if(meta > 0)
			this.setHasSubtypes(true);
		GameRegistry.register(this);
		ModItems.items.add(this);
		this.meta = meta;
	}

	
	public ItemMod(String s){
		this(s,0);
	}
	
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		
		if(stack.getItemDamage() > 0 && this.meta > 0)
			return super.getUnlocalizedName()+stack.getItemDamage();
		
		
		return super.getUnlocalizedName(stack);
	}



	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		
		if(this.meta == 0 || needsDifferentNames())
			return super.getItemStackDisplayName(stack);
		
		
		
		
		return I18n.translateToLocal(this.getUnlocalizedName(stack).replace(String.valueOf(stack.getMetadata()), "")+".name");
	}
	
	
	
	
	
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> l) {
		if(!needsDifferentNames())
		super.getSubItems(itemIn, tab, l);
		else{
			
			for(int i = 0; i<=meta; i++){
				l.add(new ItemStack(this,1,i));
			}
			
			
		}
			
	}

	

	public boolean needsDifferentNames(){
		
		return false;
	}


	public boolean skipVariants() {
		
		return false;
	}
	
	public IItemColor getColor(){
		
		
		return null;
	}
	
	
	
}
