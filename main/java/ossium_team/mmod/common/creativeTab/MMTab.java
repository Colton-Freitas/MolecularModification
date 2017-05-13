package ossium_team.mmod.common.creativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import ossium_team.mmod.common.item.ModItems;
import ossium_team.mmod.common.lib.ConstLib;

public class MMTab extends CreativeTabs {

    public MMTab () {
        super(ConstLib.Name);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.itemStrengthenedDiamond);
    }
}
