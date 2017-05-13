package ossium_team.mmod.common.recipies;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ossium_team.mmod.common.item.ModItems;

public class ModMeltingTableRecipe {

    private ItemStack ret;

    private ModMeltingTableRecipe(ItemStack ret) {
        this.ret = ret;
    }

    public static ModMeltingTableRecipe isValidRecipie(ItemStack stack1, ItemStack stack2) {
        if(stack1.isEmpty() || stack2.isEmpty())
            return null;
        if (stack1.getItem().equals(new ItemStack(Blocks.GLASS).getItem()) && stack2.getItem().equals(ModItems.itemConcaveMould)) {
            return new ModMeltingTableRecipe(new ItemStack(ModItems.itemConcaveLens));
        } else if (stack1.getItem().equals(new ItemStack(Blocks.GLASS).getItem()) && stack2.getItem().equals(ModItems.itemConvexMould))
            return new ModMeltingTableRecipe(new ItemStack(ModItems.itemConvexLens));
        else if (stack1.getItem().equals(Items.GLOWSTONE_DUST) && (stack2.getItem().equals(ModItems.itemConcaveLens)))
            return new ModMeltingTableRecipe(new ItemStack(ModItems.itemEnlightenedConcaveLens, 1));
        else if (stack1.getItem().equals(Items.GLOWSTONE_DUST) && (stack2.getItem().equals(ModItems.itemConvexLens)))
            return new ModMeltingTableRecipe(new ItemStack(ModItems.itemEnlightenedConvexLens, 1));
        else if (stack1.getItem().equals(Items.ENDER_PEARL) && (stack2.getItem().equals(ModItems.itemEnlightenedConcaveLens)))
            return new ModMeltingTableRecipe(new ItemStack(ModItems.itemEnderGlazedConcaveLens));
        else if (stack1.getItem().equals(Items.ENDER_PEARL) && (stack2.getItem().equals(ModItems.itemEnlightenedConvexLens)))
            return new ModMeltingTableRecipe(new ItemStack(ModItems.itemEnderGlazedConvexLens));
        return null;
    }

    public ItemStack getRecipieResult() {
        return ret;
    }
}
