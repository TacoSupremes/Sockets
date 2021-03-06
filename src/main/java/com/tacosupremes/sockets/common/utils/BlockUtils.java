package com.tacosupremes.sockets.common.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockUtils {
	/*
	public static BlockPos[] createBlockRange(BlockPos center, int range){
		
		BlockPos[] b = new BlockPos[(int) (Math.pow(2*range+1, 3))];
		
		int i =0;
		
		for(int xD = -range;xD<=range;xD++){
			for(int yD = -range;yD<=range;yD++){
				for(int zD = -range;zD<=range;zD++){
					
					b[i] = center.add(xD, yD, zD);
					i++;
					
				}
			}
			
		}
		
		
		
		
		
		return b;
	}
	
public static BlockPos[] createBlockRange(BlockPos center, int xR, int yR, int zR){
		
		BlockPos[] b = new BlockPos[(2*xR+1)*(2*yR+1)*(2*zR+1)];
		
		int i =0;
		
		for(int xD = -xR;xD<=xR;xD++){
			for(int yD = -yR;yD<=yR;yD++){
				for(int zD = -zR;zD<=zR;zD++){
					
					b[i] = center.add(xD, yD, zD);
					i++;
					
				}
			}
			
		}
		
		
		
		
		
		return b;
	}

*/
public static IBlockState fromItemStack(ItemStack is){
	
	Block b = Block.getBlockFromItem(is.getItem());
	if(b == null)
		return null;
	
	
	return b.getStateFromMeta(is.getItemDamage());
	
}

public static ItemStack toItemStack(IBlockState s){
	

	return new ItemStack(s.getBlock(),1,s.getBlock().getMetaFromState(s));
}

public static int getMeta(World w, BlockPos pos){
	
	return w.getBlockState(pos).getBlock().getMetaFromState(w.getBlockState(pos));
	
}
  
  public static List<BlockPos> getConnectedBlocks(World w, BlockPos pos, int r, IBlockState ib){
		
	  return getConnectedBlocks(w, pos, null, null, true, r, ib);
  }
  
  private static List<BlockPos> getConnectedBlocks(World w, BlockPos pos, List<BlockPos> b, List<String> s, boolean firstTime, int r, IBlockState ib){
		
		
		if(firstTime){
			
			List<BlockPos> b2 = new ArrayList<BlockPos>();
			List<String> s2 = new ArrayList<String>();
			
			for(int xD = -r;xD<=r;xD++){
				for(int yD = -r;yD<=r;yD++){
					for(int zD = -r;zD<=r;zD++){
						
						if(xD==0 &&yD==0&&zD==0)
							continue;
						
						BlockPos bp = pos.add(xD, yD, zD);
					
						
						if(w.getBlockState(bp).getBlock() == ib.getBlock() && ib.getBlock().getMetaFromState(ib) == w.getBlockState(bp).getBlock().getMetaFromState(w.getBlockState(bp))){
							
							
							b2.add(bp);
							s2.add(bp.toString());
							continue;
						}
						
						
						
						
						
						
						
				}
				
				}
			}
				
			return getConnectedBlocks(w, pos, b2, s2, false, r, ib);
			}else{
				
				boolean blocksAdded = false;
				
				
				for(int i=0; i<b.size();i++){
					
					BlockPos pos2 = b.get(i);
				for(int xD = -r;xD<=r;xD++){
					for(int yD = -r;yD<=r;yD++){
						for(int zD = -r;zD<=r;zD++){
							
							if(xD==0 &&yD==0&&zD==0)
								continue;
							
							
							BlockPos bp = pos2.add(xD, yD, zD);
						
							
							if(s.contains(bp.toString()))
								continue;
							
					
							if(w.getBlockState(bp).getBlock() == ib.getBlock() && ib.getBlock().getMetaFromState(ib) == w.getBlockState(bp).getBlock().getMetaFromState(w.getBlockState(bp))){
								
								
								b.add(bp);
								s.add(bp.toString());
								blocksAdded = true;
								continue;
							}
							
							
							
							
							
							
							
					}
					
					}
				}
				
				}
				
				
				
				
				if(blocksAdded)
					return getConnectedBlocks(w, pos, b, s, false, r, ib);
				
			}
		
		return b;
		
	}

  public static List<BlockPos> getConnectedLogs(World w, BlockPos pos, int r){
		
	  return getConnectedLogs(w, pos, null, null, true, r);
  }
  
  private static List<BlockPos> getConnectedLogs(World w, BlockPos pos, List<BlockPos> b, List<String> s, boolean firstTime, int r){
		
		
		if(firstTime){
			
			List<BlockPos> b2 = new ArrayList<BlockPos>();
			List<String> s2 = new ArrayList<String>();
			
			for(int xD = -r;xD<=r;xD++){
				for(int yD = -r;yD<=r;yD++){
					for(int zD = -r;zD<=r;zD++){
						
						if(xD==0 &&yD==0&&zD==0)
							continue;
						
						BlockPos bp = pos.add(xD, yD, zD);
					
						
						if(w.getBlockState(bp).getBlock().isWood(w,bp)){
							
							
							b2.add(bp);
							s2.add(bp.toString());
							continue;
						}
						
						
						
						
						
						
						
				}
				
				}
			}
				
			return getConnectedLogs(w, pos, b2, s2, false, r);
			}else{
				
				boolean blocksAdded = false;
				
				
				for(int i=0; i<b.size();i++){
					
					BlockPos pos2 = b.get(i);
				for(int xD = -r;xD<=r;xD++){
					for(int yD = -r;yD<=r;yD++){
						for(int zD = -r;zD<=r;zD++){
							
							if(xD==0 &&yD==0&&zD==0)
								continue;
							
							
							BlockPos bp = pos2.add(xD, yD, zD);
						
							
							if(s.contains(bp.toString()))
								continue;
							
					
							if(w.getBlockState(bp).getBlock().isWood(w,bp)){
										
								
								b.add(bp);
								s.add(bp.toString());
								blocksAdded = true;
								continue;
							}
							
							
							
							
							
							
							
					}
					
					}
				}
				
				}
				
				
				
				
				if(blocksAdded)
					return getConnectedLogs(w, pos, b, s, false, r);
				
				
				
			}
			
			
		
		
		
		return b;
		
	}
  
  
 
  
  public static void drawLine(World w, Vector3 start, Vector3 end, EnumParticleTypes type){
	  
	  double i = 0;
	  
	  
	 double vectorLength = Math.sqrt(Math.pow((start.getX()-end.getX()),2)+Math.pow((start.getY()-end.getY()),2)+Math.pow((start.getZ()-end.getZ()),2));
	 
	 while (i <= vectorLength){
		 
		 double xD = end.getX() - start.getX();
		 double yD = end.getY() - start.getY();
		 double zD = end.getZ() - start.getZ();
		 
		 double dL = i / vectorLength;
		 
		 xD *= dL;
		 yD *= dL;
		 zD *= dL;
		
		 w.spawnParticle(type, start.getX()+xD,start.getY()+yD,start.getZ()+zD, 0, 0, 0, 0);
			 
		 
		 i++;
	 }
	  
	  
	  
  }
  
  public static boolean isBlockPowered(World w, BlockPos pos){
	  
	
		  return w.isBlockPowered(pos);
	  

  }
  
  public static void fillBlock(World w, BlockPos pos,  EnumParticleTypes type){
	  
	  for(int i = 0; i<=40; i++){
		  
		  w.spawnParticle(type, pos.getX() + 0.5D + Math.random()-Math.random(), pos.getY() + 0.5D+Math.random()-Math.random(), pos.getZ() + 0.5D + Math.random()-Math.random(), 0, 0, 0, 0);
	  }
	  
  }
  
  public static RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids, double r)
  {
      float f = playerIn.rotationPitch;
      float f1 = playerIn.rotationYaw;
      double d0 = playerIn.posX;
      double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
      double d2 = playerIn.posZ;
      Vec3d vec3d = new Vec3d(d0, d1, d2);
      float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
      float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
      float f4 = -MathHelper.cos(-f * 0.017453292F);
      float f5 = MathHelper.sin(-f * 0.017453292F);
      float f6 = f3 * f4;
      float f7 = f2 * f4;
      double d3 = r;
      Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
      return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
  }


public static RayTraceResult rayTrace2(World worldIn, EntityPlayer playerIn, double r)
  {
      float f = playerIn.rotationPitch;
      float f1 = playerIn.rotationYaw;
      double d0 = playerIn.posX;
      double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
      double d2 = playerIn.posZ;
      Vec3d vec3d = new Vec3d(d0, d1, d2);
      float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
      float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
      float f4 = -MathHelper.cos(-f * 0.017453292F);
      float f5 = MathHelper.sin(-f * 0.017453292F);
      float f6 = f3 * f4;
      float f7 = f2 * f4;
      double d3 = r;
      Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
      return worldIn.rayTraceBlocks(vec3d, vec3d1, false, true, false);
  }


public static Entity rayTraceEntity(World w, EntityPlayer player, int r2){
	 
	 RayTraceResult r = rayTrace2(w, player, r2);
	 
	 if(r == null)
		 return null;
	 
	 List<Entity> e2 =	w.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.getPosition(), r.getBlockPos()).expand(1, 1, 1));
	 
	 if(e2.isEmpty())
		 return null;
	 
	 float d = -1;
	 int in = -1;
	 
	 for(int i = 0; i<e2.size(); i++){
		 
		 if(e2.get(i).getDistanceToEntity(player) < d || d == -1){
			 in = i;
			 d = e2.get(i).getDistanceToEntity(player);
		 }
	 }
	 
	 return e2.get(in);
}



}
