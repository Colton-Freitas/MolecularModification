package ossium_team.mmod.common.shape;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import ossium_team.mmod.common.item.tools.MMSwordBase;

public class PistonShape implements IMolecularShapeTestable {

    private Block[][] shape = new Block[9][9];

    public PistonShape() {
        for (int i = 0; i < 9; i++) {
            shape[0][i] = Blocks.PISTON.getDefaultState().getBlock();
        }
        for (int i = 0; i < 9; i++) {
            shape[8][i] = Blocks.PISTON.getDefaultState().getBlock();
        }

        for (int i = 1; i < 8; i++) {
            shape[i][0] = Blocks.PISTON.getDefaultState().getBlock();
        }

        for (int i = 1; i < 8; i++) {
            shape[i][8] = Blocks.PISTON.getDefaultState().getBlock();
        }
    }

    @Override
    public int[] getShapeFromBlocks(Block[][][] blocks, int baseHardness, int baseEfficiency, int baseMaxRange, int harvest, int silky, int fortune, int selfRepair) {
        int count = 0;
        for (int i = 0; i < 9; i++)
            if(isEqual(shape, blocks[i]))
                count++;

        for (int i = 0; i < 9; i++) {
            Block[][] curr = new Block[9][9];
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    curr[k][j] = blocks[k][j][i];
                }
            }
            if (isEqual(shape, curr))
                count++;
        }

        return new int[] {0, 0, count, 0, 0, 0, 0};
    }

    private boolean isEqual (Block[][] thisShape, Block[][] other) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(thisShape[i][j] == null)
                    continue;
                if(!thisShape[i][j].equals(other[i][j]))
                    return false;
            }
        }
        return true;
    }

    public Block[][] getShape() {
        return shape;
    }
}
