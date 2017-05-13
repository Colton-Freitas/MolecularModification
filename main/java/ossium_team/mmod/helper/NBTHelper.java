package ossium_team.mmod.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {

    public static boolean hasNBT (ItemStack stackIn) {
        return stackIn.hasTagCompound();
    }

    public static void initNBT (ItemStack stackIn) {
        if (!hasNBT(stackIn))
            stackIn.setTagCompound(new NBTTagCompound());
    }

    public static NBTTagCompound getNBT (ItemStack stackIn) {
        initNBT(stackIn);
        return stackIn.getTagCompound();
    }

    public static void setInt (ItemStack stackIn, String tag, int value) {
        getNBT(stackIn).setInteger(tag, value);
    }

    public static int getInt (ItemStack stackIn, String tag, int expected) {
        return verify(stackIn, tag) ? getNBT(stackIn).getInteger(tag) : expected;
    }

    public static void setBool (ItemStack stackIn, String tag, boolean value) {
        getNBT(stackIn).setBoolean(tag, value);
    }

    public static boolean getBool (ItemStack stackIn, String tag, boolean expected) {
        return verify(stackIn, tag) ? getNBT(stackIn).getBoolean(tag) : expected;
    }

    public static boolean verify(ItemStack stackIn, String tag) {
        return getNBT(stackIn).hasKey(tag);
    }


}
