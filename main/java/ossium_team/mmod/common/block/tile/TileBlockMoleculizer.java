package ossium_team.mmod.common.block.tile;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;
import ossium_team.mmod.common.lib.TagLib;
import ossium_team.mmod.common.shape.MolecularizableBlockRegistry;
import ossium_team.mmod.helper.NBTHelper;

public class TileBlockMoleculizer extends BaseModTile {

    private Block[][][] currentBlocks;

    public TileBlockMoleculizer() {
        invSize = 1;
        itemHandler = new ItemStackHandler(1);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            if(world.isBlockPowered(this.pos) && !itemHandler.getStackInSlot(0).isEmpty() && !NBTHelper.hasNBT(itemHandler.getStackInSlot(0))) {
                if(currentBlocks == null)
                    currentBlocks = getBlocksInArea();
                deleteBlocks();
                finalizeCore();
            } else if(world.isBlockPowered(this.pos) && itemHandler.getStackInSlot(0).isEmpty())
                deleteBlocks();
        }
    }

    private void deleteBlocks() {
        BlockPos start = new BlockPos(pos);
        start = start.add(-4, -4, -18);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    BlockPos current = start.add(i, j, k);
                    if(world.getBlockState(current).getBlockHardness(world, current) >= 0)
                        world.setBlockState(current, Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    private Block[][][] getBlocksInArea() {
        BlockPos start = new BlockPos(pos).add(-4, -4, -18);
        Block[][][] currentBlocks = new Block[9][9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    currentBlocks[i][j][k] = world.getBlockState(start.add(i, j, k)).getBlock().getDefaultState().getBlock();
                }
            }
        }
        return currentBlocks;
    }

    private int[] calculateBaseStats() {
        int hardness = 0;
        int efficiency = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if(currentBlocks[i][j][k].equals(Blocks.AIR))
                        continue;
                    hardness += MolecularizableBlockRegistry.getHardness(currentBlocks[i][j][k]);
                    efficiency += MolecularizableBlockRegistry.getEfficiency(currentBlocks[i][j][k]);
                }
            }
        }
        return new int[] {hardness / 2, efficiency / 12, 0, 0, 0, 0, 0};
    }

    private void finalizeCore() {
        int[] baseStats = calculateBaseStats();
        int[] toModStats = MolecularizableBlockRegistry.getShapesEffect(currentBlocks, baseStats[0], baseStats[1], baseStats[2], baseStats[3], 0, 0, 0);

        if (toModStats[4] != 0 && toModStats[5] != 0)
            toModStats[4] = 0;

        ItemStack core = itemHandler.getStackInSlot(0);
        NBTHelper.setInt(core, TagLib.MM_HARDNESS, baseStats[0] + toModStats[0]);
        NBTHelper.setInt(core, TagLib.MM_EFFICIENCY, baseStats[1] + toModStats[1]);
        NBTHelper.setInt(core, TagLib.MM_MAX_RANGE, baseStats[2] + toModStats[2]);
        NBTHelper.setInt(core, TagLib.MM_HARVEST, baseStats[3] + toModStats[3]);
        NBTHelper.setInt(core, TagLib.MM_SILK, baseStats[4] + toModStats[4]);
        NBTHelper.setInt(core, TagLib.MM_FORTUNE, baseStats[5] + toModStats[5]);
        NBTHelper.setInt(core, TagLib.MM_SELF_REPAIR, baseStats[6] + toModStats[6]);
        currentBlocks = null;
    }
}
