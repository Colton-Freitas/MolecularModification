package ossium_team.mmod.common.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ossium_team.mmod.common.block.tile.TileBlockMoleculizer;
import ossium_team.mmod.common.item.ModItems;

import javax.annotation.Nonnull;

public class ContainerBlockMoleculizer extends Container {

    TileBlockMoleculizer tile;

    public ContainerBlockMoleculizer(IInventory playerInventory, TileBlockMoleculizer tile) {
        this.tile = tile;
        IItemHandler handler = tile.getItemHandler();

        this.addSlotToContainer(new PrimarySlot(handler, 0, 80, 34));

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
        if(index == 0 && !mergeItemStack(tile.getItemHandler().getStackInSlot(0), 1, inventorySlots.size(), true))
            return ItemStack.EMPTY;
        else {
            if (getSlot(index).getHasStack() && getSlot(index).getStack().getItem().equals(ModItems.itemUpgradeCore)) {
                if (!mergeItemStack(getSlot(index).getStack(), 0, 1, true))
                    return ItemStack.EMPTY;
            }
        }
        return ItemStack.EMPTY;
    }

    private class PrimarySlot extends SlotItemHandler {

        public PrimarySlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(ModItems.itemUpgradeCore);
        }
    }
}
