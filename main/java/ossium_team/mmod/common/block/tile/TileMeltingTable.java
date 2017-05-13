package ossium_team.mmod.common.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import ossium_team.mmod.common.item.ModItems;
import ossium_team.mmod.common.recipies.ModMeltingTableRecipe;
import ossium_team.mmod.helper.StackLogicHelper;

public class TileMeltingTable extends BaseModTile{

    public TileMeltingTable() {
        itemHandler = new ItemStackHandler(3);
        invSize = 3;
    }

    @Override
    public void update() {
        if(!world.isRemote) {
            if (!itemHandler.getStackInSlot(0).isEmpty() &&
                    !itemHandler.getStackInSlot(1).isEmpty()) {
                ModMeltingTableRecipe recipeResult = ModMeltingTableRecipe.isValidRecipie(
                        itemHandler.getStackInSlot(0), itemHandler.getStackInSlot(1)
                );
                if (recipeResult != null && StackLogicHelper.canAddTo(itemHandler.getStackInSlot(2), recipeResult.getRecipieResult())) {
                    operationTicks++;
                    operationTicks %= 200;
                    world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
                    hasClientUpdated = false;
                    if (operationTicks == 199) {
                        if (itemHandler.getStackInSlot(2).isEmpty())
                            itemHandler.setStackInSlot(2, recipeResult.getRecipieResult());
                        else
                            itemHandler.getStackInSlot(2).grow(recipeResult.getRecipieResult().getCount());
                        itemHandler.getStackInSlot(0).shrink(1);
                        if (!(itemHandler.getStackInSlot(1).isItemEqual(new ItemStack(ModItems.itemConcaveMould)) ||
                                itemHandler.getStackInSlot(1).isItemEqual(new ItemStack(ModItems.itemConvexMould))))
                            itemHandler.getStackInSlot(1).shrink(1);
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
}
