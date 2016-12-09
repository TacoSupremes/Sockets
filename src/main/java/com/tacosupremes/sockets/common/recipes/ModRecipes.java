package com.tacosupremes.sockets.common.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.tacosupremes.sockets.common.block.ModBlocks;
import com.tacosupremes.sockets.common.item.ModItems;
import com.tacosupremes.sockets.common.item.socket.SocketComboRecipe;
import com.tacosupremes.sockets.common.utils.RecHolder;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	
	
	
	public static Map<String, ItemStack[][]> shapeless = new HashMap<String, ItemStack[][]>();
	public static Map<String, ItemStack[][]> shaped = new HashMap<String, ItemStack[][]>();
	public static Map<String, ItemStack> furnace = new HashMap<String, ItemStack>();
	public static Map<String, ItemStack> grinder = new HashMap<String, ItemStack>();
	public static Map<String, ItemStack> grinderio = new HashMap<String, ItemStack>();
	
	public static List<RecHolder> recs = new ArrayList<RecHolder>();

	
	
	
	public static void postInit(){
		
		
	//	addFurnaceRecipe(new ItemStack(ModBlocks.marker), new ItemStack(Blocks.TORCH),5);
		
		addFurnaceRecipe(new ItemStack(ModItems.soulGem,1,1), new ItemStack(Blocks.DIAMOND_BLOCK, 2, 0), 0);
		addGrinderRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.STONE));
		addGrinderRecipe(new ItemStack(Blocks.SAND), new ItemStack(Blocks.COBBLESTONE));
		addGrinderRecipe(new ItemStack(Items.FLINT), new ItemStack(Blocks.GRAVEL));
		addGrinderRecipe(new ItemStack(Items.COAL, 8), new ItemStack(Blocks.COAL_ORE));
		GameRegistry.addRecipe(new SocketComboRecipe());
		
		for(RecHolder r : recs){
			
			if(r.isFurnace()){
				
				addFurnaceRecipe(r.getOut(), (ItemStack)r.getO()[0], 5);
				continue;
			}
			
			if(r.isGrinder()){
				addGrinderRecipe(r.getOut(), (ItemStack)r.getO()[0]);
				continue;
			}
			
			if(r.isShapeless()){
				
				addShapelessRecipe(r.getOut(), (ItemStack[])r.getO());
				continue;
			}
			
			addShapedRecipe(r.getOut(), r.getO());		
			
		}
		
		
	}

	private static void addGrinderRecipe(ItemStack out, ItemStack itemStack) {
	
		grinder.put(out.getUnlocalizedName(), itemStack);
		grinderio.put(itemStack.getUnlocalizedName(), out);
	}

	private static void addShapelessRecipe(ItemStack itemStack, ItemStack... w) {
		GameRegistry.addShapelessRecipe(itemStack, (Object[])w);
		ItemStack is2 = new ItemStack(itemStack.getItem(),1,itemStack.getItemDamage());
		
		ItemStack[][] ls = new ItemStack[w.length][1];
		
		int index = 0;
		for(ItemStack is : w){
			
			ls[index][0] = is;
			index++;
			
		}
		
		shapeless.put(is2.getUnlocalizedName(), ls);
		
		//System.out.println(ls);
		
		
		
	}
	
	private static void addFurnaceRecipe(ItemStack result, ItemStack input, int xp){
		
		
		furnace.put(result.getUnlocalizedName(), input);
		
		GameRegistry.addSmelting(input, result, xp);

	}
	
	
	private static void addShapedRecipe(ItemStack stack, Object... recipeComponents) {
		
		if(stack == null)
			return;
		
		GameRegistry.addShapedRecipe(stack, recipeComponents);
		
		//Borrowed From CraftingManager
		 String s = "";
	        int i = 0;
	        int j = 0;
	        int k = 0;

	        if (recipeComponents[i] instanceof String[])
	        {
	            String[] astring = (String[])((String[])recipeComponents[i++]);

	            for (int l = 0; l < astring.length; ++l)
	            {
	                String s2 = astring[l];
	                ++k;
	                j = s2.length();
	                s = s + s2;
	            }
	        }
	        else
	        {
	            while (recipeComponents[i] instanceof String)
	            {
	                String s1 = (String)recipeComponents[i++];
	                ++k;
	                j = s1.length();
	                s = s + s1;
	            }
	        }

	        Map<Character, ItemStack> map;

	        for (map = Maps.<Character, ItemStack>newHashMap(); i < recipeComponents.length; i += 2)
	        {
	            Character character = (Character)recipeComponents[i];
	            ItemStack itemstack = null;

	            if (recipeComponents[i + 1] instanceof Item)
	            {
	                itemstack = new ItemStack((Item)recipeComponents[i + 1]);
	            }
	            else if (recipeComponents[i + 1] instanceof Block)
	            {
	                itemstack = new ItemStack((Block)recipeComponents[i + 1], 1, 32767);
	            }
	            else if (recipeComponents[i + 1] instanceof ItemStack)
	            {
	                itemstack = (ItemStack)recipeComponents[i + 1];
	            }

	            map.put(character, itemstack);
	        }

	        ItemStack[] aitemstack = new ItemStack[j * k];

	        for (int i1 = 0; i1 < j * k; ++i1)
	        {
	            char c0 = s.charAt(i1);

	            if (map.containsKey(Character.valueOf(c0)))
	            {
	                aitemstack[i1] = ((ItemStack)map.get(Character.valueOf(c0))).copy();
	            }
	            else
	            {
	                aitemstack[i1] = null;
	            }
	        }

	        ItemStack is2 = new ItemStack(stack.getItem(),1,stack.getItemDamage());
			
	        ItemStack[][] ls = new ItemStack[aitemstack.length][1];
			
			int index = 0;
			for(ItemStack is : aitemstack){
				
				ls[index][0] = is;
				index++;
			}
	        
	        shaped.put(is2.getUnlocalizedName(), ls);
	      
	    }

	public static ItemStack[][] getRecipe(ItemStack item) {
		
	ItemStack is2 = new ItemStack(item.getItem(), 1, item.getItemDamage());

		
		return shapeless.get(is2.getUnlocalizedName()) != null ? shapeless.get(is2.getUnlocalizedName()) : shaped.get(is2.getUnlocalizedName());
		
		
		
	
	}
		
	
	private static void addOreDictRecipe(ItemStack output, Object... r) {	
		
		if(output == null)
			return;
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, r));
		
		List<ItemStack[]> l = new ArrayList<ItemStack[]>();
		
		int sCount = 0;
		for(int i = 0; i< r.length; i++){
			
			if(sCount == 3)
				break;
			
			if(r[i] instanceof String){
				sCount++;
				for(char c : ((String)r[i]).toCharArray()){
					
					if(c == ' '){
						l.add(null);
						continue;
					}
					for(int i2 = 0; i2< r.length; i2++){
						
						if(!(r[i2] instanceof Character))
							continue;
						
						if(((Character)r[i2]) == c){
							
							if(r[i2+1] instanceof ItemStack){
								l.add(new ItemStack[]{(ItemStack)r[i2+1]});
							}else{
								l.add(OreDictionary.getOres((String)r[i2+1]).toArray(new ItemStack[OreDictionary.getOres((String)r[i2+1]).size()]));
							}
							
							continue;
							
						}else if(i2 != r.length-1)
							continue;
						else{
							
							System.err.println("RIP RUN");
							
							return;
							
							}
						
					}
					
				}
				
			}
			
			
		}
		
		  ItemStack is2 = new ItemStack(output.getItem(),1,output.getItemDamage());
		
		shaped.put(is2.getUnlocalizedName(), l.toArray(new ItemStack[l.size()][9]));
		
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		
		if(output == null)
			return;
		
		ItemStack[][] ns = new ItemStack[recipe.length][1]; 
		
		int index = 0;
		
		List<String> ls = new ArrayList<String>();
		
		for(Object o : recipe){
			
			if(o instanceof ItemStack){
				
				ns[index][0] = (ItemStack)o;
				index++;
				continue;
			}else if(o instanceof String){
				
				ls.add((String)o);
				continue;
				
				
			}
			
		}
		
		if(ls.isEmpty()){
			
		System.err.println("USE NORMAL SHAPELESSRECIPE");
			return;
		}else{
			
			for(String s : ls){
				
				
				
				ns[index] = OreDictionary.getOres(s).toArray(new ItemStack[OreDictionary.getOres(s).size()]);
				index++;
				
			}
			
			
		}
		  ItemStack is2 = new ItemStack(output.getItem(),1,output.getItemDamage());
		  
		 shapeless.put(is2.getUnlocalizedName(), ns);
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
	
	
	

}
