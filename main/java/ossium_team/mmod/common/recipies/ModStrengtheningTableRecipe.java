package ossium_team.mmod.common.recipies;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ossium_team.mmod.common.item.ModItems;

public class ModStrengtheningTableRecipe {

    private ItemStack ret;

    private ModStrengtheningTableRecipe (ItemStack ret) {
        this.ret = ret;
    }

    public static ModStrengtheningTableRecipe isValidRecipe(ItemStack stack) {
        if(stack.isEmpty())
            return null;
        Item item = stack.getItem();

        if(item.equals(Items.IRON_INGOT))
            return new ModStrengtheningTableRecipe(new ItemStack(ModItems.itemStrengthenedIronIngot));
        else if (item.equals(Items.DIAMOND))
            return new ModStrengtheningTableRecipe(new ItemStack(ModItems.itemStrengthenedDiamond));
        return null;
    }

    public ItemStack getRecipeReturn() {
        return ret;
    }
}
