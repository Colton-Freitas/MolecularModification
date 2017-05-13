package ossium_team.mmod.common.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ossium_team.mmod.common.block.tile.TileStrengtheningTable;
import ossium_team.mmod.common.recipies.ModStrengtheningTableRecipe;

import javax.annotation.Nonnull;

public class ContainerStrengtheningTable extends Container {

    private TileStrengtheningTable tile;

    public ContainerStrengtheningTable(IInventory playerInv, TileStrengtheningTable tile) {
        this.tile = tile;
        IItemHandler handler = tile.getItemHandler();

        this.addSlotToContainer(new StrengtheningTableFirstSlot(handler, 0, 31, 34));
        this.addSlotToContainer(new StrengtheningTableSecondSlot(handler, 1, 77, 34));
        this.addSlotToContainer(new StrengtheningTableThirdSlot(handler, 2, 123, 34));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        if(index > 2) {
            if(getSlot(index).getHasStack())
            {
                for (int i = 0; i < 2; i++) {
                    if(getSlot(i).isItemValid(getSlot(index).getStack()))
                        mergeItemStack(getSlot(index).getStack(), i, i + 1, false);
                }
            }
        }
        else if (index < 3){
            if(getSlot(index).getHasStack())
                mergeItemStack(getSlot(index).getStack(), 3, inventorySlots.size(), true);
        }
        return ItemStack.EMPTY;
    }


    private class StrengtheningTableFirstSlot extends SlotItemHandler {

        public StrengtheningTableFirstSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(new ItemStack(Blocks.STONE, 1, 0).getItem());
        }
    }

    private class StrengtheningTableSecondSlot extends SlotItemHandler {
        public StrengtheningTableSecondSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return ModStrengtheningTableRecipe.isValidRecipe(stack) != null;
        }
    }

    private class StrengtheningTableThirdSlot extends SlotItemHandler {
        public StrengtheningTableThirdSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return false;
        }
    }
}
