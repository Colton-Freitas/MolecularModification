package ossium_team.mmod.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackLogicHelper {

    public static boolean canAddTo(ItemStack stack1, ItemStack stack2) {
        if (stack1.isEmpty() || stack2.isEmpty())
            return true;
        if(!NBTHelper.hasNBT(stack1) && NBTHelper.hasNBT(stack2))
            return false;
        else if(NBTHelper.hasNBT(stack1) && !NBTHelper.hasNBT(stack2))
            return false;
        else if (NBTHelper.hasNBT(stack1) && NBTHelper.hasNBT(stack2)){
            NBTTagCompound stack1Compound = stack1.getTagCompound();
            NBTTagCompound stack2Compound = stack2.getTagCompound();
            if(!stack1Compound.toString().equals(stack2Compound.toString()))
                return false;
        }
        if (stack1.isItemEqual(stack2)) {
            return stack1.getCount() + stack2.getCount() <= stack1.getMaxStackSize();
        } else return false;
    }
}
