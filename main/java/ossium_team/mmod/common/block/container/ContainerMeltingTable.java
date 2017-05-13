package ossium_team.mmod.common.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ossium_team.mmod.common.block.tile.TileMeltingTable;
import ossium_team.mmod.common.item.ModItems;

import javax.annotation.Nonnull;

public class ContainerMeltingTable extends Container {

    private TileMeltingTable meltingTable;

    public ContainerMeltingTable(IInventory playerInv, TileMeltingTable meltingTable) {
        this.meltingTable = meltingTable;
        IItemHandler handler = meltingTable.getItemHandler();

        this.addSlotToContainer(new MeltingTableFirstSlot(handler, 0, 32, 43));
        this.addSlotToContainer(new MeltingTableMiddleSlot(handler, 1, 78, 43));
        this.addSlotToContainer(new MeltingTableSlot(handler, 2, 124, 43));

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
        if (index < 3) {
            if (getSlot(index).getHasStack())
                mergeItemStack(getSlot(index).getStack(), 3, inventorySlots.size(), true);
        }
        else if (index > 2)
                if (getSlot(index).getHasStack())
                    for (int i = 0; i < 2; i++)
                        if (getSlot(i).isItemValid(getSlot(index).getStack())) {
                            mergeItemStack(getSlot(index).getStack(), i, i + 1, true);
                            return ItemStack.EMPTY;
                        }
        return ItemStack.EMPTY;
    }

    private class MeltingTableSlot extends SlotItemHandler {

        public MeltingTableSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return false;
        }
    }

    private class MeltingTableMiddleSlot extends SlotItemHandler {

        public MeltingTableMiddleSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        /**
         * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
         * of armor slots)
         */
        @Override
        public int getSlotStackLimit() {
            return 1;
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            Item item = stack.getItem();
            return item.equals(ModItems.itemConcaveMould) ||
                    item.equals(ModItems.itemConvexMould) ||
                    item.equals(ModItems.itemConcaveLens) ||
                    item.equals(ModItems.itemConvexLens) ||
                    item.equals(ModItems.itemEnlightenedConcaveLens) ||
                    item.equals(ModItems.itemEnlightenedConvexLens);
        }
    }

    private class MeltingTableFirstSlot extends SlotItemHandler {

        public MeltingTableFirstSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            Item stackItem = stack.getItem();
            return stackItem.equals(new ItemStack(Blocks.GLASS).getItem()) ||
                    stackItem.equals(Items.GLOWSTONE_DUST) ||
                    stackItem.equals(Items.ENDER_PEARL);
        }
    }
}
