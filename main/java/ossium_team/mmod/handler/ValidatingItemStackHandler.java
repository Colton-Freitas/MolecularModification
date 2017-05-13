package ossium_team.mmod.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class ValidatingItemStackHandler extends ItemStackHandler {

    public ValidatingItemStackHandler(int size) {
        super(size);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return super.insertItem(slot, stack, simulate);
    }
}
