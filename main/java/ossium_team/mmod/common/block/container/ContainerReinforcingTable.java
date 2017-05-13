package ossium_team.mmod.common.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ossium_team.mmod.common.block.tile.TileReinforcingTable;
import ossium_team.mmod.common.item.ModItems;
import ossium_team.mmod.common.recipies.ModReinforcementValues;
import ossium_team.mmod.common.recipies.ModReinforcingTableRecipe;
import ossium_team.mmod.helper.NBTHelper;

import javax.annotation.Nonnull;

public class ContainerReinforcingTable extends Container{

    public ContainerReinforcingTable(IInventory playerInventory, TileReinforcingTable tile) {
        IItemHandler handler = tile.getItemHandler();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.addSlotToContainer(new ReinforcingTableFirstSlot(handler, (i * 3) + j, 22 + j * 18, 16 + i * 18));
            }
        }

        this.addSlotToContainer(new ReinforcingTableNoEntrySlot(handler, 9, 137, 34));
        this.addSlotToContainer(new ReinforcingTableLastSlot(handler, 10, 137, 7));
        this.addSlotToContainer(new ReinforcingTableNoEntrySlot(handler, 11, 137, 61));

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

    private class ReinforcingTableFirstSlot extends SlotItemHandler {

        public ReinforcingTableFirstSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return ModReinforcementValues.getEfficiencyValue(stack) != 0 ||
                    ModReinforcementValues.getFlexibilityValue(stack) != 0 ||
                    ModReinforcementValues.getHardnessValue(stack) != 0 || (ModItems.itemUpgradeCore.equals(stack.getItem())
                    && NBTHelper.hasNBT(stack));
        }
    }

    private class ReinforcingTableLastSlot extends SlotItemHandler {
        public ReinforcingTableLastSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super (handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return ModReinforcingTableRecipe.isValidRecipe(stack) != null;
        }
    }

    private class ReinforcingTableNoEntrySlot extends SlotItemHandler {
        public ReinforcingTableNoEntrySlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return false;
        }
    }
}
