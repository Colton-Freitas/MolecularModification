package ossium_team.mmod.common.block.tile;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import ossium_team.mmod.common.item.tools.IToolNBTInstanciation;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.common.recipies.ModMolecularCraftingTableRecipe;
import ossium_team.mmod.helper.NBTHelper;

import java.util.Arrays;

public class TileMolecularCraftingTable extends BaseModTile {

    public TileMolecularCraftingTable() {
        invSize = 10;
        itemHandler = new ItemStackHandler(10);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            ItemStack[] inputItems = new ItemStack[9];
            for (int i = 0; i < inputItems.length; i++)
                inputItems[i] = itemHandler.getStackInSlot(i);
            if(ModMolecularCraftingTableRecipe.isValidRecipe(inputItems) != null && itemHandler.getStackInSlot(9).isEmpty()) {
                operationTicks++;
                operationTicks %= 200;
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                hasClientUpdated = false;
                if (operationTicks == 199) {
                    itemHandler.setStackInSlot(9, finalizeRecipe(ModMolecularCraftingTableRecipe.isValidRecipe(inputItems).getRecipeReturn()));
                }
            } else
            {
                operationTicks = 0;
                if(!hasClientUpdated) {
                    world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                    hasClientUpdated = true;
                }
            }
        }
    }

    private ItemStack finalizeRecipe(ItemStack recipeReturn) {
        ItemStack ret = recipeReturn;
        int handleHardness = 0;
        int handleFlexibility = 0;
        int baseHardness = 0;
        int baseEfficiency = 0;
        for (int i = 0; i < 9; i++) {
            ItemStack current = itemHandler.getStackInSlot(i);
            if (!current.isEmpty()) {
                if (NBTHelper.verify(current, TagLib.ITEM_REINFORCEMENT_FLEXIBILITY)) {
                    handleHardness += NBTHelper.getInt(current, TagLib.ITEM_REINFORCEMENT_HARDNESS, 0);
                    handleFlexibility += NBTHelper.getInt(current, TagLib.ITEM_REINFORCEMENT_FLEXIBILITY, 0);
                } else {
                    baseEfficiency += NBTHelper.getInt(current, TagLib.ITEM_REINFORCEMENT_EFFICIENCY, 0);
                    baseHardness += NBTHelper.getInt(current, TagLib.ITEM_REINFORCEMENT_HARDNESS, 0);
                }
            }
        }
        double flexibilityMod = handleFlexibility > handleHardness ? (double) handleHardness / handleFlexibility : (double) handleFlexibility / handleHardness;
        if (ret.getItem() instanceof IToolNBTInstanciation) {
            ret = ((IToolNBTInstanciation) ret.getItem()).baseNBT(ret, (int) (baseHardness * flexibilityMod), (int)((baseEfficiency * flexibilityMod) / 2.5),
                    3, 0, 0, 0, 0, 0);
            for (int i = 0; i < 9; i++) {
                if (!itemHandler.getStackInSlot(i).isEmpty())
                    itemHandler.getStackInSlot(i).shrink(1);
            }
            return ret;
        }
        else if (!ret.getItem().equals(Items.AIR))
        {
            for (int i = 0; i < 9; i++) {
                if (!itemHandler.getStackInSlot(i).isEmpty())
                    itemHandler.getStackInSlot(i).shrink(1);
            }
            return ret;
        }
        return ItemStack.EMPTY;
    }

}
