package dayz.common.items;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import dayz.DayZ;

public class ItemEmptyBottle extends Item
{
    /** field for checking if the bucket has been filled. */
    private int isFull;
    private boolean isRefillable;

    public ItemEmptyBottle(int i, int j, boolean refillable)
    {
        super(i);
        maxStackSize = 1;
        this.isFull = j;
        this.isRefillable = refillable;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if (this.isRefillable == true)
    	{
	        MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
	
	        if (var4 == null)
	        {
	            return par1ItemStack;
	        }
	        else
	        {
	            if (var4.typeOfHit == EnumMovingObjectType.TILE)
	            {
	                int var5 = var4.blockX;
	                int var6 = var4.blockY;
	                int var7 = var4.blockZ;
	
	                if (!par2World.canMineBlock(par3EntityPlayer, var5, var6, var7))
	                {
	                    return par1ItemStack;
	                }
	
	                if (par2World.getBlockMaterial(var5, var6, var7) == Material.water)
	                {
	                    --par1ItemStack.stackSize;
	
	                    if (par1ItemStack.stackSize <= 0)
	                    {
	                        return new ItemStack(DayZ.waterbottledirty);
	                    }
	
	                    if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DayZ.waterbottledirty)))
	                    {
	                        par3EntityPlayer.dropPlayerItem(new ItemStack(DayZ.waterbottledirty, 1, 0));
	                    }
	                }
	            }
		    }

    	}
        return par1ItemStack;
    }
    
    @Override
    public String getTextureFile()
    {
        return "/dayz/images/food.png";
    }
}
