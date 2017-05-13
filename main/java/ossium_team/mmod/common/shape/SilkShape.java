package ossium_team.mmod.common.shape;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import ossium_team.mmod.common.item.tools.MMSwordBase;

public class SilkShape implements IMolecularShapeTestable {

    private Block[][] shape;

    public SilkShape() {
        shape = new Block[9][9];
        for (int i = 0; i < 9; i++) {
            shape[0][i] = Blocks.WOOL;
            shape[8][i] = Blocks.WOOL;
        }
        for (int i = 1; i < 8; i++) {
            shape[i][0] = Blocks.WOOL;
            shape[i][8] = Blocks.WOOL;
        }
    }


    @Override
    public int[] getShapeFromBlocks(Block[][][] blocks, int baseHardness, int baseEfficiency, int baseMaxRange, int harvest, int silky, int fortune, int selfRepair) {
        for (int i = 0; i < 9; i++) {
            if(isEqual(shape, blocks[i]))
                return new int[]{0, 0, 0, 0, 1, 0, 0};
        }

        for (int i = 0; i < 9; i++) {
            Block[][] curr = new Block[9][9];
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    curr[k][j] = blocks[k][j][i];
                }
            }
            if (isEqual(shape, curr))
                return new int[]{0, 0, 0, 0, 1, 0, 0};
        }

        return new int[7];
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
