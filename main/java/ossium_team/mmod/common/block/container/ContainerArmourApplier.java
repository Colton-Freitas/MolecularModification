package ossium_team.mmod.common.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ossium_team.mmod.common.block.tile.TileArmourApplier;
import ossium_team.mmod.common.item.ModItems;

import javax.annotation.Nonnull;

public class ContainerArmourApplier extends Container {

    public ContainerArmourApplier(IInventory playerInventory, TileArmourApplier tile) {
        IItemHandler itemHandler = tile.getItemHandler();

        this.addSlotToContainer(new ArmourApplierHeadSlot(itemHandler, 0, 80, 8));
        this.addSlotToContainer(new ArmourApplierChestSlot(itemHandler, 1, 80, 26));
        this.addSlotToContainer(new ArmourApplierLegSlot(itemHandler, 2, 80, 44));
        this.addSlotToContainer(new ArmourApplierBootSlot(itemHandler, 3, 80, 62));

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
        return ItemStack.EMPTY;
    }

    private class ArmourApplierHeadSlot extends SlotItemHandler {

        public ArmourApplierHeadSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(ModItems.itemArmourHead);
        }
    }

    private class ArmourApplierChestSlot extends SlotItemHandler {

        public ArmourApplierChestSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(ModItems.itemArmourChest);
        }
    }

    private class ArmourApplierLegSlot extends SlotItemHandler {

        public ArmourApplierLegSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(ModItems.itemArmourLegs);
        }
    }

    private class ArmourApplierBootSlot extends SlotItemHandler {

        public ArmourApplierBootSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(ModItems.itemArmourBoots);
        }
    }
}
