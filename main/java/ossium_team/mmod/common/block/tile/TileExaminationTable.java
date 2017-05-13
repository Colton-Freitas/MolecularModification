package ossium_team.mmod.common.block.tile;

import net.minecraftforge.items.ItemStackHandler;

public class TileExaminationTable extends BaseModTile {

    public TileExaminationTable() {
        invSize = 1;
        itemHandler = new ItemStackHandler(1);
    }

    @Override
    public void update() {
        if (!world.isRemote && !itemHandler.getStackInSlot(0).isEmpty())
            world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 8);
    }
}
