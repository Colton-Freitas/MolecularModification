package ossium_team.mmod.common.item.tools;

import net.minecraft.item.ItemStack;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.helper.NBTHelper;

public class MMSword extends MMSwordBase {

    public MMSword() {
        super("item_mm_sword");
    }

    @Override
    public ItemStack baseNBT(ItemStack stackIn, Integer hardness, Integer efficiency, Integer harvest, Integer range, Integer maxRange, Integer silk, Integer fortune, Integer selfRepair) {
        super.baseNBT(stackIn, hardness, efficiency, harvest, range, maxRange, silk, fortune, selfRepair);
        int efficiencyValueNBT = NBTHelper.getInt(stackIn, TagLib.MM_EFFICIENCY, 0);
        if (efficiencyValueNBT / 10 < 10)
            NBTHelper.setInt(stackIn, TagLib.MM_EFFICIENCY, 10);
        else
            NBTHelper.setInt(stackIn, TagLib.MM_EFFICIENCY, efficiencyValueNBT / 10);
        return stackIn;
    }
}
