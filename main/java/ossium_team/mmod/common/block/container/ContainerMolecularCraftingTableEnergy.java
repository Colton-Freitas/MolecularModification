package ossium_team.mmod.common.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ossium_team.mmod.common.block.tile.TileMolecularCraftingTableEnergy;
import ossium_team.mmod.common.item.ModItems;
import ossium_team.mmod.common.item.tools.MMReinforcedSword;
import ossium_team.mmod.common.item.tools.MMReinforcedTool;
import ossium_team.mmod.common.item.tools.MMSwordBase;
import ossium_team.mmod.common.item.tools.MMToolBase;

import javax.annotation.Nonnull;

public class ContainerMolecularCraftingTableEnergy extends Container {

    public ContainerMolecularCraftingTableEnergy(IInventory playerInventory, TileMolecularCraftingTableEnergy tile) {
        IItemHandler handler = tile.getItemHandler();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.addSlotToContainer(new MolecularCraftingTableEnergyFirstSlot(handler, i * 3 + j, 22 + j * 18, 16 + i * 18));
            }
        }

        this.addSlotToContainer(new MolecularCraftingTableEnergyUpgradeSlot(handler, 11, 93, 16));
        this.addSlotToContainer(new MolecularCraftingTableEnergyEnergySlot(handler, 10, 93, 52));
        this.addSlotToContainer(new MolecularCraftingTableEnergyLastSlot(handler, 9, 125, 34));

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

    private class MolecularCraftingTableEnergyFirstSlot extends SlotItemHandler {

        public MolecularCraftingTableEnergyFirstSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(ModItems.itemReinforcedDiamond) ||
                    stack.getItem().equals(ModItems.itemReinforcedStick) ||
                    stack.getItem().equals(ModItems.itemReinforcedIron) ||
                    stack.getItem().equals(ModItems.itemStrengthenedDiamond);
        }
    }

    private class MolecularCraftingTableEnergyLastSlot extends SlotItemHandler {

        public MolecularCraftingTableEnergyLastSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return false;
        }
    }

    private class MolecularCraftingTableEnergyEnergySlot extends SlotItemHandler {

        public MolecularCraftingTableEnergyEnergySlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return (stack.getItem() instanceof MMToolBase || stack.getItem() instanceof MMSwordBase) && !(stack.getItem() instanceof MMReinforcedTool || stack.getItem() instanceof MMReinforcedSword);
        }
    }

    private class MolecularCraftingTableEnergyUpgradeSlot extends SlotItemHandler {

        public MolecularCraftingTableEnergyUpgradeSlot(IItemHandler handler, int index, int xPosition, int yPosition) {
            super(handler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(ModItems.itemUpgradeCore);
        }
    }
}
