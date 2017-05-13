package ossium_team.mmod.common.item.tools;

import net.minecraft.item.ItemStack;

public interface IToolNBTInstanciation {

    ItemStack baseNBT(ItemStack stackIn, Integer hardness, Integer efficiency, Integer harvest, Integer range, Integer maxRange, Integer silk, Integer fortune, Integer selfRepair);
}
