package ossium_team.mmod.common.recipies;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ossium_team.mmod.common.item.ModItems;

public class ModReinforcingTableRecipe {

    private ItemStack ret;

    private ModReinforcingTableRecipe(ItemStack ret) {
        this.ret = ret;
    }

    public ItemStack getRecipeReturn() {
        return ret;
    }

    public static ModReinforcingTableRecipe isValidRecipe(ItemStack stackIn) {
        Item stackItem = stackIn.getItem();
        if(stackItem.equals(Items.IRON_INGOT))
            return new ModReinforcingTableRecipe(new ItemStack(ModItems.itemReinforcedIron));
        else if(stackItem.equals(Items.DIAMOND))
            return new ModReinforcingTableRecipe(new ItemStack(ModItems.itemReinforcedDiamond));
        else if(stackItem.equals(Items.STICK))
            return new ModReinforcingTableRecipe(new ItemStack(ModItems.itemReinforcedStick));
        else
            return null;
    }
}
