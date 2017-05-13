package ossium_team.mmod.common.block.tile;

import net.minecraftforge.items.ItemStackHandler;
import ossium_team.mmod.common.recipies.ModStrengtheningTableRecipe;
import ossium_team.mmod.helper.StackLogicHelper;

public class TileStrengtheningTable extends BaseModTile {

    public TileStrengtheningTable() {
        itemHandler = new ItemStackHandler(3);
        invSize = 3;
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            if (!itemHandler.getStackInSlot(0).isEmpty() && !itemHandler.getStackInSlot(1).isEmpty()) {
                ModStrengtheningTableRecipe recipeResult = ModStrengtheningTableRecipe.isValidRecipe(itemHandler.getStackInSlot(1));
                if (recipeResult != null && StackLogicHelper.canAddTo(itemHandler.getStackInSlot(2), recipeResult.getRecipeReturn())) {
                    operationTicks++;
                    operationTicks %= 200;
                    world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                    hasClientUpdated = false;
                    if (operationTicks == 199) {
                        itemHandler.getStackInSlot(0).shrink(1);
                        itemHandler.getStackInSlot(1).shrink(1);
                        if (itemHandler.getStackInSlot(2).isEmpty())
                            itemHandler.setStackInSlot(2, recipeResult.getRecipeReturn());
                        else
                            itemHandler.getStackInSlot(2).grow(recipeResult.getRecipeReturn().getCount());
                    }
                }
            }
            else {
                operationTicks = 0;
                if(!hasClientUpdated) {
                    world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                    hasClientUpdated = true;
                }
            }
        }
    }
}
