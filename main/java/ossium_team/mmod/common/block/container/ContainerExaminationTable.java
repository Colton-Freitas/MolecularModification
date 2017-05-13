package ossium_team.mmod.common.block.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ossium_team.mmod.common.block.tile.TileExaminationTable;
import ossium_team.mmod.common.item.BaseItem;
import ossium_team.mmod.common.recipies.ModReinforcementValues;
import ossium_team.mmod.common.shape.MolecularizableBlockRegistry;
import ossium_team.mmod.helper.NBTHelper;

import javax.annotation.Nonnull;

public class ContainerExaminationTable extends Container {

    public ContainerExaminationTable(IInventory playerInventory, TileExaminationTable table) {
        IItemHandler handler = table.getItemHandler();

        this.addSlotToContainer(new ExaminationTableSlot(handler, 0, 11, 9));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        if(index != 0) {
            if (getSlot(index).getHasStack() && getSlot(index).isItemValid(getSlot(index).getStack()))
                mergeItemStack(getSlot(index).getStack(), 0, 1, true);
        }
        else {
            if(getSlot(index).getHasStack())
                mergeItemStack(getSlot(index).getStack(), 1, inventorySlots.size(), true);
        }
        return ItemStack.EMPTY;
    }

    private class ExaminationTableSlot extends SlotItemHandler {

        public ExaminationTableSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            if(stack.getItem() instanceof ItemBlock)
                return  MolecularizableBlockRegistry.getHardness(((ItemBlock) stack.getItem()).getBlock()) != 0;
            else if(stack.getItem() instanceof BaseItem && NBTHelper.hasNBT(stack))
                return true;
            else
                return ModReinforcementValues.getHardnessValue(stack) != 0;
        }
    }
}
