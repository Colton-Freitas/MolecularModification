package ossium_team.mmod.common.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import ossium_team.mmod.common.item.ModItems;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.common.recipies.ModReinforcementValues;
import ossium_team.mmod.common.recipies.ModReinforcingTableRecipe;
import ossium_team.mmod.helper.NBTHelper;
import ossium_team.mmod.helper.StackLogicHelper;

public class TileReinforcingTable extends BaseModTile {

    public TileReinforcingTable() {
        itemHandler = new ItemStackHandler(12);
        invSize = 12;
    }

    @Override
    public void update() {
        if(!world.isRemote)
        {
            if (!(itemHandler.getStackInSlot(9).isEmpty()) && StackLogicHelper.canAddTo(itemHandler.getStackInSlot(9), itemHandler.getStackInSlot(11))
                    && ModReinforcingTableRecipe.isValidRecipe(itemHandler.getStackInSlot(9)) == null)
            {
                if(!itemHandler.getStackInSlot(11).isEmpty())
                {
                    itemHandler.getStackInSlot(9).shrink(1);
                    itemHandler.getStackInSlot(11).grow(1);
                }
                else if(itemHandler.getStackInSlot(11).isEmpty())
                {
                    itemHandler.setStackInSlot(11, itemHandler.getStackInSlot(9).copy());
                    itemHandler.getStackInSlot(9).shrink(1);
                }
            }
            else if(itemHandler.getStackInSlot(9).isEmpty() && !itemHandler.getStackInSlot(10).isEmpty()) {
                itemHandler.setStackInSlot(9, new ItemStack(itemHandler.getStackInSlot(10).getItem()));
                itemHandler.getStackInSlot(10).shrink(1);
            }
            if (hasReinforcingMaterials() && ModReinforcingTableRecipe.isValidRecipe(itemHandler.getStackInSlot(9)) != null) {
                operationTicks++;
                operationTicks %= 200;
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                hasClientUpdated = false;
                if (operationTicks == 199) {
                    itemHandler.setStackInSlot(9, calculateNBTForRecipe(ModReinforcingTableRecipe.isValidRecipe(itemHandler.getStackInSlot(9)).getRecipeReturn()));
                    for (int i = 0; i < 9; i++) {
                        if (!itemHandler.getStackInSlot(i).isEmpty())
                            itemHandler.getStackInSlot(i).shrink(1);
                    }
                }
            } else {
                operationTicks = 0;
                if(!hasClientUpdated) {
                    world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                    hasClientUpdated = true;
                }
            }
        }
    }

    private ItemStack calculateNBTForRecipe(ItemStack recipeResult) {
        if (recipeResult.getItem().equals(ModItems.itemReinforcedDiamond) || recipeResult.getItem().equals(ModItems.itemReinforcedIron)) {
            int totalHardness = 0;
            int totalEfficiency = 0;
            for (int i = 0; i < 9; i++) {
                if(this.itemHandler.getStackInSlot(i).isEmpty())
                    continue;
                if(this.getItemHandler().getStackInSlot(i).getItem().equals(ModItems.itemUpgradeCore))
                    totalHardness += NBTHelper.getInt(itemHandler.getStackInSlot(i), TagLib.MM_HARDNESS, 0) / 10;
                else
                    totalHardness += ModReinforcementValues.getHardnessValue(this.itemHandler.getStackInSlot(i));
            }
            for (int i = 0; i < 9; i++) {
                if(this.itemHandler.getStackInSlot(i).isEmpty())
                    continue;
                if(this.getItemHandler().getStackInSlot(i).getItem().equals(ModItems.itemUpgradeCore))
                    totalEfficiency += NBTHelper.getInt(itemHandler.getStackInSlot(i), TagLib.MM_EFFICIENCY, 0) / 10;
                else
                    totalEfficiency += ModReinforcementValues.getEfficiencyValue(this.itemHandler.getStackInSlot(i));
            }
            NBTHelper.initNBT(recipeResult);
            if (recipeResult.getItem().equals(ModItems.itemReinforcedDiamond))
            {
                totalEfficiency *= 2;
                totalHardness *= 2;
            }
            NBTHelper.setInt(recipeResult, TagLib.ITEM_REINFORCEMENT_HARDNESS, totalHardness);
            NBTHelper.setInt(recipeResult, TagLib.ITEM_REINFORCEMENT_EFFICIENCY, totalEfficiency);
            return recipeResult;
        }
        else if(recipeResult.getItem().equals(ModItems.itemReinforcedStick)) {
            int totalHardness = 0;
            int totalFlexibility = 0;
            for (int i = 0; i < 9; i++) {
                if(this.itemHandler.getStackInSlot(i).isEmpty())
                    continue;
                if(this.getItemHandler().getStackInSlot(i).getItem().equals(ModItems.itemUpgradeCore))
                    totalHardness += NBTHelper.getInt(itemHandler.getStackInSlot(i), TagLib.MM_HARDNESS, 0);
                else
                    totalHardness += ModReinforcementValues.getHardnessValue(this.itemHandler.getStackInSlot(i));
            }
            for (int i = 0; i < 9; i++) {
                if(this.itemHandler.getStackInSlot(i).isEmpty())
                    continue;
                totalFlexibility += ModReinforcementValues.getFlexibilityValue(this.itemHandler.getStackInSlot(i));
            }
            NBTHelper.initNBT(recipeResult);
            NBTHelper.setInt(recipeResult, TagLib.ITEM_REINFORCEMENT_HARDNESS, totalHardness);
            NBTHelper.setInt(recipeResult, TagLib.ITEM_REINFORCEMENT_FLEXIBILITY, totalFlexibility);
            return recipeResult;
        }
        else {
            return null;
        }
    }

    private boolean hasReinforcingMaterials () {
        for (int i = 0; i < 9; i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty())
                return true;
        }
        return false;
    }
}
