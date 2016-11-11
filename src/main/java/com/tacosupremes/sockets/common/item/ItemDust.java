package com.tacosupremes.sockets.common.item;

import java.util.List;

import com.tacosupremes.sockets.common.lib.LibMisc;
import com.tacosupremes.sockets.common.recipes.ModRecipes;
import com.tacosupremes.sockets.common.utils.RecHolder;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.oredict.OreDictionary;

public class ItemDust extends ItemMod {

	private String[] s; 


	public ItemDust(String[] s) {
		super("dust", s.length-1);

		this.s = s;
		
		for (int i = 0; i< s.length; i++){
			
			if(OreDictionary.getOres("ingot" + s[i]) == null || OreDictionary.getOres("ingot" + s[i]).isEmpty())
				continue;
			
			ModRecipes.recs.add(new RecHolder(OreDictionary.getOres("ingot" + s[i]).get(0), false, true, new ItemStack(this,1,i)));
		
			LibMisc.Ores.oL.put("dust" + s[i], new ItemStack(this,1,i));
		}
		
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> l) {
			
			for(int i = 0; i<=meta; i++){
				
				if(!OreDictionary.getOres("ingot"+s[i]).isEmpty())
					l.add(new ItemStack(this,1,i));
			}
			
			
		}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
	
		return OreDictionary.getOres("ingot" + s[stack.getItemDamage()]).isEmpty() ? "ERROR" : OreDictionary.getOres("ingot" + s[stack.getItemDamage()]).get(0).getDisplayName().replace(I18n.translateToLocal(LibMisc.MODID+"." + "ingot"), I18n.translateToLocal(LibMisc.MODID+"." + "dust"));
	}

	

}
